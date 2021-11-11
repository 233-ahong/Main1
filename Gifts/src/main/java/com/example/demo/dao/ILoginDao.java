package com.example.demo.dao;

import com.example.demo.domain.Information;
import com.example.demo.domain.LogUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ILoginDao {
    /**
     * 插入用户
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
     * @param logUser
     * @return
     */
    @Update("update loguser set nick=#{nick},sessionKey=#{sessionKey},imgUrl=#{imgUrl},sex=#{sex} where " +
            "openId=#{openId}")
    int saveLoginUser(LogUser logUser);

    /**
     * 微信模糊查询
     * @param described
     * @return
     */
    @Select("select HTTPS, PRICE, DESCRIBED, PICTURE, SALES_DROP from information where DESCRIBED like CONCAT('%',#{described},'%')")
    List<Information> search(String described);
}
