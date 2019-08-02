package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

public interface SysTemplateService {
    /**
     * @description 获取系统消息模板列表
     * @param jsonObject
     * @return java.util.List<net.sf.json.JSONObject>
     **/
    JSONObject getSysTemplateList(JSONObject jsonObject);

    /**
     * @description 添加系统消息模板
     * @param jsonObject
     * @return int
     **/
    JSONObject insertSysTemplate(JSONObject jsonObject);

    /**
     * @description 修改系统消息模板（包括设置成引导语）
     * @param jsonObject
     * @return void
     **/
    JSONObject updateSysTemplate(JSONObject jsonObject);

    /**
     * @description 删除系统消息模板
     * @param jsonObject
     * @return void
     **/
    JSONObject updateDelTemplate(JSONObject jsonObject);

}
