package com.example.demo.service;

import com.example.demo.domain.Admin;
import com.example.demo.domain.LogUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAdminService {
    /**
     * 查询所有
     *
     * @return
     */
    @Autowired
    List<Admin> findAll();

    @Autowired
    Admin findAdmin(String admin_name, String admin_passord);

    @Autowired
    int count();

    @Autowired
    List<LogUser> tableByPAge(LogUser logUser, int start, int rows);

    @Autowired
    LogUser findLonginUserByOpenId(String openId);

    @Autowired
    int deleteByOpenId(String openId);

    @Autowired
    int upAdminPassword(String adminName, String password);
}
