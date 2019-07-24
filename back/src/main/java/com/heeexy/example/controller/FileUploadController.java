package com.heeexy.example.controller;

import com.heeexy.example.util.FileNameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
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
     * @param req
     * @param multiReq
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public String imgUpload(HttpServletRequest req, MultipartHttpServletRequest multiReq) throws IOException {
        System.out.println(multiReq);
        MultipartFile file = multiReq.getFile("file");
        System.out.println(file);
        String originalFileName = file.getOriginalFilename();
        String desFilePath =
                "E:" + File.separator+"image"
                        + File.separator+"rent"
                        + "/" ;
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
        String srcUrl ="http://localhost:8080/image/"+newFileName;
        /*File serverDir = new File(srcUrl,newFileName);
        if(!serverDir.exists())
        {
            serverDir.mkdirs();
        }
        File newFile = new File(srcUrl);
        file.transferTo(newFile);*/
        System.out.println(srcUrl);
        return srcUrl;
    }
}
