package com.example.myblog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * author: zy
 * date: 2022/3/17 4:18
 * qq:546359148
 *
 * 登录过滤拦截器
 * 作用:在没有登录的情况下，不能让游客访问到后台管理页面，在这里就需要加一个登录拦截器，
 * 将访问路径给过滤掉，这里就用SpringBoot里面内置的interceptor，
 * 创建LoginInterceptor登录过滤拦截器，实现HandlerInterceptor接口
 * 重写预处理方法，进行拦截
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //判断session里面是否有用户，没有的话重定向到登录页面，给拦截掉
        if(request.getSession().getAttribute("user")==null){
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
