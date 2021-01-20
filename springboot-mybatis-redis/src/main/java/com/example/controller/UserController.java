package com.example.controller;

import com.example.pojo.LeeJSONResult;
import com.example.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

//@Controller
@RequestMapping("/user")
@RestController     //相当于在主类定义了@Controller， 并且在方法定义了@ResponseBody
public class UserController {
    @RequestMapping("/getuser")
//    @ResponseBody
    public User getUser() {
        User user = new User();
        user.setName("root");
        user.setAge(20);
        user.setBirthday(new Date());
        user.setDesc(null);
        user.setPassword("root");

        return user;
    }

    /**
     * 将数据封装为json数据
     * @return
     */
    @RequestMapping("/getuserjson")
//    @ResponseBody
    public LeeJSONResult getUserJson() {
        User user = new User();
        user.setName("root");
        user.setAge(20);
        user.setBirthday(new Date());
        user.setPassword("root");
//        user.setDesc("Hello Administrator");

        return LeeJSONResult.ok(user);
    }
}
