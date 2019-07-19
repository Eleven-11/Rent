package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.util.model.TestSuitModel.StandardList;
import com.heeexy.example.util.model.TestSuitModel.SubTitleList;
import com.heeexy.example.util.model.TestSuitModel.SubstandardList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TestController
 * @Description 测试用接口
 * @Author Lingling00
 * @DATE 7/19/2019 14:04
 * @VERSION 1.0
 **/
@RestController
@RequestMapping("/stdTest")
public class TestController {
    /**
     * 功能描述:
     *
     * @param
     * @return
     */
    @GetMapping("/testApi1")
    public JSONObject getdemocrat(HttpServletRequest request) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bigTile", "民主测评");
        jsonObject.put("explain", "其实不民主");
        jsonObject.put("number", "3");

        List<String> substandardName1 = new ArrayList<>();
        substandardName1.add("180");
        substandardName1.add("175");
        substandardName1.add("172");

        List<String> substandardName2 = new ArrayList<>();
        substandardName2.add("a");
        substandardName2.add("b");
        substandardName2.add("c");


        SubstandardList substandard = new SubstandardList();
        substandard.setSubstandardName(substandardName1);

        SubstandardList substandard2 = new SubstandardList();
        substandard2.setSubstandardName(substandardName2);

        List<SubstandardList> substandardList1 = new ArrayList<>();
        substandardList1.add(substandard);
        substandardList1.add(substandard2);

        List<SubstandardList> substandardList2 = new ArrayList<>();
        substandardList2.add(substandard);
        substandardList2.add(substandard2);

        List<SubTitleList> subTitleList = new ArrayList<>();
        SubTitleList subTitle1 = new SubTitleList();
        SubTitleList subTitle2 = new SubTitleList();


        List<StandardList> standardList1 = new ArrayList<>();
        List<StandardList> standardList2 = new ArrayList<>();

        StandardList standard1 = new StandardList();
        StandardList standard2 = new StandardList();

        standard1.setOptionRule(12);
        standard1.setStandardName("健康数据");
        standard1.setSubstandardList(substandardList1);
        standard1.setSubstandardList(substandardList2);

        standard2.setOptionRule(12);
        standard2.setStandardName("成绩");
        standard2.setSubstandardList(substandardList1);
        standard2.setSubstandardList(substandardList2);

        standardList1.add(standard1);
        standardList1.add(standard2);
        standardList2.add(standard1);
        standardList2.add(standard2);

        subTitle1.setSubTitle("身高？");
        subTitle1.setStandardList(standardList1);
        subTitle1.setStandardList(standardList2);

        subTitle2.setSubTitle("成绩？");
        subTitle2.setStandardList(standardList1);
        subTitle2.setStandardList(standardList2);

        subTitleList.add(subTitle1);
        subTitleList.add(subTitle2);

        jsonObject.put("subTitleList", subTitleList);
        return jsonObject;
    }
    @GetMapping("/testApi2")
    public JSONObject getQuestionaire(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        List<JSONObject> OptionList = new ArrayList<>();
        JSONObject Option = new JSONObject();
        List<String> OptionName = new ArrayList<>();
        OptionName.add("java");
        OptionName.add("c#");
        OptionName.add(".NET");
        Option.put("OptionName",OptionName);
        OptionList.add(Option);
        List<JSONObject> SubjectList = new ArrayList<>();
        JSONObject Subject = new JSONObject();
        Subject.put("questionName","你最想学的语言？");
        Subject.put("OptionRule",1);
        Subject.put("OptionList",OptionList);
        SubjectList.add(Subject);


        jsonObject.put("Title","民主测评");
        jsonObject.put("explain","dddddddddddddd");
        jsonObject.put("manNum",1);
        jsonObject.put("SubjectList",SubjectList);

        return jsonObject;




    }
}
