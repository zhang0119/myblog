package com.example.myblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author: zy
 * date: 2022/3/17 4:33
 * qq:546359148
 *
 * 博客实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    /*主键*/
    private Long id;
    /*博客标题*/
    private String title;
    /*博客内容*/
    private String content;
    /*博客首图*/
    private String firstPicture;
    /*博客标记*/
    private String flag;
    /*浏览量*/
    private Integer views;
    /*评论数*/
    private Integer commentCount;
    /*赞赏*/
    private boolean appreciation;
    /**/
    private boolean shareStatement;
    /*是否评论*/
    private boolean commentAble;
    /*是否发表*/
    private boolean published;
    /*是否推荐*/
    private boolean recommend;
    /*博客创建时间*/
    private Date createTime;
    /*博客更新时间*/
    private Date updateTime;
    /*博客描述*/
    private String description;

    private Type type;
    private User user;
    private Long typeId;
    private Long userId;
    private List<Comment> comments = new ArrayList<>();
}
