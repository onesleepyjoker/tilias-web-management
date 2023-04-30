package com.example.tiliaswebmanagement.mapper;

import com.example.tiliaswebmanagement.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
//    //查询总记录数
//    @Select("select count(*) from emp")
//    public Long count();
//
//    //分页查询获得列表数据
//    @Select("select * from emp limit #{start},#{pagesize}")
//    public List<Emp> page(Integer start,Integer pagesize);


    //查询操作，因为数据过多可以通过插件自动进行分页操作
    //@Select("select * from emp")
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);

    @Insert("insert into emp( username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp getbyusernameandpassword(Emp emp);

}
