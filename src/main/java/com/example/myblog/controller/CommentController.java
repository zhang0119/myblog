package com.example.myblog.controller;

import com.example.myblog.entity.Comment;
import com.example.myblog.entity.User;
import com.example.myblog.queryvo.DetailedBlog;
import com.example.myblog.service.BlogService;
import com.example.myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * author: zy
 * date: 2022/3/26 13:31
 * qq:546359148
 *
 * 评论控制器
 */
@Controller
public class CommentController {

    private CommentService commentService;
    private BlogService blogService;
    public CommentController(CommentService commentService,
                             BlogService blogService){
        this.commentService = commentService;
        this.blogService = blogService;
    }

    @Value("${comment.avatar}")
    private String avatar;

    /**
     * 查询评论列表
     * 查询评论列表：调用接口查询评论信息列表，局部刷新评论信息
     *
     */
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable("blogId")Long blogId, Model model){
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments",comments);
        /*把comments的数据发送给blog页面中id为commentList的区域,实现局部刷新评论信息*/
        return "blog :: commentList";
    }

    /**
     * 新增评论
     *
     * 对评论进行判断，区分游客和管理员
     */
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session, Model model){
        Long blogId = comment.getBlogId();
        User user = (User) session.getAttribute("user");
        if(user!=null){
            /*用户不为空代表是管理员*/
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else{
            //设置头像
            /*用户为空，代表是普通访客*/
            comment.setAvatar(avatar);
        }
        if(comment.getParentComment().getId()!=null){
            comment.setParentCommentId(comment.getParentComment().getId());
        }
        commentService.saveComment(comment);
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments",comment);
        return "blog :: commentList";
    }

    /**
     * 删除评论
     *
     * 删除评论：将博客id和评论id参数传入，判断删除的是哪一条评论，
     * 这里没有做迭代删除子评论，若删除了含有回复的评论，
     * 根据之前的查询来看，在前端回复也不会查询出来，
     * 但回复并没有删除，依然在数据库里面，删除的只是父评论
     *
     */
    @GetMapping("/comment/{blogId}/{id}/delete")
    public String delete(@PathVariable("blogId")Long blogId, @PathVariable("id")Long id,
                         Comment comment, Model model){
        commentService.deleteComment(comment,id);
        DetailedBlog detailedBlog = blogService.getDetailedBlog(blogId);
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("blog",detailedBlog);
        model.addAttribute("comments",comments);
        return "blog";
    }
}
