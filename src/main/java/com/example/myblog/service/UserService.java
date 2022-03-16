package com.example.myblog.service;

import com.example.myblog.entity.User;

/**
 * author: zy
 * date: 2022/3/17 3:42
 * qq:546359148
 *
 * 用户业务层接口
 */
public interface UserService {

    //核对用户名和密码
    User checkUser(String username,String password);
}
