package com.itheima.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.bean.Province;
import com.itheima.dao.ProvinceDao;
import com.itheima.utils.JedisUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @Description:
 * @Author: yp
 */
public class ProvinceService {

    public String findAll() throws Exception {
        String data = null;
        Jedis jedis = null;
        try {
            //1.先从Redis里面获得
            jedis = JedisUtils.getJedis();
            data = getFromRedis(jedis);
            if (data != null) {
                //2.如果有 就直接返回
                System.out.println("Redis里面有数据,直接从Redis里面获得...");
            } else {
                //3.没有 从Mysql获得 再存到Redis
                System.out.println("Redis里面没有数据,从Mysql获得 再存到Redis...");
                data = getFromMysql();
                saveToRedis(jedis, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //方式二
            System.out.println("Redis服务器没开或者挂了, 从Mysql获得...");
            data = getFromMysql();
        } finally {
            JedisUtils.close(jedis);
            return data;
        }
    }

    //把数据存到Redis
    private void saveToRedis(Jedis jedis, String data) {
        if (jedis != null) {
            jedis.set("province_key", data);
        }
    }

    //从Redis获得
    private String getFromRedis(Jedis jedis) {
        if (jedis != null) {
            return jedis.get("province_key");
        }
        return null;
    }

    //从Mysql获得【Ctrl+Alt+M】
    public String getFromMysql() throws JsonProcessingException {
        //1.调用Dao, 获得List<Province> list
        ProvinceDao provinceDao = new ProvinceDao();
        List<Province> list = provinceDao.findAll();
        //2.把list转成json 返回
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(list);
    }
}
