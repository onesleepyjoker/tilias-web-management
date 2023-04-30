package com.example.tiliaswebmanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept {
    private Integer id; //部门id
    private String name;//部门名称
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更改时间

}
