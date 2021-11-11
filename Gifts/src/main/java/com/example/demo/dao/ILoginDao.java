package com.example.demo.dao;

import com.example.demo.domain.LogUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ILoginDao {
    /**
     * 插入用户
     *
     * @param logUser
     * @return
     */
    @Insert("insert into loguser(userId, nick, openId, sessionKey, imgUrl, sex) " +
            "values (#{userId},#{nick},#{openId},#{sessionKey},#{imgUrl},#{sex})")
    int getUserData(LogUser logUser);

    @Select("select * from loguser where openId=#{openId}")
    LogUser login(String openId);

    /**
     * 修改用户
     *
     * @param logUser
     * @return
     */
    @Update("update loguser set nick=#{nick},sessionKey=#{sessionKey},imgUrl=#{imgUrl},sex=#{sex} where " +
            "openId=#{openId}")
    int saveLoginUser(LogUser logUser);
}
