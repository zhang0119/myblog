package com.example.myblog.service.impl;

import com.example.myblog.dao.BlogDao;
import com.example.myblog.entity.Blog;
import com.example.myblog.queryvo.BlogQuery;
import com.example.myblog.queryvo.SearchBlog;
import com.example.myblog.queryvo.ShowBlog;
import com.example.myblog.service.BlogService;
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
}
