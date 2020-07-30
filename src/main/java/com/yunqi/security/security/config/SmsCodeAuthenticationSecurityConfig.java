 package com.yunqi.security.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.yunqi.security.security.filter.SmsCodeAuthenticationFilter;
import com.yunqi.security.security.provider.SmsVerificationCodeAuthenticationProvider;
import com.yunqi.security.service.impl.SysUserDetailsService;

/**
 * @author qsx
 * @date 2020-07-30 09:09:35
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
   
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private SysUserDetailsService userDetailsService;
    
    @Autowired
    SmsVerificationCodeAuthenticationProvider smsCodeAuthenticationProvider;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //自定义SmsCodeAuthenticationFilter过滤器
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        //设置自定义SmsCodeAuthenticationProvider的认证器userDetailsService
        smsCodeAuthenticationProvider.setSysUserDetailsService(userDetailsService);
        //在UsernamePasswordAuthenticationFilter过滤前执行
        http.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
