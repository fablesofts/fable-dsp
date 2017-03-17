package com.fable.dsp.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface LogAnnotation {
    
    public char[] operationType();
    
    public String[] operationDescribe();
    
    //表名-操作列名 （如 taskName）
    public String[] targetName();
    
    //index可以从第几个参数中获得到主键 
    public int[] keyIndex();
    
    //index可以从第几个参数中获得操作人
    public int userIndex();
    
    //主键的属性名 如 ：id 、taskId 等
    public String[] primaryKey();
}
