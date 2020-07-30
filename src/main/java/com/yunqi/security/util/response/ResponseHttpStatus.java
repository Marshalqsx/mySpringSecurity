/**
 * 
 */
package com.yunqi.security.util.response;

/**
 * @author qinshuangxi
 * @date 2020年6月24日
 * 
 */
public enum ResponseHttpStatus {

	/**
	 * 请求成功
	 */
	OK(200, "OK"),
	
	UNAUTHORIZED(401, "Unauthorized"),
	
	/***/
	BAD_REQUEST(400, "Bad Request"),
	
	/***/
	FORBIDDEN(403, "Forbidden"),
	
	/***/
	NOT_FOUND(404, "Not Found"),
	
	/***/
	METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
	
	/***/
	UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
	
	/**
	 * 请求失败，服务器内部错误
	 */
	INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
	
	/***/
	BAD_GATEWAY(502, "Bad Gateway"),
	
	/***/
	SERVICE_UNAVAILABLE(503, "Service Unavailable"),
	
	/***/
	GATEWAY_TIMEOUT(504, "Gateway Timeout");

	private final int value;

	private final String reasonPhrase;

	ResponseHttpStatus(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	public int value() {
		return this.value;
	}

	public String getReasonPhrase() {
		return this.reasonPhrase;
	}

	public static ResponseHttpStatus resolve(int statusCode) {
		for (ResponseHttpStatus status : values()) {
			if (status.value == statusCode) {
				return status;
			}
		}
		return null;
	}

	public static ResponseHttpStatus valueOf(int statusCode) {
		ResponseHttpStatus status = resolve(statusCode);
		if (status == null) {
			throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
		}
		return status;
	}
}
