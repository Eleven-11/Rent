package com.heeexy.example.common.enums;

/**
 * @author: mac
 * @description: TODO
 * @date: 2019-07-29 19:08
 */
public enum ReportType {
    POST("post"), COMMON("common");

    private String type;

    ReportType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void serType(String type) {
        this.type = type;
    }
}
