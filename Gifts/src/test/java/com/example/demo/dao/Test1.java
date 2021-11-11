package com.example.demo.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Admin;
import com.example.demo.domain.Information;
import com.example.demo.domain.LogUser;
import com.example.demo.service.IAdminService;
import com.example.demo.service.ILoginService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
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
        user.setOpenId("0984");
        user.setSessionKey("ewsf");
        int i = loginService.getUserData(user);
        System.out.println(i);
    }

    @Test
    public void test4() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IAdminService adminService = (IAdminService) context.getBean("AdminServiceImpl");
        List<LogUser> logUsers = adminService.tableByPAge(null, 0, 5);
        String js = JSON.toJSONString(logUsers);
        System.out.println(js);
    }

    @Test
    public void test5() {
        String s = "{\"nick\":\"1\",\"sex\":\"��\",\"openId\":\"1\",\"sessionKey\":\"1\",\"imgUrl\":\"1\"}";
        JSONObject jsonObject = JSON.parseObject(s);
        System.out.println(jsonObject);
        LogUser logUser = JSON.parseObject(s, LogUser.class);
        System.out.println(logUser);
    }

    @Test
    public void test6() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IAdminService adminService = (IAdminService) context.getBean("AdminServiceImpl");
        LogUser logUser = new LogUser();
        logUser.setSex("0");
        logUser.setNick("1");
        List<LogUser> logUsers = adminService.tableByPAge(logUser, 0, 5);
        System.out.println(logUsers);
    }

    @Test
    public void test7() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IAdminService adminService = (IAdminService) context.getBean("AdminServiceImpl");
        int i = adminService.upAdminPassword("123", "1234");
        System.out.println(i);
        Information information=new Information();
    }
    @Test
    public void test8(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ILoginService loginService= (ILoginService) context.getBean("LoginServiceImpl");
        List<Information>list=loginService.search("送男友");
        System.out.println(JSON.toJSONString(list));
    }
}
