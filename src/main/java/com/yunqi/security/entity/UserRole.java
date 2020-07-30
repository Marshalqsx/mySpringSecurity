package com.yunqi.security.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @author qsx
 * @date 2020-07-28 15:22:56
 */
@TableName(value = "t_user_role")
@Data
public class UserRole {

    @TableId
    private Long id;

    private Long userId;

    private Long roleId;
}
