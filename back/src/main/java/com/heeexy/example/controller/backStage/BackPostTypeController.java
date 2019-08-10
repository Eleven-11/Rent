package com.heeexy.example.controller.backStage;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.PostTypeService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lingling
 * @description 对小程序显示的八大类型（求租、合租、转租等），进行图标和类别名称的编辑和增删操作
 * @date 2019/7/2 0002
 */
@RestController
@RequestMapping("/postType")
public class BackPostTypeController {
    @Autowired
    private PostTypeService postTypeService;

    /**
     *@description:获取帖子类型列表
     *@param request
     *@return com.alibaba.fastjson.JSONObject
     */
    @GetMapping("/getPostTypelist")
    public JSONObject getPostTypeList(HttpServletRequest request) {
        return postTypeService.getPostTypeList(CommonUtil.request2Json(request));

    }

    /**
     * @description 后台管理：对广告栏图片进行编辑
     * @param request  首行类型id - formerTypeId
     *                 末行图片id - laterTypeId
     *                 首行图片排序时间 - formerSortTime
     *                 末行图片排序时间 - laterSortTime
     * @return com.alibaba.fastjson.JSONObject
     **/
    @PostMapping("/sortPostType")
    public JSONObject sortPostType(HttpServletRequest request){
        return postTypeService.sortPostType(CommonUtil.request2Json(request));
    }

    /**
     *@description:添加帖子类型信息
     *@param request(帖子类型名称 -- postTypeName,帖子类型图标链接 -- postTypeImg
     *@return com.alibaba.fastjson.JSONObject
     */
    @PostMapping("/insertPostType")
    public JSONObject insertPostType(HttpServletRequest request) {
        return postTypeService.insertPostType(CommonUtil.request2Json(request));
    }

    /**
     *@description:根据帖子类型id更新帖子类型相关信息
     *@param request(帖子类型id--postTypeId,帖子类型名称postTypeName,帖子类型图标postTypeImg
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
    @PostMapping("/updateDelPostType")
    public JSONObject updateDelPostTypeById(HttpServletRequest request) {
        return postTypeService.updateDelPostTypeById(CommonUtil.request2Json(request));
    }

}
