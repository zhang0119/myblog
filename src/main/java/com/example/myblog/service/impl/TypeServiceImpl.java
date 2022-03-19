package com.example.myblog.service.impl;

import com.example.myblog.dao.TypeDao;
import com.example.myblog.entity.Type;
import com.example.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * author: zy
 * date: 2022/3/17 23:37
 * qq:546359148
 *
 * 分类业务层接口实现类
 */
@Service
public class TypeServiceImpl implements TypeService {

    private TypeDao typeDao;
    public TypeServiceImpl(TypeDao typeDao){
        this.typeDao=typeDao;
    }

    @Override
    @Transactional
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }

    @Override
    @Transactional
    public Type getType(Long id) {
        return typeDao.getType(id);
    }

    @Override
    @Transactional
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }

    @Override
    @Transactional
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Override
    @Transactional
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }

    @Override
    @Transactional
    public void deleteType(Long id) {
        typeDao.deletedType(id);
    }
}
