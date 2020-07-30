package com.yunqi.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.yunqi.security.entity.User;
import com.yunqi.security.service.UserService;
import com.yunqi.security.util.response.AjaxResponseResult;
import com.yunqi.security.util.response.ResponseHttpStatus;

/**
 * @author qsx
 * @date 2020-07-28 09:08:01
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("user/{id}")
    public AjaxResponseResult<User> getUserById(@PathVariable Long id) {
	return new AjaxResponseResult<>(ResponseHttpStatus.OK.value(), userService.getById(id));
    }

    @PostMapping("user/user")
    public AjaxResponseResult<Long> insertUser(@RequestBody User user) {
	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	userService.insertUser(user);
	return new AjaxResponseResult<>(ResponseHttpStatus.OK.value(), user.getId());
    }

    @GetMapping("user")
    public AjaxResponseResult<User> getUserByUsername(@RequestParam String username) {
	return new AjaxResponseResult<>(ResponseHttpStatus.OK.value(), userService.findUserByName(username));
    }
}
