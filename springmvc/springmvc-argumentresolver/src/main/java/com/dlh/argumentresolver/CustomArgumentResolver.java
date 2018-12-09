package com.dlh.argumentresolver;

import com.dlh.annotation.RequestDateParam;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;

import java.text.SimpleDateFormat;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/9 13:57
 */
public class CustomArgumentResolver extends AbstractNamedValueMethodArgumentResolver {

//    public CustomArgumentResolver() {
//        System.out.println("参数解析器初始化");
//    }

    /**
     * 获取当前参数的注解信息
     * @param methodParameter
     * @return
     */
    @Override
    protected NamedValueInfo createNamedValueInfo(MethodParameter methodParameter) {
        RequestDateParam requestDateParam = methodParameter.getParameterAnnotation(RequestDateParam.class);
        return new NamedValueInfo(requestDateParam.value(),requestDateParam.required(),null);
    }

    /**
     * 在这里进行参数的类型转换
     * @param s
     * @param methodParameter 需要被解析的Controller参数
     * @param nativeWebRequest 当前request
     * @return 转换后的参数值
     * @throws Exception
     */
    @Override
    protected Object resolveName(String s, MethodParameter methodParameter, NativeWebRequest nativeWebRequest) throws Exception {
        String content = nativeWebRequest.getParameter(s);
        if (content == null) {
            return null;
        } else {
            try {
                RequestDateParam annotation = methodParameter.getParameterAnnotation(RequestDateParam.class);
                SimpleDateFormat dateFormat = new SimpleDateFormat(annotation.pattern().getValue());
                return dateFormat.parse(content);
            } catch (Exception e) {
                throw new IllegalArgumentException("Date format conversion error", e);
            }
        }

    }

    /**
     * 解析器是否支持当前参数
     * @param methodParameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(RequestDateParam.class);
    }

    /**
     * 当前参数值为空且注解的默认值也为空则抛出异常
     * @param name
     * @param parameter
     * @param request
     * @throws Exception
     */
    @Override
    protected void handleMissingValue(String name, MethodParameter parameter, NativeWebRequest request) throws Exception {
        throw new MissingServletRequestParameterException(name, parameter.getParameterType().getSimpleName());
    }
}
