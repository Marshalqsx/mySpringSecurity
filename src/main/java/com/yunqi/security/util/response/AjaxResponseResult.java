package com.yunqi.security.util.response;



import com.yunqi.security.exception.ExceptionUtil;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
public class AjaxResponseResult<T> {

    private int code = 200;

    private String msg;

    private T data;

    public void setException(Exception ex) {
	code = ResponseHttpStatus.INTERNAL_SERVER_ERROR.value();
	msg = ex.getMessage();
	ExceptionUtil.printStackTrace(ex, log);
    }

    public int getCode() {
	return code;
    }

    public void setCode(int code) {
	this.code = code;
    }

    public String getMsg() {
	return msg;
    }

    public void setMsg(String msg) {
	this.msg = msg;
    }

    public T getData() {
	return data;
    }

    public void setData(T data) {
	this.data = data;
    }

    public AjaxResponseResult(int code, String msg, T data) {
	super();
	this.code = code;
	this.msg = msg;
	this.data = data;
    }

    public AjaxResponseResult(int code, T data) {
	super();
	this.code = code;
	this.data = data;
    }

    public AjaxResponseResult(String msg, T data) {
	super();
	this.msg = msg;
	this.data = data;
    }

    public AjaxResponseResult(T data) {
	super();
	this.data = data;
    }

    public AjaxResponseResult() {
	super();
    }
}
