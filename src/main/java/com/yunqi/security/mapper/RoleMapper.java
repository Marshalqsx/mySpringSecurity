package com.yunqi.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yunqi.security.entity.Role;

/**
 * @author qsx
 * @date 2020-07-27 18:00:33
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * @param username
     * @return
     */
    @Select("SELECT r.* FROM `t_user` u LEFT JOIN t_user_role ur ON u.id = ur.user_id LEFT JOIN t_role r ON ur.role_id = r.id WHERE u.username = #{username}")
    List<Role> getUserRoles(String username);

}
