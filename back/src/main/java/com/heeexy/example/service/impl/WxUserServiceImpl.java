package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.common.Const;
import com.heeexy.example.config.websocket.WebSocketServer;
import com.heeexy.example.dao.WxUserDao;
import com.heeexy.example.service.WxUserService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * @author: Lingling
 * @description: 小程序用户业务逻辑实现
 * @date: 2019-07-23 17:48
 */
@Service
public class  WxUserServiceImpl implements WxUserService {

    @Autowired
    private WxUserDao wxUserDao;

    /**
     * 功能描述:获取用户列表
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getWxUserList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = wxUserDao.countWxUser(jsonObject);
        List<JSONObject> list = wxUserDao.getWxUserList(jsonObject);
        for (int i=0;i<list.size();i++){
            list.get(i).put("region",list.get(i).get("wxCountry")==null?"":list.get(i).get("wxCountry")+
                    " "+list.get(i).get("wxProvince")==null?"":list.get(i).get("wxProvince")+
                    " "+list.get(i).get("wxCity")==null?"":list.get(i).get("wxCity"));
        }
        return CommonUtil.successPage(jsonObject, list, count);
    }

    /**
     * @description 新增微信授权用户信息
     * @param jsonObject
     * @return java.lang.String
     **/
    @Override
    public String insertWxUser(JSONObject jsonObject) {
        String userId = UUIDUtils.getUUID();
        jsonObject.put("userId",userId);
        wxUserDao.insertWxUser(jsonObject);
        /* 发送系统消息
         * targetId       接收人ID
         * content        内容
         * createTime     创建时间
         */
        try{
            JSONObject sys = new JSONObject();
            sys.put("targetId",userId);
            sys.put("content","微信用户第一次授权");
            sys.put("createTime",new Date());
            WebSocketServer.sendMessageAll(sys, Const.SEND_SYS_MESSAGE);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userId;
    }

    /**
     * @description 获取微信用户信息
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    @Override
    public JSONObject getWxUserInfo(JSONObject jsonObject) {
        return wxUserDao.getWxUserInfo(jsonObject);
    }

    /**
     * @description 判断用户授权状态，若已授权则返回相应信息
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    @Override
    public JSONObject getAuthStatus(JSONObject jsonObject) {
        return wxUserDao.getAuthStatus(jsonObject);
    }

    @Override
    public JSONObject updateWxUserSummary(JSONObject jsonObject) {
        wxUserDao.updateWxUserSummary(jsonObject);
        return CommonUtil.successJson(jsonObject.get("summary"));
    }

    /**
     * @description 修改用户粉丝数量
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    @Override
    public JSONObject updateWxUserInfo(JSONObject jsonObject) {
        wxUserDao.updateWxUserInfo(jsonObject);
        return CommonUtil.successJson("修改成功，刷新后查看");
    }

}
