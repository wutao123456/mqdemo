package com.wutao.netty.udp;

import java.net.InetSocketAddress;

/**
 * @author wutao
 * @date 2019/12/17
 */
public class LogEvent {
    public static final byte SEPARATOR = (byte) '%';
    private final InetSocketAddress source;
    private final String logfile;
    private final String msg;
    private final long received;

    public LogEvent(String logfile, String msg) { // 用于传出消息的构造函数
        this(null, -1, logfile, msg);
    }

    public LogEvent(InetSocketAddress source, long received, String logfile, String msg) { // 用于 传入消息的构造函数
        this.source = source;
        this.logfile = logfile;
        this.msg = msg;
        this.received = received;
    }

    public InetSocketAddress getSource() { // 返回发送LogEvent 的源的InetSocketAddress
        return source;
    }

    public String getLogfile() { // 返回所发送的LogEvent 的日志文件的名称
        return logfile;
    }

    public String getMsg() { // 返回消息内容
        return msg;
    }

    public long getReceivedTimestamp() { // 返回接收LogEvent的时间
        return received;
    }

    public static void main(String[] args) {
        String str = "info.log%2019-12-17 11:32:08  [ http-nio-8080-exec-4:1254979 ] - [ INFO ]   [DUBBO] Notify urls for subscribe url consumer://192.168.2.58/com.hyq.gas.service.edit.EditFillRecordsService?application=cool.web&application.version=1.0&category=providers,configurators,routers&dubbo=2.8.4&interface=com.hyq.gas.service.edit.EditFillRecordsService&logger=log4j&methods=insertCheckRecords,insertBatchCheckRecords,insertBatchFillRecords,updateByPrimaryKeySelective,updateByPrimaryKey,insert,deleteByPrimaryKey,insertForFilterRecord&owner=wqh&pid=12472&revision=2.3.44&side=consumer&timestamp=1576553527680, urls: [dubbo://192.168.2.212:20883/com.hyq.gas.service.edit.EditFillRecordsService?application=lpg-server&application.version=1.0.0&dubbo=2.8.4&generic=false&interface=com.hyq.gas.service.edit.EditFillRecordsService&loadbalance=roundrobin&methods=insertCheckRecords,insertBatchCheckRecords,insertBatchFillRecords,updateByPrimaryKeySelective,updateByPrimaryKey,insert,deleteByPrimaryKey,insertForFilterRecord&organization=dlh&owner=wqh&pid=11148&revision=2.3.41&side=provider&timeout=5000&timestamp=1567598939999, empty://192.168.2.58/com.hyq.gas.service.edit.EditFillRecordsService?application=cool.web&application.version=1.0&category=configurators&dubbo=2.8.4&interface=com.hyq.gas.service.edit.EditFillRecordsService&logger=log4j&methods=insertCheckRecords,insertBatchCheckRecords,insertBatchFillRecords,updateByPrimaryKeySelective,updateByPrimaryKey,insert,deleteByPrimaryKey,insertForFilterRecord&owner=wqh&pid=12472&revision=2.3.44&side=consumer&timestamp=1576553527680, empty://192.168.2.58/com.hyq.gas.service.edit.EditFillRecordsService?application=cool.web&application.version=1.0&category=routers&dubbo=2.8.4&interface=com.hyq.gas.service.edit.EditFillRecordsService&logger=log4j&methods=insertCheckRecords,insertBatchCheckRecords,insertBatchFillRecords,updateByPrimaryKeySelective,updateByPrimaryKey,insert,deleteByPrimaryKey,insertForFilterRecord&owner=wqh&pid=12472&revision=2.3.44&side=consumer&timestamp=1576553527680], dubbo version";
        System.out.println(str.length());
    }
}
