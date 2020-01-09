package com.dlh.dubbo.demo.action;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dlh.dubbo.demo.DemoService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wutao
 * @date 2020/1/9
 */
@Component("annotationAction")
public class AnnotationAction {

    @Reference
    private DemoService demoService;

    public List<String> test(){
        return demoService.getPermissions(1L);
    }

}
