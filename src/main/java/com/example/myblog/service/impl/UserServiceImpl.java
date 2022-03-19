package com.example.myblog.service.impl;

import com.example.myblog.dao.UserDao;
import com.example.myblog.entity.User;
import com.example.myblog.service.UserService;
import com.example.myblog.utils.MD5Util;
import org.springframework.stereotype.Service;

/**
 * author: zy
 * date: 2022/3/17 3:43
 * qq:546359148
 *
 * 用户业务层接口实现类
 */
@Service
public class UserServiceImpl implements UserService {

    public UserDao userDao;
    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public User checkUser(String username, String password) {

        String pwd = MD5Util.code(password);

        return userDao.findByUsernameAndPassword(username, pwd);
    }
}
