package com.hanking.pr.constant;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * @author Alex
 * 
 */
public final class C {

	public static final class common {

		public static final String UNDEFINED = "UNDEFINED";

		public static final String INFO = "INFO";
		public static final String MESSAGE = "MESSAGE";

		public static final String STATUS = "STATUS";
		public static final int FAILED = 0;
		public static final int SUCCESS = 1;
		
		public static final int NORMAL = 0;		//正常
		public static final int DISABLED = 1;	//已经删除
		
		public static final String SYSTEM_USER = "SYSTEM";

		public static final String RESULT = "res";

		public static final String CONTENT_TYPE_IMAGE = "image/jpeg";

		public static final String CONTENT_TYPE_SERVLET = "text/html; charset=UTF-8";

		public static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";
		
		public static final String CONTENT_TYPE_TEXT = "application/text;charset=UTF-8";
		
		public static final String CONTENT_TYPE_EXCEL = "application/vnd.ms-excel";

		public static final String VERIFY_IMAGE_CODE = "VERIFY_IMAGE_CODE";

		public static final String SALT = "OHYEAH!";

		public static final String ANONYMOUS = "ANONYMOUS";

		public static final String HEARTBEAT = "HEARTBEAT";

		public static final int OFFLINE = 0;
		public static final int ONLINE = 1;
		public static final int LOCKED = 2;

	}

	public static final class profile {

		public static final String DEFAULT_LOCALE = SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME;
		public static final String SESSION_LOCALE = "SESSION_LOCALE";

		public static final String SESSION_TYPE = "SESSION_TYPE";

		public static final String SESSION_TYPE_DEFAULT = "SESSION_TYPE_DEFAULT";
		public static final String SESSION_TYPE_USER = "SESSION_TYPE_USER";

		public static final String APP_PROFILE = "APP_PROFILE";
		public static final String APP_NAME = "APP_NAME";
		public static final String SITE_NAME = "SITE_NAME";
		public static final String STATUS = "STATUS";
		public static final String LOCALE = "LOCALE";
		public static final String CONFIG = "CONFIG";

		public static final String ONLINE_SESSION = "ONLINE_SESSION_";

		public static final String ONLINE_USER_LIST = "ONLINE_USER_LIST";

		public static final String LOGIN_USER = "LOGIN_USER";

		public static final String USER_NAME = "USER_NAME";

		public static final String ONLINE_SESSION_MAP = "ONLINE_SESSION_MAP";

		public static final String REMOTE_ADDR = "REMOTE_ADDR";

		public static final String LOGIN_TIME = "LOGIN_TIME";

		public static final String SERVER_STARTUP_DATE = "SERVER_STARTUP_DATE";

		public static final String SYSTEM_USER = "SYSTEM";

		public static final String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";

		public static final String SPRING_APP_CONTEXT_KEY = "org.springframework.web.servlet.FrameworkServlet.CONTEXT.SpringMVC";

	}

	public static final class requestType {

		public static final String LOGIN = "LOGIN";
		public static final String ADMIN = "ADMIN";

	}
	
	public static final class Err {
		
		public static Map<String, Integer> errorCodeMap = null;
		
		public static final String NO_EXCEPTION = "NO_EXCEPTION";
		
		public static final String INCOMPLETE_EXCEPTION = "INCOMPLETE_EXCEPTION";
		public static final String REPETITIOUS_EXCEPTION = "REPETITIOUS_EXCEPTION";
		public static final String OBJECT_NOTFOUND_EXCEPTION = "OBJECT_NOTFOUND_EXCEPTION";
		public static final String INCORRECT_EXCEPTION = "INCORRECT_EXCEPTION";
		public static final String NO_PARAMS_EXCEPTION = "NO_PARAMS_EXCEPTION";
		public static final String JSON_PARSE_EXCEPTION = "JSON_PARSE_EXCEPTION";
		public static final String FILE_NOTFOUND_EXCEPTION = "FILE_NOTFOUND_EXCEPTION";
		public static final String UNKNOWN_DATA_EXCEPTION = "UNKNOWN_DATA_EXCEPTION";
		public static final String SYSTEM_EXCEPTION = "SYSTEM_EXCEPTION";
		public static final String INSUFFICIENT_PERMISSIONS_EXCEPTION = "INSUFFICIENT_PERMISSIONS_EXCEPTION";
		public static final String ACCOUNT_DISABLED_EXCEPTION = "ACCOUNT_DISABLED_EXCEPTION";
		public static final String ACCOUNT_EXPIRED_EXCEPTION = "ACCOUNT_EXPIRED_EXCEPTION";
		public static final String LOGIN_FAILED_EXCEPTION = "LOGIN_FAILED_EXCEPTION";
		public static final String UNAUTHENTICATED_EXCEPTION = "UNAUTHENTICATED_EXCEPTION";
		public static final String PROCESS_FAILED_EXCEPTION = "PROCESS_FAILED_EXCEPTION";
		public static final String PARAMS_EXCEPTION = "Parameters Exception";
		
