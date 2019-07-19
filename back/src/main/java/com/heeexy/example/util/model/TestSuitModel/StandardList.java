package com.heeexy.example.util.model.TestSuitModel;

import java.util.List;

/**
 * @ClassName standardList
 * @Description xxx
 * @Author Lingling00
 * @DATE 7/19/2019 15:31
 * @VERSION 1.0
 **/
public class StandardList {

    private String standardName;

    public int getOptionRule() {
        return optionRule;
    }

    public void setOptionRule(int optionRule) {
        this.optionRule = optionRule;
    }

    private int optionRule;

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }


    public List<SubstandardList> getSubstandardList() {
        return substandardList;
    }

    public void setSubstandardList(List<SubstandardList> substandardList) {
        this.substandardList = substandardList;
    }

    private List<SubstandardList> substandardList;
}
