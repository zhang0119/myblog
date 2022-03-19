package com.example.myblog.dao;

import com.example.myblog.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * author: zy
 * date: 2022/3/17 3:24
 * qq:546359148
 *
 * 用户持久层接口
 * *@Mapper注解： 让Mybatis找到对应的mapper，在编译的时候动态生成代理类，实现相应SQL功能
 */
@Repository
public interface UserDao {

    User findByUsernameAndPassword(@Param("username")String username, @Param("password")String pwd);
}
