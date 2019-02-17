package com.dlh.component;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/2/17 21:45
 */
@Component
public class SpringUtil implements BeanFactoryAware {

    private static BeanFactory beanFactory = null;

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        SpringUtil.beanFactory = beanFactory;
    }

    public static <T> T getBean(String beanName){
        return (T)beanFactory.getBean(beanName);
    }
}
