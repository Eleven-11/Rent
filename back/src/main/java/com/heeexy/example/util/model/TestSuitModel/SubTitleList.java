package com.heeexy.example.util.model.TestSuitModel;

import java.util.List;

/**
 * @ClassName subTitleList
 * @Description zz
 * @Author Lingling00
 * @DATE 7/19/2019 15:34
 * @VERSION 1.0
 **/
public class SubTitleList {

    private String subTitle;

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public List<StandardList> getStandardList() {
        return standardList;
    }

    public void setStandardList(List<StandardList> standardList) {
        this.standardList = standardList;
    }

    private List<StandardList> standardList;
}
