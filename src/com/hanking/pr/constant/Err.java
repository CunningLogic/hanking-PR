package com.hanking.pr.constant;

import java.util.HashMap;
import java.util.Map;


public class Err {

	public static final String NO_EXCEPTION = "The operation completed successfully.";

	public static final String IMPERFECT_EXCEPTION = "Imperfect Exception";
	public static final String REPETITIOUS_EXCEPTION = "Repetitious Exception";
	public static final String OBJECT_NOTFOUND_EXCEPTION = "Object not found Exception";
	public static final String INCORRECT_EXCEPTION = "Data incorrect Exception";
	public static final String NO_PARAMS_EXCEPTION = "No parameters Exception";
	public static final String JSON_PARSE_EXCEPTION = "Json parse Exception";
	public static final String FILE_NOTFOUND_EXCEPTION = "File not found Exception";
	public static final String UNKNOWN_DATA_EXCEPTION = "Unknown data Exception";
	public static final String NO_SUCH_FIELD_EXCEPTION = "No such field Exception";
	public static final String PARAMS_EXCEPTION = "Parameters Exception";
	public static final String DATA_NOTFOUND_EXCEPTION = "Data not found Exception";
	public static final String AUTHENTICATION_EXCEPTION = "Authentication failure"; 
	public static final String SUCCESS = "success";
	public static final String BAD_REQUEST = "bad_request";
	public static final String UNAUTHORIZED = "unauthorized";
	public static final String NOT_FOUND = "not_found";
	public static final String INVALID_PARAMS = "invalid_params";
	public static final String SERVER_ERROR = "server_error";
	public static final String SHA256_ERROR = "sha256 encry error";
	public static final String INSERT_ERROR = "insert oracle error";
	public static final String UNIQUE_ERROR = "no unique data";
	
	public static final String SYSTEM_EXCEPTION = "System Exception";

	public static final String UNEXPECTED_EXCEPTION = "Unexpected Exception";
	
	public static final String THIS_DATA_CANNOT_BE_DELETED = "This data cannot be deleted";
	
	public static final String TYPE_ERROR = "type error";
	public static final String WRITEOFF_MONEY_OVERFLOW = "writeoff money overflow";
	public static final String BALANCE_IS_NOT_ENOUGH = "balance is not enough";
	public static final String COUNTRY_ALREADY_EXISTS = "country already exists";
	public static final String CUSTOMER_TOKEN_ALREADY_EXISTS = "customer code already exists";
	public static final String PI_IMPORT_ERP = "pi import erp Exception";
	public static final String PRODUCT_NAME_EXISTS = "product name already exists";
	public static final String EFFECTIVE_INCORRECT = "Enter a valid time format is incorrect or no input";
	public static final String EFFECTIVE_INVALID_OVERLAP = "Effective or invalid time overlap";
	public static final String LOCK_PRODUCT_ALREADY_EXISTS = "lock product already exists";
	public static final String INVALID_OPERATION = "invalid operation";
	public static final String IMPORTED_SUCCESSFULLY = "Data has been imported successfully";
	public static final String AD_NONEXISTENT = "Ad does not exist";
	public static final String PLEASE_CANCEL_THE_RECOGNITION = "Please cancel the recognition";
	public static final String EFFECTIVE_TIME_IS_GREATER_THAN_THE_INVALID_TIME = "the old effective time is greater than the new effective time";
	public static final String DATA_ALREADY_EXISTS = "data already exists";

	public static final class Code {

		public static final int NO_EXCEPTION = 0;

		public static final int IMPERFECT_EXCEPTION = 100;
		public static final int REPETITIOUS_EXCEPTION = 101;
		public static final int OBJECT_NOTFOUND_EXCEPTION = 102;
		public static final int INCORRECT_EXCEPTION = 103;
		public static final int NO_PARAMS_EXCEPTION = 104;
		public static final int JSON_PARSE_EXCEPTION = 105;
		public static final int FILE_NOTFOUND_EXCEPTION = 106;
		public static final int UNKNOWN_DATA_EXCEPTION = 107;
		
		public static final int NO_SUCH_FIELD_EXCEPTION = 108;
		public static final int PARAMS_EXCEPTION = 110;
		public static final int DATA_NOTFOUND_EXCEPTION = 111;
		public static final int AUTHENTICATION_EXCEPTION = 112;
		
		public static final int SUCCESS = 200;
		public static final int BAD_REQUEST = 400;
		public static final int UNAUTHORIZED = 401;
		public static final int NOT_FOUND = 404;
		public static final int INVALID_PARAMS = 422;
		public static final int SERVER_ERROR = 500;
		public static final int SHA256_ERROR = 501;
		public static final int INSERT_ERROR = 502;
		public static final int UNIQUE_ERROR = 503;
		
		
		public static final int SYSTEM_EXCEPTION = 998;

		public static final int UNEXPECTED_EXCEPTION = 999;
		public static final int THIS_DATA_CANNOT_BE_DELETED = 113;
		public static final int TYPE_ERROR = 114;
		public static final int WRITEOFF_MONEY_OVERFLOW = 115;
		public static final int BALANCE_IS_NOT_ENOUGH = 116;
		public static final int COUNTRY_ALREADY_EXISTS = 117;
		public static final int CUSTOMER_TOKEN_ALREADY_EXISTS = 118;
		public static final int PI_IMPORT_ERP = 119;
		public static final int PRODUCT_NAME_EXISTS = 120;
		public static final int EFFECTIVE_INCORRECT = 121;
		public static final int EFFECTIVE_INVALID_OVERLAP =122;
		public static final int LOCK_PRODUCT_ALREADY_EXISTS = 123;
		public static final int INVALID_OPERATION = 124;
		public static final int IMPORTED_SUCCESSFULLY = 125;
		public static final int AD_NONEXISTENT = 126;
		public static final int PLEASE_CANCEL_THE_RECOGNITION = 127;
		public static final int EFFECTIVE_TIME_IS_GREATER_THAN_THE_INVALID_TIME = 128;
		public static final int DATA_ALREADY_EXISTS = 129;
	}

