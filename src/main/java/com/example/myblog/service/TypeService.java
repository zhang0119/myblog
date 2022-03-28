package com.example.myblog.service;

import com.example.myblog.entity.Type;

import java.util.List;

/**
 * author: zy
 * date: 2022/3/17 23:33
 * qq:546359148
 *
 * 分类业务接口
 */
public interface TypeService {

    /*新增保存分类*/
    int saveType(Type type);

    /*根据id查询分类*/
    Type getType(Long id);

    /*查询所有分类*/
    List<Type> getAllType();

    /*根据分类名称查询分类*/
    Type getTypeByName(String name);

    /*编辑修改分类*/
    int updateType(Type type);

    /*删除分类*/
    void deleteType(Long id);

    /*-----分类页面下调用的接口----------------------------------------------*/
    //查询所有分类
    List<Type> getAllTypeAndBlog();
}
