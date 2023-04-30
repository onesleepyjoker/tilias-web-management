package com.example.tiliaswebmanagement.cnotroller;

import com.example.tiliaswebmanagement.pojo.Emp;
import com.example.tiliaswebmanagement.pojo.Result;
import com.example.tiliaswebmanagement.service.EmpService;
import com.example.tiliaswebmanagement.utils.JwtUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("员工登录：{}",emp);
        Emp e=empService.login(emp);

        //如果成功则生成令牌下发令牌
        if(e!=null){
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",e.getId());
            claims.put("name",e.getName());
            claims.put("username",e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);

        }
        //否则登陆失败返回失败信息
        return Result.error("用户名或密码错误");
    }

}
