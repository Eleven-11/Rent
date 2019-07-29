package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.common.util.ReportUtil;
import com.heeexy.example.dao.PostBaseDao;
import com.heeexy.example.dao.WxReportDao;
import com.heeexy.example.dao.WxUserPostDao;
import com.heeexy.example.service.WxReportService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author: chens
 * @description: 微信用户举报业务逻辑
 * @date: 2019-07-29 18:06
 */
@Service
public class WxReportServiceImpl implements WxReportService {

    @Autowired
    private WxReportDao wxReportDao;

    @Autowired
    private PostBaseDao postBaseDao;

    /**
     * 用户举报业务逻辑
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject userReport(JSONObject jsonObject) {
        //判断举报类型
        if (!ReportUtil.queryreportType(jsonObject.get("targetType").toString())) {
            return CommonUtil.errorJson(ErrorEnum.WX_950);
        }
        //确认已举报过
        if (wxReportDao.queryReportByUserId(jsonObject) != null){
            return CommonUtil.errorJson(ErrorEnum.WX_951);
        }
        //统计{today}举报次数
        Integer count = wxReportDao.countUserReportToday(jsonObject);
        //{today}举报次数不超过3次
        if (count != null && count >= 3){
            return CommonUtil.errorJson(ErrorEnum.WX_952);
        }
        //判断帖子是否存在
        String postId = StringUtils.isEmpty(jsonObject.get("targetId")) ? null : jsonObject.get("targetId").toString();
        if (postId != null){
            jsonObject.put("postId",postId);
            if (postBaseDao.getWxPostInfoByPostId(jsonObject) == null){
                return CommonUtil.errorJson(ErrorEnum.WX_953);
            }
        }else {
            return CommonUtil.errorJson(ErrorEnum.WX_953);
        }
        //插入举报记录
        wxReportDao.insertReport(jsonObject);
        return CommonUtil.successJson("举报成功");
    }
}
