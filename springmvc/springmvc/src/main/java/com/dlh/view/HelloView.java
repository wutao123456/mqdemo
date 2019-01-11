package com.dlh.view;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/5 20:38
 */
@Component
public class HelloView implements View {

    public String getContentType() {
        return "text/html";
    }

    public void render(Map<String, ?> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Type","text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print("hello view "+new Date());
    }
}
