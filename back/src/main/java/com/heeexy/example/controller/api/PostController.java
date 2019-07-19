package com.heeexy.example.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.PostBaseService;
import com.heeexy.example.service.PostCommentService;
import com.heeexy.example.service.PostImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName PostController
 * @Description 帖子信息接口（小程序）
 * @Author Lingling00
 * @DATE 7/18/2019 15:52
 * @VERSION 1.0
 **/
@RestController
@RequestMapping("/miniPrgPost")
public class PostController {
    @Autowired
    private PostBaseService postBaseService;

    @Autowired
    private PostCommentService postCommentService;

    @Autowired
    private PostImgService postImgService;

    @GetMapping("/getPostInfo")
    public JSONObject getPostBaseList(HttpServletRequest request) {
        return null;

    }


}
