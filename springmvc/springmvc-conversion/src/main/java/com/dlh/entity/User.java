package com.dlh.entity;

import java.util.Date;

/**
 * Created by admin on 2017/9/10.
 */
public class User {


    private int id;

    private String name;

    private String gender;

    private String job;

    private Date birth;

    public User() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public User(int id, String name, String gender, String job, Date birth) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.job = job;
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", job='" + job + '\'' +
                ", birth=" + birth +
                '}';
    }
}
