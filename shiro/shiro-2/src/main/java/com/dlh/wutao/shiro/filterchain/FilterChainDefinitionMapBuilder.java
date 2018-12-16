package com.dlh.wutao.shiro.filterchain;

import java.util.LinkedHashMap;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/16 21:17
 */
public class FilterChainDefinitionMapBuilder {

    public LinkedHashMap<String,String> buildFilterChainDefinitionMap(){
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("/login.jsp","anon");
        map.put("/shiro/login","anon");
        map.put("/shiro/logout","logout");
        map.put("/unauthorized.jsp","anon");
        map.put("/user.jsp","authc,roles[user]");
        map.put("/admin.jsp","authc,roles[admin]");
        map.put("/success.jsp","user");
        map.put("/**","authc");
        return map;
    }
}
