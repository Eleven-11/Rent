package com.heeexy.example.controller.api;

import com.heeexy.example.service.HttpService;
import com.heeexy.example.service.VisitorService;
import com.heeexy.example.service.WxUserService;
import com.heeexy.example.util.AesCbcUtil;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.UUIDUtils;
import com.heeexy.example.util.WxConfig;
import com.heeexy.example.util.constants.ErrorEnum;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName WxLoginController
 * @Description 微信小程序登录接口
 * @Author Lingling00
 * @DATE 7/23/2019 15:12
 * @VERSION 1.0
 **/
@RestController
@RequestMapping("/wxLogin")
public class WxLoginController {
    @Autowired
    private HttpService httpService;
    @Autowired
    private WxUserService wxUserService;
    @Autowired
    private VisitorService visitorService;
    /**
     * 解密用户敏感数据
     *
     * @param encryptedData 明文,加密数据
     * @param iv            加密算法的初始向量
     * @param code          用户允许登录后，回调内容会带上 code（有效期五分钟），开发者需要将 code 发送到开发者服务器后台，使用code 换取 session_key api，将 code 换成 openid 和 session_key
     * @return*/
    @SuppressWarnings("all")
    @RequestMapping(value = "/decodeUserInfo", method = RequestMethod.GET)
    public Map decodeUserInfo(@RequestParam String encryptedData,
                              @RequestParam String iv,
                              @RequestParam String code) throws IOException {

        Map map = new HashMap();
        //登录凭证不能为空
        if (code == null || code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "code 不能为空");
            return map;
        }
        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
        //请求参数
        Map params = new HashMap();
        /*Properties pro = new Properties();
        FileInputStream in = new FileInputStream("WxConfig.properties");
        pro.load(in);*/
        params.put("appid", WxConfig.WX_APP_ID);
        params.put("secret",WxConfig.WX_APP_SECRET);
        params.put("js_code", code);
        params.put("grant_type", "authorization_code");
        /*in.close();*/
        //请求url
        String url = "https://api.weixin.qq.com/sns/jscode2session";
//        String url = "http://www.baidu.com";
        //发送请求
        String str = "";
        try {
            str += httpService.doGet(url, params);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            map.put("status", 0);
            map.put("msg", "解密失败");
            return map;
        }
        //解析相应内容（转换成json对象）
        String session_key = "";
        if (StringUtils.isNotEmpty(str)) {
            JSONObject json = JSONObject.fromObject(str);
            //获取会话密钥（session_key）
            session_key = json.get("session_key").toString();
            //用户的唯一标识（openid）
            String openid = (String) json.get("openid");
        }
        //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
        try {
            com.alibaba.fastjson.JSONObject userInfoJSON = AesCbcUtil.decrypt(encryptedData, session_key, iv);
            if (null != userInfoJSON) {
                map.put("status", 1);
                map.put("msg", "解密成功");
                Map userInfo = new HashMap();
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("nickName", userInfoJSON.get("nickName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("country", userInfoJSON.get("country"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                userInfo.put("unionId", userInfoJSON.get("unionId"));
                //测试用代码
                if (userInfo.get("unionId") == null){
                    userInfo.put("unionId", UUIDUtils.getUUID());
                }
                map.put("userInfo", userInfo);
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("status", 0);
        map.put("msg", "解密失败");
        return map;
    }
    /**
     *@description:微信用户登录操作，根据是否授权获取用户信息分为四种情况
     *@param request 系统用户id-userId（后台生成）
     *               微信用户唯一id-unionId
     *               微信用户头像-wxAvatar
     *               微信用户昵称-wxNickname
     *               微信用户性别-wxGender
     *               微信用户城市-wxCity
     *               微信用户省份-wxProvince
     *               微信用户国家-wxCountry
     *               创建时间-createTime
     *               最近活跃时间-activeTime
     *@return 首次登录返回系统用户信息userId
     *        其余情况返回用户信息
     */
    @PostMapping("/login")
    public com.alibaba.fastjson.JSONObject login(HttpServletRequest request) {
        com.alibaba.fastjson.JSONObject jsonObject = CommonUtil.request2Json(request);
        System.out.println(jsonObject);
        if (jsonObject.get("openId")!=null &&jsonObject.get("userId")==null) {
            //当用户已授权过但清除缓存（即无法获取userId）
            System.out.println(wxUserService.getAuthStatus(jsonObject));
            if(wxUserService.getAuthStatus(jsonObject)!=null){
                return wxUserService.getAuthStatus(jsonObject);
            }
            //用户首次登录即授权
            else {
                String userId = wxUserService.insertWxUser(jsonObject);
                com.alibaba.fastjson.JSONObject authJson = new com.alibaba.fastjson.JSONObject();
                authJson.put("userId",userId);
                return authJson;
            }
        }
        else if(jsonObject.get("openId")==null &&jsonObject.get("userId")==null){
            //首次登录未授权，执行插入游客信息操作
            String visitorId = visitorService.insertVisitor(jsonObject);
            com.alibaba.fastjson.JSONObject visitorJson = new com.alibaba.fastjson.JSONObject();
            visitorJson.put("userId",visitorId);
            return visitorJson;
        }
        else if(jsonObject.get("openId")!=null&&jsonObject.get("userId")!=null){
            //首次登录未授权，获取游客信息userId，之后登录授权，此时执行插入微信用户信息操作
            return wxUserService.getWxUserList(jsonObject);

        }
        else if(jsonObject.get("openId")==null&&jsonObject.get("userId")!=null){
            //以游客身份登录
        }
        else {
            return CommonUtil.errorJson(ErrorEnum.E_400);
        }
        return null;
    }
}
