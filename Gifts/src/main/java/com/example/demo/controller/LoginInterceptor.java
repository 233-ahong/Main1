package com.example.demo.controller;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//拦截器
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求的URL
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();
        Object userInfo = request.getSession().getAttribute("user");
        if (userInfo == null) { //表示未登录就想进入系统
            //直接重定向到登录界面
            response.sendRedirect(request.getContextPath() + "/admins/log");
            return false;
        } else {
            //登陆成功，不拦截
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}

