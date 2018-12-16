package com.dlh.wutao.shiro.controller;

import com.dlh.wutao.shiro.service.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/16 15:58
 */
@Controller
@RequestMapping(value = "shiro")
public class LoginController {

    @Autowired
    private ShiroService shiroService;

    @RequestMapping(value = "/login")
    public String login(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password){
        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            token.setRememberMe(true);
            try {
                System.out.println("1."+token.hashCode());
                currentUser.login(token);
            } catch (AuthenticationException e) {
                System.out.println("登录失败"+e.getMessage());
                e.printStackTrace();
            }
        }
        return "redirect:/success.jsp";
    }


    @RequestMapping(value = "/testShiroAnnotation")
    public String testShiroAnnotation(){
        shiroService.testMethod();
        return "redirect:/success.jsp";
    }

    @RequestMapping(value = "/testSession")
    public String testSession(HttpSession session){
        session.setAttribute("key","test1");
        shiroService.testSession();
        return "redirect:/success.jsp";
    }
}
