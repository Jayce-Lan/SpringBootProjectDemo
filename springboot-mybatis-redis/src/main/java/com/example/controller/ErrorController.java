package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("err")
public class ErrorController {
    /**
     * 前端的异常捕获
     *
     * @return
     */
    @RequestMapping("error")
    public String error() {
        int num = 1 / 0;

        return "thymeleaf/error";
    }

    /**
     * Ajax的异常捕获
     *
     * @return
     */
    @RequestMapping("ajaxerror")
    public String ajaxError() {
        int num = 1 / 0;
        return "thymeleaf/ajaxerror";
    }


}
