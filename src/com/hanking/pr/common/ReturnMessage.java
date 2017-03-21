/**
 * 
 */
package com.hanking.pr.common;

import com.hanking.pr.constant.C;


/**
 * @author Alex Yu
 * 
 */
public class ReturnMessage extends AbsMapModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6810251941252267926L;

	private int status = C.common.FAILED;

	private String message;

	private Error error = Error.newInstance();

	/**
	 * 事务回滚
	 */
	private boolean rollback = true;

	public ReturnMessage() {

	}

	public ReturnMessage(int status) {
		this.status = status;
	}

	public ReturnMessage(String message) {
		super();
		this.message = message;
	}

	public ReturnMessage(String message, Error error) {
		super();
		this.message = message;
		this.error = error;
	}

	public static ReturnMessage newInstance() {
		return new ReturnMessage();
	}

	public boolean isSuccessed() {
		return this.status == C.common.SUCCESS;
	}

	public ReturnMessage toSuccess() {
		this.setStatus(C.common.SUCCESS);
		return this;
	}

	public ReturnMessage injectMessage(String message) {
		this.message = message;
		return this;
	}

	public ReturnMessage injectMessage(String message, Error error) {
		this.message = message;
		this.error = error;
		return this;
	}

	/********************/
	public String getMessage() {
		if(this.message == null || "".equals(this.message)){
			return this.error.getMessage();
		}
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isRollback() {
		return this.rollback;
	}

	public void setRollback(boolean rollback) {
		this.rollback = rollback;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ReturnMessage [status=" + status + ", message=" + message
				+ ", error=" + error + ", rollback=" + rollback + "]";
	}

}
