package com.example.myblog.queryvo;

import com.example.myblog.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * author: zy
 * date: 2022/3/19 15:05
 * qq:546359148
 *
 * 查询博客列表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogQuery {

    /*主键*/
    private Long id;
    /*标题*/
    private String title;
    /*更新时间*/
    private Date updateTime;
    /*是否被推荐*/
    private Boolean recommend;
    /*是否发表*/
    private Boolean published;
    /*分类id*/
    private Long typeId;
    /*分类*/
    private Type type;
}
