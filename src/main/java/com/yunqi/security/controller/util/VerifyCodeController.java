package com.yunqi.security.controller.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.Producer;
import com.yunqi.security.constant.RedisConstant;
import com.yunqi.security.util.RandomUtil;
import com.yunqi.security.util.redis.RedisUtil;
import com.yunqi.security.util.response.AjaxResponseResult;
import com.yunqi.security.util.response.ResponseHttpStatus;
import com.yunqi.security.util.verify.VerifyUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author qsx
 * @date 2020-07-28 17:33:57
 */
@RestController
@Slf4j
public class VerifyCodeController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private Producer producer;

    @Autowired
    private VerifyUtil verifyUtil;

    /**
     * 前端传个随机数过来，取图片验证码，验证码有效期单位为分钟
     */
    @GetMapping("/verifycode/string")
    public AjaxResponseResult<String> getVerifyCode(@RequestParam String randomStr) throws IOException {
	String text = producer.createText();
	log.info("随机数：{}对应的图片验证码的值是：{}", randomStr, text);
	BufferedImage image = producer.createImage(text);
	String base64pic = verifyUtil.getBase64(image);
	log.debug("图片验证码base64串为：{}", base64pic);
	redisUtil.setString(randomStr, text, 5, TimeUnit.MINUTES);
	return new AjaxResponseResult<>(ResponseHttpStatus.OK.value(), base64pic);
    }

    /**
     * 直接给页面回写个图片验证码
     */
    @GetMapping("/verifycode/image")
    public void getVerifyCode(HttpServletResponse resp, HttpSession session, @RequestParam String randomStr)
	    throws IOException {
	resp.setContentType("image/jpeg");
	String text = producer.createText();
	redisUtil.setString(randomStr, text, 5, TimeUnit.MINUTES);
	BufferedImage image = producer.createImage(text);
	try (ServletOutputStream out = resp.getOutputStream()) {
	    ImageIO.write(image, "jpg", out);
	}
    }

    /**
     * 根据手机号获取短信验证码
     * */
    @GetMapping("/verifycode/sms")
    public void getVerificationCode(@RequestParam String phone) throws IOException {
	Integer[] random = RandomUtil.random(10, 4);
	StringBuffer sbf = new StringBuffer();
	for (Integer i : random) {
	    sbf.append(i);
	}
	redisUtil.setString(RedisConstant.SMSPREFIX + phone, sbf.toString(), 10, TimeUnit.MINUTES);
	log.info("向手机号为：{}的用户发送的短信验证码为：{}", phone, sbf.toString());
	// TODO 发送短信验证码
    }
}
