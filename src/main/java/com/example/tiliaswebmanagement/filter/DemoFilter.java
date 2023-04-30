package com.example.tiliaswebmanagement.filter;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.springframework.boot.autoconfigure.web.WebProperties;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("请求被拦截了");
        filterChain.doFilter(servletRequest,servletResponse);//放行的操作 可以访问对应的资源
    }

    @Override
    public void destroy() {
        System.out.println("销毁方法执行了");
    }
}
