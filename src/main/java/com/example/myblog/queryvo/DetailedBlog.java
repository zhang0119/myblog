package com.example.myblog.queryvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * author: zy
 * date: 2022/3/26 7:26
 * qq:546359148
 *
 * 博客详情实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailedBlog {

    //博客信息
    private Long id;
    private String firstPicture;
    private String flag;
    private String title;
    private String content;

    private Integer views;
    private Integer commentCount;
    private Date updateTime;
    private boolean commentAble;
    private boolean shareStatement;

    private boolean appreciation;
    private String username;
    private String avatar;

    //分类名称
    private String typeName;
}
