package com.dlh.controller;

import com.dlh.exception.AjaxException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/8 14:39
 */
@Controller
@RequestMapping(value = "/handler")
public class TestExceptionHandler {

    /**
     * 局部异常处理器
     * @param e
     * @return
     */
//    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception e){
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("message","this is local exceptionHandler");
        mv.addObject("exception",e);
        return mv;
    }

    @RequestMapping(value = "/test")
    public void test(){
        System.out.println(10/0);
    }

    @RequestMapping(value = "/testexceptionresolver")
    public String testexceptionresolver(){
        return "testexceptionresolver";
    }

    @RequestMapping(value = "/testResolver",method = RequestMethod.POST)
    @ResponseBody
    public String testResolver(int i)throws Exception{
        if(i==1){
            throw new Exception("报了个错");
        }
        return "操作成功";
    }

    @RequestMapping(value = "/testAjaxException",method = RequestMethod.POST)
    public ModelAndView testAjaxException()throws Exception{
        throw new AjaxException();
    }
}
