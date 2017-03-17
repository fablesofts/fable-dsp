package com.fable.dsp.common.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CommonExceptionHandler implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		Map<String,Object>model=new HashMap<String, Object>();
		model.put("ex",ex);
		//根据不同错误转向不同页面
		if(ex instanceof TaskException) {
			return new ModelAndView("../errors/500",model);
		}else{
			return new ModelAndView("../errors/500",model);
		}
	}
	
	
}
