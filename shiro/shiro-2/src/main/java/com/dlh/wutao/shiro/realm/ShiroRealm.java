package com.dlh.wutao.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/16 15:59
 * 若只需开启认证继承AuthenticatingRealm,若需认证和授权则继承AuthorizingRealm
 * AuthenticatingRealm继承自AuthorizingRealm
 */
public class ShiroRealm extends AuthorizingRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("2."+authenticationToken.hashCode());
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        System.out.println(username);
        if("unknown".equals(username)){
            throw new UnknownAccountException("用户不存在");
        }

        if("locked".equals(username)){
            throw new LockedAccountException("用户被锁定");
        }

        //password加密方式见spring-shiro.xml中credentialsMatcher

        String password = "fc1709d0a95a6be30bc5926fdb7f22f4";
        if("admin".equals(username)){
            password = "038bdaf98f2037b31f1e75b5b4c9b26e";
        }else if("user".equals(username)){
            password = "098d2c478e9c11555ce2823231e02ec1";
        }
        //盐值加密
        ByteSource salt = ByteSource.Util.bytes(username);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,password,salt,getName());
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
        Set<String> roles = new HashSet<>();
        //用户admin可以访问admin.jsp,user.jsp,用户user只能访问user.jsp
        roles.add("user");
        if("admin".equals(primaryPrincipal)){
            roles.add("admin");
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(roles);
        return authorizationInfo;
    }
}
