package com.hanking.pr.exception;

import java.util.HashMap;
import java.util.Map;

public class ParameterException extends Exception {

	private static final long serialVersionUID = 1L;

	private Map<String, String> fields = new HashMap<String, String>();

	public Map<String, String> getFields() {
		return fields;
	}

	public void setFields(Map<String, String> fields) {
		this.fields = fields;
	}

	public void addFields(String key, String value) {
		fields.put(key, value);
	}

	public boolean isEmpty() {
		return fields.isEmpty();
	}

	public ParameterException() {
		super();
	}

	public ParameterException(String message, String flag) {
		super(message);
	}

}
