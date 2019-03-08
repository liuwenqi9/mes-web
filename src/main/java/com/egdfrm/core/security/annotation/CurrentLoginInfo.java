package com.egdfrm.core.security.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.egdfrm.core.common.Constants;

@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentLoginInfo {

    /**
     * 当前用户在request中的登录信息
     * 
     * @return
     */
    /**
     * @author sjf
     * @date 2017年1月5日 
     * @return
     */
    String value() default Constants.CURRENT_LOGIN_INFO;

}
