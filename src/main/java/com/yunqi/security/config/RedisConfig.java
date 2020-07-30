package com.yunqi.security.config;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author 作者 :QSX
 * @version 创建时间：2019年8月1日 上午10:44:48 Redis配置Bean对象
 */
//@Configuration
//@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig
{
    @Bean
    public RedisTemplate<String, ?> redisTemplate(RedisConnectionFactory factory)
    {
        RedisTemplate<String, ?> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        template.setEnableTransactionSupport(true);
        return template;
    }

}
