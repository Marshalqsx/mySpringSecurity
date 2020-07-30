package com.yunqi.security.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 短信登录验证信息封装类
 * 
 * @since 1.0
 */
public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {

    /**
     *
     */
    private static final long serialVersionUID = 901776948843213167L;
    /**
     * 手机号
     */
    private final Object principal;

    /**
     * 验证码
     */
    private String smsCode;

    /**
     * SmsCodeAuthenticationFilter中构建的未认证的Authentication
     * 
     * @param phone
     * @param smsCode
     */
    public SmsCodeAuthenticationToken(String phone, String smsCode) {
	super(null);
	this.principal = phone;
	this.setSmsCode(smsCode);
	setAuthenticated(false);
    }

    /**
     * SmsCodeAuthenticationProvider中构建已认证的Authentication
     * 
     * @param principal
     * @param smsCode
     * @param authorities
     */
    public SmsCodeAuthenticationToken(Object principal, String smsCode,
	    Collection<? extends GrantedAuthority> authorities) {
	super(authorities);
	this.principal = principal;
	this.setSmsCode(smsCode);
	super.setAuthenticated(true); // must use super, as we override
    }

    @Override
    public Object getCredentials() {
	return null;
    }

    @Override
    public Object getPrincipal() {
	return this.principal;
    }

    /**
     * @param isAuthenticated
     * @throws IllegalArgumentException
     */
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
	if (isAuthenticated) {
	    throw new IllegalArgumentException(
		    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
	}

	super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
	super.eraseCredentials();
    }

    /**
     * @return the smsCode
     */
    public String getSmsCode() {
	return smsCode;
    }

    /**
     * @param smsCode the smsCode to set
     */
    public void setSmsCode(String smsCode) {
	this.smsCode = smsCode;
    }
}
