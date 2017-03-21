/**
 * 
 */
package com.hanking.pr.common;

import org.springframework.ui.ModelMap;


/**
 * @author Alex
 * 
 */
public abstract class AbsMapModel implements IModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2447859121666893375L;

	protected ModelMap map = new ModelMap();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.effecia.iasia.IAuditable#serialize()
	 */
	@Override
	public String serialize() {
		return this.map.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.effecia.iasia.IModel#put(java.lang.String, java.lang.Object)
	 */
	@Override
	public void put(String key, Object obj) {
		this.map.put(key, obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.effecia.iasia.IModel#get(java.lang.String)
	 */
	@Override
	public Object get(String key) {
		return this.map.get(key);
	}
	
	@Override
	public Object remove(String key) {
		if(!this.map.isEmpty() && this.map.containsKey(key)){
			Object obj = this.map.get(key);
			this.map.remove(key);
			return obj;
		}
		return null;
	}
	
	@Override
	public boolean isContainsKey(String key) {
		return this.map != null && !this.map.isEmpty() && this.map.containsKey(key);
	}

	public ModelMap getMap() {
		return map;
	}

	public void setMap(ModelMap map) {
		this.map = map;
	}

}
