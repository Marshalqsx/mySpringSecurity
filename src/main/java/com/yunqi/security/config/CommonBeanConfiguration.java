package com.yunqi.security.config;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

/**
 * 此类用来配置通用Bean对象
 * 
 * @author qsx
 * @date 2020-07-16 14:30:35
 */
@Configuration
public class CommonBeanConfiguration {

    @Bean
    public RestTemplate restTemplate() {
	RestTemplateBuilder rtBuilder = new RestTemplateBuilder();
	rtBuilder.setConnectTimeout(Duration.ofSeconds(3 * 1000L));// 链接超时时间
	rtBuilder.setReadTimeout(Duration.ofSeconds(3 * 1000L)); // 读超时时间
	return rtBuilder.build();
    }

    @Bean
    public Gson gson() {
	return new Gson();
    }
    
}
