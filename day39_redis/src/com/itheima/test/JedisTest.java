package com.itheima.test;

import com.itheima.utils.JedisUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description:
 * @Author: yp
 */
public class JedisTest {

    @Test
    //Jedis入门【操作字符串】
    public void fun01() {
        //1. 创建Jedis对象
        String host = "127.0.0.1";
        int port = 6379;
        Jedis jedis = new Jedis(host, port);
        //2. 操作Redis
        //2.1 存  set key value
        jedis.set("akey", "你好");
        //2.2 取 get key
        System.out.println(jedis.get("akey"));

        //2.3 删 del key
        jedis.del("akey");
        System.out.println(jedis.get("akey"));


        //3. 释放资源
        jedis.close();

    }

    @Test
    //Jedis池子的使用【操作字符串】
    public void fun02() {
        //1.创建池子配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxWaitMillis(3000); //设置等待时间
        jedisPoolConfig.setMaxTotal(10); //最大连接数据量

        //2.创建jedis池子对象
        String host = "127.0.0.1";
        int port = 6379;
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port);

        //3.从jedis池子里面获得jedis
        Jedis jedis = jedisPool.getResource();

        //4.操作Redis
        jedis.set("akey","aaa");
        System.out.println(jedis.get("akey"));
        jedis.del("akey");
        System.out.println(jedis.get("akey"));

        //5.释放资源
        jedis.close();

    }

    @Test
    //JedisUtils【操作字符串】
    public void fun03() {
        //1.从工具类里面获得jedis
        Jedis jedis = JedisUtils.getJedis();


        //4.操作Redis
        jedis.set("akey","aaa");
        System.out.println(jedis.get("akey"));
        jedis.del("akey");
        System.out.println(jedis.get("akey"));

        //5.释放资源
        JedisUtils.close(jedis);


    }


}
