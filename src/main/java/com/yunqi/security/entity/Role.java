package com.yunqi.security.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 角色
 * 
 * @author qsx
 * @date 2020-07-16 18:01:08
 */
@TableName(value = "t_role")
@Data
public class Role {

    @TableId
    private Long id;

    private String name;

    private String nameZh;

    public Role() {
    }

    public Role(String name) {
	this.name = name;
    }

    public Role(String name, String nameZh) {
	this.name = name;
	this.nameZh = nameZh;
    }

    public Role(Long id, String name, String nameZh) {
	this.id = id;
	this.name = name;
	this.nameZh = nameZh;
    }
}
