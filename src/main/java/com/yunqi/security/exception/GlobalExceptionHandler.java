package com.yunqi.security.exception;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.yunqi.security.util.response.AjaxResponseResult;
import com.yunqi.security.util.response.ResponseHttpStatus;

import lombok.extern.slf4j.Slf4j;

/**
 * 捕捉全局异常
 * 
 * @author qsx
 * @date 2020-07-16 14:15:11
 * 
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 全局异常捕捉处理
     */
    @ExceptionHandler(value = Exception.class)
    public AjaxResponseResult<String> globalExceptionHandler(Exception ex) {
	AjaxResponseResult<String> result = new AjaxResponseResult<>();
	result.setCode(ResponseHttpStatus.INTERNAL_SERVER_ERROR.value());
	result.setMsg(ex.getMessage());
	if (ex instanceof ServletRequestBindingException) {
	    result.setMsg("parameter missing!");
	}
	log.info("====================" + ex.toString() + ":" + ex.getMessage());
	ExceptionUtil.printStackTrace(ex, log);
	return result;
    }

    /**
     * 拦截捕捉自定义异常 CustomizedException.class
     */
    @ExceptionHandler(value = CustomizedException.class)
    public AjaxResponseResult<String> customizedExceptionHandler(CustomizedException ex) {
	AjaxResponseResult<String> result = new AjaxResponseResult<>();
	ex.setCode(ResponseHttpStatus.INTERNAL_SERVER_ERROR.value());
	result.setCode(ex.getCode());
	result.setMsg(ex.getMsg());
	log.info("====================" + ex.toString() + ":" + ex.getMessage());
	ExceptionUtil.printStackTrace(ex, log);
	return result;
    }
}
