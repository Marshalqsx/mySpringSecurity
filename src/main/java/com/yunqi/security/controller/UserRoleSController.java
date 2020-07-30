package com.yunqi.security.controller;

import static com.yunqi.security.util.check.CommonJudgeUtils.isNullOrEmpty;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yunqi.security.entity.Role;
import com.yunqi.security.service.UserRoleService;
import com.yunqi.security.util.response.AjaxResponseResult;
import com.yunqi.security.util.response.ResponseHttpStatus;
import com.yunqi.security.vo.UserRoleVO;

/**
 * @author qsx
 * @date 2020-07-28 15:22:39
 */
@RestController
public class UserRoleSController {

    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("userRole")
    public AjaxResponseResult<List<Long>> insertUserRole(@RequestBody UserRoleVO userRole) {
	if (isNullOrEmpty(userRole.getRoles()) || userRole.getId() == null) {
	    return new AjaxResponseResult<>(ResponseHttpStatus.BAD_REQUEST.value(), "请求参数错误", null);
	}
	return new AjaxResponseResult<>(ResponseHttpStatus.OK.value(), userRoleService.grantRoles2User(userRole));
    }

    @GetMapping("userRole/{userId}")
    public AjaxResponseResult<List<Role>> getUserById(@PathVariable Long userId) {
	List<Role> roles = userRoleService.getUserRoles(userId);
	return new AjaxResponseResult<>(ResponseHttpStatus.OK.value(), roles);
    }

    @GetMapping("userRole")
    public AjaxResponseResult<List<Role>> getUserById(@RequestParam String username) {
	List<Role> roles = userRoleService.getUserRoles(username);
	return new AjaxResponseResult<>(ResponseHttpStatus.OK.value(), roles);
    }
}
