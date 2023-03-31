package com.yu1998.entity;

import java.util.Map;

import com.yu1998.config.WxConfig;

import lombok.Data;

/**
 * @author Duke_yzl
 * @date 20230219.01
 * @describe：模版消息体
 */
@Data
public class WxMessage {

    {
        
    }
    private WxConfig wxConfig;
    /**接收者openid **/
    private String touser;
    /**模板ID **/
    private String template_id;
    /**模板数据 **/
    private Map<String,Wxdata> dataBean;
    private String[] data;
    /**模板跳转链接（海外帐号没有跳转能力） **/
    private String url;
    /**跳小程序所需数据，不需跳小程序可不用传该数据 **/
    private Miniprogram miniprogram;
    /** 防重入id。对于同一个openid + client_msg_id, 只发送一条消息,10分钟有效,超过10分钟不保证效果。若无防重入需求，可不填 **/
    private String client_msg_id;

    /**
     * 消息体
     * @param touser 接受人openid
     * @param template_id 模版id
     * @param url 跳转地址
     * @param appid 小程序id
     * @param pagepath 小程序页面路径 示例index?foo=bar
     * @param client_msg_id 唯一编码 一般不需要使用
     * @param data
     */
    /**
     * 创建微信消息体工具bena
     * @param wxConfig
     */
    
    public void createJson(String touser,String template_id,String url,String appid,String pagepath,String client_msg_id,String ...data){

    }


    /**跳小程序所需数据，不需跳小程序可不用传该数据 **/
    @Data
    public class Miniprogram{

        /**所需跳转到的小程序appid（该小程序 appid 必须与发模板消息的公众号是绑定关联关系，暂不支持小游戏） **/
        private String appid;

        /**所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar），要求该小程序已发布，暂不支持小游戏 **/
        private String pagepath;
    }
    @Data
    public class Wxdata{
        private String value;
        private String color;
    }
}