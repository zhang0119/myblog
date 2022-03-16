package com.example.myblog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * author: zy
 * date: 2022/3/17 2:24
 * qq:546359148
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(){

    }

    public NotFoundException(String message){
        super(message);
    }

    public NotFoundException(String message,Throwable throwable){
        super(message,throwable);
    }
}
/*
    继承RuntimeException，实现继承RuntimeException的构造函数
    @ResponseStatus(HttpStatus.NOT_FOUND)注解表示资源找不到的状态码，标识了状态码的时候就不拦截
 */