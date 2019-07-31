package com.heeexy.example.controller;

import com.heeexy.example.util.FileNameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;

/**
 * @author Lingling
 * @description 文件上传
 * @date 2019/7/2 0002
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {

    /**
     * 文件上传
     * @param
     * @param multiReq
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public String imgUpload(String type, MultipartHttpServletRequest multiReq) throws IOException {
        MultipartFile file = multiReq.getFile("file");
        String originalFileName = file.getOriginalFilename();
        if("chat".equals(type)) {
            String desFilePath =
                    "E:" + File.separator + "image"
                            + File.separator + "rent"
                            + File.separator + "chat"
                            + "/";
            String newFileName = FileNameUtils.getFileName(originalFileName);
            File dir = new File(desFilePath, newFileName);
            File filepath = new File(desFilePath);
            if (!filepath.exists()) {
                filepath.mkdirs();
            }
            file.transferTo(dir);
            String srcUrl = "http://192.168.1.7:8080/image/chat" + newFileName;
            System.out.println(srcUrl);
            return srcUrl;
        }
        else{
            String desFilePath =
                    "E:" + File.separator + "image"
                            + File.separator + "rent"
                            + File.separator + "static"
                            + "/";
            String newFileName = FileNameUtils.getFileName(originalFileName);
            File dir = new File(desFilePath, newFileName);
            File filepath = new File(desFilePath);
            if (!filepath.exists()) {
                filepath.mkdirs();
            }
            file.transferTo(dir);
            String srcUrl = "http://192.168.1.7:8080/image/static" + newFileName;
            System.out.println(srcUrl);
            return srcUrl;

        }
    }
}
