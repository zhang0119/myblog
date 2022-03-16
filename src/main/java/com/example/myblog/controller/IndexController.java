package com.example.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * author: zy
 * date: 2022/3/17 2:10
 * qq:546359148
 *
 * 首页控制器
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){

        return "index";
    }
}
