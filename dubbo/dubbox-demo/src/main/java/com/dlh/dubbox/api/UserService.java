package com.dlh.dubbox.api;

import com.dlh.dubbox.model.User;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/1/16 22:14
 */
public interface UserService {

    String regist(User user);

    User queryById(long id);

}
