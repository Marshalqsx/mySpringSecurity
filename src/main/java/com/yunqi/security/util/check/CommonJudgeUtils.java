package com.yunqi.security.util.check;

import java.util.Collection;
import java.util.regex.Pattern;

import org.springframework.util.Assert;

/**
 * 通用判断类的工具类
 */
public class CommonJudgeUtils {

    /**
     * 集合判空
     */
    public static boolean isNullOrEmpty(Collection<?> collection) {
	return collection == null || collection.isEmpty();
    }

    /**
     * 字符串判空
     */
    public static boolean isNullOrEmpty(String str) {
	return str == null || str.length() == 0;
    }

    /**
     * 判断字符串是否满足指定正则表达式
     * 
     * @param pattern 正则表达式
     *                <p>
     *                常见正则判断示例： 姓名：中文、字母、数字、“_”、“·”、和空格
     *                "^[\u4E00-\u9FA5A-Za-z0-9_·\\s]+$"
     *                手机号（严格）："^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$"
     *                手机号（宽松）："^[1]([3-9])[0-9]{9}$"
     *                身份证号（包括18位长的和15位长的）："(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)"
     *                </p>
     * @param target  目标字符串
     */
    public static boolean matcher(String pattern, String target) {
	Assert.notNull(pattern, "正则表达式不能为空!");
	Assert.notNull(target, "判断目标不能为空");
	Pattern ptn = Pattern.compile(pattern);
	return ptn.matcher(target).matches();
    }
}