		public static final String UNEXPECTED_EXCEPTION = "UNEXPECTED_EXCEPTION";
		
		static {
			errorCodeMap = new HashMap<String, Integer>();
			errorCodeMap.put(NO_EXCEPTION, 0);
			
			errorCodeMap.put(INCOMPLETE_EXCEPTION, 101);
			errorCodeMap.put(REPETITIOUS_EXCEPTION, 102);
			errorCodeMap.put(OBJECT_NOTFOUND_EXCEPTION, 103);
			errorCodeMap.put(INCORRECT_EXCEPTION, 104);
			errorCodeMap.put(NO_PARAMS_EXCEPTION, 105);
			errorCodeMap.put(JSON_PARSE_EXCEPTION, 106);
			errorCodeMap.put(FILE_NOTFOUND_EXCEPTION, 107);
			errorCodeMap.put(UNKNOWN_DATA_EXCEPTION, 108);
			errorCodeMap.put(SYSTEM_EXCEPTION, 109);
			
			errorCodeMap.put(INSUFFICIENT_PERMISSIONS_EXCEPTION, 110);
			errorCodeMap.put(ACCOUNT_DISABLED_EXCEPTION, 111);
			errorCodeMap.put(ACCOUNT_EXPIRED_EXCEPTION, 112);
			errorCodeMap.put(LOGIN_FAILED_EXCEPTION, 113);
			errorCodeMap.put(UNAUTHENTICATED_EXCEPTION, 114);
			errorCodeMap.put(PROCESS_FAILED_EXCEPTION, 115);
			errorCodeMap.put(PARAMS_EXCEPTION, 116);
			errorCodeMap.put(UNEXPECTED_EXCEPTION, 999);
			
		}
		
		public static final Integer getCode(String message){
			if(errorCodeMap != null && errorCodeMap.containsKey(message)){
				return errorCodeMap.get(message);
			}
			return null;
		}
		
	}

	public static final class error {

		public static final int NO_ERROR = 0;

		public static final int SYSTEM_ERROR = 1000;
		public static final int SYSTEM_SESSION_IS_NULL = 1001;

		public static final int AUTH_ERROR = 2000;
		public static final int AUTH_LOGIN_FAILED = 2001;
		public static final int AUTH_NOT_YET_LOGIN = 2002;

		public static final int USER_ERROR = 3000;
		public static final int USER_OPERATE_FAILED = 3001;
		public static final int USER_OPERATE_UNCURRECT = 3002;

		public static final int OUTHER_ERROR = 4000;

		public static final int UNKNOWN_ERROR = 9999;
	}

	public static final class osType {
		public static final String LINUX = "LINUX";
		public static final String WINDOWS_XP = "WINDOWS XP";
		public static final String AIX = "AIX";
		public static final String WIN7_OR_VISTA = "Windows Vista";
	}

	public static final class app {
		public static final int CLOSED = 0;
		public static final int RUNNING = 1;

	}

	public static final class Role {
		public static final int ADMIN = 1;
		public static final int USER = 2;
	}
	
	public static final class Color {
		public static final String RED = "red";
		public static final String GREEN = "green";
		public static final String YELLOW = "yellow";
		public static final String BLUE = "blue";
		public static final String WHITE = "white";
		public static final String BLACK = "black";
		public static final String ORANGE = "orange";
		
	}

}
