package com.heeexy.example.controller;

/**
 * @author Lingling
 * @description 文件上传
 * @date 2019/7/2 0002
 */

import com.heeexy.example.util.FileNameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

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
                "E:" + File.separator+"Image"
                        + File.separator+"rent"
                        + "/" ;
        File localFile  = new File(desFilePath);
        String newFileName = FileNameUtils.getFileName(originalFileName);
        File dir = new File(desFilePath, newFileName);
        File filepath = new File(desFilePath);
        if(!filepath.exists()){
            filepath.mkdirs();
        }
        file.transferTo(dir);
        /*if (!localFile.exists()) {
            localFile.createNewFile();
            file.transferTo(localFile);
        }*/
        String srcUrl ="http://localhost:8080/images/"+newFileName;
        File newFile = new File(srcUrl);
        System.out.println(srcUrl);
        return srcUrl;
    }
}
