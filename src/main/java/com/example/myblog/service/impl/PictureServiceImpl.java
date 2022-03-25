package com.example.myblog.service.impl;

import com.example.myblog.dao.PictureDao;
import com.example.myblog.entity.Picture;
import com.example.myblog.service.PictureService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: zy
 * date: 2022/3/23 1:16
 * qq:546359148
 */
@Service
public class PictureServiceImpl implements PictureService {

    private PictureDao pictureDao;
    public PictureServiceImpl(PictureDao pictureDao){
        this.pictureDao=pictureDao;
    }
    @Override
    public List<Picture> listPicture() {
        return pictureDao.listPicture();
    }

    @Override
    public int savePicture(Picture picture) {
        return pictureDao.savePicture(picture);
    }

    @Override
    public Picture getPicture(Long id) {
        return pictureDao.getPicture(id);
    }

    @Override
    public int updatePicture(Picture picture) {
        return pictureDao.updatePicture(picture);
    }

    @Override
    public void deletePicture(Long id) {
        pictureDao.deletePicture(id);
    }
}
