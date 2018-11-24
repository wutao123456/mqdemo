package com.dlh.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextHolder implements ApplicationContextAware, DisposableBean {
    private static ApplicationContext context = null;

    public void destroy() throws Exception {
        clearHolder();
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public void clearHolder(){
        context = null;
    }

    public static <T> T getBean(String beanName){
        return (T)context.getBean(beanName);
    }

    public static <T> T getBean(Class<T> requiredType){
        return (T)context.getBean(requiredType);
    }
}
