package com.heeexy.example.util;

/**
 * @ClassName UUIDUtils
 * @Description UUID工具类
 * @Author Lingling00
 * @DATE 7/17/2019 11:13
 * @VERSION 1.0
 **/
import java.util.UUID;

public class UUIDUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }


}