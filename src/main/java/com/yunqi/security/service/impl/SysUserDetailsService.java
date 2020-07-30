package com.yunqi.security.service.impl;

import static com.yunqi.security.util.check.CommonJudgeUtils.isNullOrEmpty;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yunqi.security.entity.Role;
import com.yunqi.security.entity.User;
import com.yunqi.security.service.UserRoleService;
import com.yunqi.security.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2018/1/4.
 *
 * @author zlf
 * @since 1.0
 */
@Service
@Slf4j
public class SysUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 从数据库查询用户信息
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	log.info("【SysUserDetailsService】 loadUserByUsername 表单登录用户名  username={}", username);
	User user = userService.findUserByName(username);
	if (user != null) {
	    List<Role> userRoles = userRoleService.getUserRoles(username);
	    List<GrantedAuthority> authorities = new ArrayList<>();
	    if (!isNullOrEmpty(authorities)) {
		for (Role role : userRoles) {
		    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		}
		user.setAuthorities(authorities);
	    }
	    return user;
	} else {
	    throw new BadCredentialsException("用户不存在！");
	}
    }
}
