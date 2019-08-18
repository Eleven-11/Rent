package com.heeexy.example.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.PostLabelService;
import com.heeexy.example.util.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName WxPostLabelController
 * @Description 小程序帖子标签列表
 * @Author Lingling00
 * @DATE 7/26/2019 16:13
 * @VERSION 1.0
 **/
@RestController
@RequestMapping("/wxPostLabel")
public class WxPostLabelController {
    @Autowired
    private PostLabelService postLabelService;
    /**
     * @description 获取帖子标签列表
     * @param request（父类标签id-labelParentId） -- 1.家用设备，2.热门商圈，3.地铁周边
     * @return com.alibaba.fastjson.JSONObject
     **/
    @GetMapping("/getPostLabelList")
    public JSONObject getPostLabel(HttpServletRequest request){
        return postLabelService.getPostLabelList(CommonUtil.request2Json(request));
    }
}
