package com.heeexy.example.common.util;


import com.heeexy.example.common.enums.ReportType;
import org.springframework.util.StringUtils;

/**
 * @author: chens
 * @description: 举报静态方法
 * @date: 2019-07-29 19:10
 */
public class ReportUtil {

    /**
     * 校验举报类型是否合法
     * @param type
     * @return 合法返回true
     */
    public static boolean queryreportType(String type){
        //如果type为空返回false
        if (StringUtils.isEmpty(type)){
            return false;
        }
        for (ReportType rt : ReportType.values()){
            //匹配成功返回true
            if (rt.getType().equals(type)){
                return true;
            }
        }
        return false;
    }
}
