package com.example.controller;

import com.example.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("tl")
public class ThymeleafController {
    @RequestMapping("index")
    public String index(ModelMap map) {
        map.addAttribute("name", "Jayce");
        return "thymeleaf/index";
    }

    @RequestMapping("center")
    public String center() {
        return "thymeleaf/center/center";
    }

    @RequestMapping("user")
    public String user(ModelMap map) {
        User user = new User();
        user.setName("Jayce");
        user.setPassword("root");
        user.setBirthday(new Date());
        user.setAge(25);
        user.setDesc("管理员");

        User user1 = new User();
        user1.setName("Jack");
        user1.setPassword("root");
        user1.setBirthday(new Date());
        user1.setAge(20);
        user1.setDesc("管理员");

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);

        map.addAttribute("user", user);
        map.addAttribute("userList", userList);
        return "thymeleaf/user";
    }
}
