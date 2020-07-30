package com.yunqi.security.entity;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 
 * 用户
 * 
 * @author qsx
 * @date 2020-07-16 18:02:30
 */
@TableName(value = "t_user")
@Data
public class User implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = -1704229914894479839L;

    @TableId
    private Long id;

    private String username;

    private String password;

    private String mobile;

    private String email;

    /**
     * 账户是否没有过期
     */
    private boolean accountNonExpired;

    /**
     * 账户是否没有被锁定
     */
    private boolean accountNonLocked;

    /**
     * 密码是否没有过期
     */
    private boolean credentialsNonExpired;

    /**
     * 以及账户是否可用
     */
    private boolean enabled;

    @TableField(exist=false)
    private transient  List<GrantedAuthority> authorities;



    @Override
    public String getPassword() {
	return password;
    }

    @Override
    public String getUsername() {
	return username;
    }

    @Override
    public boolean isAccountNonExpired() {
	return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
	return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
	return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
	return enabled;
    }

    /**
     * 
     * 自动生成的equals方法，只依赖username字段
     * 
     * @see org.springframework.security.core.userdetails.User
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	User other = (User) obj;
	if (username == null) {
	    if (other.username != null)
		return false;
	} else if (!username.equals(other.username))
	    return false;
	return true;
    }

    /**
     * 自动生成的hashCode方法，只依赖username字段
     * 
     * @see org.springframework.security.core.userdetails.User
     */
    @Override
    public int hashCode() {
	int result = 1;
	result = 31 * result + ((username == null) ? 0 : username.hashCode());
	return result;
    }

    public User() {
    }

    public User(String username, String password, List<GrantedAuthority> authorities) {
	super();
	this.username = username;
	this.password = password;
	this.authorities = authorities;
    }
}
