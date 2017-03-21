/**
 * 
 */
package com.hanking.pr.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * @author Alex
 * 
 */
public final class LogHelper {

	private static Map<String, Logger> loggerMap = new HashMap<String, Logger>();

	private static Logger getLogger(Class clazz) {
		String className = clazz.getPackage() + "." + clazz.getName();
		Logger logger = loggerMap.get(className);
		if (logger == null) {
			logger = Logger.getLogger(clazz);
			loggerMap.put(className, logger);
		}
		return logger;
	}

	public static void i(Class clazz, String msg) {
		Logger logger = getLogger(clazz);
		if (logger.isEnabledFor(Level.INFO)) {
			logger.info(msg);
		}
	}

	public static void w(Class clazz, String msg) {
		Logger logger = getLogger(clazz);
		if (logger.isEnabledFor(Level.WARN)) {
			logger.warn(msg);
		}
	}

	public static void e(Class clazz, String msg) {
		Logger logger = getLogger(clazz);
		if (logger.isEnabledFor(Level.ERROR)) {
			logger.error(msg);
		}
	}

	public static void d(Class clazz, String msg) {
		Logger logger = getLogger(clazz);
		if (logger.isEnabledFor(Level.DEBUG)) {
			logger.debug(msg);
		}
	}

	public static void main(String[] args) {
		System.out.println(LogHelper.getLogger(LogHelper.class));

		LogHelper.i(LogHelper.class, "asdfasdf");
		LogHelper.w(LogHelper.class, "asdfasdf");
		LogHelper.e(LogHelper.class, "asdfasdf");
		LogHelper.d(LogHelper.class, "asdfasdf");
	}
}
