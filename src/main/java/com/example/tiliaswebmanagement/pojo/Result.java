package com.example.tiliaswebmanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;//响应码 1。成功 2.失败
    private String msg;//响应消息 描述字符串
    private Object data;//返回的数据

    public static Result success(){return new Result(1,"success",null);}
    //增删改相应成功，没有返回的数据
    public static Result success(Object data){return new Result(1,"success",data);}
    //查询响应成功，需要返回数据信息
    public static Result error(String msg){return new Result(0,msg,null);}
    //响应失败
}
