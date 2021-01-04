package com.example.service.impl;

import com.example.dao.UserDao;
import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional  //控制事务
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void saveUser(User user) {
        //设置用户状态
        user.setStatus("已激活");
        //设置用户注册时间
        user.setRegisterTime(new Date());
        userDao.saveUser(user);
    }
}
