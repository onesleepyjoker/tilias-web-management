package com.example.tiliaswebmanagement.mapper;

import com.example.tiliaswebmanagement.pojo.Dept;
import com.example.tiliaswebmanagement.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
    //查询全部的部门数据 数据库直接接口
    @Select("select *from dept")
    List<Dept> list();

    @Delete("delete from dept where id =#{id}")
    void deletebyid(Integer id);

    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    @Select("select * from dept where id = #{id}")
    Emp getemp(Integer id);

    @Delete("delete from emp where dept_id=#{deptid}")
    void deletebydeptid(Integer deptid);
}
