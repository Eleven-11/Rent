package com.heeexy.example.config.websocket;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.common.Const;
import com.heeexy.example.config.SpringUtil;
import com.heeexy.example.dao.PostBaseDao;
import com.heeexy.example.dao.WxUserDao;
import com.heeexy.example.dao.WxUserInformationDao;
import com.heeexy.example.util.CommonUtil;
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

    @Autowired
    private WxUserDao wxUserDao = (WxUserDao) SpringUtil.getBean("wxUserDao");;

    /**
     * 手动显示加载bean
     */
    @Autowired
    private PostBaseDao postBaseDao = (PostBaseDao) SpringUtil.getBean("postBaseDao");;

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
        System.out.println("已连接:" + username);
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
        if (jsonObject != null){
            System.out.println(jsonObject.getString("startId") +" to "+jsonObject.getString("receiveId") +":"+ jsonObject.getString("content"));
            //判断是否有接受者
            JSONObject u = new JSONObject();
            u.put("userId",jsonObject.get("receiveId"));
            if (jsonObject.getString("receiveId") != null
                && null != wxUserDao.getWxUserInfo(u)){
                //判断是个人消息还是系统消息
                if (!jsonObject.getString("receiveId").toLowerCase().equals("sys_infor_key")){
                    jsonObject.put("createTime",new Date());
                    wxUserInformationDao.insertInformation(jsonObject);
                    sendMessage(jsonObject, jsonObject.getString("receiveId"), Const.SEND_MESSAGE);
                }
            }
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
     * 发送系统推送
     * 个人
     * 点赞、消息、评论、系统消息
     * @param jsonObject
     * @param to
     * @param type
     * @throws IOException
     */
    public static void sendMessage(JSONObject jsonObject,String to,String type) throws IOException {
        //判断是否在线
        if (queryOnLine(to)){
            WebSocketServer toServer = clients.get(to);
            toServer.session.getAsyncRemote().sendText(
                     CommonUtil.sendParam(type, jsonObject).toJSONString());
        }
    }

    /**
     * 消息群发
     * 系统消息
     * @param jsonObject
     * @throws IOException
     */
    public static void sendMessageAll(JSONObject jsonObject, String type) throws IOException {
        for (WebSocketServer item : clients.values()) {
            item.session.getAsyncRemote().sendText(
                    CommonUtil.sendParam(type, jsonObject).toJSONString());
        }
    }

    /**
     * 判断用户是否在线
     * @param key
     * @return
     */
    public static boolean queryOnLine(String key){
        WebSocketServer toServer = clients.get(key);
        //判断是否在线
        if (toServer != null && toServer.session != null){
            return true;
        }
        return false;
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
