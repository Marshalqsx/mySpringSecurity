package com.yunqi.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunqi.security.entity.Role;
import com.yunqi.security.mapper.RoleMapper;
import com.yunqi.security.service.RoleService;

/**
 * @author qsx
 * @date 2020-07-28 15:26:15
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int insertRole(Role role) {
	return roleMapper.insert(role);
    }

    @Override
    public int updateRole(Role role) {
	return roleMapper.updateById(role);
    }

    @Override
    public int deleteRole(Role role) {
	return roleMapper.deleteById(role.getId());
    }

}
