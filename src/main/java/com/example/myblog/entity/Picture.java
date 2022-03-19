package com.example.myblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author: zy
 * date: 2022/3/17 15:58
 * qq:546359148
 *
 * 相册实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Picture {

    private Long id;
    private String pictureName;
    private String pictureTime;
    private String pictureAddress;
    private String pictureDescription;
}
