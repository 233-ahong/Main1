package com.example.demo.service;

import com.example.demo.domain.LogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginService")
public interface ILoginService {
    @Autowired
    int getUserData(LogUser logUser);
}
