package com.heeexy.example.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.config.exception.WxPageException;
import com.heeexy.example.service.WxUserPostService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: chens
 * @description: 微信用户帖子操作控制器
 * @date: 2019-07-29 11:53
 */
@RestController
@RequestMapping("/wxUserPost")
public class WxUserPostController {

    @Autowired
    private WxUserPostService wxUserPostService;

    /**
     * 分页加载用户发布的帖子列表
     * userId 用户id
     * pageNum 第几页
     * pageSize 页大小
     * @param request
     * @return
     */
    @GetMapping("/list")
    public JSONObject getWxUserPostList(HttpServletRequest request){
        try{
            return wxUserPostService.getWxUserPostList(CommonUtil.request2Json(request));
        } catch (WxPageException e) {
            return CommonUtil.errorJson(ErrorEnum.WX_900);
        }catch (Exception e){
            return CommonUtil.errorJson(ErrorEnum.E_405);
        }
    }
}
