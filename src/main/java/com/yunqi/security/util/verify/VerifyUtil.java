package com.yunqi.security.util.verify;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

import com.yunqi.security.exception.ExceptionUtil;
import com.yunqi.security.util.sign.Base64;

import lombok.extern.slf4j.Slf4j;

/**
 * @author qsx
 * @date 2020-07-28 18:28:44
 */
@Slf4j
@Component
public class VerifyUtil {
    /**
     * 将生成的图片转成base64编码的字符串
     */
    public String getBase64(BufferedImage bufferedImage) {
	String base64 = null;
	try {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();// io流
	    ImageIO.write(bufferedImage, "png", baos);// 写入流中
	    base64 = Base64.encode(baos.toByteArray());
	    log.info("base64编码后的图片字符串" + base64);
	} catch (IOException e) {
	    ExceptionUtil.printStackTrace(e, log);
	}
	return base64;
    }
}
