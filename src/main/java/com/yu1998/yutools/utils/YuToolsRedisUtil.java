package com.yu1998.yutools.utils;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author duke_yzl 添加锁
 */
@Component
@Slf4j
public class YuToolsRedisUtil {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static StringRedisTemplate stringRedisTemplateStatic;

    @PostConstruct
    public void init() {
        stringRedisTemplateStatic = stringRedisTemplate;
    }

    public static String get(String key) {
        return stringRedisTemplateStatic.opsForValue().get(key);
    }

    /**
     * 存入Hash类型
     */
    public static void putHash(Object h, Object hk, Object hv) {
        stringRedisTemplateStatic.opsForHash().put(h.toString(), hk.toString(), hv.toString());
    }

    public static void setHashTime(Object key, long timeout, TimeUnit unit) {
        stringRedisTemplateStatic.expire(key.toString(), timeout, unit);
    }

    /**
     * 获得hash类型的value值
     */
    public static Object getHash(Object h, Object hk) {
        return stringRedisTemplateStatic.opsForHash().get(h.toString(), hk);
    }

    /**
     * 获得所有value值
     */
    public static List getHashValues(Object h) {
        return stringRedisTemplateStatic.opsForHash().values(h.toString());
    }

    /**
     * 获得hash类型的所有key值
     */
    public static Set<Object> getHashKeys(Object h) {
        return stringRedisTemplateStatic.opsForHash().keys(h.toString());
    }

    /**
     * 设置有效时间
     */
    public static void setTime(String key, long timeout, TimeUnit unit) {
        stringRedisTemplateStatic.expire(key, timeout, unit);
    }

    /**
     * 默认时间一天
     *
     * @param key
     * @param data
     */
    public static void set(String key, String data) {
        stringRedisTemplateStatic.opsForValue().set(key, data, 60 * 60 * 24, TimeUnit.SECONDS);
    }

    /**
     * 自定义时间
     *
     * @param key
     * @param data
     */
    public static void set(String key, String data, long timeout, TimeUnit unit) {
        stringRedisTemplateStatic.opsForValue().set(key, data, timeout, unit);
    }

    public static Boolean setNx(String key, String data) {
        return stringRedisTemplateStatic.opsForValue().setIfAbsent(key, data);
    }

    /**
     * 根据Key删除缓存
     *
     * @param key
     */
    public static void delete(String key) {
        stringRedisTemplateStatic.delete(key);
    }

    /**
     * 根据key删除hash的值
     *
     * @param key
     * @param sn
     */
    public static void deleteHash(String key, String sn) {
        stringRedisTemplateStatic.opsForHash().delete(key, sn);
    }

    /**
     * 获取
     *
     * @param prefix uc-
     * @param id     222
     * @param key    feafds
     * @return
     */
    public static String getMatch(String prefix, Long id, String key) {
        String matchkey = "[prefix:" + prefix + id + "]" + key;
        return stringRedisTemplateStatic.opsForValue().get(matchkey);
    }

    /**
     * 存储REDIS队列 顺序存储
     *
     * @param key  reids键名
     * @param data 数据
     */
    public static void rpush(String key, String data) {
        stringRedisTemplateStatic.opsForList().rightPush(key, data);
    }

    /**
     * 获取队列数据
     *
     * @param key reids键名
     */
    public static String lpop(String key) {
        return stringRedisTemplateStatic.opsForList().leftPop(key);
    }

    /**
     * @Description: 添加set集合
     * @Param: [key, data]
     * @Author: liangbl
     * @Date: 2018/10/22
     */
    public static void setAdd(String key, String data) {
        stringRedisTemplateStatic.opsForSet().add(key, data);
    }

    /**
     * @Description: 移除set集合中一个或多个成员
     * @Param: [key, values]
     * @Author: liangbl
     * @Date: 2018/10/22
     */
    public static void setRemove(String key, Object... values) {
        stringRedisTemplateStatic.opsForSet().remove(key, values);
    }

    /**
     * @Description: 返回set集合中的所有成员
     * @Param: [key]
     * @Author: liangbl
     * @Date: 2018/10/22
     */
    public static Set<String> setMembers(String key) {
        return stringRedisTemplateStatic.opsForSet().members(key);
    }

    /**
     * 计数器 val自增自减
     *
     * @param key
     * @param val eg:1加   -1减
     */
    public static void incre(String key, long val) {
        stringRedisTemplateStatic.boundValueOps(key).increment(val);
    }

    /**
     * hash计数器 val自增自减
     *
     * @param key
     * @param hk
     * @param val eg:1加   -1减
     */
    public static void hashIncre(String key, String hk, long val) {
        stringRedisTemplateStatic.opsForHash().increment(key, hk, val);
    }

    public static void pushMsg(String key, String msg) {
        stringRedisTemplateStatic.convertAndSend(key, msg);
    }

    /**
     * Redis分布式锁
     *
     * @return
     */
    public static boolean tryLock(String key, String value, long timeout) {
        if (timeout == 0) {
            //默认过期时间
            timeout = 60;
        }
        boolean isSuccess = stringRedisTemplateStatic.opsForValue().setIfAbsent(key, value);
        //设置过期时间，防止死锁
        if (isSuccess) {
            stringRedisTemplateStatic.expire(key, timeout, TimeUnit.SECONDS);
        }
        return isSuccess;
    }

    /**
     * Redis 分布式锁释放
     *
     * @param key
     * @param value
     */
    public static void unLock(String key, String value) {
        try {
            String currentValue = stringRedisTemplateStatic.opsForValue().get(key);
            if (ObjectUtil.isNotEmpty(currentValue) && ObjectUtil.equals(currentValue, value)) {
                stringRedisTemplateStatic.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            //这个是我的自定义异常，你可以删了
        }
    }

}


