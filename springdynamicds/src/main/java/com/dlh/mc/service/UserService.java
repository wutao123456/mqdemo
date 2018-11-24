package com.dlh.mc.service;

import com.dlh.annotation.DataTarget;
import com.dlh.aspect.LoggerTest;
import com.dlh.mc.dao.system.UserMapper;
import com.dlh.util.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @DataTarget(value = 1)
    public int countUser(int corpSN){

        return userMapper.countUser(corpSN);
    }
}
