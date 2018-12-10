package com.dlh;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/10 20:38
 * FirstSuccessfulStrategy：
 * 只要有一个Realm验证成功即可，只返回第一个Realm身份验证成功的认证信息，其他的忽略；
 *
 * AtLeastOneSuccessfulStrategy：
 * 只要有一个Realm验证成功即可，和FirstSuccessfulStrategy不同，返回所有Realm身份验证成功的认证信息；
 *
 * AllSuccessfulStrategy：
 * 所有Realm验证成功才算成功，且返回所有Realm身份验证成功的认证信息，如果有一个失败就失败了。
 *
 * ModularRealmAuthenticator默认使用AtLeastOneSuccessfulStrategy策略。
 *
 *
 */
public class AuthenticatorTest {

    @Test
    public void testAtLeastOne(){
        login("classpath:shiro-authenticator-atLeastOne-success.ini");
        Subject subject = SecurityUtils.getSubject();
        //得到一个身份集合，其包含了Realm验证成功的身份信息
        PrincipalCollection collection = subject.getPrincipals();
        Assert.assertEquals(1,collection.asList().size());
    }

    private void login(String configFile){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wutao","123");
        subject.login(token);

    }
}
