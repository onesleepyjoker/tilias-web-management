package com.example.tiliaswebmanagement.service.impl;


import com.example.tiliaswebmanagement.aop.MyLog;
import com.example.tiliaswebmanagement.mapper.DeptMapper;
import com.example.tiliaswebmanagement.pojo.Dept;
import com.example.tiliaswebmanagement.pojo.Emp;
import com.example.tiliaswebmanagement.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServicelmpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;//服务层不能操作数据所以需要注入数据库的接口功能


    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    //删除部门
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    @Override
    public void delete(Integer id) {
        deptMapper.deletebyid(id);
//        int i=1;
//        System.out.println(i=1/0);
        deptMapper.deletebydeptid(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public String getbyid(Integer id) {
        Emp emp=deptMapper.getemp(id);
        return emp.getName();
    }
}
