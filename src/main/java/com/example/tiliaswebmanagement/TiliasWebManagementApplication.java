package com.example.tiliaswebmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan//开启了servlet的组件的支持
@SpringBootApplication
public class
TiliasWebManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiliasWebManagementApplication.class, args);
    }

}
