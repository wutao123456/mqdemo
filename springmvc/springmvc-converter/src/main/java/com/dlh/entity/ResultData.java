package com.dlh.entity;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/5 23:13
 */
public class ResultData {

    private int status;

    private String message;

    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
