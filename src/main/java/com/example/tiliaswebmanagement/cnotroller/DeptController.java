package com.example.tiliaswebmanagement.cnotroller;

import com.example.tiliaswebmanagement.aop.MyLog;
import com.example.tiliaswebmanagement.pojo.Dept;
import com.example.tiliaswebmanagement.pojo.Emp;
import com.example.tiliaswebmanagement.pojo.Result;
import com.example.tiliaswebmanagement.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j//生成日志注解
@RestController//可以将result对象自动转换成json格式的数据返回到前端页面当中
@RequestMapping("/depts")//可以将下面共有的/depts代码抽取到上面下面就可以省略了
public class DeptController {
    @Autowired
    private DeptService deptService; //面向接口编程  自动注入deptservice接口的对象，使用接口功能
//每一层都需要使用上一层的接口功能来实现操作，所以mapper层只有接口
    //private static Logger log=LoggerFactory.getLogger(DeptController.class);
    //@RequestMapping(value = "/depts",method = RequestMethod.GET)//链接网页的地址注解 请求方式是get

    //查询部门全部数据
    @GetMapping//只能接受通过get的方式发送请求 相当于上面的方式
    public Result list() {
        log.info("查询全部部门的数据");
        //调用服务层的接口方法 完成需要的操作并且return给服务器页面
        List<Dept> deptList=deptService.list();
        return Result.success(deptList);
    }

    //删除部门
    @MyLog
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门：{}",id);
        deptService.delete(id);
        return Result.success();
    }
    //新增部门
    @MyLog
    @PostMapping//根据需求文档确定前端页面传过来的数据是什么格式的用什么对象去接收
    public Result add(@RequestBody Dept dept){
        //用方法的参数取接受数据，json数据用类去封装同时使用@requestbody注释来解析数据封装到dept类对象中，key与属性名对应
        //如果多出来的属性名则为null，同时在服务层设置null的地方传给mapper层与数据库进行对接
        log.info("新增部门：{}",dept);
        deptService.add(dept);
        return Result.success();

    }
    //根据id查询部门
    @GetMapping("/{id}")
    public Result getbyid(@PathVariable Integer id){
        log.info("查询到部门id：{}",id);
        String name=deptService.getbyid(id);
        return Result.success(name);
    }


}
