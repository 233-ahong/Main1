package com.example.demo.dao;

import com.example.demo.domain.Admin;
import com.example.demo.domain.LogUser;
import org.apache.ibatis.annotations.*;

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
    @Select("<script> "
            + " select  nick, openId, sessionKey, imgUrl, sex from loguser where 1=1 "
            + "<if test = 'logUser.nick != null'> "   //if标签开始
            + " and nick like concat('%',#{logUser.nick},'%')"
            + " </if> "                         //if标签结束
            + " <if test = 'logUser.sex != null'> "
            + "  and sex = #{logUser.sex}"
            + " </if> "
            + " <if test = 'logUser.sessionKey != null'> "
            + "  and sessionKey like concat('%', #{logUser.sessionKey},'%')"
            + " </if> "
            + "  limit ${start},${rows}"
            + "</script>")
    List<LogUser> tableByPage(@Param("logUser") LogUser logUser, @Param("start") int start, @Param("rows") int rows);

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

    @Update("update admin set admin_password=#{password} where admin_name=#{adminName}")
    int upAdminPassword(@Param("adminName") String adminName, @Param("password") String password);
}
