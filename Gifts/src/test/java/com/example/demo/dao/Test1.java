package com.example.demo.dao;

import com.example.demo.domain.Admin;
import com.example.demo.domain.LogUser;
import com.example.demo.service.IAdminService;
import com.example.demo.service.ILoginService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.UUID;

public class Test1 {
    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IAdminService adminServiceImpl = (IAdminService) context.getBean("AdminServiceImpl");
        for (Admin admin : adminServiceImpl.findAll()) {
            System.out.println(admin);
        }
    }

    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IAdminService adminServiceImpl = (IAdminService) context.getBean("AdminServiceImpl");
        Admin admin = adminServiceImpl.findAdmin("123", "11");
        System.out.println(admin);
    }

    @Test
    public void test3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ILoginService loginService = (ILoginService) context.getBean("LoginServiceImpl");
        LogUser user = new LogUser();
        user.setUserId(UUID.randomUUID().toString());
        user.setImgUrl("123");
        user.setNick("1234");
        user.setSex("0");
        user.setOpenId("0983");
        user.setSessionKey("ewsf");
        int i = loginService.getUserData(user);
        System.out.println(i);
    }
}
