package com.yu1998.yutools.bean.message;

import lombok.Builder;
import lombok.Data;

/**
 * @author Duke_yzl
 * @date 20230220.01
 * @describe：
 */
@Data
@Builder
public class WxMessageData{
    /** 内容 **/
    private String value;
    /** 字体颜色 默认为黑色 **/
    private String color;
}