package com.hanking.pr.common;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 遍历树形结构的数据
 * @author o-yang.jin
 *
 */
public class JsonNode {

	//遍历树形结构的数据
	public static JSONArray treeNodeList(JSONArray jsonArray, int parentId) {
		JSONArray childMenu = new JSONArray();
		for (Object object : jsonArray) {
			JSONObject jsonNode = JSONObject.fromObject(object);
			int menuId = jsonNode.getInt("id");
			int pid = jsonNode.getInt("parent_id");
			if (parentId == pid) {
				JSONArray c_node = treeNodeList(jsonArray, menuId);
				jsonNode.put("cate", c_node);
				childMenu.add(jsonNode);
			}
		}
		return childMenu; 
	}

}
