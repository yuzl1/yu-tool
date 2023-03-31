package com.yu1998.exception;

/**
 * @author Duke_yzl
 * @date 20230301.01
 * @describe：自定义工具类异常
 */
public class YuToolsException extends Exception{

    public YuToolsException() {
        super();
    }
    public YuToolsException(String str) {
        super(str);
    }
}