package com.example.tiliaswebmanagement.service;

import com.example.tiliaswebmanagement.pojo.Emp;
import com.example.tiliaswebmanagement.pojo.pagebean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service//交给ioc容器管理成为bean对象
public interface EmpService {

    pagebean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin,LocalDate end);

    void delete(List<Integer> ids);

    void save(Emp emp);

    Emp login(Emp emp);
}
