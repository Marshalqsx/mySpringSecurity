package com.yunqi.security.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

/**
 * spring security 防火墙相关的配置类，默认情况不用修改下面的配置
 * 
 * @author qsx
 * @date 2020-07-17 14:31:55
 */
@Configuration
public class SecurityHttpFirewallConfig {

    @Bean
    HttpFirewall httpFirewall() {
	// 以下所有配置的限制都是对requestURI的限制，并不能限制请求参数
	StrictHttpFirewall firewall = new StrictHttpFirewall();
	// 允许请求地址中出现编码后的双斜杠：“"%2F%2F”，如http://localhost:8080//hello
	firewall.setAllowUrlEncodedDoubleSlash(true);
	// 许请求地址中出现分号：“ ;”
	firewall.setAllowSemicolon(true);
	// 许请求地址中出现编码后的百分号：“"%25”
	firewall.setAllowUrlEncodedPercent(true);
	// 许请求地址中出现反斜杠：“\”
	firewall.setAllowBackSlash(true);
	// 允许请求地址中出现编码后的斜杠：“"%2F”
	firewall.setAllowUrlEncodedSlash(true);
	// 允许请求地址中出现编码后的点号 ：“"%2E”
	firewall.setAllowUrlEncodedPeriod(true);
	return firewall;
    }
}
