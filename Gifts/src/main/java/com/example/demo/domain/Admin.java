package com.example.demo.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component("admin")
public class Admin implements Serializable {
    private String id;
    private String admin_name;
    private String admin_password;
    private String realname;
    private String admin_qq;
    private String admin_mail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAdmin_qq() {
        return admin_qq;
    }

    public void setAdmin_qq(String admin_qq) {
        this.admin_qq = admin_qq;
    }

    public String getAdmin_mail() {
        return admin_mail;
    }

    public void setAdmin_mail(String admin_mail) {
        this.admin_mail = admin_mail;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", admin_name='" + admin_name + '\'' +
                ", admin_password='" + admin_password + '\'' +
                ", realname='" + realname + '\'' +
                ", admin_qq='" + admin_qq + '\'' +
                ", admin_mail='" + admin_mail + '\'' +
                '}';
    }
}
