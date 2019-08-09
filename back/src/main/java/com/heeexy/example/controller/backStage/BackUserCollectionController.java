package com.heeexy.example.controller.backStage;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.UserCollectionService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Lingling
 * @description 用户收藏列表相关操作
 * @date 2019/6/27 0027
 */
@RestController
@RequestMapping("/userCollection")
public class BackUserCollectionController {
    @Autowired
    private UserCollectionService userCollectionService;


    /**
     *功能描述:根据用户ID查询用户收藏的帖子列表
     *@param request (用户id--wxUserId)
     *@return 用户收藏帖子列表（包括发帖人poster、帖子内容content、帖子图片image、收藏时间collTime）
     */
    @GetMapping("/getUserCollList")
    public JSONObject getUserCollList(HttpServletRequest request) {
        return userCollectionService.getUserCollList(CommonUtil.request2Json(request));
    }

    /**
     *@description:对用户收藏的帖子进行排序
     *@param request:进行排序操作的上面一条帖子id--formerPostId,对应的排序时间formerSortTime
     *              下面一条帖子的id--laterPostId，对应的排序时间laterSortTime
     *@return com.alibaba.fastjson.JSONObject
     */
    @PostMapping("/sortUserColl")
    public JSONObject updateUserColl(HttpServletRequest request) {
        JSONObject jsonObject = CommonUtil.request2Json(request);
        /*获取传入的排序时间*/
        String formerKey = "formerSortTime";
        String laterKey = "laterSortTime";

        /*进行字符串到时间类型的转换*/
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date formerSortTime = sDateFormat.parse(jsonObject.get(formerKey).toString());
            Date laterSortTime = sDateFormat.parse(jsonObject.get(laterKey).toString());
            /*如果两个时间相等，在要排后的时间formerSortTime增加1s并交换*/
            if(formerSortTime ==laterSortTime) {
                long time = 1*1000;
                Date temp = new Date(formerSortTime.getTime()+time);
                jsonObject.put(laterKey,laterSortTime);
                jsonObject.put(formerKey,temp);

            }
            /*否则两个时间直接交换*/
            else {
                Date temp = formerSortTime;
                jsonObject.put(laterKey,laterSortTime);
                jsonObject.put(formerKey,temp);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return userCollectionService.sortUserColl(jsonObject);
    }
}
