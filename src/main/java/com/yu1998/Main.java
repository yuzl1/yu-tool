package com.yu1998;

import com.yu1998.config.WxConfig;

/**
 * @author Duke_yzl
 * @date ${YEAR}${MONTH}${DAY}.01
 * @describe：
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        WxConfig wxConfig = new WxConfig();
        System.out.println(wxConfig.getAppid());
        
    }
}
