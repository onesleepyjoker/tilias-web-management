package com.example.tiliaswebmanagement;

import com.example.tiliaswebmanagement.cnotroller.DeptController;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.security.PrivateKey;
import java.util.Date;
import java.util.HashMap;

@SpringBootTest
class TiliasWebManagementApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private DeptController deptController2;

    @Test
    public void testgetbean(){
        DeptController deptController = (DeptController) applicationContext.getBean("deptController");
        System.out.println(deptController);//根据名字获取bean
        System.out.println(deptController2);

    }


}
