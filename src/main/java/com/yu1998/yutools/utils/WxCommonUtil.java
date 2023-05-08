package com.yu1998.yutools.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.yu1998.yutools.bean.menu.Menu;
import com.yu1998.yutools.config.YuToolsConfig;
import com.yu1998.yutools.exception.YuToolsException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author Duke_yzl
 * @date 20230220.01
 * @describe：wx的公共方法 比如获取全局access_token
 */
@Data
@Slf4j
@Component
public class WxCommonUtil {

    @Resource
    private YuToolsConfig yuToolsConfig;


    /** 全局token */
    private String accessToken;
    /** token过期时间  */
    private long expiresIn;


    /**
     * 获取微信全局token
     * @throws YuToolsException
     */
    public void getAccessTokenUri() throws YuToolsException{
        this.accessToken = YuToolsRedisUtil.get(yuToolsConfig.getWx().getRedisTokenKey());
        if(StrUtil.isEmpty(accessToken)){
            String uri = StrUtil.format(yuToolsConfig.getWx().getAccessTokenUri(), yuToolsConfig.getWx().getAppid(), yuToolsConfig.getWx().getAppsecret());
            log.info("方法{}--请求地址：{}",Thread.currentThread().getStackTrace()[1].getMethodName(),uri);
            String restfulBody = HttpRequest.get(uri).execute().body();
            log.info("方法{}--请求结果：{}",Thread.currentThread().getStackTrace()[1].getMethodName(),restfulBody);
            JSONObject restfulJson = JSONUtil.parseObj(restfulBody);
            this.accessToken = restfulJson.getStr("access_token");
            this.expiresIn = restfulJson.getLong("expires_in");
            YuToolsRedisUtil.set(yuToolsConfig.getWx().getRedisTokenKey(),accessToken,--expiresIn, TimeUnit.SECONDS);
            if(StrUtil.isEmpty(accessToken)){
                throw new YuToolsException(CommonEnum.ERROR_TOKEN.getDesc());
            }
        }
    }


    /**
     * 发送微信模版消息
     * @return
     * @throws YuToolsException
     */
    public void sendMessage(String messageBody) throws YuToolsException {
        this.getAccessTokenUri();
        String uri =StrUtil.format(yuToolsConfig.getWx().getSendMessageUri(), this.accessToken);
        log.info("方法{}--请求地址：{}",Thread.currentThread().getStackTrace()[1].getMethodName(),uri);
        String restfulBody = HttpRequest.post(uri).body(messageBody).execute().body();
        log.info("方法{}--请求结果：{}",Thread.currentThread().getStackTrace()[1].getMethodName(),restfulBody);
        JSONObject restfulJson = JSONUtil.parseObj(restfulBody);
        if(CommonEnum.SUCCESS.getCode() != restfulJson.getInt("errcode")){
            throw new YuToolsException(CommonEnum.ERROR_MENU.getDesc());
        }
    }

    /**
     * 设置微信公众号菜单
     * @param menu
     * @throws YuToolsException
     */
    public void sendMenu(Menu menu) throws YuToolsException{
        this.getAccessTokenUri();
        String uri = StrUtil.format(yuToolsConfig.getWx().getSendMenuUri(), this.accessToken);
        log.info("方法{}--请求地址：{}",Thread.currentThread().getStackTrace()[1].getMethodName(),uri);
        String restfulBody = HttpRequest.post(uri).body(JSONUtil.toJsonStr(menu)).execute().body();
        log.info("方法{}--请求结果：{}",Thread.currentThread().getStackTrace()[1].getMethodName(),restfulBody);
        JSONObject restfulJson = JSONUtil.parseObj(restfulBody);
        if (CommonEnum.SUCCESS.getCode() != restfulJson.getInt("errcode")){
            throw new YuToolsException(CommonEnum.ERROR_MENU.getDesc());
        }

    }

    /**
     * 获取jsapi配置的临时签名
     * @return
     */
    public String getJsapiTicket() throws YuToolsException {
        String wx_ticket = YuToolsRedisUtil.get(yuToolsConfig.getWx().getRedisJsapiTicketKey());
        if ((StrUtil.isEmpty(wx_ticket))){
            this.getAccessTokenUri();
            String uri = StrUtil.format(StrUtil.format(yuToolsConfig.getWx().getJsapiTicketUri(),this.accessToken));
            log.info("方法{}--请求地址：{}",Thread.currentThread().getStackTrace()[1].getMethodName(),uri);
            String restfulBody = HttpRequest.get(uri).execute().body();
            log.info("方法{}--请求结果：{}",Thread.currentThread().getStackTrace()[1].getMethodName(),restfulBody);
            JSONObject jSONResult = JSONUtil.parseObj(restfulBody);
            YuToolsRedisUtil.set(yuToolsConfig.getWx().getRedisJsapiTicketKey(),jSONResult.getStr("ticket"),jSONResult.getInt("expires_in")-5, TimeUnit.SECONDS);
            wx_ticket = jSONResult.getStr("ticket");
        }
        return wx_ticket;

    }

    /**
     * 获取登录token 同时获取openid
     * @param code
     * @return
     * @throws YuToolsException
     */
    public String getWxLoginToken(String code) throws YuToolsException {
            String uri = StrUtil.format(StrUtil.format(yuToolsConfig.getWx().getAccessTokenLoginUri(),yuToolsConfig.getWx().getAppid(), yuToolsConfig.getWx().getAppsecret(),code));
            log.info("方法{}--请求地址：{}",Thread.currentThread().getStackTrace()[1].getMethodName(),uri);
            String restfulBody = HttpRequest.get(uri).execute().body();
            log.info("方法{}--请求结果：{}",Thread.currentThread().getStackTrace()[1].getMethodName(),restfulBody);
        return restfulBody;

    }
}