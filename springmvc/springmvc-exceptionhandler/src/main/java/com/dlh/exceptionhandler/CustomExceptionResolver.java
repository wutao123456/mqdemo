package com.dlh.exceptionhandler;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义异常返回
 */
//@Component
public class CustomExceptionResolver extends SimpleMappingExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
            if (!(request.getHeader("X-Requested-With")!= null && request
                    .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1)) {

                return super.doResolveException(request, response, handler, ex);
            }else{
                try {
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter pw = response.getWriter();
                    pw.write(JSON.toJSONString(ex.getMessage()));
                    pw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return new ModelAndView();
            }


    }
}
