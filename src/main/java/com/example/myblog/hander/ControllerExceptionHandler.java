package com.example.myblog.hander;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * author: zy
 * date: 2022/3/17 2:13
 * qq:546359148
 *
 * 拦截异常处理
 */
@ControllerAdvice  //ControllerAdvice本质上是一个Component，因此也会被当成组建扫描
public class ControllerExceptionHandler {

    //将异常记录到日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {

        //记录异常信息：请求的uri,异常信息
        logger.error("Request URL: {},Exception: {}",request.getRequestURL(),e);

        //当表示了状态码后就不拦截了
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;
        }

        //将记录的异常信息返回给error页面
        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);
        mv.setViewName("error/error");
        return mv;
    }
}
/*
    @ControllerAdvice表示拦截掉所有带有@Controller注解的控制器
    @ExceptionHandler表明是异常处理方法
    ModelAndView：返回一个页面信息
    通过拦截异常信息，在日志中记录，并返回给error页面
    标识了状态码的时候就不拦截，如资源找不到异常
*/
