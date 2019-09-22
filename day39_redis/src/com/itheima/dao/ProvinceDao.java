package com.itheima.dao;

import com.itheima.bean.Province;
import com.itheima.utils.C3P0Utils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Description:
 * @Author: yp
 */
public class ProvinceDao {


    public List<Province> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(C3P0Utils.getDataSource());
        String sql = "select * from province";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Province.class));

    }
}
