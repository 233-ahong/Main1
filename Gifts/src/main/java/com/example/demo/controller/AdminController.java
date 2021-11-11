package com.example.demo.controller;

import com.example.demo.domain.Admin;
import com.example.demo.service.IAdminService;
import com.example.demo.util.RandomValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    @Qualifier("AdminServiceImpl")
    private IAdminService adminService;


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
                    model.addAttribute("msg", user.getAdmin_name());
                    return "/findAll";
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

}
