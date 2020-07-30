package com.yunqi.security.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.yunqi.security.entity.User;



/**
 * Security相关的工具类
 * 
 * @author qsx
 * @date 2020-07-16 16:23:09
 */
public final class SecurityUtil {

    private SecurityUtil() {
    }

    /**
     * 获取当前登陆用户的用户信息
     */
    public static Object getPrincipal() {
	return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 获取当前用户的Id
     */
    public static Long getUserId() {
	User user = (User) getPrincipal();
	return user == null ? null : user.getId();
    }

    /**
     * 获取当前用户姓名
     */
    public static String getUserName() {
	User user = (User) getPrincipal();
	return user == null ? "" : user.getUsername();
    }

    /**
     * 更新用户信息
     */
    public static void setPrincipal(Authentication authResult) {
	SecurityContextHolder.getContext().setAuthentication(authResult);
    }

}
