package com.yunqi.security.security.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.yunqi.security.security.provider.VerifyCodeAuthenticationProvider;
import com.yunqi.security.service.impl.SysUserDetailsService;

/**
 * @author qsx
 * @date 2020-07-16 11:13:11
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder(10);
    }
    
    @Autowired
    SysUserDetailsService sysUserDetailsService;
    
    @Autowired
    SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    /**
     * 核心过滤器配置方法
     * <p>
     * WebSecurity 是基于 Servlet Filter 用来配置 springSecurityFilterChain
     * <p>
     * springSecurityFilterChain 又被委托给了 Spring Security 核心过滤器 Bean
     * DelegatingFilterProxy
     * 
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
	// web.ignoring() 用来配置忽略掉的 URL 地址，一般对于静态文件，我们可以采用此操作，接口不应在此处配置忽略。
	web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
    }

    /**
     * 配置认证管理器AuthenticationManager
     * <p>
     * 所有 UserDetails 相关的它都管，包含 PasswordEncoder 密码机
     * @throws Exception 
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(sysUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

	http.authorizeRequests()
		.antMatchers("/verifycode/**","/auth/mobile").permitAll()
		// 不进行权限验证的请求或资源(从配置文件中读取)
//		.anyRequest().permitAll()
		// 其他的需要登陆后才能访问
		.anyRequest().authenticated()
		.and()
		// 登陆-------------------
		.formLogin().loginProcessingUrl("/login/userLogin") // 设置登陆接口的请求url
		.and()
                .apply(smsCodeAuthenticationSecurityConfig) // 加上短信认证的方式
                .and()
		// 登出-------------------
		.logout() // 登出
		.logoutUrl("/login/userLogout") // 登出接口的url
		.and().csrf().disable() // Cross-site request forgery --> disable
	;
    }

    // 定义认证的Provider
    @Bean
    VerifyCodeAuthenticationProvider verifyCodeAuthenticationProvider() {
	VerifyCodeAuthenticationProvider verifyCodeAuthenticationProvider = new VerifyCodeAuthenticationProvider();
	// 自己的加密方式
	verifyCodeAuthenticationProvider.setPasswordEncoder(passwordEncoder());
	// UserDetailsService
	verifyCodeAuthenticationProvider.setUserDetailsService(sysUserDetailsService);
        return verifyCodeAuthenticationProvider;
    }
    
    // AuthenticationManager 中加入自己定义的Provider
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        ProviderManager manager = new ProviderManager(Arrays.asList(verifyCodeAuthenticationProvider()));
        return manager;
    }
}
