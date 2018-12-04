package com.dlh.dao;


import com.dlh.entity.User;

public interface UserDao {

	 User findByName(String name);

	 void save(User user);
}
