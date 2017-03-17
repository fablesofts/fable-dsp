/*
 * Copyright (C) 2013-2033 Fable Limited.
 */
package com.fable.dsp.core.spring;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class DspPropertyConfigurer extends PropertyPlaceholderConfigurer {

	private static Map<String, String> propertiesMap;  
	  
    @Override  
    protected void processProperties(ConfigurableListableBeanFactory beanFactory,  
            Properties props)throws BeansException { 
  
        super.processProperties(beanFactory, props);  
        propertiesMap = new HashMap<String, String>();  
        for (Object key : props.keySet()) {  
            String keyStr = key.toString();  
            String value = props.getProperty(keyStr);  
            propertiesMap.put(keyStr, value);  
        }  
    }  
  
    public static String getDspProperty(String name) {  
        return propertiesMap.get(name);  
    }
    
    public static void setDspProperty(String name, String value) {  
        propertiesMap.put(name, value);  
    }
}
