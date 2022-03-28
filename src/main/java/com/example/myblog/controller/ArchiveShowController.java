package com.example.myblog.controller;

import com.example.myblog.queryvo.BlogQuery;
import com.example.myblog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * author: zy
 * date: 2022/3/27 16:14
 * qq:546359148
 *
 * 留念记页面显示控制器
 */
@Controller
public class ArchiveShowController {

    private BlogService blogService;
    public ArchiveShowController(BlogService blogService){
        this.blogService=blogService;
    }

    /**
     * 跳转到时间轴页面
     */
    @GetMapping("/archives")
    public String archive(Model model){
        List<BlogQuery> blogs = blogService.getAllBlog();
        model.addAttribute("blogs",blogs);
        return "archives";
    }
}
