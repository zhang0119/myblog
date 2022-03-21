package com.example.myblog.service;

import com.example.myblog.entity.Blog;
import com.example.myblog.queryvo.BlogQuery;

import java.util.List;

/**
 * author: zy
 * date: 2022/3/19 15:00
 * qq:546359148
 *
 * 博客列表业务层接口
 */
public interface BlogService {

    /*新增博客*/
    int saveBlog(Blog blog);

    /*查询文章管理列表*/
    List<BlogQuery> getAllBlog();
}
