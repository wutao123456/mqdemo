package com.dlh.dubbo.notify;

import java.util.List;

/**
 * @author wutao
 * @date 2018/11/13
 */
public interface Notify {

    void oninvoke(Long id);

    void onreturn(List<String> list, Long id);

    void onthrow(Throwable ex, Long id);
}
