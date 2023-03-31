package com.yu1998.utils;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.yu1998.yutools.bean.menu.Menu;
import com.yu1998.yutools.config.WxConfig;
import com.yu1998.yutools.exception.YuToolsException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Duke_yzl
 * @date 20230220.01
 * @describe：wx的公共方法 比如获取全局access_token
 */
@Data
@Slf4j
public class WxCommonUtil {

    private WxConfig wxConfig;

    /** 全局token */
    private String accessToken;
    /** token过期时间  */
    private String expiresIn;
    public WxCommonUtil(WxConfig wxConfig){
        this.wxConfig = wxConfig;
    }

    /**
     * 获取微信全局token
     * @throws YuToolsException
     */
    public void getAccessTokenUri() throws YuToolsException{
        String uri = StrUtil.format(wxConfig.getAccessTokenUri(), wxConfig.getAppid(), wxConfig.getAppsecret());
        log.info("方法{}--请求地址：{}",Thread.currentThread().getStackTrace()[1].getMethodName(),uri);
        String restfulBody = HttpRequest.get(uri).execute().body();
        log.info("方法{}--请求结果：{}",Thread.currentThread().getStackTrace()[1].getMethodName(),restfulBody);
        JSONObject restfulJson = JSONUtil.parseObj(restfulBody);
        this.accessToken = restfulJson.getStr("access_token");
        this.expiresIn = restfulJson.getStr("expires_in");
        if(StrUtil.isEmptyIfStr(accessToken)){
            throw new YuToolsException(CommonEnum.ERROR_TOKEN.getDesc());
        }
    }

    /**
     * 发送微信模版消息
     * @return
     * @throws YuToolsException
     */
    public void sendMessage(String messageBody) throws YuToolsException {
        String uri =StrUtil.format(wxConfig.getSendMessageUri(), this.accessToken);
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
        String uri = StrUtil.format(wxConfig.getSendMenuUri(), this.accessToken);
        log.info("方法{}--请求地址：{}",Thread.currentThread().getStackTrace()[1].getMethodName(),uri);
        String restfulBody = HttpRequest.post(uri).body(JSONUtil.toJsonStr(menu)).execute().body();
        log.info("方法{}--请求结果：{}",Thread.currentThread().getStackTrace()[1].getMethodName(),restfulBody);
        JSONObject restfulJson = JSONUtil.parseObj(restfulBody);
        if (CommonEnum.SUCCESS.getCode() != restfulJson.getInt("errcode")){
            throw new YuToolsException(CommonEnum.ERROR_MENU.getDesc());
        }

    }
}