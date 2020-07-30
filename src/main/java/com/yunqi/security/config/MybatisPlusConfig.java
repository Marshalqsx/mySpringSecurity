package com.yunqi.security.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis Plus配置类
 * @author qsx
 * @date 2020-07-27 17:55:28
 */
@Configuration
@MapperScan("com.yunqi.security.mapper")
public class MybatisPlusConfig {

}
