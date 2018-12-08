package com.dlh.exceptionhandler;

import com.dlh.entity.ErrorInfo;
import com.dlh.exception.AjaxException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/8 15:04
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常处理器到错误页面
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception e){
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("message","this is global exceptionHandler");
        mv.addObject("exception",e);
        return mv;
    }

//    @ExceptionHandler(Exception.class)
//    public String redirect(Exception e, Model model){
//        model.addAttribute("exception",e.getMessage());
//        return "redirect:error.jsp";
//    }

    /**
     * 全局异常处理器返回json格式异常信息
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(AjaxException.class)
    @ResponseBody
    public ErrorInfo<String> ajaxException(HttpServletRequest request,Exception e){
        ErrorInfo<String> errorInfo = new ErrorInfo<>();
        errorInfo.setCode(ErrorInfo.ERROR);
        errorInfo.setMessage(e.getMessage());
        errorInfo.setUrl(request.getRequestURI().toString());
        errorInfo.setData("some data");
        return errorInfo;
    }
}
