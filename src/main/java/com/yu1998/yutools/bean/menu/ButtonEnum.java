package com.yu1998.yutools.bean.menu;

import lombok.Data;

/**
 * @author Duke_yzl
 * @date 20230301.01
 * @describe：
 */
public enum ButtonEnum{
    /** key是根据默认情况添加的 可以修改为自己所需要的  */
    TYPE_VIEW("view","网页类型","view"),
    TYPE_CLICK("click","点击类型","V1001"),
    TYPE_MINIPROGRAM("miniprogram","小程序类型","miniprogram"),
    TYPE_SCANCODE_WAITMSG("scancode_waitmsg","扫码带提示","rselfmenu_0_0"),
    TYPE_SCANCODE_PUSH("scancode_push","扫码推事件","rselfmenu_0_1"),
    TYPE_PIC_SYSPHOTO("pic_sysphoto","系统拍照发图","rselfmenu_1_0"),
    TYPE_PIC_PHOTO_OR_ALBUM("pic_photo_or_album","拍照或者相册发图","rselfmenu_1_1"),
    TYPE_PIC_WEIXIN("pic_weixin","微信相册发图","rselfmenu_1_2"),
    TYPE_LOCATION_SELECT("location_select","发送位置","rselfmenu_2_0"),
    TYPE_MEDIA_ID("media_id","图片","MEDIA_ID1"),
    TYPE_VIEW_LIMITED("view_limited","图文消息","MEDIA_ID2"),
    TYPE_ARTICLE_ID("article_id","发布后的图文消息","ARTICLE_ID1"),
    TYPE_ARTICLE_VIEW_LIMITED("article_view_limited","发布后的图文消息","ARTICLE_ID2");
    private String type;
    private String desc;
    private String key;

    ButtonEnum(String type, String desc,String key) {
        this.type = type;
        this.desc = desc;
        this.key = key;
    }
    /**
     * 自己定义一个静态方法,通过code返回枚举常量对象
     * @param type
     * @return
     */
    public static Button getButton(String type){
        for (ButtonEnum  buttonEnum: values()) {
            if(type.equals(buttonEnum.type)){
                return Button.builder().type(buttonEnum.type).key(buttonEnum.key).build();
            }
        }
        return  Button.builder().build();

    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public String getKey() {
        return key;
    }
}