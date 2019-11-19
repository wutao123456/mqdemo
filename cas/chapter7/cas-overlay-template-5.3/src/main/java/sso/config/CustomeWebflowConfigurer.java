package sso.config;

import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.web.flow.CasWebflowConstants;
import org.apereo.cas.web.flow.configurer.AbstractCasWebflowConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.Flow;
import org.springframework.webflow.engine.ViewState;
import org.springframework.webflow.engine.builder.BinderConfiguration;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import sso.entity.CustomCredential;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/11/14 22:42
 * 自定义web认证流程
 */
public class CustomeWebflowConfigurer extends AbstractCasWebflowConfigurer {

    public CustomeWebflowConfigurer(FlowBuilderServices flowBuilderServices, FlowDefinitionRegistry loginFlowDefinitionRegistry, ApplicationContext applicationContext, CasConfigurationProperties casProperties) {
        super(flowBuilderServices, loginFlowDefinitionRegistry, applicationContext, casProperties);
    }

    @Override
    protected void doInitialize() {
        final Flow flow = super.getLoginFlow();
        bindCredential(flow);

    }

    /**
     * 绑定自定义的Credential信息
     * @param flow
     */
    protected void bindCredential(Flow flow){
        // 重写绑定自定义credential
        createFlowVariable(flow, CasWebflowConstants.VAR_ID_CREDENTIAL, CustomCredential.class);
        // 登录页绑定新参数
        final ViewState viewState = (ViewState) flow.getState(CasWebflowConstants.STATE_ID_VIEW_LOGIN_FORM);
        final BinderConfiguration cfg = getViewStateBinderConfiguration(viewState);
        // 由于用户名以及密码已经绑定，所以只需对新加系统参数绑定即可
        // 字段名，转换器，是否必须字段
        cfg.addBinding(new BinderConfiguration.Binding("email",null,true));
        cfg.addBinding(new BinderConfiguration.Binding("telephone",null,true));
    }
}
