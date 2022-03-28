package com.example.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * author: zy
 * date: 2022/3/28 15:32
 * qq:546359148
 *
 * 关于我界面显示控制器
 */
@Controller
public class AboutShowController {

    /**
     * 跳转到关于我的页面
     */
    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
