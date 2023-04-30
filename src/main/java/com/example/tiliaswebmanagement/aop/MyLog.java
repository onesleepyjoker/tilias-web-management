package com.example.tiliaswebmanagement.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//运行时生效
@Target({ElementType.METHOD})//作用在方法上面    还可以最用在类上面
public @interface MyLog {
}
