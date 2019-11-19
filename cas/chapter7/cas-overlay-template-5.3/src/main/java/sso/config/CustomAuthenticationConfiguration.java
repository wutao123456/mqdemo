package sso.config;

import org.apereo.cas.authentication.AuthenticationEventExecutionPlan;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlanConfigurer;
import org.apereo.cas.authentication.AuthenticationHandler;
import org.apereo.cas.authentication.principal.DefaultPrincipalFactory;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/11/1 22:38
 * 自定义认证配置
 */
@Configuration("customAuthenticationConfiguration")
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class CustomAuthenticationConfiguration implements AuthenticationEventExecutionPlanConfigurer {

    @Autowired
    private CasConfigurationProperties casConfigurationProperties;

    @Autowired
    @Qualifier("servicesManager")
    private ServicesManager servicesManager;

    @Bean
    public AuthenticationHandler myAuthenticationHandler(){
        return new CustomerHandlerAuthentication(CustomerHandlerAuthentication.class.getName(),
                servicesManager, new DefaultPrincipalFactory(), 1);
    }

    @Override
    public void configureAuthenticationExecutionPlan(AuthenticationEventExecutionPlan plan) {
        plan.registerAuthenticationHandler(myAuthenticationHandler());
    }
}
