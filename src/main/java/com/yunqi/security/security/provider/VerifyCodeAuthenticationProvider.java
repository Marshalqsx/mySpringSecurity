package com.yunqi.security.security.provider;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yunqi.security.util.redis.RedisUtil;

/**
 * 在DaoAuthenticationProvider之前加一个图片验证码验证
 * @author qsx
 * @date 2020-07-28 17:27:20
 */
public class VerifyCodeAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 图片验证码验证
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
	    UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	String code = req.getParameter("code");
	String randomStr = req.getParameter("randomStr");
	String verifyCode = redisUtil.getString(randomStr);
	if (code == null || verifyCode == null || !code.equals(verifyCode)) {
	    throw new AuthenticationServiceException("验证码错误");
	}
	super.additionalAuthenticationChecks(userDetails, authentication);
    }
}
