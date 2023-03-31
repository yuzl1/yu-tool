
package com.yu1998.config;

import lombok.Data;
import cn.hutool.core.io.resource.Resource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;
/**
 * @Author: Duke_yzl 
 * @Date: 2023-02-19 11:36:34 
 * @Last Modified by: Duke_yzl
 * @Last Modified time: 2023-02-19 15:45:01
 */
@Data
@Component
@ConfigurationProperties(value = "app")
public class WxConfig {

    /**读取配置文件的值**/
    private String wxConfigPath = "application.properties";

    private String appid;
    private String appsecret;

    private String authorizationUri = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={}&redirect_uri={}&response_type=code&scope={}&state={}#wechat_redirect";

    private String redirectUri;

    private String accessTokenLoginUri = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={}&secret={}&code={}&grant_type=authorization_code";
    private String accessTokenUri = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={}&secret={}";

    public WxConfig(){
        init(wxConfigPath);
    }
    public WxConfig(String path){
        init(path);
    }
    public void init(String path) {
        Properties properties;
        try {
            properties = PropertiesLoaderUtils.loadAllProperties(path);
            this.appid = properties.getProperty("wx.appid");
            this.appsecret = properties.getProperty("wx.appsecret");
            this.redirectUri = properties.getProperty("wx.redirectUri");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    public void init() {
        
        
    }
}
