package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Admin;
import com.example.demo.domain.LogUser;
import com.example.demo.service.IAdminService;
import com.example.demo.service.ILoginService;
import com.example.demo.util.RandomValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    @Qualifier("AdminServiceImpl")
    private IAdminService adminService;

    @Autowired
    @Qualifier("LoginServiceImpl")
    private ILoginService loginService;


    @RequestMapping("/findAll")
    public String list(Model model) {
        List<Admin> admins = adminService.findAll();
        model.addAttribute("list", admins);
        return "findAll";
    }

    @RequestMapping("/log")
    public String log(Model model) {
        return "log";
    }

    @RequestMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/login")
    public String login(Model model, String admin_name, String admin_password, String captcha, HttpSession session) {
        //从session中获取随机数
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
        try {
            if (random != null && !random.equalsIgnoreCase(captcha)) {
                model.addAttribute("msg", "验证码错误");
                return "log";
            } else {
                Admin user = adminService.findAdmin(admin_name, admin_password);
                if (user != null) {
                    session.setAttribute("user", user);
                    model.addAttribute("user", user.getAdmin_name());
                    return "redirect:/admins/front";
                } else {
                    model.addAttribute("msg", "用户名或密码错误");
                    return "log";
                }
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
            model.addAttribute("msg", "用户名或密码错误");
            return "log";
        }
    }

    @RequestMapping("/tableByPage")
    @ResponseBody
    public String tablePage(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        int count = adminService.count();
        int page = Integer.parseInt(request.getParameter("page"));
        int rows = Integer.parseInt(request.getParameter("rows"));
        int start = (page - 1) * rows;
        String nick = request.getParameter("nick");
        String sex = request.getParameter("sex");
        String sessionKey = request.getParameter("sessionKey");
        LogUser logUser = new LogUser();
        if (sex != null && !sex.equals("")) {
            logUser.setSex(sex);
        }
        if (sessionKey != null && !sessionKey.equals("")) {
            logUser.setSessionKey(sessionKey);
        }
        if (nick != null && !nick.equals("")) {
            logUser.setNick(nick);
        }
        List<LogUser> users = adminService.tableByPAge(logUser, start, rows);
        String js = JSON.toJSONString(users);
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":" + count + ",\"data\":" + js + "}";
        return jso;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String editLogUser(@RequestBody String s){
        LogUser logUser = JSON.parseObject(s, LogUser.class);
        String  i = String.valueOf(loginService.savaLoginUser(logUser));
        return i;
    }

    @RequestMapping(value = "/addLoginUser")
    @ResponseBody
    public String addLoginUser(@RequestBody String s) {
        LogUser user = JSON.parseObject(s, LogUser.class);
        LogUser userByOpenId = adminService.findLonginUserByOpenId(user.getOpenId());
        if (userByOpenId != null) {
            String js = "0";
            return js;
        } else {
            user.setUserId(UUID.randomUUID().toString());
            loginService.getUserData(user);
            String js = "1";
            return js;
        }
    }

    @RequestMapping("/setAdminPassword")
    @ResponseBody
    public String setAdminPassword(@RequestBody String setAdmin, Model model) {
        System.out.println("=>"+setAdmin);
        JSONObject jo = JSON.parseObject(setAdmin);
        String adminName = jo.getString("admin_name");
        String newPassword = jo.getString("new_password");
        String oldPassword = jo.getString("old_password");
        Admin admin = adminService.findAdmin(adminName, oldPassword);
        String s=null;
        if (admin == null) {
            s="1";
            return s;
        } else {
            int i = adminService.upAdminPassword(adminName, newPassword);
            if (i == 1) {
                s="2";
                return s;
            } else {
                s="3";
                return s;
            }
        }
    }

    @RequestMapping("/delLoginUser")
    @ResponseBody
    public String delLonginUser(@RequestBody String openId) {
        return String.valueOf(adminService.deleteByOpenId(openId));
    }

    @RequestMapping("/delList")
    @ResponseBody
    public String deList(@RequestBody String openIdList){
        List<LogUser> logUsers = JSON.parseArray(openIdList, LogUser.class);
        int count = 0;
        for (LogUser log:logUsers) {
            adminService.deleteByOpenId(log.getOpenId());
            count++;
        }
        return String.valueOf(count);
    }

    @RequestMapping("/welcome")
    public String welcome(Model model){
        int inCount=adminService.inCount();
        model.addAttribute("inCount",inCount);
        int logCount=adminService.count();
        model.addAttribute("logCount",logCount);
        return "welcome";
    }

    @RequestMapping("/out")
    public String out(HttpSession session){
        session.removeAttribute("user");
        return "log";
    }
    @RequestMapping("/table")
    public String table() {
        return "table";
    }

    @RequestMapping("/add")
    public String add() {
        return "add";
    }

    @RequestMapping("/front")
    public String front() {
        return "front";
    }

    @RequestMapping("/setPassword")
    public String setPassword() {
        return "setPassword";
    }

    @RequestMapping("/setting")
    public String setting(){
        return "setting";
    }

}
