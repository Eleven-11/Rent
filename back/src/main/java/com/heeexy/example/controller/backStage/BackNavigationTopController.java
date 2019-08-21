package com.heeexy.example.controller.backStage;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.NavigationTopService;
import com.heeexy.example.util.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
     * @param request 导航栏id列表 -- navigationIds，要置顶的帖子id -- postId
     * @return 置顶后生成的主键 -- topPostId
     **/
    @RequiresPermissions("post:top")
    @PostMapping("/insertNavigationTop")
    public JSONObject insertNavigationTop(HttpServletRequest request){
        return navigationTopService.insertNavigationTop(CommonUtil.request2Json(request));

    }
    /**
     * @description 获取导航栏置顶帖子列表
     * @param
     * @return
     **/
    @RequiresPermissions("top:list")
    @GetMapping("/getNavigationTopList")
    public JSONObject getNavigationTopList(HttpServletRequest request){
        return CommonUtil.successJson(navigationTopService.getNavigationTopList(CommonUtil.request2Json(request)));
    }

    /**
     * @description 获取置顶模块名称列表
     * @param
     * @return
     **/
    @RequiresPermissions("top:list")
    @GetMapping("/getNavigationTitle")
    public JSONObject getNavigationTitle(HttpServletRequest request){
        return CommonUtil.successJson(navigationTopService.getNavigationTitle(CommonUtil.request2Json(request)));
    }
    /**
     * @description 更新导航栏置顶帖子信息
     * @param
     * @return
     **/
    @RequiresPermissions("top:update")
    @PostMapping("/updateNavigationTop")
    public JSONObject updateNavigationTop(HttpServletRequest request){
        return navigationTopService.updateNavigationTop(CommonUtil.request2Json(request));
    }
    /**
     * @description 对置顶帖子进行排序
     * @param  request 首行帖子id - formerNavId，末行帖子id
     *                 首行帖子排序时间-formerSortTime，末行帖子排序时间 - laterSortTime
     * @return
     **/
    @RequiresPermissions("top:sort")
    @PostMapping("/sortNavigationTop")
    public JSONObject sortNavigationTop(HttpServletRequest request){
        return navigationTopService.sortNavigationTop(CommonUtil.request2Json(request));
    }

    /**
     * @description 逻辑删除置顶帖子记录
     * @param  request navigationId-置顶模块id，置顶帖子主键（navigationTop表主键）-topPostId
     * @return
     **/
    @RequiresPermissions("top:del")
    @PostMapping("/updateDelNav")
    public JSONObject updateDelNavigationTop(HttpServletRequest request){
        return navigationTopService.updateDelNavigationTop(CommonUtil.request2Json(request));
    }
}
