package com.example.myblog.dao;

import com.example.myblog.entity.Blog;
import com.example.myblog.queryvo.BlogQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author: zy
 * date: 2022/3/19 14:58
 * qq:546359148
 *
 * 博客管理持久层接口
 */
@Repository
public interface BlogDao {

    /*新增博客*/
    int saveBlog(Blog blog);

    /*查询博客管理列表*/
    List<BlogQuery> getAllBlogQuery();

}
