package com.example.demo.dao;

import com.example.demo.domain.Admin;
import com.example.demo.domain.LogUser;
import org.apache.ibatis.annotations.*;
import org.w3c.dom.ls.LSException;

import java.util.List;

@Mapper
public interface IAdminDao {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from admin;")
    List<Admin> findAll();

    /**
     * 增加用户
     */
    @Insert("insert into admin values (#{id},#{admin_name},#{admin_password},#{realname},#{admin_qq},#{admin_mail})")
    void saveAdmin(Admin admin);

    /**
     * admin登录查询
     *
     * @param admin_name
     * @param admin_password
     * @return
     */
    @Select("select * from admin where admin_name=#{admin_name} and admin_password=#{admin_password}")
    Admin findAdmin(@Param("admin_name") String admin_name, @Param("admin_password") String admin_password);

    @Select("select count(*)from loguser")
    int count();

    /**
     * 分页查询
     *
     * @param start
     * @param rows
     * @return
     */
    @Select("select *from loguser limit #{start},#{rows}")
    List<LogUser> tableByPage(@Param("start") int start, @Param("rows") int rows);

    /**
     * 通过openID查询登录用户
     *
     * @param openId
     * @return
     */
    @Select("select * from loguser where openId=#{openId}")
    LogUser findLoginUserByOpenId(String openId);

    @Delete("delete loguser from loguser where openId=#{openId}")
    int deleteByOpenId(String openId);
}
