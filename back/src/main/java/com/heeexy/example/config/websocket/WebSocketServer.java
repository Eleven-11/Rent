package com.heeexy.example.config.websocket;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author: chens
 * @description: WebSocketServer服务器
 * @since https://blog.csdn.net/moshowgame/article/details/80275084
 * @date: 2019-07-26 15:46
 */
@ServerEndpoint("/WebSocketServer/{username}")
@Component
public class WebSocketServer {

    private static int onlineCount = 0;
    private static Map<String, WebSocketServer> clients = new ConcurrentHashMap<String, WebSocketServer>();
    private Session session;
    private String username;

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
     * @param message
     * @throws IOException
     */
    @OnMessage
    public void onMessage(String message) throws IOException {
        JSONObject jsonTo = JSONObject.parseObject(message);
        System.out.println(jsonTo.getString("to") +":"+ jsonTo.getString("msg"));

        if (!jsonTo.getString("to").toLowerCase().equals("all")){
            sendMessageTo(jsonTo.getString("msg"), jsonTo.getString("to"));
        }else{
            sendMessageAll(jsonTo.getString("msg"));
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 消息单发
     * @param message
     * @param To
     * @throws IOException
     */
    public void sendMessageTo(String message, String To) throws IOException {
        // session.getBasicRemote().sendText(message);  
        //session.getAsyncRemote().sendText(message);  
        for (WebSocketServer item : clients.values()) {
            if (item.username.equals(To) ){
                item.session.getAsyncRemote().sendText(message);
            }
        }
    }

    /**
     * 消息群发
     * @param message
     * @throws IOException
     */
    public void sendMessageAll(String message) throws IOException {
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
