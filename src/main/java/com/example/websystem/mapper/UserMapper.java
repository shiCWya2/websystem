package com.example.websystem.mapper;

import com.example.websystem.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where username=#{username} and password=#{password}")
    User login(String username, String password);
    @Select("select * from user where username=#{username}")
    User getuser(String username);
    @Insert("insert into user (username,password) values (#{username},#{password})")
    void adduser(User user);
}
