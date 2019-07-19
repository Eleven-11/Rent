package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.PostBaseDao;
import com.heeexy.example.dao.PostCommentDao;
import com.heeexy.example.dao.PostImgDao;
import com.heeexy.example.dao.UserResonateDao;
import com.heeexy.example.service.PostForMiniPrgService;
import com.heeexy.example.util.model.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<PostDto> getPostInfo(JSONObject jsonObject) {
        List<PostDto> list = new ArrayList<>();
        List<JSONObject> postBaseList = postBaseDao.getPostBaseList(jsonObject);
        for (int i=0;i<postBaseList.size();i++)
        {
            PostDto postDto = new PostDto();
            System.out.println(postBaseList.get(i).toString());
            List<JSONObject> postCommentList = postCommentDao.getPostCommentList(postBaseList.get(i));
            System.out.println(postCommentList);
            List<JSONObject> postImgList = postImgDao.getPostImgList(postBaseList.get(i));
            List<JSONObject> postLikeList = userResonateDao.getPostLikeList(postBaseList.get(i));


            postDto.setActiveTime((Date) postBaseList.get(i).get("activeTime"));
            postDto.setBrowse((Integer) postBaseList.get(i).get("browse"));
            postDto.setCollections((Integer) postBaseList.get(i).get("collection"));
            postDto.setContent((String) postBaseList.get(i).get("content"));
            postDto.setCreateTime((Date) postBaseList.get(i).get("createTime"));
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



            postDto.setCommentList(postCommentList);
            postDto.setLikeList(postLikeList);
            postDto.setPostImgs(postImgList);

            list.add(postDto);
        }
       return list;
    }
}
