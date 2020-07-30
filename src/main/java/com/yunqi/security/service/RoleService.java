 package com.yunqi.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunqi.security.entity.Role;

/**
 * @author qsx
 * @date 2020-07-27 18:04:43
 */
public interface RoleService extends IService<Role> {

    int insertRole(Role role);

    int updateRole(Role role);

    int deleteRole(Role role);

}
