package com.example.demo.dao;

import com.example.demo.domain.LogUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ILoginDao {
    @Insert("insert into loguser(userId, nick, openId, sessionKey, imgUrl, sex) " +
            "values (#{userId},#{nick},#{openId},#{sessionKey},#{imgUrl},#{sex})")
    int getUserData(LogUser logUser);
}
