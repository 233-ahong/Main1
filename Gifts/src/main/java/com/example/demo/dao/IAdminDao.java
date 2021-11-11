package com.example.demo.dao;

import com.example.demo.domain.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IAdminDao {
    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from admin;")
    List<Admin> findAll();

    /**
     * 增加用户
     */
    @Insert("insert into admin values (#{id},#{admin_name},#{admin_password},#{realname},#{admin_qq},#{admin_mail})")
    void saveAdmin(Admin admin);

    @Select("select * from admin where admin_name=#{admin_name} and admin_password=#{admin_password}")
    Admin findAdmin(@Param("admin_name") String admin_name, @Param("admin_password") String admin_password);
}
