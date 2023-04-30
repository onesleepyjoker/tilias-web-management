package com.example.tiliaswebmanagement.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.tiliaswebmanagement.pojo.Result;
import com.example.tiliaswebmanagement.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Request;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) servletRequest;
        //通过强转将servlet里的请求变成常用的http类型的请求 方便使用
        HttpServletResponse rep= (HttpServletResponse) servletResponse;
        String url = req.getRequestURL().toString();
//        获取请求的地址信息
        log.info("请求的url，",url);
        if(url.contains("login")){//查看是不是登录请求，是登录请求直接放行
            log.info("登陆操作，放行");
            filterChain.doFilter(servletRequest,servletResponse);
            return;//代码不在继续执行---相当于结束方法
        }

        String jwt = req.getHeader("token");//找到写在请求头中的token里面的twj令牌
        if(!StringUtils.hasLength(jwt)){//判断jwt令牌的长度是不是空的
            log.info("请求头为空，返回未登陆的信息");
            Result error = Result.error("NOT_LOGIN");//获取要返回的result对象
            //controller层中因为有@restcontroller注释所以可以自动地将return的数据转换成json格式的数据返回，
            //但是在这里并没有注释所以需要手动的转换   对象--json
            String notlogin = JSONObject.toJSON(error).toString();//通过jsonobject方法将result转换成json格式
            rep.getWriter().write(notlogin);//在通过请求的写出方法返回json格式的数据   底层--打印流

            return;

        }
        try {
            JwtUtils.parseJWT(jwt);
        }catch (Exception e){//解析令牌失败会爆出异常，捕获异常返回错误的json数据
            e.printStackTrace();
            log.info("令牌解析失败，返回未登录的错误信息");
            Result error = Result.error("NOT_LOGIN");
            String notlogin = JSONObject.toJSON(error).toString();
            rep.getWriter().write(notlogin);
            return;
        }
        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest,servletResponse);

    }
}
