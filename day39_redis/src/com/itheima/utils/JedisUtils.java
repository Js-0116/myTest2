package com.itheima.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description: Jedis的工具类
 * 1.提供jedis
 * 2.保证jedis池子只有一个
 *
 * 【作业】: 把配置的值抽取的到配置文件(properties或者xml) 解析出来赋值
 */
public class JedisUtils {

    private static  JedisPoolConfig jedisPoolConfig;
    private static  JedisPool jedisPool;

    static {
        //1.创建池子配置对象
         jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxWaitMillis(3000); //设置等待时间
        jedisPoolConfig.setMaxTotal(10); //最大连接数据量
        //2.创建jedis池子对象
        String host = "127.0.0.1";
        int port = 6379;
        jedisPool = new JedisPool(jedisPoolConfig, host, port);
    }

    /**
     * 从Jedis池子里面获得Jedis
     * @return
     */
    public static Jedis getJedis(){
        //3.从jedis池子里面获得jedis
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }

    /**
     * 释放
     * @param jedis
     */
    public static void close(Jedis jedis){
        if(jedis != null){
            jedis.close();
        }
    }


}
