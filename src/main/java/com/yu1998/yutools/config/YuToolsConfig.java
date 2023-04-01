package com.yu1998.yutools.config;

import com.yu1998.yutools.bean.config.WxConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Duke_yzl
 * @date 20230219.01
 * @describe：
 */
@Data
@Component
@ConfigurationProperties(value = "yutool")
public class YuToolsConfig {

    private WxConfig wx;


}
