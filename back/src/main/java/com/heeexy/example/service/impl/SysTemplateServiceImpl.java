package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.SysTemplateDao;
import com.heeexy.example.service.SysTemplateService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return CommonUtil.successPage(sysTemplateDao.getSysTemplateList(jsonObject));
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
        //FIXME 已有则覆盖之前多引导语
        if (jsonObject.get("isGuide")!=null && "1".equals(jsonObject.get("isGuide"))){
            //获取当前系统消息模板中是否存在引导语，若已存在则不允许修改
            if (sysTemplateDao.getGuideNum(jsonObject)==1){
                return CommonUtil.errorJson(ErrorEnum.E_30001);
            }
            else {
                sysTemplateDao.updateSysTemplate(jsonObject);
                return CommonUtil.successJson();
            }
        }
        else {
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
