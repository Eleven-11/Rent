package com.heeexy.example.controller.api;

import com.heeexy.example.service.PostForMiniPrgService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.model.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    private PostForMiniPrgService postForMiniPrgService;

    @GetMapping("/getPostInfo")
    public List<PostDto> getPostList(HttpServletRequest request) {
        return postForMiniPrgService.getPostInfo(CommonUtil.request2Json(request));

    }


}
