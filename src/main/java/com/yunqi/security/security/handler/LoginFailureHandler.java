package com.yunqi.security.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.yunqi.security.security.util.HandlerResponseUtil;
import com.yunqi.security.util.response.AjaxResponseResult;
import com.yunqi.security.util.response.ResponseHttpStatus;


/**
 * 登陆失败的handler,登陆失败后向前端返回Json字符串,不同异常分别判断
 * 
 * @author qsx
 * @date 2020-07-16 15:35:27
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private HandlerResponseUtil handlerResponseUtil;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
	    AuthenticationException exception) throws IOException, ServletException {
	String exceptionMeg = null;
	if (exception instanceof LockedException) {
	    exceptionMeg = "账户被锁定，请联系管理员!";
	} else if (exception instanceof CredentialsExpiredException) {
	    exceptionMeg = "密码过期，请联系管理员!";
	} else if (exception instanceof AccountExpiredException) {
	    exceptionMeg = "账户过期，请联系管理员!";
	} else if (exception instanceof DisabledException) {
	    exceptionMeg = "账户被禁用，请联系管理员!";
	} else if (exception instanceof BadCredentialsException) {
	    exceptionMeg = "用户名或者密码输入错误，请重新输入!";
	}
	handlerResponseUtil.responseJson(response,
		new AjaxResponseResult<>(ResponseHttpStatus.INTERNAL_SERVER_ERROR.value(), exceptionMeg,null));
    }
}
