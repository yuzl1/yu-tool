package com.yu1998.yutools.aspectj;

import com.yu1998.yutools.annotation.YuToolsRegistry;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Duke_yzl
 * @date 20230331.01
 * @describe：开启工具类的注入
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(YuToolsRegistry.class)
public @interface EnableYuTools {
}
