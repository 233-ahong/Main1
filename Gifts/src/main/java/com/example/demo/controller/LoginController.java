package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.LogUser;
import com.example.demo.domain.Login;
import com.example.demo.domain.Result;
import com.example.demo.service.ILoginService;
import com.example.demo.util.Aes;
import com.example.demo.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private ILoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody String code) {
        Result rs = new Result();     //自定义的结果返回值（详细查看上一篇）
        Login result = new Login();//登录数据的实体类，存放登录信息等
        System.out.println(code);
        //获取微信session和生成自定义token
        HttpUtil hrs = new HttpUtil();
        //获取session_key和openid
        JSONObject session_key = hrs.domain("getSession_key", code);
        String session = session_key.getString("session_key");
        String openid = session_key.getString("openid");
        result.setSession(session);
        //把参数放入
        rs.setData(result);
        String jo = JSON.toJSONString(rs);
        return jo;
    }

    @RequestMapping(value = "/getUserData", method = RequestMethod.POST)
    @ResponseBody
    public String getUserData(@RequestBody String login) {
        System.out.println("获取用户数据");
        JSONObject log = JSON.parseObject(login);
        String userInfo = log.getString("userInfo");
        JSONObject loginUser = JSON.parseObject(userInfo);
        Aes aes = new Aes();
        JSONObject jObject = aes.domain(log.getString("encryptedData"), log.getString("code"), log.getString("iv"));
        Result rs = new Result();
        LogUser user = new LogUser();
        user.setUserId(UUID.randomUUID().toString());
        user.setImgUrl(jObject.getString("avatarUrl"));
        user.setSex(String.valueOf(jObject.getInteger("gender")));
        user.setNick(jObject.getString("nickName"));
        user.setOpenId(jObject.getString("openId"));
        //调用服务层处理
        //int us=loginService.getUserData(user);
        rs.setData(user);
        String use = JSON.toJSONString(rs);
        return use;
    }
}
