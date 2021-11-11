package com.example.demo.service;

import com.example.demo.domain.Admin;
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
}
