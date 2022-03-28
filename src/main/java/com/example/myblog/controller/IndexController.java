package com.example.myblog.controller;

import com.example.myblog.queryvo.DetailedBlog;
import com.example.myblog.queryvo.FirstPageBlog;
import com.example.myblog.queryvo.RecommendBlog;
import com.example.myblog.service.BlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: zy
 * date: 2022/3/17 2:10
 * qq:546359148
 *
 * 首页控制器
 */
@Slf4j
@Controller
public class IndexController {

    private BlogService blogService;
    public IndexController(BlogService blogService){
        this.blogService=blogService;
    }

    /**
     * 分页查询博客列表
     */
    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(defaultValue = "1",value="pageNum")Integer pageNum,
                        RedirectAttributes attributes){
        PageHelper.startPage(pageNum,10);
        List<FirstPageBlog> allFirstPageBlog = blogService.getAllFirstPageBlog();
        List<RecommendBlog> recommendedBlog = blogService.getRecommendedBlog();

        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(allFirstPageBlog);
        log.info("pageInfo:{}",pageInfo);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("recommendedBlog",recommendedBlog);

        return "index";
    }

    /**
     * 搜索博客
     */
    @PostMapping("/search")
    public String search(Model model,
                         @RequestParam(defaultValue = "1",value="pageNum")Integer pageNum,
                         @RequestParam("query") String query){
        PageHelper.startPage(pageNum,10);
        List<FirstPageBlog> searchBlog = blogService.getSearchBlog(query);

        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("query",query);
        return "search";
    }

    /**
     * 博客信息统计
     */
    @GetMapping("/footer/blogmessage")
    public String blogMessage(Model model){
        /*博客总数*/
        Integer blogTotal = blogService.getBlogTotal();
        /*博客的总浏览量*/
        Integer blogViewTotal = blogService.getBlogViewTotal();
        /*博客的总评论数*/
        Integer blogCommentTotal = blogService.getBlogCommentTotal();
        /*博客的总消息数*/
        Integer blogMessageTotal = blogService.getBlogMessageTotal();

        model.addAttribute("blogTotal",blogTotal);
        model.addAttribute("blogViewTotal",blogViewTotal);
        model.addAttribute("blogCommentTotal",blogCommentTotal);
        model.addAttribute("blogMessageTotal",blogMessageTotal);

        /*这里指返回index页面的id为“blogMessage”部分的页面*/
        return "index :: blogMessage";
    }

    /**
     * 跳转到博客详情页
     */
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable("id")Long id,Model model){

        DetailedBlog detailedBlog = blogService.getDetailedBlog(id);
        model.addAttribute("blog",detailedBlog);

        return "blog";
    }

}
