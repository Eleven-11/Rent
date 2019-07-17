package com.heeexy.example.util;

/**
 * @ClassName FileNameUtils
 * @Description 生成uuid文件名
 * @Author Lingling00
 * @DATE 7/17/2019 11:11
 * @VERSION 1.0
 **/
public class FileNameUtils {
    /**
     * 获取文件后缀
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成新的文件名
     * @param fileOriginName 源文件名
     * @return
     */
    public static String getFileName(String fileOriginName){
        return UUIDUtils.getUUID() + FileNameUtils.getSuffix(fileOriginName);
    }
}
