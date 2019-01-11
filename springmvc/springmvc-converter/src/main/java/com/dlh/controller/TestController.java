package com.dlh.controller;

import com.dlh.entity.ResultData;
import com.dlh.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/4 22:36
 */
@Controller
@RequestMapping("/aa")
public class TestController {

    @RequestMapping(value = "/testconverter")
    public String convert(){
        return "testconverter";
    }

    /**
     * 自定义转换器返回fastjson格式
     * @param user
     * @return
     */
    @RequestMapping(value = "/custom")
    @ResponseBody
    public ResultData testMessageConverter(User user){
        ResultData resultData = new ResultData();
        resultData.setData(user);
        return resultData;
    }
}
