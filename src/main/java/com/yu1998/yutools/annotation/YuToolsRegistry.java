package com.yu1998.yutools.annotation;

import com.yu1998.yutools.config.YuToolsConfig;
import com.yu1998.yutools.utils.WxCommonUtil;
import com.yu1998.yutools.utils.YuToolsRedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Duke_yzl
 * @date 20230331.01
 * @describe：所有需要注入的工具类都在这里添加
 */
@Slf4j
public class YuToolsRegistry implements ImportBeanDefinitionRegistrar {


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        if (log.isInfoEnabled()) {
            log.info("YuToolRegistry-registerBeanDefinitions begin to init yutools configure");
        }
        //启动所需要加载的配置文件都要注册进来
        //配置类
        BeanRegistrationUtil.registerBeanDefinitionIfNotExists(registry, YuToolsConfig.class.getName(),
                YuToolsConfig.class);
        //redis工具类
        BeanRegistrationUtil.registerBeanDefinitionIfNotExists(registry, YuToolsRedisUtil.class.getName(),
                YuToolsRedisUtil.class);
        //tutool工具类统一调用入口
        BeanRegistrationUtil.registerBeanDefinitionIfNotExists(registry, WxCommonUtil.class.getName(),
                WxCommonUtil.class);



    }


}