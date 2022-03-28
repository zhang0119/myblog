package com.example.myblog.controller;

import com.example.myblog.entity.Type;
import com.example.myblog.queryvo.FirstPageBlog;
import com.example.myblog.service.BlogService;
import com.example.myblog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * author: zy
 * date: 2022/3/26 21:49
 * qq:546359148
 *
 * 分类页面控制器
 */
@Controller
public class TypeShowController {

    private TypeService typeService;
    private BlogService blogService;
    public TypeShowController(TypeService typeService,
                              BlogService blogService){
        this.typeService=typeService;
        this.blogService=blogService;
    }

    /**
     * 分页查询分类
     *
     * {id}:当id为-1时，表示从首页导航栏进入分类页面,默认第一个分类显示颜色
     * getAllTypeAndBlog: 查询分类名称和博客信息，前端统计出该分类下博客的数量
     * getByTypeId:查询博客列表
     */
    @GetMapping("/types/{id}")
    public String types(@RequestParam(defaultValue = "1",value="pageNum")Integer pageNum,
                        @PathVariable("id") Long id,
                        Model model){
        List<Type> types = typeService.getAllTypeAndBlog();

        //id为-1表示从首页导航栏点击进入分类页面
        if(id == -1){
            id = types.get(0).getId();
        }
        model.addAttribute("types",types);
        List<FirstPageBlog> blogs = blogService.getByTypeId(id);

        PageHelper.startPage(pageNum,10);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(blogs);

        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTypeId",id);
        return "types";
    }
}
