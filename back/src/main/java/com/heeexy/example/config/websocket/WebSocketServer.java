package com.heeexy.example.config.websocket;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.config.SpringUtil;
import com.heeexy.example.dao.WxUserInformationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author: chens
 * @description: WebSocketServer服务器
 * @since https://blog.csdn.net/moshowgame/article/details/80275084
 * @date: 2019-07-26 15:46
 */
@ServerEndpoint(value = "/websocket/{username}")
@Component
public class WebSocketServer {

    private static int onlineCount = 0;
    private static Map<String, WebSocketServer> clients = new ConcurrentHashMap<String, WebSocketServer>();
    private Session session;
    private String username;

    /**
     * 手动显示加载bean
     */
    @Autowired
    private WxUserInformationDao wxUserInformationDao = (WxUserInformationDao) SpringUtil.getBean("wxUserInformationDao");;

    /**
     * 建立链接时触发
     * @param username
     * @param session
     * @throws IOException
     */
    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) throws IOException {

        this.username = username;
        this.session = session;
        if (clients.get(username) == null){
            addOnlineCount();
        }
        clients.put(username, this);
        System.out.println("已连接");
    }

    /**
     * 关闭链接时触发
     * @throws IOException
     */
    @OnClose
    public void onClose() throws IOException {
        clients.remove(username);
        subOnlineCount();
    }

    /**
     * 发送消息
     * startId     发送人ID
     * receiveId   接收人ID
     * content     发送内容
     * @param message
     * @throws IOException
     */
    @OnMessage
    public void onMessage(String message) throws IOException {
        JSONObject jsonObject = JSONObject.parseObject(message);
        System.out.println(jsonObject.getString("startId") +" to "+jsonObject.getString("receiveId") +":"+ jsonObject.getString("content"));

        if (jsonObject.getString("receiveId") != null){
            //判断是个人消息还是系统消息
            if (!jsonObject.getString("receiveId").toLowerCase().equals("sys_infor_key")){
                jsonObject.put("createTime",new Date());
                wxUserInformationDao.insertInformation(jsonObject);
                sendMessageTo(jsonObject, jsonObject.getString("receiveId"));
            }
//            else {
//                sendMessageAll(jsonObject.getString("msg"));
//            }
        }

    }

    /**
     * 链接出错
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 消息单发
     * @param jsonObject
     * @param to
     * @throws IOException
     */
    public void sendMessageTo(JSONObject jsonObject, String to) throws IOException {
        // session.getBasicRemote().sendText(message);  
        //session.getAsyncRemote().sendText(message);
        //获取接收人的socker对象
        WebSocketServer toServer = clients.get(to);
        //判断是否在线
        if (toServer != null && toServer.session != null){
            toServer.session.getAsyncRemote().sendText(jsonObject.toJSONString());
        }
//        for (WebSocketServer item : clients.values()) {
//            if (item.username.equals(to) ){
//                item.session.getAsyncRemote().sendText(message);
//            }
//        }
    }

    /**
     * 消息群发
     * @param message
     * @throws IOException
     */
    public static void sendMessageAll(String message) throws IOException {
        for (WebSocketServer item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    public static synchronized Map<String, WebSocketServer> getClients() {
        return clients;
    }
}
