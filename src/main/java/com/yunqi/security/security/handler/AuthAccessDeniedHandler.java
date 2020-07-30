package com.yunqi.security.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.yunqi.security.security.util.HandlerResponseUtil;
import com.yunqi.security.util.response.AjaxResponseResult;
import com.yunqi.security.util.response.ResponseHttpStatus;


/**
 * @Description 暂无权限处理类
 * @author qsx
 * @date 2020-07-20 16:07:25
 */
@Component
public class AuthAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    private HandlerResponseUtil handlerResponseUtil;

    /*
     * @see
     * org.springframework.security.web.access.AccessDeniedHandler#handle(javax.
     * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * org.springframework.security.access.AccessDeniedException)
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
	    AccessDeniedException accessDeniedException) throws IOException, ServletException {
	handlerResponseUtil.responseJson(response,
		new AjaxResponseResult<>(ResponseHttpStatus.FORBIDDEN.value(), "暂无权限！", null));

    }

}
