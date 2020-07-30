package com.yunqi.security.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.yunqi.security.security.util.HandlerResponseUtil;
import com.yunqi.security.util.response.AjaxResponseResult;
import com.yunqi.security.util.response.ResponseHttpStatus;


/**
 * 用户未登录处理类
 * 
 * @author qsx
 * @date 2020-07-20 16:08:09
 */
@Component
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Autowired
    private HandlerResponseUtil handlerResponseUtil;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.security.web.AuthenticationEntryPoint#commence(javax.
     * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * org.springframework.security.core.AuthenticationException)
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
	    AuthenticationException authException) throws IOException, ServletException {
	handlerResponseUtil.responseJson(response,
		new AjaxResponseResult<>(ResponseHttpStatus.UNAUTHORIZED.value(), "未授权！", null));

    }

}
