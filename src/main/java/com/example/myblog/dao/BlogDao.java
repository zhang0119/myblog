package com.example.myblog.dao;

import com.example.myblog.entity.Blog;
import com.example.myblog.queryvo.*;
import org.apache.ibatis.annotations.Param;
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

    /*删除博客*/
    void deleteBlog(Long id);

    /*编辑博客*/
    int updateBlog(ShowBlog showBlog);

    /*查询编辑修改的博客*/
    ShowBlog getBlogById(Long id);

    /*搜索博客管理列表*/
    List<BlogQuery> searchByTitleAndType(SearchBlog searchBlog);

    /*-----------博客首页的信息------------------------------------------------------------------*/

    /*查询首页最新博客列表信息*/
    List<FirstPageBlog> getFirstPageBlog();

    /*查询首页最新推荐信息*/
    List<RecommendBlog> getAllRecommendBlog();

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

    /*-----------博客详情页的接口方法------------------------------------------------------------*/
    /*查询博客详情,通过blogId找到整个博客信息*/
    DetailedBlog getDetailedBlog(Long id);

    /*文章访问更新,更新浏览量+1*/
    int updateViews(Long id);

    /*根据博客id查询出评论数量*/
    int getCommentCountById(Long id);

    /*-------分类页面下显示的内容------------------------------------------------------------------*/
    /*根据typeId查询博客列表，显示在分类页面*/
    List<FirstPageBlog> getByTypeId(Long typeId);
}
