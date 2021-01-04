package com.example.dao;

import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper //创建dao对象
public interface UserDao {
    void saveUser(User user);
}
