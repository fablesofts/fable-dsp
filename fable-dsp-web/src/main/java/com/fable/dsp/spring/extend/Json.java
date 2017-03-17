/*
 * Copyright (C) 2013-2033 Fable Limited.
 */
package com.fable.dsp.spring.extend;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author xieruidong 2014年3月18日 下午4:14:36
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Json {

    /**
     * 针对List 泛型对象化映射,单元类型定义
     */
    Class[] types() default java.lang.Object.class;

    /**
     * json key path定义
     */
    String path() default "";

}
