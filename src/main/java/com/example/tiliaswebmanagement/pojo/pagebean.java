package com.example.tiliaswebmanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//分页查询结果的包装类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class pagebean {
    private Long total;//总记录数
    private List rows;//数据列表
}
