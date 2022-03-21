package com.example.myblog.controller.admin;

import com.example.myblog.entity.Blog;
import com.example.myblog.entity.User;
import com.example.myblog.queryvo.BlogQuery;
import com.example.myblog.service.BlogService;
import com.example.myblog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * author: zy
 * date: 2022/3/19 15:01
 * qq:546359148
 *
 * 博客管理控制器
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    private BlogService blogService;
    private TypeService typeService;
    public BlogController(BlogService blogService,
                          TypeService typeService){
        this.blogService=blogService;
        this.typeService=typeService;
    }

    /**
     * 跳转到新增博客页
     * @param model 存放数据的模型对象
     * @return 返回到新增博客页
     */
    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("blog",new Blog());
        return "admin/blogs-input";
    }

    /**
     * 跳转到博客列表页
     * @param model 存放数据的模型
     * @param pageNum 页码
     * @return 返回到博客列表页
     */
    @GetMapping("/blogs")
    public String blogs(Model model,
                        @RequestParam(defaultValue = "1",value="pageNum")Integer pageNum){
        //按照排序字段倒序
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum,10,orderBy);
        List<BlogQuery> list = blogService.getAllBlog();
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(list);
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogs";
    }

    /**
     * 新增博客
     * @param blog 新增博客的对象
     * @param attributes 属性对象
     * @param session session对象
     * @return 返回到新增博客页
     */
    @PostMapping("/blogs")
    public String post(Blog blog,RedirectAttributes attributes,HttpSession session){
        //新增的时候需要传递blog对象，blog对象需要有user
        blog.setUser((User) session.getAttribute("user"));
        //设置blog的类型
        blog.setType(typeService.getType(blog.getType().getId()));
        //设置blog中的typeId属性
        blog.setTypeId(blog.getType().getId());
        //设置用户id
        blog.setUserId(blog.getUser().getId());
        int b = blogService.saveBlog(blog);

        if(b == 0) {
            attributes.addFlashAttribute("message", "新增失败");
        }else{
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/blogs";
    }

    //删除
}
