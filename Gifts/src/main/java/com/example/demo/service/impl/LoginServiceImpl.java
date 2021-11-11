package com.example.demo.service.impl;

import com.example.demo.dao.ILoginDao;
import com.example.demo.domain.LogUser;
import com.example.demo.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("LoginServiceImpl")
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private ILoginDao loginDao;

    @Override
    public int getUserData(LogUser logUser) {
        return loginDao.getUserData(logUser);
    }

    @Override
    public LogUser login(String openId) {
        return loginDao.login(openId);
    }

    @Override
    public int savaLoginUser(LogUser logUser) {
        return loginDao.saveLoginUser(logUser);
    }

}
