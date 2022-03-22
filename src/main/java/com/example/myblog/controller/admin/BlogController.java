package com.example.myblog.controller.admin;

import com.example.myblog.entity.Blog;
import com.example.myblog.entity.Type;
import com.example.myblog.entity.User;
import com.example.myblog.queryvo.BlogQuery;
import com.example.myblog.queryvo.SearchBlog;
import com.example.myblog.queryvo.ShowBlog;
import com.example.myblog.service.BlogService;
import com.example.myblog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

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

    /**
     * 删除博客
     * @param id 待删除博客的id
     * @param attributes 重定向的属性
     * @return 返回到blogs页面
     */
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/blogs";
    }

    /**
     * 编辑博客
     * @param id 待编辑的博客id
     * @param model 存放数据的model对象
     * @return 返回到blogs-input页面
     */
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable("id") Long id,Model model){
        ShowBlog blogById = blogService.getBlogById(id);
        List<Type> allType = typeService.getAllType();
        model.addAttribute("blog",blogById);
        model.addAttribute("types",allType);
        return "admin/blogs-input";
    }

    /**
     * 编辑修改博客内容,将编辑好的内容重新保存，使用post请求方式
     * @param showBlog blog显示的博客内容
     * @param attributes 重定向携带的内容
     * @return 返回到blogs页面
     */
    @PostMapping("/blogs/{id}")
    public String editPost(@Valid ShowBlog showBlog,RedirectAttributes attributes){
        int b = blogService.updateBlog(showBlog);
        if(b == 0){
            attributes.addFlashAttribute("message","修改失败!");
        }else{
            attributes.addFlashAttribute("message","修改成功!");
        }
        return "redirect:/admin/blogs";
    }

    /**
     * 通过类型名或者标题关键字来搜索
     * @param model 存储数据的模型对象
     * @param pageNum 当前页码
     * @return 跳转到blogs页面
     */
    @PostMapping("/blogs/search")
    public String search(SearchBlog searchBlog,
                         Model model,
                         @RequestParam(defaultValue = "1",value="pageNum")Integer pageNum){
        List<BlogQuery> blogBySearch = blogService.getBlogBySearch(searchBlog);
        PageHelper.startPage(pageNum,10);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogBySearch);
        model.addAttribute("pageInfo",pageInfo);
        /*::这是thymeleaf的一个模板片断，相当于返回admin/blogs模板中的某个片段*/
        return "admin/blogs :: blogList";
    }

}
