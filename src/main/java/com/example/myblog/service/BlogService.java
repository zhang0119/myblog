package com.example.myblog.service;

import com.example.myblog.entity.Blog;
import com.example.myblog.queryvo.*;

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

    /*-------------------------------------------------------------*/
    /*查询首页最新博客列表信息*/
    List<FirstPageBlog> getAllFirstPageBlog();

    /*查询首页最新推荐消息*/
    List<RecommendBlog> getRecommendedBlog();

    /*搜索博客列表*/
    List<FirstPageBlog> getSearchBlog(String query);

    /*统计博客总数*/
    Integer getBlogTotal();

    /*统计访问总数*/
    Integer getBlogViewTotal();

    /*统计评论总数*/
    Integer getBlogCommentTotal();

    /*统计留言总数*/
    Integer getBlogMessageTotal();
}
