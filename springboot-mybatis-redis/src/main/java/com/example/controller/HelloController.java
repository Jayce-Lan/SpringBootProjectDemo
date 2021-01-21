package com.example.controller;

import com.example.pojo.MyJSONResult;
import com.example.pojo.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private Resource resource;

    @RequestMapping("/hello")
    public Object hello() {
        return "hello SpringBoot";
    }

    @RequestMapping("/getResource")
    public MyJSONResult getResource() {
        Resource bean = new Resource();
        BeanUtils.copyProperties(resource, bean);
        return MyJSONResult.ok(bean);
    }
}
