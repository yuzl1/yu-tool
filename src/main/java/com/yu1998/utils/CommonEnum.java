package com.yu1998.utils;

/**
 * @author Duke_yzl
 * @date 20230301.01
 * @describe：
 */
public enum CommonEnum {
    SUCCESS(0,"请求成功"),
    ERROR_TOKEN(10,"获取微信全局token异常"),
    ERROR_MENU(11,"获取微信全局token异常");

    CommonEnum(int code,String desc){

    }
    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
