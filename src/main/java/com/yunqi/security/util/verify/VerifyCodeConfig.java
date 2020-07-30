package com.yunqi.security.util.verify;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

/**
 * @author qsx
 * @date 2020-07-28 17:28:44
 */
@Configuration
public class VerifyCodeConfig {

    @Bean
    Producer verifyCode() {
	Properties properties = new Properties();

	// 图片边框，合法值：yes , no
	properties.setProperty("kaptcha.border", "yes");
	// 边框颜色，合法值： r,g,b (and optional alpha) 或者 white,black,blue.
	properties.setProperty("kaptcha.border.color", "red");
	// 图片长度
	properties.setProperty("kaptcha.image.width", "150");
	// 图片宽度
	properties.setProperty("kaptcha.image.height", "50");
	// 图片内容取值范围
	properties.setProperty("kaptcha.textproducer.char.string", "0123456789");
	// 验证码的长度
	properties.setProperty("kaptcha.textproducer.char.length", "4");
	// 字体颜色，合法值： r,g,b 或者 white,black,blue.
	properties.setProperty("kaptcha.textproducer.font.color", "black");
	// 字体大小
	properties.setProperty("kaptcha.textproducer.font.size", "45");

	// 干扰 颜色，合法值： r,g,b 或者 white,black,blue.
	properties.setProperty("kaptcha.noise.color", "blue");
	// 背景颜色渐变，开始颜色
	properties.setProperty("kaptcha.background.clear.from", "YELLOW");
	// 背景颜色渐变， 结束颜色
	properties.setProperty("kaptcha.background.clear.to", "WHITE");
	/**
	 * <p>
	 * com.google.code.kaptcha.impl.WaterRipple
	 * <p>
	 * com.google.code.kaptcha.impl.FishEyeGimpy
	 * <p>
	 * com.google.code.kaptcha.impl.ShadowGimpy
	 * 
	 */
	properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");

	Config config = new Config(properties);
	DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
	defaultKaptcha.setConfig(config);
	return defaultKaptcha;
    }
}
