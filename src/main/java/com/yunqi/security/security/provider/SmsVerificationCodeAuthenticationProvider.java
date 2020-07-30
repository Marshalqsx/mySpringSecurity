package com.yunqi.security.security.provider;

import static com.yunqi.security.util.check.CommonJudgeUtils.isNullOrEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.yunqi.security.constant.RedisConstant;
import com.yunqi.security.security.token.SmsCodeAuthenticationToken;
import com.yunqi.security.service.impl.SysUserDetailsService;
import com.yunqi.security.util.redis.RedisUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 短信验证码认证的实现方式(抄AbstractUserDetailsAuthenticationProvider)
 * 
 * @author qsx
 * @date 2020-07-21 10:41:42
 */
@Component
@Slf4j
public class SmsVerificationCodeAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private RedisUtil redisUtil;

    private SysUserDetailsService sysUserDetailsService;

    private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	Assert.isInstanceOf(SmsCodeAuthenticationToken.class, authentication,
		() -> messages.getMessage("AbstractUserDetailsAuthenticationProvider.onlySupports",
			"Only SmsCodeAuthenticationToken is supported"));

	SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;

	// 从请求中取手机号
	String phone = (String) authenticationToken.getPrincipal();
	// 从请求中取验证码
	String smsCode = (String) authenticationToken.getSmsCode();
	// 根据手机号从数据库查用户
	UserDetails user = getSysUserDetailsService().loadUserByUsername(phone);
	if (isNullOrEmpty(user.getUsername())) {
	    throw new BadCredentialsException("无法获取用户信息");
	}
	// 检查用户状态
	checkUserDetailStatus(user);
	// 检查短信验证码
	checkSmsVerificationCode(phone, smsCode);
	// 如果user不为空重新构建SmsCodeAuthenticationToken（已认证）
	SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, null,
		user.getAuthorities());
	authenticationResult.setDetails(authenticationToken.getDetails());
	return authenticationResult;
    }

    /**
     * 校验短信验证码是否匹配
     * 
     * @param phone
     * @param smsCode
     */
    private void checkSmsVerificationCode(String phone, String smsCode) {
	// 从redis中取短信验证码
	String verificationCode = redisUtil.getString(RedisConstant.SMSPREFIX + phone);
	log.info("手机号：{}，页面传过来的验证码是：{}，从redis中取到的短信验证码是：{}", phone, smsCode, verificationCode);
	if (isNullOrEmpty(verificationCode) || isNullOrEmpty(smsCode) || !verificationCode.equals(smsCode))
	    throw new BadCredentialsException("短信验证码不匹配！");
    }

    /**
     * 检查账户的4种状态是否正常
     */
    private void checkUserDetailStatus(UserDetails userDetails) {
	log.info("账户状态-->账户是否没有锁定：{}，密码是否没有过期：{}，账户是否没有过期：{}，账户是否没有被禁用：{}", userDetails.isAccountNonLocked(),
		userDetails.isCredentialsNonExpired(), userDetails.isAccountNonExpired(), userDetails.isEnabled());
	if (!userDetails.isAccountNonLocked()) {
	    throw new LockedException("账户已锁定，请联系管理员!");
	} else if (!userDetails.isCredentialsNonExpired()) {
	    throw new CredentialsExpiredException("密码过期，请联系管理员!");
	} else if (!userDetails.isAccountNonExpired()) {
	    throw new AccountExpiredException("账户过期，请联系管理员!");
	} else if (!userDetails.isEnabled()) {
	    throw new DisabledException("账户被禁用，请联系管理员!");
	}
    }

    /**
     * 只有Authentication为SmsCodeAuthenticationToken使用此Provider认证
     *
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
	return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    /**
     * @return the sysUserDetailsService
     */
    public SysUserDetailsService getSysUserDetailsService() {
	return sysUserDetailsService;
    }

    /**
     * @param sysUserDetailsService the sysUserDetailsService to set
     */
    public void setSysUserDetailsService(SysUserDetailsService sysUserDetailsService) {
	this.sysUserDetailsService = sysUserDetailsService;
    }

}