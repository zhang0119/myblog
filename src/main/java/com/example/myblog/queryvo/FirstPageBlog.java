package com.example.myblog.queryvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * author: zy
 * date: 2022/3/23 3:29
 * qq:546359148
 *
 * 首页博客信息实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FirstPageBlog {

    //博客信息
    private Long id;
    private String title;
    private String firstPicture;
    private Integer views;
    private Integer commentCount;
    private Date createTime;
    private Date updateTime;
    private String description;

    //分类名称
    private String typeName;

    //用户名
    private String username;
    //用户头像
    private String avatar;
}
