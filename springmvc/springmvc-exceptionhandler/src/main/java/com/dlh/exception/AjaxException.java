package com.dlh.exception;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/8 15:51
 */
public class AjaxException extends RuntimeException {

    public AjaxException() {
        super();
    }

    public AjaxException(String message) {
        super(message);
    }
}
