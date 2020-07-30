package com.yunqi.security.vo;

import java.util.List;

import com.yunqi.security.entity.Role;
import com.yunqi.security.entity.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author qsx
 * @date 2020-07-28 15:34:50
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class UserRoleVO extends User {

    private static final long serialVersionUID = 3549164404566030370L;
    
    private List<Role> roles;
}
