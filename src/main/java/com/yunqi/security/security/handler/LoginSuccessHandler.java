package com.yunqi.security.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.yunqi.security.entity.User;
import com.yunqi.security.security.util.HandlerResponseUtil;
import com.yunqi.security.util.response.AjaxResponseResult;
import com.yunqi.security.util.response.ResponseHttpStatus;


/**
 * 登陆成功的handler,登陆成功后向前端返回Json字符串
 * 
 * @author qsx
 * @date 2020-07-16 14:30:35
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private HandlerResponseUtil handlerResponseUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	    Authentication authentication) throws IOException, ServletException {
	// 返回页面内容自己定义，此处只返回了个principal
	User principal = (User) authentication.getPrincipal();
//	handlerResponseUtil.responseJson(response, new AjaxResponseResult<>(ResponseHttpStatus.OK.value(), "登陆成功！",
//		JWTTokenUtil.createAccessToken(principal)));
    }
}
