package com.yunqi.security.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;

/**
 * 角色相关的配置
 * 
 * @author qsx
 * @date 2020-07-16 17:38:04
 */
@Configuration
public class SecurityRoleConfig {

    /**
     * 配置角色继承关系
     * <p>
     * 在配置时，需要给角色手动加上 ROLE_ 前缀。上面的配置表示 ROLE_admin 自动具备 ROLE_user 的权限
     */
    @Bean
    RoleHierarchy roleHierarchy() {
	RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
	hierarchy.setHierarchy("ROLE_admin > ROLE_user > ROLE_student");
	return hierarchy;
    }
}
