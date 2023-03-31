package com.yu1998.yutools.bean.menu;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Duke_yzl
 * @date 20230301.01
 * @describe：
 */
@Data
@Builder
public class Button{
    /** 菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型 */
    private String type;
    /** 菜单标题，不超过16个字节，子菜单不超过60个字节 */
    private String name;
    /** type=click等点击类型必须 菜单 KEY 值，用于消息接口推送，不超过128字节 */
    private String key;
    /** type=view、miniprogram类型必须 网页 链接，用户点击菜单可打开链接，不超过1024字节。 type为 miniprogram 时，不支持小程序的老版本客户端将打开本url。*/
    private String url;
    /** type=media_id类型和view_limited类型必须 调用新增永久素材接口返回的合法media_id*/
    private String media_id;
    /** type=miniprogram类型必须 小程序的appid（仅认证公众号可配置）*/
    private String appid;
    /** type=miniprogram类型必须 小程序的页面路径*/
    private String pagepath;
    /** type=article_id类型和article_view_limited类型必须 发布后获得的合法 article_id*/
    private String article_id;


    /** 长度为1-5 */
    List<Button> sub_button;
}
