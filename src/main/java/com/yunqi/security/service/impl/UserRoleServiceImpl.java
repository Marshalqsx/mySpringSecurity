package com.yunqi.security.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunqi.security.entity.Role;
import com.yunqi.security.entity.UserRole;
import com.yunqi.security.mapper.RoleMapper;
import com.yunqi.security.mapper.UserRoleMapper;
import com.yunqi.security.service.UserRoleService;
import com.yunqi.security.vo.UserRoleVO;

/**
 * @author qsx
 * @date 2020-07-28 15:29:18
 */
@Service
@Transactional
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int insertUserRole(UserRole userRole) {
	return userRoleMapper.insert(userRole);
    }

    @Override
    public int updateUserRole(UserRole userRole) {
	return userRoleMapper.updateById(userRole);
    }

    @Override
    public int deleteUserRole(UserRole userRole) {
	return userRoleMapper.deleteById(userRole.getId());
    }

    @Override
    public List<Role> getUserRoles(Long userId) {
	QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
	queryWrapper.eq("userId", userId);
	List<UserRole> selectList = userRoleMapper.selectList(queryWrapper);
	return roleMapper.selectBatchIds(selectList.stream().map(UserRole::getRoleId).collect(Collectors.toList()));
    }

    @Override
    public List<Role> getUserRoles(String username) {
	return roleMapper.getUserRoles(username);
    }

    @Override
    public List<Long> grantRoles2User(UserRoleVO userRole) {
	List<Role> roles = userRole.getRoles();
	List<Long> userRoleIds = new ArrayList<>();
	roles.forEach(role -> {
	    UserRole userrole = new UserRole();
	    userrole.setRoleId(role.getId());
	    userrole.setUserId(userRole.getId());
	    userRoleMapper.insert(userrole);
	    userRoleIds.add(userrole.getId());
	});
	return userRoleIds;
    }
}
