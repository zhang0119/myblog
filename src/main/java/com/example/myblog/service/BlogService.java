package com.example.myblog.service;

import com.example.myblog.entity.Blog;
import com.example.myblog.queryvo.BlogQuery;
import com.example.myblog.queryvo.SearchBlog;
import com.example.myblog.queryvo.ShowBlog;

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

    /*删除博客*/
    void deleteBlog(Long id);

    /*查询编辑修改的博客*/
    ShowBlog getBlogById(Long id);

    /*编辑修改文章*/
    int updateBlog(ShowBlog showBlog);

    /*搜索博客管理列表*/
    List<BlogQuery> getBlogBySearch(SearchBlog searchBlog);
}
