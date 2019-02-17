package com.dlh.entity;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/2 17:07
 */
public class Person implements InitializingBean {

    private String user;

    private String password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person() {
        System.out.println("开始初始化>>>>>>>>>");
    }

    public void init(){
        System.out.println("初始化之后");
    }

    public void destroy(){
        System.out.println("销毁之前");
    }

    @Override
    public String toString() {
        return "Person{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @PostConstruct
    public void post(){
        System.out.println("PostConstruct");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    //执行顺序如下
    //Construct-->PostConstruct-->afterPropertiesSet-->init-method
}
