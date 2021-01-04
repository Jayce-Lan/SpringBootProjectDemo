package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin    //由于是前后端分离，因此允许跨域
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public Map<String, Object> register(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        try {
            userService.saveUser(user);
            map.put("state", true);
            map.put("msg", "注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", "注册失败");
        }

        return map;
    }
}
