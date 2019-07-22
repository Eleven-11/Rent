package com.heeexy.example.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.PostBaseService;
import com.heeexy.example.service.PostForMiniPrgService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.model.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName PostController
 * @Description 帖子信息接口（小程序专用）
 * @Author Lingling00
 * @DATE 7/18/2019 15:52
 * @VERSION 1.0
 **/
@RestController
@RequestMapping("/miniPrgPost")
public class PostController {
    @Autowired
    private PostForMiniPrgService postForMiniPrgService;
    @Autowired
    private PostBaseService postBaseService;

    @GetMapping("/getPostInfo")
    public List<PostDto> getPostList(HttpServletRequest request) {
        return postForMiniPrgService.getPostInfo(CommonUtil.request2Json(request));
    }
    /**
     * @description 用户发布帖子插入帖子信息
     * @param request
     *             postId-帖子Id,
     *             posterId-发帖人id（userid）,
     *             typeId -帖子类型id,
     *             content-帖子内容,
     *             minPrice-最低价,
     *             maxPrice-最高价,
     *             phone-联系方式,
     *             address-地址,
     *             labels-帖子标签集合,
     *             totalBrowse-浏览量,
     *             totalLike-点赞量,
     *             activeTime-活跃时间（默认与发布时间相同）,
     *             createTime-发布时间,
     *             fee-是否有中介费，
     *             postImgList-图片列表
     * @return 帖子id-postId
     **/
    @PostMapping("/insertPostBase")
    public JSONObject insertPostBase(HttpServletRequest request){
        return postBaseService.insertPostBase(CommonUtil.request2Json(request));
    }


}
