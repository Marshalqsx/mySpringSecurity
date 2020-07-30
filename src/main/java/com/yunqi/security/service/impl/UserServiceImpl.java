package com.yunqi.security.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunqi.security.entity.User;
import com.yunqi.security.mapper.UserMapper;
import com.yunqi.security.service.UserService;

/**
 * @author qsx
 * @date 2020-07-27 10:40:00
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private BaseMapper<User> baseMapper;

    @Override
    public int insertUser(User user) {
	return baseMapper.insert(user);
    }

    @Override
    public int updateUser(User user) {
	return baseMapper.updateById(user);
    }

    @Override
    public int deleteUser(User user) {
	return baseMapper.deleteById(user.getId());
    }

    @Override
    public User findUserByName(String username) {
	QueryWrapper<User> queryWrapper = new QueryWrapper<>();
	//根据username字段查询User
	queryWrapper.eq("username", username).or().eq("mobile", username).or().eq("email", username);
	return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public IPage<User> getUserPage(Page<User> page, User user) {
	QueryWrapper<User> queryWrapper = new QueryWrapper<>();
	Map<String, Object> params = new HashMap<>();
	queryWrapper.allEq(params);
	// TODO 没有实现
	return baseMapper.selectPage(page, queryWrapper);
    }
}
