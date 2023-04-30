package com.example.tiliaswebmanagement.cnotroller;

import com.example.tiliaswebmanagement.aop.MyLog;
import com.example.tiliaswebmanagement.pojo.Emp;
import com.example.tiliaswebmanagement.pojo.Result;
import com.example.tiliaswebmanagement.pojo.pagebean;
import com.example.tiliaswebmanagement.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class Empcontroller {

    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1")Integer page,
                       @RequestParam(defaultValue = "10")Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
        log.info("分页查询，参数：{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);

        pagebean pagebean= empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pagebean);


    }

    @DeleteMapping("/{ids}")
    @MyLog
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除操作，ids{}",ids);
        empService.delete(ids);
        return Result.success();
    }
    @PostMapping
    @MyLog
    public Result save(@RequestBody Emp emp){
        log.info("新增员工，emp:{}",emp);
        empService.save(emp);
        return Result.success();
    }


}
