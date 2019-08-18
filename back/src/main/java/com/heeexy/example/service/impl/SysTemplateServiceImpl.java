package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.SysTemplateDao;
import com.heeexy.example.service.SysTemplateService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SysTemplateServiceImpl
 * @Description
 * @Author Lingling00
 * @DATE 8/2/2019 14:43
 * @VERSION 1.0
 **/
@Service
public class SysTemplateServiceImpl implements SysTemplateService {
    @Autowired
    private SysTemplateDao sysTemplateDao;


    /**
     * @description 获取系统消息模板列表
     * @param jsonObject
     * @return java.util.List<net.sf.json.JSONObject>
     **/
    @Override
    public JSONObject getSysTemplateList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = sysTemplateDao.countSysTemplate(jsonObject);
        List<JSONObject> list = sysTemplateDao.getSysTemplateList(jsonObject);
        System.out.println(list);
        return CommonUtil.successPage(jsonObject, list, count);
    }

    /**
     * @description 获取系统消息模板列表
     * @param jsonObject
     * @return java.util.List<net.sf.json.JSONObject>
     **/
    @Override
    public JSONObject getTemplateList(JSONObject jsonObject) {
        List<JSONObject> list = sysTemplateDao.getTemplateList(jsonObject);
        return CommonUtil.successPage(list);
    }

    /**
     * @description 添加系统消息模板
     * @param jsonObject
     * @return int
     **/
    @Override
    public JSONObject insertSysTemplate(JSONObject jsonObject) {
        sysTemplateDao.insertSysTemplate(jsonObject);
        return CommonUtil.successJson(jsonObject.get("sysTempId"));
    }

    /**
     * @description 修改系统消息模板（包括设置成引导语）
     * @param jsonObject
     * @return void
     **/
    @Override
    public JSONObject updateSysTemplate(JSONObject jsonObject) {
        //是否要设置成引导语
        if (jsonObject.get("isGuide")!=null && "1".equals(jsonObject.get("isGuide"))){
            //获取当前系统消息模板中是否存在引导语，若已存在则不允许修改
            if (sysTemplateDao.getGuideNum(jsonObject)==1){
                //将原来的引导语删除（设置为普通模板）
                sysTemplateDao.updateDelGuide(jsonObject);
                //将当前的模板设置为新的引导语
                sysTemplateDao.updateSysTemplate(jsonObject);
                return CommonUtil.successJson();
            }
            else {
                sysTemplateDao.updateSysTemplate(jsonObject);
                return CommonUtil.successJson();
            }
        } else {
            sysTemplateDao.updateSysTemplate(jsonObject);
            return CommonUtil.successJson();
        }
    }

    /**
     * @description 删除系统消息模板
     * @param jsonObject
     * @return void
     **/
    @Override
    public JSONObject updateDelTemplate(JSONObject jsonObject) {
        sysTemplateDao.updateDelTemplate(jsonObject);
        return CommonUtil.successJson();
    }

}
