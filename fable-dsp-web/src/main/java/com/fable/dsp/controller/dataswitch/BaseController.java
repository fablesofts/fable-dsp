package com.fable.dsp.controller.dataswitch;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fable.dsp.common.exception.TaskException;

public class BaseController {
	@ExceptionHandler
	public String exp(HttpServletRequest request,Exception ex){
	request.setAttribute("ex", ex);
	if(ex instanceof TaskException){
		return "error-task";
	}else{
		return "error-commonweb";
	}
}
}
