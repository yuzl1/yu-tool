package com.yu1998.yutools.bean.message;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.yu1998.yutools.bean.config.WxConfig;
import lombok.Data;

import java.util.Map;

/**
 * @author Duke_yzl
 * @date 20230219.01
 * @describe：
 */
@Data
public class WxMessage {


    private transient WxConfig wxConfig;

    /**接收者openid **/
    private String touser;
    /**模板ID **/
    private String template_id;
    /**模板数据 **/
    private Map<String,WxMessageData> data;
    private transient String messageBody;
    /**模板跳转链接（海外帐号没有跳转能力） **/
    private String url;
    /**跳小程序所需数据，不需跳小程序可不用传该数据 **/
    private Miniprogram miniprogram;
    /** 防重入id。对于同一个openid + client_msg_id, 只发送一条消息,10分钟有效,超过10分钟不保证效果。若无防重入需求，可不填 **/
    private String client_msg_id;

    public WxMessage(WxConfig wxConfig){
        this.wxConfig = wxConfig;
    }

    /**
     * 消息体
     * @param touser 接受人openid
     * @param templateId 模版id
     * @param url 跳转地址
     * @param isToApplet 是否跳转小程序
     * @param pagepath 小程序页面路径 示例index?foo=bar
     * @param clientMsgId 唯一编码 一般不需要使用
     * @param objectMap 消息字段
     */
    public void createJson(String touser,String templateId,String url,boolean isToApplet,String pagepath,String clientMsgId,Map<String,WxMessageData> objectMap){
        this.touser = touser;
        this.template_id = templateId;
        if (StrUtil.isNotEmpty(url)){
            this.url = url;
        }
        if (isToApplet){
            this.miniprogram.setAppid(this.wxConfig.getAppletAppid());
            if (StrUtil.isNotEmpty(pagepath)){
                this.miniprogram.setPagepath(pagepath);
            }
        }
        if (StrUtil.isNotEmpty(clientMsgId)){
            this.client_msg_id = clientMsgId;
        }
        this.data = objectMap;
        this.messageBody =  JSONUtil.toJsonStr(this);
        System.out.println("消息体："+this.messageBody);
    }



}