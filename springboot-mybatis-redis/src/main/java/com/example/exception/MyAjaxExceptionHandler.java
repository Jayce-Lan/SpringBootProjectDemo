package com.example.exception;

import com.example.pojo.MyJSONResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@ControllerAdvice
@RestControllerAdvice
public class MyAjaxExceptionHandler {
    public static final String MY_ERROR_VIEW = "error"; //错误页面

    /**
     * 单纯的Ajax异常捕获
     *
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public MyJSONResult defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        e.printStackTrace();
        return MyJSONResult.errorException(e.getMessage());
    }

    /**
     * 兼容Ajax与前端的异常捕获
     *
     * @param request
     * @param response
     * @param e
     * @return
     * @throws Exception
     */
//    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e)
        throws Exception {
        e.printStackTrace();

        if (isAjax(request)) {
            return MyJSONResult.errorException(e.getMessage());
        } else {
            ModelAndView mav = new ModelAndView();

            mav.addObject("exception", e);
            mav.addObject("url", request.getRequestURI());
            mav.setViewName(MY_ERROR_VIEW);

            return mav;
        }
    }

    /**
     * 判断数据类型是否为Ajax
     *
     * @param httpRequest
     * @return
     */
    public static boolean isAjax(HttpServletRequest httpRequest){
        return  (httpRequest.getHeader("X-Requested-With") != null
                && "XMLHttpRequest"
                .equals( httpRequest.getHeader("X-Requested-With").toString()) );
    }
}
