package com.example.demo.service.impl;

import com.example.demo.dao.IAdminDao;
import com.example.demo.domain.Admin;
import com.example.demo.domain.LogUser;
import com.example.demo.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("AdminServiceImpl")
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private IAdminDao adminDao;

    public void setAdminDao(IAdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public List<Admin> findAll() {
        return adminDao.findAll();
    }

    @Override
    public Admin findAdmin(String admin_name, String admin_password) {
        return adminDao.findAdmin(admin_name, admin_password);
    }

    @Override
    public int count() {
        return adminDao.count();
    }

    @Override
    public List<LogUser> tableByPAge(int start, int rows) {
        return adminDao.tableByPage(start, rows);
    }

    @Override
    public LogUser findLonginUserByOpenId(String openId) {
        return adminDao.findLoginUserByOpenId(openId);
    }

    @Override
    public int deleteByOpenId(String openId) {
        return adminDao.deleteByOpenId(openId);
    }
}
