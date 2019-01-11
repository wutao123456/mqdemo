package com.dlh.converter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/6 22:59
 * 添加fastjson消息类型转换器,使用此converter将返回fastjson对象类型
 */
public class JRequestMessageConverter extends RequestMappingHandlerAdapter {

    public JRequestMessageConverter() {
//        this.getMessageConverters().add(new MyMessageConverter());
        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
        List<MediaType> mediaList = new ArrayList();
        mediaList.add(MediaType.parseMediaType("application/json"));
        mediaList.add(MediaType.parseMediaType("text/html;charset=UTF-8"));
        fastJsonConverter.setSupportedMediaTypes(mediaList);
        fastJsonConverter.setCharset(Charset.forName("UTF-8"));
        fastJsonConverter.setFeatures(new SerializerFeature[]{SerializerFeature.WriteMapNullValue, SerializerFeature.QuoteFieldNames});
        this.getMessageConverters().add(fastJsonConverter);
    }
}
