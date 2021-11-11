package com.example.demo.service;

import com.example.demo.domain.Information;
import com.example.demo.domain.LogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ILoginService {
    @Autowired
    int getUserData(LogUser logUser);

    @Autowired
    LogUser login(String openId);

    @Autowired
    int savaLoginUser(LogUser logUser);

    @Autowired
    List<Information> search(String described);
}
