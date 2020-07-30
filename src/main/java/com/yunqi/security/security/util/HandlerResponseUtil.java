package com.yunqi.security.security.util;

import java.io.PrintWriter;

import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.yunqi.security.util.response.AjaxResponseResult;

import lombok.extern.slf4j.Slf4j;

/**
 * @author qsx
 * @date 2020-07-16 14:45:48
 */
@Component
@Slf4j
public class HandlerResponseUtil {

    @Autowired
    private Gson gson;

    public void responseJson(ServletResponse response, AjaxResponseResult<Object> result) {
	PrintWriter out = null;
	try {
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("application/json");
	    out = response.getWriter();
	    out.println(gson.toJson(result));
	} catch (Exception e) {
	    log.error("【JSON输出异常】" + e);
	} finally {
	    if (out != null) {
		out.flush();
		out.close();
	    }
	}
    }
}
