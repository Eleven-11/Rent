package com.heeexy.example.controller.backStage;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.AdverBannerService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName AdvertBannerController
 * @Description 后台管理：广告栏图片相关操作
 * @Author Lingling00
 * @DATE 7/25/2019 14:22
 * @VERSION 1.0
 **/
@RestController
@RequestMapping("/advBanner")
public class BackAdvertBannerController {
    @Autowired
    private AdverBannerService adverBannerService;
    /**
     * @description 后台管理：新增广告栏图片
     * @param request
     *          广告图片标题-advTitle
     *          广告图片链接-advImg
     * @return com.alibaba.fastjson.JSONObject
     **/
    @PostMapping("/insertAdvImg")
    public JSONObject insertAdvImg(HttpServletRequest request){
        return adverBannerService.insertAdvImg(CommonUtil.request2Json(request));
    }
    /**
     * @description 后台管理：逻辑删除广告图片
     * @param request （图片id-advId）
     * @return com.alibaba.fastjson.JSONObject
     **/
    @GetMapping("/updateDelAdvImg")
    public JSONObject updateDelAdvImg(HttpServletRequest request){
        return adverBannerService.updateDelAdvImg(CommonUtil.request2Json(request));
    }
    /**
     * @description 后台管理：修改广告栏图片
     * @param request  图片id-advId
     *                 图片路径 - advImg
     *                 标题 - advTitle
     * @return com.alibaba.fastjson.JSONObject
     **/
    @PostMapping("/updateAdvImg")
    public JSONObject updateAdvImg(HttpServletRequest request){
        return adverBannerService.updateAdvImg(CommonUtil.request2Json(request));
    }
    
}
