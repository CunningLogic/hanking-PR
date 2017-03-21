package com.hanking.pr.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

import com.hanking.pr.constant.Err;
import com.hanking.pr.utils.StringUtil;

/**
 * json转化工具
 * @author o-yang.jin
 *
 */
public class JsonParseTools {

	public ReturnMessage parseJsonString(Class clazz,String methodName, String jsonString){
		return this.parseJsonString(clazz,methodName, jsonString, true);
	}
	
	private ReturnMessage parseJsonString(Class clazz,String methodName, String jsonString, boolean showLog) {
		ReturnMessage msg = ReturnMessage.newInstance();
		if(showLog){
			if(jsonString.indexOf("password") > 0){//如果参数中有密码关键字，不能将密码写入到日志
				JSONObject jsonParam = JSONObject.fromObject(jsonString);
				jsonParam.remove("password");
				LogHelper.i(clazz, "{" + methodName + "}" + " jsonString --> " + jsonParam.toString());
			}else{
				LogHelper.i(clazz, "{" + methodName + "}" + " jsonString --> " + jsonString);
			}
		}
		if (StringUtil.isEmpty(jsonString)) {
			Error error = Error.newInstance();
			error.setCode(Err.Code.NO_PARAMS_EXCEPTION);
			error.setMessage(Err.getMessage(Err.Code.NO_PARAMS_EXCEPTION));
			msg.setError(error);
			return msg;
		}

		JSONObject p = null;
		try {
			p = JSONObject.fromObject(jsonString);
		} catch (Exception e) {
			LogHelper.e(clazz,"JSONObject parse failed, jsonString : " + jsonString);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("errorCode", Err.Code.JSON_PARSE_EXCEPTION);
			jsonObject.put("errorMessage", Err.JSON_PARSE_EXCEPTION);
			Error error = Error.newInstance();
			error.setCode(Err.Code.JSON_PARSE_EXCEPTION);
			error.setMessage(Err.getMessage(Err.Code.JSON_PARSE_EXCEPTION));
			error.setCause(e);
			msg.setError(error);
			return msg;
		}

		Map<String, Object> params = new HashMap<String, Object>();
		@SuppressWarnings("rawtypes")
		Iterator it = p.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			Object value = p.get(key);
			params.put(key, value instanceof net.sf.json.JSONNull ? "" : value);
		}
		msg.put("res", params);
		return msg.toSuccess();
	}
	
	/**
	 * 返回数据
	 * 
	 * @param msg
	 * @return
	 */
	public String toResponse(ReturnMessage msg) {
		JSONObject jsonObject = new JSONObject();
		if (msg != null && msg.isSuccessed()) {
			// 返回数据
			jsonObject.put("result", msg.get("res"));
		} else {
			jsonObject.put("result", false);
		}
		if(msg != null){
			jsonObject.put("error", msg.getError().toJsonString());
		} else {
			Error error = Error.newInstance();
			error.setCode(Err.Code.SYSTEM_EXCEPTION);
			error.setMessage(Err.getMessage(Err.Code.SYSTEM_EXCEPTION));
			jsonObject.put("error", error.toJsonString());
		}
		return jsonObject.toString();
	}
	
}
