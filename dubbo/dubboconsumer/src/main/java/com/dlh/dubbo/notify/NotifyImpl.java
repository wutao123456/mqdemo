package com.dlh.dubbo.notify;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wutao
 * @date 2018/11/13
 */
public class NotifyImpl implements Notify {

    public Map<Long, List<String>> rets = new HashMap<Long, List<String>>();

    public Map<Long,Throwable> errors = new HashMap<Long, Throwable>();

    public void oninvoke(Long id) {
        System.out.println("调用getPermissions方法,入参: "+id);
    }

    public void onreturn(List<String> list, Long id) {
        System.out.println("onreturn: "+list);
        rets.put(id,list);
    }

    public void onthrow(Throwable ex, Long id) {
        errors.put(id,ex);
    }
}
