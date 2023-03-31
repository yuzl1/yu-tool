package com.yu1998.bean.message;

/**
 * @author Duke_yzl
 * @date 20230220.01
 * @describe：
 */

import lombok.Data;

/**跳小程序所需数据，不需跳小程序可不用传该数据 **/
@Data
public class Miniprogram{

    /**所需跳转到的小程序appid（该小程序 appid 必须与发模板消息的公众号是绑定关联关系，暂不支持小游戏） **/
    private String appid;

    /**所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar），要求该小程序已发布，暂不支持小游戏 **/
    private String pagepath;
}
