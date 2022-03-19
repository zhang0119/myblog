package com.example.myblog.dao;

import com.example.myblog.entity.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author: zy
 * date: 2022/3/17 19:42
 * qq:546359148
 *
 * 分类持久层接口
 */
@Repository
public interface TypeDao {

    /*新增保存分类*/
    int saveType(Type type);

    /*根据id查询分类*/
    Type getType(Long id);

    /*查询所有分类*/
    List<Type> getAllType();

    /*根据分类名称查找分类*/
    Type getTypeByName(String name);

    /*编辑修改分类*/
    int updateType(Type type);

    /*删除分类*/
    void deletedType(Long id);

}
