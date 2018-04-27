package com.open.source.redis;

import com.open.source.redis.util.RedisUtil;
import org.junit.Test;

/**
 * @author tumingjian
 * @date 2018/4/27
 * 说明:
 */
public class RedisUtilTest {


    @Test
    public void test1(){
        String set = RedisUtil.set("abc", "haha");
        System.out.println(set);
    }
}
