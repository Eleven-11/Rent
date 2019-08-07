package com.heeexy.example.controller.backStage;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.BackSysInformationService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author: chens
 * @description: 后台系统消息控制层
 * @date: 2019-07-31 14:12
 */
@RestController
@RequestMapping("/backInfor")
public class BackSysInformationController {

    @Autowired
    private BackSysInformationService backSysInformationService;

    /**
     * 获取后台系统消息列表
     * @param request
     * @return
     */
    @GetMapping("/list")
    public JSONObject getBackSysInforList(HttpServletRequest request) {
        return backSysInformationService.getBackSysInforList(CommonUtil.request2Json(request));
    }

    /**
     * 发送系统消息
     * @param request
     * @return
     */
    @PostMapping("/send")
    public JSONObject sendMessage(HttpServletRequest request) {
        try {
            return backSysInformationService.sendMessage(CommonUtil.request2Json(request));
        } catch (IOException e) {
            e.printStackTrace();
            return CommonUtil.errorJson(ErrorEnum.E_405);
        }
    }

    /**
     * 逻辑删除系统消息
     * @param request
     * @return
     */
    @PostMapping("/del")
    public JSONObject updateDel(HttpServletRequest request) {
        try {
            return backSysInformationService.sendMessage(CommonUtil.request2Json(request));
        } catch (IOException e) {
            e.printStackTrace();
            return CommonUtil.errorJson(ErrorEnum.E_405);
        }
    }


}
