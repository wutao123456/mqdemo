package com.dlh.wutao.shiro.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/16 20:45
 */
@Service
public class ShiroService {

    @RequiresRoles({"admin"})
    public void testMethod(){
        System.out.println("test method: "+new Date());
    }

    public void testSession(){
        Subject subject = SecurityUtils.getSubject();
        Object key = subject.getSession().getAttribute("key");
        System.out.println("service sessionval: "+key);
    }
}
