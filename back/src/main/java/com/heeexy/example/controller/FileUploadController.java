package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.PostLabelService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.FileNameUtils;
import com.heeexy.example.util.constants.ErrorEnum;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author Lingling
 * @description 文件上传
 * @date 2019/7/2 0002
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private PostLabelService postLabelService;
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
            String srcUrl = "http://192.168.1.7:8080/image/chat/" + newFileName;
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
            String srcUrl = "http://192.168.1.7:8080/image/static/" + newFileName;
            System.out.println(srcUrl);
            return srcUrl;

        }
    }
    @RequestMapping(value = "/importLabel")
    @ResponseBody
    public JSONObject imporLabel(@RequestParam("file")MultipartFile file) throws IOException{
        System.out.println("调用后台导入接口");
        int insertCount = 0;
        int updataCount = 0;
        InputStream is ;
        if(!file.getOriginalFilename().endsWith(".xlsx")||file.isEmpty()==true){
            return CommonUtil.errorJson(ErrorEnum.E_10001);
        }
        try{
            is = file.getInputStream();
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheetAt(0);

            //excel格式第一行为标题行
            XSSFRow titleRow = sheet.getRow(0);
            for(int rowIndex = 1 ; rowIndex < sheet.getPhysicalNumberOfRows() ; rowIndex++ ){
                //获取当前行
                XSSFRow currentRow = sheet.getRow(rowIndex);
                System.out.println(currentRow);
                if(currentRow == null){
                    continue;
                }
                //一行为一个标签对象
                Map<String,Object> labelItem = new LinkedMap();
                for(int cellIndex = 0 ; cellIndex < currentRow.getPhysicalNumberOfCells(); cellIndex++){
                    if(currentRow.getCell(cellIndex).getCellType()== CellType.NUMERIC){
                        labelItem.put(titleRow.getCell(cellIndex).toString(),(int)currentRow.getCell(cellIndex).getNumericCellValue());
                        continue;
                    }
                    labelItem.put(titleRow.getCell(cellIndex).toString(),currentRow.getCell(cellIndex).toString());

                }

                JSONObject label  = new JSONObject(labelItem);
                System.out.println(label);
                JSONObject jo = postLabelService.getLabelByContent(label);
                JSONObject info = (JSONObject) jo.get("info");
                List list = (List) info.get("list");
                Integer totalCount = (Integer) info.get("totalCount");

                if(totalCount==0){
                    postLabelService.insertPostLabel(label);
                    insertCount++;
                }else{
                    postLabelService.updateDelPostLabel((JSONObject) list.get(0));
                    postLabelService.insertPostLabel(label);
                    updataCount++;
                }

            }
            return CommonUtil.successJson("成功插入"+insertCount+"条个标签，"+updataCount+"个标签已更新");
        }catch (Exception e){
            return CommonUtil.errorJson(ErrorEnum.E_10002);
        }

    }
}
