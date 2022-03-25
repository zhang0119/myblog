package com.example.myblog.service;

import com.example.myblog.entity.Picture;

import java.util.List;

/**
 * author: zy
 * date: 2022/3/23 1:12
 * qq:546359148
 *
 * 照片墙业务层接口
 */
public interface PictureService {

    /*查询照片*/
    List<Picture> listPicture();

    /*添加照片*/
    int savePicture(Picture picture);

    /*根据id查询照片*/
    Picture getPicture(Long id);

    /*编辑修改相册*/
    int updatePicture(Picture picture);

    /*删除照片*/
    void deletePicture(Long id);
}
