package com.dlh.dubbo.callback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wutao
 * @date 2018/11/13
 */
public class CallbackServiceImpl implements CallbackService {

    private final Map<String,CallbackListener> listeners = new ConcurrentHashMap<String, CallbackListener>();

    public CallbackServiceImpl() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                while (true){
                    try {
                        for(Map.Entry<String,CallbackListener> entry:listeners.entrySet()){
                            try {
                                entry.getValue().changed(entry.getKey());
                            } catch (Exception e) {
                                listeners.remove(entry.getKey());
                            }
                        }
                        Thread.sleep(5000);//定时触发变更通知
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    public void addListener(String key, CallbackListener listener) {
        listeners.put(key,listener);
        listener.changed(getChanged(key));
    }

    public String getChanged(String key){
        return "changed: listener " + key + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
