package com.yunqi.security.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.yunqi.security.security.util.HandlerResponseUtil;
import com.yunqi.security.util.response.AjaxResponseResult;
import com.yunqi.security.util.response.ResponseHttpStatus;


/**
 * @author qsx
 * @date 2020-07-20 17:39:25
 */
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private HandlerResponseUtil handlerResponseUtil;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
	    throws IOException, ServletException {
	SecurityContextHolder.clearContext();
	handlerResponseUtil.responseJson(response,
		new AjaxResponseResult<>(ResponseHttpStatus.OK.value(), "登出成功！", null));
    }

}