	private static Map<Integer, String> errorMsg = new HashMap<Integer, String>();

	static {
		errorMsg.put(Code.NO_EXCEPTION, NO_EXCEPTION);

		errorMsg.put(Code.IMPERFECT_EXCEPTION, IMPERFECT_EXCEPTION);
		errorMsg.put(Code.REPETITIOUS_EXCEPTION, REPETITIOUS_EXCEPTION);
		errorMsg.put(Code.OBJECT_NOTFOUND_EXCEPTION, OBJECT_NOTFOUND_EXCEPTION);
		errorMsg.put(Code.INCORRECT_EXCEPTION, INCORRECT_EXCEPTION);
		errorMsg.put(Code.NO_PARAMS_EXCEPTION, NO_PARAMS_EXCEPTION);
		errorMsg.put(Code.JSON_PARSE_EXCEPTION, JSON_PARSE_EXCEPTION);
		errorMsg.put(Code.FILE_NOTFOUND_EXCEPTION, FILE_NOTFOUND_EXCEPTION);
		errorMsg.put(Code.UNKNOWN_DATA_EXCEPTION, UNKNOWN_DATA_EXCEPTION);
		errorMsg.put(Code.NO_SUCH_FIELD_EXCEPTION, NO_SUCH_FIELD_EXCEPTION);
		
		errorMsg.put(Code.SYSTEM_EXCEPTION, SYSTEM_EXCEPTION);

		errorMsg.put(Code.UNEXPECTED_EXCEPTION, UNEXPECTED_EXCEPTION);
		errorMsg.put(Code.PARAMS_EXCEPTION, PARAMS_EXCEPTION);
		errorMsg.put(Code.DATA_NOTFOUND_EXCEPTION, DATA_NOTFOUND_EXCEPTION);
		errorMsg.put(Code.AUTHENTICATION_EXCEPTION, AUTHENTICATION_EXCEPTION);
		errorMsg.put(Code.SUCCESS, SUCCESS);
		errorMsg.put(Code.BAD_REQUEST, BAD_REQUEST);
		errorMsg.put(Code.UNAUTHORIZED, UNAUTHORIZED);
		errorMsg.put(Code.NOT_FOUND, NOT_FOUND);
		errorMsg.put(Code.INVALID_PARAMS, INVALID_PARAMS);
		errorMsg.put(Code.SERVER_ERROR, SERVER_ERROR);
		errorMsg.put(Code.SHA256_ERROR, SHA256_ERROR);
		errorMsg.put(Code.INSERT_ERROR, INSERT_ERROR);
		errorMsg.put(Code.UNIQUE_ERROR, UNIQUE_ERROR);
		errorMsg.put(Code.THIS_DATA_CANNOT_BE_DELETED, THIS_DATA_CANNOT_BE_DELETED);
		errorMsg.put(Code.TYPE_ERROR, TYPE_ERROR);
		errorMsg.put(Code.WRITEOFF_MONEY_OVERFLOW, WRITEOFF_MONEY_OVERFLOW);
		errorMsg.put(Code.BALANCE_IS_NOT_ENOUGH, BALANCE_IS_NOT_ENOUGH);
		errorMsg.put(Code.COUNTRY_ALREADY_EXISTS, COUNTRY_ALREADY_EXISTS);
		errorMsg.put(Code.CUSTOMER_TOKEN_ALREADY_EXISTS,CUSTOMER_TOKEN_ALREADY_EXISTS);
		errorMsg.put(Code.PI_IMPORT_ERP,PI_IMPORT_ERP);
		errorMsg.put(Code.PRODUCT_NAME_EXISTS,PRODUCT_NAME_EXISTS);
		errorMsg.put(Code.EFFECTIVE_INCORRECT,EFFECTIVE_INCORRECT);
		errorMsg.put(Code.EFFECTIVE_INVALID_OVERLAP,EFFECTIVE_INVALID_OVERLAP);
		errorMsg.put(Code.LOCK_PRODUCT_ALREADY_EXISTS,LOCK_PRODUCT_ALREADY_EXISTS);
		errorMsg.put(Code.INVALID_OPERATION,INVALID_OPERATION);
		errorMsg.put(Code.IMPORTED_SUCCESSFULLY,IMPORTED_SUCCESSFULLY);
		errorMsg.put(Code.AD_NONEXISTENT,AD_NONEXISTENT);
		errorMsg.put(Code.PLEASE_CANCEL_THE_RECOGNITION,PLEASE_CANCEL_THE_RECOGNITION);
		errorMsg.put(Code.EFFECTIVE_TIME_IS_GREATER_THAN_THE_INVALID_TIME,EFFECTIVE_TIME_IS_GREATER_THAN_THE_INVALID_TIME);
		errorMsg.put(Code.DATA_ALREADY_EXISTS,DATA_ALREADY_EXISTS);
	}

	public static String getMessage(int code){
		if(errorMsg.containsKey(code)){
			return errorMsg.get(code);
		}
		return null;
	}
}
