package com.heeexy.example.controller;

/**
 * @author Lingling
 * @description 文件上传
 * @date 2019/7/2 0002
 */

import com.heeexy.example.util.FileUtil;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 文件上传
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {

    @RequestMapping(value = "/upload")
    public String imgUpload(HttpServletRequest req, MultipartHttpServletRequest multiReq) throws IOException {
        MultipartFile file = multiReq.getFile("file");
        String originalFileName = file.getOriginalFilename();
        String desFilePath =
                "D:" + File.separator+"Ionia"
                        + File.separator+"SpringBoot-Shiro-Vue"
                        + File.separator+"vue"
                        + File.separator+"src"
                        + File.separator+"assets"
                        + File.separator+"static"
                        + "/" + originalFileName;
        File localFile  = new File(desFilePath);
        if (!localFile.exists()) {
            localFile.createNewFile();
            file.transferTo(localFile);
        }
        String newFileName = FileUtil.renameToUUID(originalFileName);
        String srcUrl ="http://localhost:8080/static/"+newFileName;
        File newFile = new File(srcUrl);
        if (!newFile.exists()) {
            newFile.createNewFile();
            file.transferTo(newFile);
        }
        return srcUrl;
    }
}
