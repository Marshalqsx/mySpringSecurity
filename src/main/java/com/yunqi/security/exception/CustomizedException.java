package com.yunqi.security.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 自定义异常，在需要手动抛出异常的地方抛出此自定义异常，尽量不要去抛出RuntimeException
 * 
 * @author qsx
 * @date 2020-07-16 14:13:11
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
public class CustomizedException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 7452538702309228915L;

    private int code;

    private String msg;

    public CustomizedException(String msg) {
	super();
	this.msg = msg;
    }

    public CustomizedException(int code) {
	super();
	this.code = code;
    }

    public CustomizedException(int code, String msg) {
	super();
	this.code = code;
	this.msg = msg;
    }

    public CustomizedException() {
	super();
    }

}
