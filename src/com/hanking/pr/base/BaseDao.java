package com.hanking.pr.base;

import java.util.Map;


public interface BaseDao<T>{
	public int add(T t);

	public int update(T t);

	public int delete(Map<String, Object> map);

	public T getModelById(Map<String, Object> map);

/*	public PageBean<T> find(PageBean<T> pageBean);
	
	public PageBean<T> findBySql(PageBean<T> pageBean,Map<String,String>params);*/
	
	public int getCount(String queryStr);
	
	//用完删除
	public int delete(int id);
	
	//根据对象删除（主要做伪删除）
	public int delete(T t);
	
	public T getModelById(int id);
}
