package com.example.myblog.dao;

import com.example.myblog.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author: zy
 * date: 2022/3/26 9:54
 * qq:546359148
 *
 * 评论持久层接口
 */
@Repository
public interface CommentDao {

    /*查询父级评论*/
    List<Comment> findByBlogIdParentIdNull(@Param("blogId")Long blogId,@Param("blogParentId")Long blogParentId);

    /*查询一级回复*/
    List<Comment> findByBlogIdParentIdNotNull(@Param("blogId")Long blogId,@Param("id")Long id);

    /*查询二级回复*/
    List<Comment> findByBlogIdAndReplayId(@Param("blogId")Long blogId,@Param("childId")Long childId);

    /*添加一个评论*/
    int saveComment(Comment comment);

    /*删除评论*/
    void deleteComment(Long id);

}
