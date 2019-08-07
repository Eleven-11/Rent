package com.heeexy.example.config.websocket;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.common.Const;
import com.heeexy.example.config.SpringUtil;
import com.heeexy.example.dao.WxUserDao;
import com.heeexy.example.dao.WxUserInformationDao;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Calendar;
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
    private static boolean openHeart = false;
    private static final Map<String, WebSocketServer> clients = new ConcurrentHashMap<String, WebSocketServer>();

    private Session session;
    private String username;
    private long timeStr;
    private boolean isHeart = false;

    /**
     * 手动显示加载bean
     */
    @Autowired
    private WxUserInformationDao wxUserInformationDao = (WxUserInformationDao) SpringUtil.getBean("wxUserInformationDao");
    @Autowired
    private WxUserDao wxUserDao = (WxUserDao) SpringUtil.getBean("wxUserDao");

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
            this.isHeart = true;

        }else {
            onClose(username);
        }
        clients.put(username, this);
        //启动心跳
        if (!openHeart){
            openHeart = true;
            startHeart();
        }
        System.out.println("已连接:" + username + this.session);
    }

    /**
     * 关闭链接时触发
     * @throws IOException
     */
    @OnClose
    public void onClose() throws IOException {
        System.out.println("移除:" + username);
        clients.remove(username);
        subOnlineCount();
    }

    /**
     * 手动关闭关闭链接
     * @throws IOException
     */
    public void onClose(String username) throws IOException {
        WebSocketServer server = clients.get(username);
        if (server.session.isOpen()) {
            System.out.println("手动关闭链接:" + username + server.session);
            server.session.close();
        }else {
            System.out.println("手动关闭链接（isOpen = false）:" + username + server.session);
        }
    }

    /**
     * 发送消息
     * startId     发送人ID
     * receiveId   接收人ID
     * content     发送内容
     * @param message
     */
    @OnMessage
    public void onMessage(String message) {
        try{
            //判断数据是否是心跳数据
            JSONObject jsonObject = JSONObject.parseObject(message);
            if (jsonObject != null) {
                JSONObject info = (JSONObject) jsonObject.get("info");
                //数据不为空
                if (info.getString("sendType") != null ) {
                    //是心跳数据
                    if (info.getString("sendType").equals(Const.SEND_HEART_CHECK)) {
                        JSONObject entry = (JSONObject) info.get("entry");
                        if (queryOnLine(entry.getString("username"))) {
                            WebSocketServer toServer = clients.get(entry.getString("username"));
                            toServer.isHeart = true;
                        }
                        System.out.println(this.username + "：返回的心跳消息");
                        //是普通消息数据
                    } else if (info.getString("sendType").equals(Const.SEND_MESSAGE)) {
                        //获取消息对应实体
                        jsonObject = (JSONObject) info.get("entry");
                        System.out.println(jsonObject.getString("startId") + " to " + jsonObject.getString("receiveId") + ":" + jsonObject.getString("content"));
                        //判断是否有接受者
                        JSONObject u = new JSONObject();
                        u.put("userId", jsonObject.get("receiveId"));
                        if (jsonObject.getString("receiveId") != null
                                && null != wxUserDao.getWxUserInfo(u)) {
                            //获取发送者信息
                            u.put("userId", jsonObject.get("startId"));
                            JSONObject user = wxUserDao.getWxUserInfo(u);
                            //判断发送者是否存在
                            if (user != null) {
                                //保存消息
                                jsonObject.put("createTime", new Date());
                                wxUserInformationDao.insertInformation(jsonObject);
                                //封装返回的消息数据
                                jsonObject.put("startAvatar", user.getString("wxAvatar"));
                                jsonObject.put("startNickName", user.getString("wxNickname"));
                                sendMessage(jsonObject, jsonObject.getString("receiveId"), Const.SEND_MESSAGE);
                            }
                        }
                    }
                }
             }
        }catch (Exception e){
            e.printStackTrace();
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
        if (StringUtils.isEmpty(key)){
            return false;
        }
        WebSocketServer toServer = clients.get(key);
        //判断是否在线
        if (toServer != null && toServer.session != null){
            return true;
        }
        return false;
    }

    /**
     * 设置心跳接收时间戳
     * @return
     */
    private static long getTimeInMillis(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.SECOND,c.get(Calendar.SECOND)+8);
        return c.getTimeInMillis();
    }

    /**
     * 发送心跳包
     * @param message
     * @throws IOException
     */
    public synchronized void sendPing(String message) throws IOException{
        Map<String, WebSocketServer>  clients = getClients();
        if (clients.size() <=0) {
            return;
        }
        for (String key : clients.keySet()) {
                WebSocketServer item = clients.get(key);
                synchronized (clients) {
                    item.timeStr = getTimeInMillis();
                    item.isHeart = false;
                    //判断是否有链接
                    item.session.getAsyncRemote().sendText(message);
                    System.out.println(item.username + item.session + " to heart");

                }
        }
    }

    /**
     * 启动心跳包
     */
    private synchronized void startHeart(){
        ExamineHeartThread examineHeart =new ExamineHeartThread();
        Thread examineThread=new Thread(examineHeart);

        KeepHeartThread keepHeart=new KeepHeartThread();
        Thread keepThread=new Thread(keepHeart);

        keepThread.start();
        examineThread.start();

    }

    /**
     * server发送心跳包 10秒一次
     */
    private class KeepHeartThread implements Runnable {
        @Override
        public void run() {
            JSONObject heartJson=new JSONObject();
            heartJson.put("type", Const.SEND_HEART_CHECK);
            heartJson.put("content", "heart_check");
            while (true) {
                try {
//                    logger.debug("发送心跳包当前人数为:"+getUserOnlineNum());
                    sendPing(CommonUtil.sendParam(Const.SEND_HEART_CHECK, heartJson).toJSONString());
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 检测是否收到client心跳 每秒一次
     */
    private class ExamineHeartThread implements Runnable{
        @Override
        public void run() {
            while (true) {
                try {
                    long timeMillins=System.currentTimeMillis();
                    for (WebSocketServer item : clients.values()) {
                        if (!item.isHeart && item.timeStr != 0 && timeMillins > item.timeStr){
                            System.out.println(item.username + item.session + " is die");
                            //关闭链接
                            item.onClose(item.username);
                        }
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
