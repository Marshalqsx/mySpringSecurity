package com.yunqi.security.exception;

import org.slf4j.Logger;

public class ExceptionUtil {

    /**
     * 打印stackTrace到日志
     * 
     * @author qsx
     * @date 2020-07-16 14:13:11
     */
    public static void printStackTrace(Throwable th, Logger log) {
	log.error(th.getMessage());
	StackTraceElement[] stackTrace = th.getStackTrace();
	for (StackTraceElement st : stackTrace) {
	    log.error(st.toString());
	}
    }
}
