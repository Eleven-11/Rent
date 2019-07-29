package com.heeexy.example.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.AdverBannerService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName WxAdvBannerController
 * @Description 小程序：广告栏图片相关操作
 * @Author Lingling00
 * @DATE 7/26/2019 15:40
 * @VERSION 1.0
 **/
@RestController
@RequestMapping("/wxAdvBanner")
public class WxAdvBannerController {
    @Autowired
    private AdverBannerService adverBannerService;
    /**
     * @description 获取广告栏图片列表
     * @param request
     * @return 广告栏图片列表
     *          广告栏图片唯一识别id-advId
     *          广告栏图片标题-advTitle
     *          广告栏图片链接-advImg
     **/
    @GetMapping("/getAdvImgList")
    public JSONObject getAdvImgList(HttpServletRequest request){
        return adverBannerService.getAdvImgList(CommonUtil.request2Json(request));
    }
}
