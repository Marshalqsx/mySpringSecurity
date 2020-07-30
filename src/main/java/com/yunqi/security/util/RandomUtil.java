package com.yunqi.security.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.Assert;

import com.yunqi.security.exception.CustomizedException;

public class RandomUtil {

	// 验证码字符集,0-9放2次是为了增加数字出现的概率
	private static final char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    

	// 字符数量
	private static final int SIZE = 4;

	// 干扰线数量
	private static final int LINES = 3;

	// 宽度
	private static final int WIDTH = 115;

	// 高度
	private static final int HEIGHT = 40;

	// 字体大小
	private static final int FONT_SIZE = 30;

	/**
	 * 生成随机验证码及图片 Object[0]：验证码字符串； Object[1]：验证码图片
	 */
	public static Object[] randomImage() throws IOException {
		StringBuilder sb = new StringBuilder();
		// 1.创建空白图片
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		// 2.获取图片画笔
		Graphics graphic = image.getGraphics();
		// 3.设置画笔颜色

		graphic.setColor(Color.WHITE);
		// 4.绘制矩形背景
		graphic.fillRect(0, 0, WIDTH, HEIGHT);
		// 5.画随机字符
		Random ran = new Random();
		for (int i = 0; i < SIZE; i++) {
			// 取随机字符索引
			int n = ran.nextInt(chars.length);
			// 设置随机颜色
			graphic.setColor(getRandomColor());
			// 设置字体大小
			graphic.setFont(new Font(null, Font.BOLD + Font.ITALIC, FONT_SIZE));
			// 画字符
			graphic.drawString(chars[n] + "", i * WIDTH / SIZE, HEIGHT * 3 / 4);
			// 记录字符
			sb.append(chars[n]);
		}
		// 6.画干扰线

		for (int i = 0; i < LINES; i++) { // 设置随机颜色
			graphic.setColor(getRandomColor()); // 随机画线
			graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT), ran.nextInt(WIDTH), ran.nextInt(HEIGHT));
		}

		// 7.返回验证码和图片
		// bufferImage->base64
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageIO.write(image, "png", outputStream);
		byte[] bytes = outputStream.toByteArray();// 转换成字节

		String base64Img = Base64.encodeBase64String(bytes);

		return new Object[] { sb.toString(), base64Img };
	}

	/**
	 * 随机取色
	 */
	public static Color getRandomColor() {
		Random ran = new Random();
		return new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256));
	}

	/**
	 * 取上限为max的int类型的随机数
	 */
	public static int random(int max) {
		Assert.isTrue(max > 0, "max只能是正整数");
		return (int) (Math.random() * max);
	}

	/**
	 * 取上限为max的int类型的随机数,返回内容不包括指定值
	 * 
	 * @param exclude 返回内容不包括exclude
	 */
	public static int nextRandom(int max, Integer... exclude) {
		Assert.isTrue(max > 0, "max只能是正整数");
		int i = random(max);
		int loopTimes = 0;
		List<Integer> excludes = new ArrayList<>(Arrays.asList(exclude));
		while (excludes.contains(i)) {
			i = random(max);
			loopTimes++;
			if (loopTimes > 1000) { // 如果循环次数过多（1000次已经够多了，不够再加）都没有取到值则主动抛出异常
				throw new CustomizedException("loop too many times to get the next value!");
			}
		}
		return i;
	}

	/**
	 * 取上限为max的int类型的随机数,返回指定个数的随机数
	 * @param max 上限
	 * @param returnNumber返回随机数的个数
	 */
	public static Integer[] random(int max, int returnNumber) {
		Assert.isTrue(max > 0, "max只能是正整数");
		Assert.isTrue(returnNumber > 0, "returnNumber只能是正整数");
		List<Integer> result = new ArrayList<>();
		if (max <= returnNumber) {
			for (int i = 0; i < max; i++) {
				result.add(i);
			}
		} else {
			for (int i = 0; i < returnNumber; i++) {
				int k = random(max);
				int loopTimes = 0;
				while (result.contains(k)) {
					k = random(max);
					loopTimes++;
					if (loopTimes > 1000) {
						throw new CustomizedException("loop too many times to get the next value!");
					}
				}
				result.add(k);
			}
		}
		return result.toArray(new Integer[result.size()]);
	}

	public static String getRandomString(int num) {
		StringBuilder sb = new StringBuilder();
		Random ran = new Random();
		for (int i = 0; i < num; i++) {
			int index = ran.nextInt(chars.length);
			sb.append(chars[index]);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
	    Integer[] random = random(10,4);
	    StringBuffer sbf = new StringBuffer();
		for (Integer i : random) {
		    sbf.append(i);
		}
	    System.out.println(sbf.toString());
	}
}
