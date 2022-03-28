package com.example.myblog.service.impl;

import com.example.myblog.dao.BlogDao;
import com.example.myblog.entity.Blog;
import com.example.myblog.exception.NotFoundException;
import com.example.myblog.queryvo.*;
import com.example.myblog.service.BlogService;
import com.example.myblog.utils.MarkdownUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * author: zy
 * date: 2022/3/19 15:00
 * qq:546359148
 *
 * 博客列表业务层接口实现类
 */
@Service
public class BlogServiceImpl implements BlogService {

    private BlogDao blogDao;
    public BlogServiceImpl(BlogDao blogDao){
        this.blogDao = blogDao;
    }

    /**
     * 保存博客
     * @param blog 待保存的博客内容
     * @return 返回1表示保存成功<br/>0表示保存失败
     *
     * 新增博客中需要初始化创建时间、更新时间、浏览数量、访问数量
     */
    @Override
    @Transactional
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blog.setCommentCount(0);
        return blogDao.saveBlog(blog);
    }

    /**
     * 查询全部博客
     * @return 返回一个BlogQuery类型的List列表
     */
    @Override
    public List<BlogQuery> getAllBlog() {
        return blogDao.getAllBlogQuery();
    }

    /**
     * 删除博客
     * @param id 待删除的博客id
     */
    @Override
    @Transactional
    public void deleteBlog(Long id) {
        blogDao.deleteBlog(id);
    }

    /**
     * 通过id查询博客内容
     * @param id 待编辑的博客id
     * @return 返回查询到的博客内容
     */
    @Override
    public ShowBlog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    /**
     * 编辑修改文章
     * @param showBlog 待展示的数据
     * @return 返回1表示修改成功<br/>0表示修改失败
     */
    @Override
    @Transactional
    public int updateBlog(ShowBlog showBlog) {
        showBlog.setUpdateTime(new Date());
        return blogDao.updateBlog(showBlog);
    }

    /**
     * 搜索博客管理列表
     * @return 返回查询到的博客管理列表
     */
    @Override
    public List<BlogQuery> getBlogBySearch(SearchBlog searchBLog) {
        return blogDao.searchByTitleAndType(searchBLog);
    }

    /**
     * 得到所有首页推荐的博客
     * @return 返回得到第一页的所有博客
     */
    @Override
    public List<FirstPageBlog> getAllFirstPageBlog() {
        return blogDao.getFirstPageBlog();
    }

    /**
     * 得到推荐的博客
     */
    @Override
    public List<RecommendBlog> getRecommendedBlog() {
        return blogDao.getAllRecommendBlog();
    }

    /**
     * 搜索博客
     */
    @Override
    public List<FirstPageBlog> getSearchBlog(String query) {
        return blogDao.getSearchBlog(query);
    }

    /**
     * 得到全部的博客
     */
    @Override
    public Integer getBlogTotal() {
        return blogDao.getBlogTotal();
    }

    /**
     * 得到博客的浏览量
     */
    @Override
    public Integer getBlogViewTotal() {
        return blogDao.getBlogViewTotal();
    }

    /**
     * 得到博客的全部评论
     */
    @Override
    public Integer getBlogCommentTotal() {
        return blogDao.getBlogCommentTotal();
    }

    /**
     * 得到博客的全部消息
     */
    @Override
    public Integer getBlogMessageTotal() {
        return blogDao.getBlogMessageTotal();
    }

    /**
     * 得到博客详情页的信息
     */
    @Override
    @Transactional
    public DetailedBlog getDetailedBlog(Long id) {
        DetailedBlog detailedBlog = blogDao.getDetailedBlog(id);
        if(detailedBlog == null){
            throw new NotFoundException("该博客不存在");
        }

        String content = detailedBlog.getContent();
        //将string文本内容转变成markdown格式
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        //文章访问数量自增
        blogDao.updateViews(id);
        //文章评论数量更新
        blogDao.getCommentCountById(id);
        return detailedBlog;
    }

    /**
     * 分类页面查询
     */
    @Override
    public List<FirstPageBlog> getByTypeId(Long typeId) {
        return blogDao.getByTypeId(typeId);
    }
}
