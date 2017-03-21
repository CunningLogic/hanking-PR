package com.hanking.pr.common;

/**
 * 
 * @author Alex Yu
 * 
 */
public interface IModel extends IAuditable {

	void put(String key, Object obj);

	Object get(String key);
	
	Object remove(String key);
	
	boolean isContainsKey(String key);

}
