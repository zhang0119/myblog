package com.example.myblog.service;

import com.example.myblog.entity.Comment;

import java.util.List;

/**
 * author: zy
 * date: 2022/3/26 10:48
 * qq:546359148
 *
 * 评论业务层接口
 */
public interface CommentService {

    /*根据博客id查询评论信息*/
    List<Comment> listCommentByBlogId(long blogId);

    /*添加保存评论*/
    int saveComment(Comment comment);

    /*删除评论*/
    void deleteComment(Comment comment,long id);
}
