package com.yunqi.security.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunqi.security.entity.Role;
import com.yunqi.security.entity.UserRole;
import com.yunqi.security.vo.UserRoleVO;

/**
 * @author qsx
 * @date 2020-07-28 15:25:04
 */
public interface UserRoleService extends IService<UserRole> {

    int insertUserRole(UserRole userRole);

    int updateUserRole(UserRole userRole);

    int deleteUserRole(UserRole userRole);

    /**
     * @param userId
     * @return
     */
    List<Role> getUserRoles(Long userId);

    List<Role> getUserRoles(String username);

    /**
     * 给用户添加角色
     * 
     * @param userRole
     * @return
     */
    List<Long> grantRoles2User(UserRoleVO userRole);
}
