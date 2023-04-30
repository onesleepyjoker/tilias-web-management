package com.example.tiliaswebmanagement.service.impl;

import com.example.tiliaswebmanagement.mapper.EmpMapper;
import com.example.tiliaswebmanagement.pojo.Emp;
import com.example.tiliaswebmanagement.pojo.pagebean;
import com.example.tiliaswebmanagement.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServicelmpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public pagebean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
////        1.获取总记录数
//        Long count=empMapper.count();
////        2.获取分页查询结果列表
//        Integer start=(page -1)*pageSize;
//        List<Emp> empList = empMapper.page(start, pageSize);
//        1.设置分页参数

        PageHelper.startPage(page,pageSize);

//        2.执行查询
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        Page<Emp> p= (Page<Emp>) empList;
//        3.分装pagebean对象返回到controller当中
        pagebean pagebean=new pagebean(p.getTotal(),p.getResult());
        return pagebean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getbyusernameandpassword(emp);

    }
}
