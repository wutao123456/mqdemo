package com.dlh.controller;

import com.dlh.entity.User;
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

    /**
     * http://localhost:8080/aa/testConversion?user=1-wutao-male-programmer
     * 输出User{id=1, name='wutao', gender='male', job='programmer', birth=Wed Dec 05 21:52:39 CST 2018}
     * @param user
     * @return
     */
    @RequestMapping("/testConversion")
    public String testConverter(User user){
        System.out.println(user.toString());
        return "test";
    }
}
