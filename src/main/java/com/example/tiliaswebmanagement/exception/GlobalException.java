package com.example.tiliaswebmanagement.exception;

import com.example.tiliaswebmanagement.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常处理器
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)//捕获所有的异常
    public Result ex(Exception ex){
        ex.printStackTrace();
        return Result.error("对不起操作失败，请联系管理员");
    }
}
