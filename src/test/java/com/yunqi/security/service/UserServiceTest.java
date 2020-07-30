 package com.yunqi.security.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.yunqi.security.entity.User;

/**
 * @author qsx
 * @date 2020-07-28 10:27:25
 */
 @RunWith(SpringRunner.class)
 @SpringBootTest
 @AutoConfigureMockMvc
public class UserServiceTest {

     @Autowired
     private UserService userService;
     
     @Autowired
     private BCryptPasswordEncoder BCryptPasswordEncoder;
     
     @Test
     public void insertUserTest() {
	 User user = new User();
	 user.setAccountNonExpired(true);
	 user.setAccountNonLocked(true);
	 user.setCredentialsNonExpired(true);
	 user.setEmail("931086187@qq.com");
	 user.setEnabled(true);
	 user.setMobile("15271863915");
	 user.setPassword(BCryptPasswordEncoder.encode("123456"));
	 user.setUsername("qsx");
	 userService.insertUser(user);
     }
}
