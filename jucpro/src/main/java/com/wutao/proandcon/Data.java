package com.wutao.proandcon;

import java.io.Serializable;

/**
 * @author wutao
 * @date 2020/1/3
 */
public class Data<T> implements Serializable {
    private T data;

    public Data(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Data{" +
                "data=" + data +
                '}';
    }
}
