package com.dlh.converter;

import com.alibaba.fastjson.JSONObject;
import com.dlh.entity.ResultData;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/5 23:06
 * 自定义返回消息类型,此处将消息转换为json字符串返回
 */
public class MyMessageConverter extends AbstractHttpMessageConverter<ResultData> {

    public MyMessageConverter() {
        super(MediaType.parseMediaType("text/plain;charset=UTF-8"));
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    protected ResultData readInternal(Class<? extends ResultData> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(ResultData resultData, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        httpOutputMessage.getBody().write(JSONObject.toJSONString(resultData.getData()).getBytes("utf-8"));
    }
}
