package com.example.myblog.dao;

import com.example.myblog.entity.Picture;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author: zy
 * date: 2022/3/23 0:57
 * qq:546359148
 *
 * 相册映射接口
 */
@Repository
public interface PictureDao {

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
