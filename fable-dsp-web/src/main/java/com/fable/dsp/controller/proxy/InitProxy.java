package com.fable.dsp.controller.proxy;

import java.net.ProxySelector;

import javax.annotation.PostConstruct;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.fable.hamal.proxy.WebProxySelector;

@Service
public class InitProxy {

   /* @PostConstruct
    public void initialize(){
        
        System.out.println("################进入WEB代理##################");
        
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("proxy.xml");
        
        WebProxySelector webProxySelector = (WebProxySelector)context.getBean("webProxySelector");
        
        ProxySelector.setDefault(webProxySelector);
        
    }*/
    
}
