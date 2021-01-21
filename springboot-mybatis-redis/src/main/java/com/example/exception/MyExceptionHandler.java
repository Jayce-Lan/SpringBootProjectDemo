package com.example.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@ControllerAdvice
public class MyExceptionHandler {
    public static final String MY_ERROR_VIEW = "error"; //错误页面

//    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        e.printStackTrace();
        ModelAndView mav = new ModelAndView();

        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURI());
        mav.setViewName(MY_ERROR_VIEW);

        return mav;
    }
}
