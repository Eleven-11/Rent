package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.PostLabelService;
import com.heeexy.example.util.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lingling
 * @description 帖子标签相关操作
 * @date 2019/7/3 0003
 */
@RestController
@RequestMapping("/postLabel")
public class PostLabelController {
    @Autowired
    private PostLabelService postLabelService;
    /**
     *@description:新增帖子标签，支持批量添加
     *@param request(上级菜单parentContent,子类菜单contents,其中子类菜单为列表形式传入
     * 如编辑地铁1号线站点信息,parentContent为地铁一号线,contents为具体站点名称)
     *@return com.alibaba.fastjson.JSONObject
     */
    @PostMapping("/insertPostLabel")
    public JSONObject insertPostType(HttpServletRequest request) {
        return postLabelService.insertPostLabel(CommonUtil.request2Json(request));
    }
    /**
     *@description:根据id更新帖子标签详情信息
     *@param request(标签id --postLabelId)
     *@return com.alibaba.fastjson.JSONObject
     */
    @PostMapping("/updatePostLabel")
    public JSONObject updatePostType(HttpServletRequest request) {
        return postLabelService.updatePostLabel(CommonUtil.request2Json(request));
    }
    /**
     *@description:根据id删除帖子标签信息
     *@param request(标签id --postLabelId)
     *@return com.alibaba.fastjson.JSONObject
     */
    @PostMapping("/deletePostLabel")
    public JSONObject deletePostType(HttpServletRequest request) {
        return postLabelService.deletePostLabel(CommonUtil.request2Json(request));
    }

}
