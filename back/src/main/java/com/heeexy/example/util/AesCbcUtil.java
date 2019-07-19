package com.heeexy.example.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.codec.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;

public class AesCbcUtil {

    private static Logger log = LoggerFactory.getLogger(AesCbcUtil.class);

    /**
     * AES解密
     *
     * @param data //密文，被加密的数据
     * @param key  //秘钥
     * @param iv   //偏移量
     * @return
     * @throws Exception
     */
    public static JSONObject decrypt(String data, String key, String iv) throws Exception {
        // 被加密的数据
        byte[] dataByte = Base64.decode(data).toString().getBytes();
        // 加密秘钥
        byte[] keyByte = Base64.decode(key).toString().getBytes();
        // 偏移量
        byte[] ivByte = Base64.decode(iv).toString().getBytes();
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSON.parseObject(result);
            }
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
        } catch (NoSuchPaddingException e) {
            log.error(e.getMessage(), e);
        } catch (InvalidParameterSpecException e) {
            log.error(e.getMessage(), e);
        } catch (IllegalBlockSizeException e) {
            log.error(e.getMessage(), e);
        } catch (BadPaddingException e) {
            log.error(e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        } catch (InvalidKeyException e) {
            log.error(e.getMessage(), e);
        } catch (InvalidAlgorithmParameterException e) {
            log.error(e.getMessage(), e);
        } catch (NoSuchProviderException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
    /**
     * 解密用户敏感数据
     *
     * @param encryptedData 明文,加密数据
     * @param iv            加密算法的初始向量
     * @param code          用户允许登录后，回调内容会带上 code（有效期五分钟），开发者需要将 code 发送到开发者服务器后台，使用code 换取 session_key api，将 code 换成 openid 和 session_key
     * @return
     *//*
    @SuppressWarnings("all")
    @RequestMapping(value = "/decodeUserInfo", method = RequestMethod.GET)
    public Map decodeUserInfo(@RequestParam String encryptedData,
                              @RequestParam String iv,
                              @RequestParam String code) {

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
        params.put("appid", WxConfig.WX_APP_ID);
        params.put("secret", WxConfig.WX_APP_SECRET);
        params.put("js_code", code);
        params.put("grant_type", "authorization_code");
        //请求url
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        //发送请求
        String str = "";
        try {
            str += httpService.doGet(url, params);
        } catch (Exception e) {
            LOG.error(e.getMessage());
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
                map.put("userInfo", userInfo);
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("status", 0);
        map.put("msg", "解密失败");
        return map;
    }*/
}