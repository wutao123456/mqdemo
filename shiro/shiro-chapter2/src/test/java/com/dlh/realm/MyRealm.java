package com.dlh.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/9 22:59
 */
public class MyRealm implements Realm {
    @Override
    public String getName() {
        return "myrealm";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        if(!"wutao".equals(username)){
            throw new AccountException();
        }

        if(!"123".equals(password)){
            throw new IncorrectCredentialsException("");
        }
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
