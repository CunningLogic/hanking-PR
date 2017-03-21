package com.hanking.pr.common;

import java.io.Serializable;

import com.hanking.pr.constant.C;



/**
 * 错误对象
 * 
 * @author alex.yu
 *
 */
public class Error implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8148835760420097507L;

	private int code = C.Err.getCode(C.Err.NO_EXCEPTION);

	private String message = C.Err.NO_EXCEPTION;
	
	private String resourceKey = C.Err.NO_EXCEPTION;

	private Throwable cause;

	public Error() {

	}
	
	public Error(String message) {
		this.message = message;
		this.code = C.Err.getCode(message);
	}

	public Error(String message, Throwable e) {
		this.message = message;
		this.code = C.Err.getCode(message);
		this.cause = e;
	}
	
	public static Error newInstance() {
		return new Error();
	}
	
	public boolean isNoError(){
		return this.code == C.Err.getCode(C.Err.NO_EXCEPTION);
	}
	
	////////////////////////////////////////////

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	@Override
	public String toString() {
		return "Error [code=" + code + ", message=" + message + cause == null ? "]"	: ", cause=" + this.cause.getMessage() + "]";
	}

	public String toJsonString() {
		return "{ \"errorCode\" : \"" + this.code + "\", \"errorMessage\" : \"" + this.message	+ "\" }";
	}

	public String getResourceKey() {
		return resourceKey;
	}

	public void setResourceKey(String resourceKey) {
		this.resourceKey = resourceKey;
	}

}
