//package com.yunqi.security.config;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
//
///**
// * 在里面定义两个 Bean 即可完成全局日期格式化处理
// * 
// * @author qsx
// * @date 2020-07-16 14:30:35
// */
//@Configuration
//public class LocalDateTimeSerializerConfig {
//
//    @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
//    private String pattern;
//
//    @Bean
//    public LocalDateTimeSerializer localDateTimeDeserializer() {
//	return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern));
//    }
//
//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
//	return builder -> builder.serializerByType(LocalDateTime.class, localDateTimeDeserializer());
//    }
//}
