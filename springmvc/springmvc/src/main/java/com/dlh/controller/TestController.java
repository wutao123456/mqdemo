package com.dlh.controller;

import com.dlh.component.SpringContextHolder;
import com.dlh.component.SpringUtil;
import com.dlh.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/4 22:36
 */
@Controller
@RequestMapping("/aa")
public class TestController {

    @RequestMapping("/test")
    public String test(){
        Person person = SpringContextHolder.getBean("person");
        System.out.println(person);
        System.out.println("testController");
        return "test";
    }

    @RequestMapping("/hello")
    public String helloView(){
        Person person = SpringUtil.getBean("person");
        System.out.println(person);
        System.out.println("test hello view");
        return "helloView";
    }

}
