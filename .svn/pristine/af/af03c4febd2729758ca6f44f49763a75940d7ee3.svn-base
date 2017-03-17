/*
 * Copyright (C) 2013-2033 Fable Limited.
 */
package com.fable.dsp.spring.extend;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 
 * @author xieruidong 2014年3月18日 下午4:09:36
 */
public class JsonMapperArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Logger log = LoggerFactory.getLogger(JsonMapperArgumentResolver.class);

    private ObjectMapper objectMapper;
    private static final String PATH_DELIMITER = "/";

    public JsonMapperArgumentResolver() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Json.class);
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        try {
            Json jsonAnn = parameter.getParameterAnnotation(Json.class);
            String path = jsonAnn.path();
            String allParam = getAllParam(webRequest);
            if (allParam == null) {
                return null;
            }
            JsonNode node = objectMapper.readTree(allParam);
            if (path == null || "".equals(path)) {
                path = parameter.getParameterName();
                if (node.has(path)) {
                    return objectMapper.readValue(node.path(path), getReferenceType(parameter, jsonAnn));
                }
                return objectMapper.readValue(allParam, getReferenceType(parameter, jsonAnn));
            } else {
                String[] paths = StringUtils.split(path, PATH_DELIMITER);
                for (String p : paths) {
                    node = node.path(p);
                }
                if (node == null) {
                    return null;
                }
                return objectMapper.readValue(node, getReferenceType(parameter, jsonAnn));
            }
        } catch (Exception e) {
            log.error("can't generate param [" + parameter.getParameterName() + "] for url "
                    + webRequest.getNativeRequest(HttpServletRequest.class).getServletPath() + ", and source input is "
                    + getAllParam(webRequest));
            throw e;
        }
    }

    /**
     * 获取反射的对象类型
     */
    private JavaType getReferenceType(MethodParameter parameter, Json annt) {
        Class[] types = annt.types();
        if (types.length == 1 && types[0].equals(Object.class)) {
            return objectMapper.getTypeFactory().constructType(parameter.getParameterType());
        }
        if (Collection.class.isAssignableFrom(parameter.getParameterType())) {
            return objectMapper.getTypeFactory().constructCollectionType(
                    (Class<? extends Collection>) parameter.getParameterType(), types[0]);
        } else if (Map.class.isAssignableFrom(parameter.getParameterType())) {
            if (types.length >= 2)
                return objectMapper.getTypeFactory().constructMapType(
                        (Class<? extends Map>) parameter.getParameterType(), types[0], types[1]);
            else
                return objectMapper.getTypeFactory().constructMapType(
                        (Class<? extends Map>) parameter.getParameterType(), types[0], Object.class);
        }

        StringBuilder buffer = new StringBuilder();
        buffer.append("Unsuppored Reference To JavaType : ").append(parameter.getParameterType().getName()).append("<");
        int i = 0;
        for (Class type : types) {
            if (i++ > 0)
                buffer.append(",");
            buffer.append(type.getSimpleName());
        }
        buffer.append(">");
        throw new UnsupportedOperationException(buffer.toString());
    }

    /**
     * 获取HttpServletRequest参数体
     * 
     * @param webRequest
     * @return
     * @throws IOException
     */
    private String getAllParam(NativeWebRequest webRequest) throws IOException {
        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        String method = httpServletRequest.getMethod();
        if (method.equals("GET") || method.equals("DELETE")) {
            return httpServletRequest.getQueryString();
        }
        StringBuilder buffer = new StringBuilder();
        String line;
        BufferedReader reader = httpServletRequest.getReader();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

}
