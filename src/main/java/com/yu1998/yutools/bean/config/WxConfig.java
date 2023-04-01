package com.yu1998.yutools.bean.config;

import lombok.Data;

/**
 * @author Duke_yzl
 * @date 20230331.01
 * @describe：
 */
@Data
public class WxConfig {
    private String appid;
    private String appsecret;
    private String appletAppid;
    private String appletAppsecret;

    private String authorizationUri = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={}&redirect_uri={}&response_type=code&scope={}&state={}#wechat_redirect";

    private String redirectUri;

    private String accessTokenLoginUri = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={}&secret={}&code={}&grant_type=authorization_code";
    private String accessTokenUri = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={}&secret={}";
    private String sendMessageUri = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token={}";
    private String sendMenuUri = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token={}";
}