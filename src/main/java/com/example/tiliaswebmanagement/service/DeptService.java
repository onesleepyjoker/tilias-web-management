package com.example.tiliaswebmanagement.service;

import com.example.tiliaswebmanagement.pojo.Dept;
import com.example.tiliaswebmanagement.pojo.Emp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeptService {
    //查询全波部门数据
    List<Dept> list();
    //删除部门
    void delete(Integer id);
//新增部门
    void add(Dept dept);

    String getbyid(Integer id);


}
