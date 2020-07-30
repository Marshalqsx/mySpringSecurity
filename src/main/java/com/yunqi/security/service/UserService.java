package com.yunqi.security.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunqi.security.entity.User;

/**
 * @author qsx
 * @date 2020-07-27 10:39:32
 */
public interface UserService extends IService<User> {

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(User user);

    /**
     * 根据username查询user,username 可以是User对象中的username/mobile/email
     */
    User findUserByName(String username);

    IPage<User> getUserPage(Page<User> page, User user);
}
