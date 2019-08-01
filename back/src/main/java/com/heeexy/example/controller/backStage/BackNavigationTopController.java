package com.heeexy.example.controller.backStage;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.NavigationTopService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName BackNavigationTopController
 * @Description 导航栏置顶帖子相关操作
 * @Author Lingling00
 * @DATE 7/29/2019 14:16
 * @VERSION 1.0
 **/
@RestController
@RequestMapping("/navigationTop")
public class BackNavigationTopController {
    @Autowired
    private NavigationTopService navigationTopService;
    /**
     * @description 添加置顶帖子信息
     * @param request 导航栏id -- navigationIds，要置顶的帖子id -- postId
     * @return 置顶后生成的主键 -- topPostId
     **/
    @PostMapping("/insertNavigationTop")
    public JSONObject insertNavigationTop(HttpServletRequest request){
        return navigationTopService.insertNavigationTop(CommonUtil.request2Json(request));

    }
    /**
     * @description 获取导航栏置顶帖子列表
     * @param
     * @return
     **/
    @PostMapping("/getNavigationTopList")
    public JSONObject getNavigationTopList(HttpServletRequest request){
        return CommonUtil.successJson(navigationTopService.getNavigationTopList(CommonUtil.request2Json(request)));
    }
    /**
     * @description 更新导航栏置顶帖子信息
     * @param
     * @return
     **/
    @PostMapping("/updateNavigationTop")
    public JSONObject updateNavigationTop(HttpServletRequest request){
        return navigationTopService.updateNavigationTop(CommonUtil.request2Json(request));
    }
}
