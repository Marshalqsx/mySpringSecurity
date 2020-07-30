 package com.yunqi.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yunqi.security.entity.Role;
import com.yunqi.security.service.RoleService;
import com.yunqi.security.util.response.AjaxResponseResult;
import com.yunqi.security.util.response.ResponseHttpStatus;

/**
 * @author qsx
 * @date 2020-07-28 15:21:05
 */
@RestController
public class RoleController {

     @Autowired
     private RoleService roleService;

     @GetMapping("role/{id}")
     public AjaxResponseResult<Role> getRoleById(@PathVariable Long id) {
 	return new AjaxResponseResult<>(ResponseHttpStatus.OK.value(), roleService.getById(id));
     }

     @PostMapping("role/role")
     public AjaxResponseResult<Long> insertRole(@RequestBody Role role) {
 	roleService.insertRole(role);
 	return new AjaxResponseResult<>(ResponseHttpStatus.OK.value(), role.getId());
     }
}
