/**
 * 
 */
package com.hanking.pr.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Alex
 * 
 */
public class JsonUtil {

	private static ObjectMapper mapper = new ObjectMapper();

	public static String toJson(Object obj) {
		if (obj != null) {
			try {
				return mapper.writeValueAsString(obj);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	public static <T> T fromJson(String json, Class<T> clazz) {
		if (json != null) {
			try {
				return mapper.readValue(json, clazz);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static <T> T fromJson(String json, TypeReference<T> type) {
		if (json != null) {
			try {
				return mapper.readValue(json, type);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void main(String[] args){
//		Demo demo = new Demo(1,"xxx");
//		
//		String json = JsonUtil.toJson(demo);
//		
//		System.out.println(json);
//		
//		
//		System.out.println(JsonUtil.fromJson(json, Demo.class));
//		
		
		
		String[] arr = {"a","b","c"};
		JsonUtil.toJson(arr);
		String json = "[\"a\",\"b\",\"c\"]";
		
		String[] s = JsonUtil.fromJson(json, String[].class);
		System.out.println(s[0]);
		System.out.println(s[1]);
		System.out.println(s[2]);
		String[] ss = JsonUtil.fromJson(JsonUtil.toJson(arr), String[].class);
		
		System.out.println(ss[0]);
		System.out.println(ss[1]);
		System.out.println(ss[2]);
		
//		System.out.println(JsonUtil.toJson(arr));
	}
	
}

class Demo {
	private Integer id;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param id
	 * @param name
	 */
	public Demo(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Demo(){
		
	}
	@Override
	public String toString() {
		return "Demo [id=" + id + ", name=" + name + "]";
	}
	
}
