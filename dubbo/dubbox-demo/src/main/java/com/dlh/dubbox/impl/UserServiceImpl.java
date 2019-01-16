package com.dlh.dubbox.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.dlh.dubbox.api.UserService;
import com.dlh.dubbox.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/1/16 22:16
 */
@Path("user")
public class UserServiceImpl implements UserService {

    @POST
    @Path("regist")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
    @Override
    public String regist(User user) {
        System.out.println(user);
        HttpServletRequest request = (HttpServletRequest) RpcContext.getContext().getRequest();
        String host = request.getLocalAddr();
        return "regist success!, ur host is:" + host;
    }

    @POST
    @Path("{id:\\d+}")
    @Produces({ MediaType.APPLICATION_JSON })
    public User queryById(@PathParam("id") long uid) {
        User user = new User();
        user.setId(uid);
        user.setName("jetty");
        user.setAge(25);
        return user;
    }

}
