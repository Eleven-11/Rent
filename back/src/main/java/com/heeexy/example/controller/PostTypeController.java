package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.PostTypeService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lingling
 * @description 对小程序显示的八大类型（求租、合租、转租等），进行图标和类别名称的编辑和增删操作
 * @date 2019/7/2 0002
 */
@RestController
@RequestMapping("/postType")
public class PostTypeController {
    @Autowired
    private PostTypeService postTypeService;

    /**
     *@description:获取帖子类型列表
     *@param request
     *@return com.alibaba.fastjson.JSONObject
     */
    @GetMapping("/getPostTypelist")
    public JSONObject getWxUserList(HttpServletRequest request) {
        return postTypeService.getPostTypeList(CommonUtil.request2Json(request));

    }

    /**
     *@description:添加帖子类型信息
     *@param request(帖子类型名称postTypeName,帖子类型图标postTypeImg,帖子类型创建时间postTypeCreateTime)
     *@return com.alibaba.fastjson.JSONObject
     */
    @PostMapping("/insertPostType")
    public JSONObject insertPostType(HttpServletRequest request) {
        return postTypeService.insertPostType(CommonUtil.request2Json(request));
    }

    /**
     *@description:根据帖子类型id更新帖子类型相关信息
     *@param request(帖子类型id--postTypeId,帖子类型名称postTypeName,帖子类型图标postTypeImg,帖子类型创建时间postTypeCreateTime)
     *@return com.alibaba.fastjson.JSONObject
     */
    @PostMapping("/updatePostType")
    public JSONObject updatePostTypeById(HttpServletRequest request) {
        return postTypeService.updatePostTypeById(CommonUtil.request2Json(request));
    }

    /**
     *@description:根据帖子类型id删除对应帖子类型信息
     *@param request(帖子类型id--postTypeId)
     *@return com.alibaba.fastjson.JSONObject
     */
    @PostMapping("/deletePostType")
    public JSONObject updateDelPostTypeById(HttpServletRequest request) {
        return postTypeService.deletePostTypeById(CommonUtil.request2Json(request));
    }

}
