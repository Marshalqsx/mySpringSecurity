package com.yunqi.security.security.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.yunqi.security.security.token.SmsCodeAuthenticationToken;

/**
 * @author qsx
 * @date 2020-07-24 14:40:51
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_FORM_PHONE_KEY = "phone";

    public static final String SPRING_SECURITY_FORM_SMSCODE_KEY = "smsCode";

    /**
     * post请求
     */
    private boolean postOnly = true;

    /**
     * @param requiresAuthenticationRequestMatcher
     */
    public SmsCodeAuthenticationFilter() {
	super(new AntPathRequestMatcher("/auth/mobile", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
	    throws AuthenticationException, IOException, ServletException {
	// 判断是是不是post请求
	if (postOnly && !request.getMethod().equals("POST")) {
	    throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
	}

	String phone = obtainPhone(request);
	String smsCode = obtainSmsCode(request);

	if (phone == null) {
	    phone = "";
	}

	if (smsCode == null) {
	    smsCode = "";
	}

	phone = phone.trim();
	
	// 创建SmsCodeAuthenticationToken(未认证)
	SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(phone,smsCode);
	// 设置用户信息
	setDetails(request, authRequest);

	return this.getAuthenticationManager().authenticate(authRequest);
    }

    private String obtainPhone(HttpServletRequest request) {
	return request.getParameter(SPRING_SECURITY_FORM_PHONE_KEY);
    }

    private String obtainSmsCode(HttpServletRequest request) {
	return request.getParameter(SPRING_SECURITY_FORM_SMSCODE_KEY);
    }

    protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
	authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setPostOnly(boolean postOnly) {
	this.postOnly = postOnly;
    }
}
