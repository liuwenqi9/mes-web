package com.egdfrm.core.security.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.egdfrm.core.security.annotation.CurrentLoginInfo;

/**
 * <p>
 * ClassName: CurrentUserMethodArgumentResolver
 * </p>
 * <p>
 * Description: 用于绑定@FormModel的方法参数解析器
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年4月21日
 * </p>
 */
public class CurrentLoginInfoMethodArgumentResolver implements HandlerMethodArgumentResolver {

    public CurrentLoginInfoMethodArgumentResolver() {
    }

    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(CurrentLoginInfo.class)) {
            return true;
        }
        return false;
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    	CurrentLoginInfo currentLogin = parameter.getParameterAnnotation(CurrentLoginInfo.class);
        return webRequest.getAttribute(currentLogin.value(), NativeWebRequest.SCOPE_REQUEST);
    }
}
