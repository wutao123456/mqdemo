package sso.config;

import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.web.flow.CasWebflowConfigurer;
import org.apereo.cas.web.flow.CasWebflowExecutionPlan;
import org.apereo.cas.web.flow.CasWebflowExecutionPlanConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/11/14 22:52
 * https://blog.csdn.net/Anumbrella/article/details/82728641
 */
@Configuration("customerAuthWebflowConfiguration")
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class CustomerAuthWebflowConfiguration implements CasWebflowExecutionPlanConfigurer {

    @Autowired
    private CasConfigurationProperties casProperties;

    @Autowired
    @Qualifier("loginFlowRegistry")
    private FlowDefinitionRegistry loginFlowDefinitionRegistry;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private FlowBuilderServices flowBuilderServices;

    @Bean
    public CasWebflowConfigurer customeWebflowConfigurer(){
        final  CustomeWebflowConfigurer c = new CustomeWebflowConfigurer(flowBuilderServices,loginFlowDefinitionRegistry,applicationContext,casProperties);
        c.initialize();
        return c;
    }

    @Override
    public void configureWebflowExecutionPlan(CasWebflowExecutionPlan plan) {
        plan.registerWebflowConfigurer(customeWebflowConfigurer());
    }
}
