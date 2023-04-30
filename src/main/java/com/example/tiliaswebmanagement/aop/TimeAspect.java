package com.example.tiliaswebmanagement.aop;

import com.alibaba.fastjson.JSONObject;
import com.example.tiliaswebmanagement.mapper.OperateLogMapper;
import com.example.tiliaswebmanagement.pojo.OperateLog;
import com.example.tiliaswebmanagement.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect  //aop类

public class TimeAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Autowired
    private HttpServletRequest request;
//    可以直接注入，并且请求里面包括了请求需要的jwt令牌

    @Around("@annotation(com.example.tiliaswebmanagement.aop.MyLog)")
    public Object recordlog(ProceedingJoinPoint joinPoint) throws Throwable {

        String jwt = request.getHeader("token");
        Claims claims= JwtUtils.parseJWT(jwt);//获取解析令牌后形成的map集合数据
        Integer operateUser = (Integer)claims.get("id");//获取到登陆员工的id信息

        LocalDateTime opereteTime = LocalDateTime.now();//获取到操作的时间

        String className = joinPoint.getTarget().getClass().getName();//操作类名

        String methodName = joinPoint.getSignature().getName();//操作方法名

        Object[] args = joinPoint.getArgs();//操作的方法参数
        String methodParams= Arrays.toString(args);//将数组转换成字符串类型

        long begin = System.currentTimeMillis();//开始时间
        Object result = joinPoint.proceed();//操作方法的返回值
        String returnValue = JSONObject.toJSONString(result);
//        原本方法的返回值都是result形式的json数据，可以通过jsonobject工具类将其转换为string再存入表格当中

        long end = System.currentTimeMillis();//结束时间
        long costTime = end - begin;//获取操作时间

        OperateLog operateLog=new OperateLog(null,operateUser,opereteTime,className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);
        log.info("AOP记录日志：{}",operateLog);
        return result;
    }
}
