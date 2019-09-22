package com.itheima.web;

import com.itheima.service.ProvinceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: yp
 */
@WebServlet("/province")
public class ProvinceServlet extends HttpServlet {
    ProvinceService provinceService = new ProvinceService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //0 处理乱码
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");

            //1.调用业务获得 省份的数据【json串】

            String jsonData = provinceService.findAll();
            //2.响应给客户端
            response.getWriter().print(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            //方式一: 查询mysql
            //String jsonData =   provinceService.getFromMysql();
            //response.getWriter().print(jsonData);


        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
