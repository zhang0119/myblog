package com.example.myblog.service.impl;

import com.example.myblog.dao.BlogDao;
import com.example.myblog.dao.CommentDao;
import com.example.myblog.entity.Comment;
import com.example.myblog.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author: zy
 * date: 2022/3/26 10:53
 * qq:546359148
 *
 * 博客评论业务层接口实现类
 */
@Service
public class CommentServiceImpl implements CommentService {

    private CommentDao commentDao;
    private BlogDao blogDao;
    public CommentServiceImpl(CommentDao commentDao,
                              BlogDao blogDao){
        this.commentDao = commentDao;
        this.blogDao = blogDao;
    }

    //创建一个list集合容器,存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    /**
     * 通过博客id查询出全部评论
     */
    @Override
    public List<Comment> listCommentByBlogId(long blogId) {
        //查询出所有的父评论
        List<Comment> comments = commentDao.findByBlogIdParentIdNull(blogId, Long.parseLong("-1"));
        for (Comment comment : comments) {
            Long id = comment.getId();
            String parentUsername1 = comment.getUsername();
            List<Comment> childComments = commentDao.findByBlogIdParentIdNotNull(blogId, id);
            //查询出子评论
            combineChildren(blogId,childComments,parentUsername1);
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return comments;
    }

    /**
     * 查询出子评论
     */
    private void combineChildren(Long blogId,List<Comment> childComments,String username){
        //判断是否有子评论
        if(childComments.size() > 0){
            //循环找到子评论的id
            for (Comment childComment : childComments) {
                String parentUsername1 = childComment.getUsername();
                childComment.setParentUsername(parentUsername1);
                tempReplys.add(childComment);
                Long childId = childComment.getId();
                //查询出二级评论
                recursively(blogId,childId,parentUsername1);
            }
        }
    }

    /**
     * 循环迭代找出子集回复
     */
    private void recursively(Long blogId, Long childId,String parentUsername){
        //根据子一级评论的id找到子二级评论
        List<Comment> replayComments = commentDao.findByBlogIdAndReplayId(blogId,childId);

        if(replayComments.size()>0){
            for (Comment replayComment : replayComments) {
                String username = replayComment.getUsername();
                replayComment.setParentUsername(username);
                Long replayId = replayComment.getId();
                tempReplys.add(replayComment);
                /*这里用到的递归回调*/
                recursively(blogId,replayId,parentUsername);
            }
        }

    }

    /**
     * 新增评论
     */
    @Override
    public int saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        int comments = commentDao.saveComment(comment);
        //文章评论计数
        blogDao.getCommentCountById(comment.getBlogId());
        return comments;
    }

    /**
     * 删除评论
     */
    @Override
    public void deleteComment(Comment comment, long id) {
        commentDao.deleteComment(id);
        blogDao.getCommentCountById(comment.getBlogId());
    }
}
