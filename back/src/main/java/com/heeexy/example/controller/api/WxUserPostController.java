package com.heeexy.example.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.config.exception.WxPageException;
import com.heeexy.example.service.WxUserPostService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
     * lookId 查看人id
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
    /**
     * 修改帖子上下架
     * @param request 进行操作的帖子id-postId，用户id-userId，上下架状态-isLowerShelf（0上架，1下架）
     * @return
     */
    @PostMapping("/updateOnShelf")
    public JSONObject updateOnShelf(HttpServletRequest request){
        return wxUserPostService.updateOnShelf(CommonUtil.request2Json(request));
    }
}
