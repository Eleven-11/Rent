package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.*;
import com.heeexy.example.service.PostForMiniPrgService;
import com.heeexy.example.util.model.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName PostForMiniPrgServiceImpl
 * @Description 小程序帖子信息相关操作
 * @Author Lingling00
 * @DATE 7/18/2019 16:03
 * @VERSION 1.0
 **/
@Service
public class PostForMiniPrgServiceImpl implements PostForMiniPrgService {

    @Autowired
    private PostBaseDao postBaseDao;

    @Autowired
    private PostImgDao postImgDao;

    @Autowired
    private UserResonateDao userResonateDao;

    @Autowired
    private PostCommentDao postCommentDao;

    @Autowired
    private UserCollectionDao userCollectionDao;

    /**
     * 获取帖子信息
     * @param jsonObject
     * @return
     */
    @Override
    public List<PostDto> getPostInfo(JSONObject jsonObject) {
        //小程序请求标识，用于判断是否显示被逻辑删除的帖子(后台管理需显示)
        jsonObject.put("isMiniPro",'1');
        List<PostDto> list = new ArrayList<>();
        List<JSONObject> postBaseList = postBaseDao.getPostBaseList(jsonObject);
        for (int i=0;i<postBaseList.size();i++)
        {
            PostDto postDto = new PostDto();
            System.out.println(postBaseList.get(i).toString());
            //获取帖子评论列表
            List<JSONObject> postCommentList = postCommentDao.getPostCommentList(postBaseList.get(i));
            System.out.println(postCommentList);
            //获取帖子图片集合
            List<JSONObject> postImgList = postImgDao.getPostImgList(postBaseList.get(i));
            //获取帖子点赞列表
            List<JSONObject> postLikeList = userResonateDao.getPostLikeList(postBaseList.get(i));

            postDto.setActiveTime((Date) postBaseList.get(i).get("activeTime"));
            postDto.setBrowse((Integer) postBaseList.get(i).get("browse"));
            postDto.setCollections((Integer) postBaseList.get(i).get("collection"));
            postDto.setContent((String) postBaseList.get(i).get("content"));
            postDto.setCreateTime((Date) postBaseList.get(i).get("createTime"));
            postDto.setComments((Integer) postBaseList.get(i).get("comments"));
            postDto.setTypeName((String) postBaseList.get(i).get("typeName"));
            int browse = (int)postBaseList.get(i).get("browse")+ (int)postBaseList.get(i).get("devBrowse");
            int like = (int)postBaseList.get(i).get("likes")+ (int)postBaseList.get(i).get("devLike");
            postDto.setLikes(like);
            postDto.setBrowse(browse);
            postDto.setMinPrice((String) postBaseList.get(i).get("minPrice"));
            postDto.setMaxPrice((String) postBaseList.get(i).get("maxPrice"));
            postDto.setFee((Integer) postBaseList.get(i).get("fee"));
            postDto.setPhone((String) postBaseList.get(i).get("phone"));
            postDto.setPostId((Integer) postBaseList.get(i).get("postId"));
            postDto.setPoster((String) postBaseList.get(i).get("poster"));
            postDto.setPostLabels((String) postBaseList.get(i).get("postLabels"));
            postDto.setPostGender((String) postBaseList.get(i).get("posterGender"));
            postDto.setPosterAvatar((String) postBaseList.get(i).get("posterAvatar"));

            //评论列表
            postDto.setCommentList(postCommentList);
            //点赞列表
            postDto.setLikeList(postLikeList);
            //图片集合
            postDto.setPostImgs(postImgList);


            if (!StringUtils.isEmpty(jsonObject.get("userId"))){
                String userId = jsonObject.get("userId").toString();
                JSONObject jo = postBaseList.get(i);
                jo.put("userId",userId);
                //是否时自己发送的帖子
                if (userId.equals(postDto.getPoster())){
                    postDto.setMyPost(1);
                }
                //是否是自己收藏的帖子
                if (userCollectionDao.getIfCollect(jo) != null){
                    postDto.setMyCollection(1);
                }
                //是否点赞过
                if (userResonateDao.getByUserIdPostId(jo) != null){
                    postDto.setMyLike(1);
                }
            }
            list.add(postDto);
        }
       return list;
    }
}
