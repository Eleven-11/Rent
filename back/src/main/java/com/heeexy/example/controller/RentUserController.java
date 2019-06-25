/*
package com.heeexy.example.controller;

import com.heeexy.example.service.RentUserService;
import com.heeexy.example.util.FileUtil;
import com.heeexy.example.util.JSONResult;
import com.heeexy.example.util.model.RentUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public class RentUserController {
    @Autowired
    private RentUserService rentUserService;

    @Value("${server.headImage.path}")
    private String fileSpace;

    @ApiOperation(value = "用户上传头像", notes = "用户上传头像的接口")


    @ApiImplicitParams({@ApiImplicitParam(name = "rentUserId", value = "租房用户id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "file", value = "文件上传", required = true, dataType = "String", paramType = "query"),})
    @PostMapping("/uploadHead")
    public JSONResult uploadHeadImage(String rentUserId, @RequestParam("headImage") MultipartFile file) {
        if (StringUtils.isBlank(rentUserId)) {
            return JSONResult.errorMsg("用户名不能为空");
        }

        // 文件保存的命名空间
        String fileName = file.getOriginalFilename();
        // 保存到数据库中的相对路径
        String path="";
        try {
            path = FileUtil.uploadFile(file.getBytes(), fileSpace, fileName);
        } catch (Exception e) {
            e.getStackTrace();
            return JSONResult.errorMsg(e.getMessage());
        }
        RentUser rentUser = new RentUser();
        rentUser.setRentUserId(rentUserId);
        rentUser.setHeadImage(path);
        rentUserService.updateRentUser(rentUser);
        return JSONResult.ok(path);
    }
}
*/
