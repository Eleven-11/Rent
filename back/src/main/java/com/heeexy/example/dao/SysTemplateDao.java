package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface SysTemplateDao {
    /**
     * @description 获取系统消息模板列表
     * @param jsonObject
     * @return java.util.List<net.sf.json.JSONObject>
     **/
    List<JSONObject> getSysTemplateList(JSONObject jsonObject);

    /**
     * 获取数据总条数
     * @param jsonObject
     * @return
     */
    Integer countSysTemplate(JSONObject jsonObject);

    /**
     * @description 添加系统消息模板
     * @param jsonObject
     * @return int
     **/
    int insertSysTemplate(JSONObject jsonObject);

    /**
     * @description 修改系统消息模板（包括设置成引导语）
     * @param jsonObject
     * @return void
     **/
    void updateSysTemplate(JSONObject jsonObject);

    /**
     * @description 删除系统消息模板
     * @param jsonObject
     * @return void
     **/
    void updateDelTemplate(JSONObject jsonObject);

    /**
     * @description 获取当前作为引导语的模板数量
     * @param jsonObject
     * @return int
     **/
    int getGuideNum(JSONObject jsonObject);


    /**
     * @description 更改系统引导语为普通模板
     * @param jsonObject
     * @return void
     **/
    void updateDelGuide(JSONObject jsonObject);


}
