package com.dlh.entity;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/8 15:48
 */
public class ErrorInfo<T> {

    public static final Integer OK = 0;
    public static final Integer ERROR = -1;

    private Integer code;
    private String message;
    private String url;
    private T data;

    public ErrorInfo() {

    }

    public ErrorInfo(Integer code) {
        this.code = code;

    }

    public ErrorInfo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorInfo(Integer code, String message, String url) {
        this.code = code;
        this.message = message;
        this.url = url;
    }

    public ErrorInfo(Integer code, String message, String url, T data) {
        this.code = code;
        this.message = message;
        this.url = url;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Integer getOk() {
        return OK;
    }

    public static Integer getError() {
        return ERROR;
    }

    @Override
    public String toString() {
        return "ErrorInfo [code=" + code + ", message=" + message + ", url=" + url + ", data=" + data + "]";
    }


}

