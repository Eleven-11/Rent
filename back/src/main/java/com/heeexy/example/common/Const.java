package com.heeexy.example.common;

import lombok.Data;

/**
 * @author: chens
 * @description: 常量定义
 * @date: 2019-07-29 19:03
 */
public class Const {

    /**
     * websocket推送类型 - 用户消息
     */
    public final static String SEND_MESSAGE = "send_message";
    /**
     * websocket推送类型 - 系统消息
     */
    public final static String SEND_SYS_MESSAGE = "send_sys_message";
    /**
     * websocket推送类型 - 评论通知
     */
    public final static String SEND_COMMENT = "send_comment";
    /**
     * websocket推送类型 - 点赞通知
     */
    public final static String SEND_RESONATE ="send_resonate";

}
