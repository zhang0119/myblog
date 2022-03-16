package com.example.myblog.controller.admin;

import com.example.myblog.entity.User;
import com.example.myblog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * author: zy
 * date: 2022/3/17 3:49
 * qq:546359148
 *
 * 用户登录控制器
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    private UserService userService;
    public LoginController(UserService userService){
        this.userService = userService;
    }

    /**
     * 跳转到登录页面
     * @return 返回到登录页
     */
    @GetMapping()
    public String loginPage(){
        return "admin/login";
    }

    /**
     * 登录校验
     * @param username 用户名
     * @param password 密码
     * @param session session对象
     * @param attributes 返回页面的消息
     * @return 登录成功跳转到成功页面，登录失败跳转到登录页面
     */
    @PostMapping("/login")
    public String login(@RequestParam("username")String username,
                        @RequestParam("password")String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username, password);

        if(user!=null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else{
            attributes.addFlashAttribute("message","用户名和密码错误!");
            return "redirect:/admin";
        }
    }

    /**
     * 注销用户
     * @param session session域对象
     * @return 返回到登录页面
     */
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
