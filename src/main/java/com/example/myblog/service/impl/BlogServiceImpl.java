package com.example.myblog.service.impl;

import com.example.myblog.dao.BlogDao;
import com.example.myblog.entity.Blog;
import com.example.myblog.queryvo.BlogQuery;
import com.example.myblog.service.BlogService;
import org.springframework.stereotype.Service;

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
}
