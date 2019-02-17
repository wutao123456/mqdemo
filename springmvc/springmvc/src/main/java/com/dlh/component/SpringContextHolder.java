package com.dlh.component;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/2/17 20:35
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    public static <T> T getBean(String beanName){
        return (T)applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class classType){
        return (T)applicationContext.getBean(classType);
    }

    public static void clearHolder(){
        applicationContext = null;
    }

}
