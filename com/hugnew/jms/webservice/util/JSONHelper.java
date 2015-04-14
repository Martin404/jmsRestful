/**
 * 
 */
package com.hugnew.jms.webservice.util;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.Iterator;

import java.util.List;

import java.util.Map;

import org.json.JSONArray;

import org.json.JSONException;

import org.json.JSONObject;

/**
 * @author Martin
 * 
 */
public class JSONHelper {

	/**
	 * 将json字符串转换为List集合
	 * 
	 * @param jsonArrStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> jsonObjList(String jsonArrStr) {
		List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
		JSONArray jsonArr = null;
		try {
			jsonArr = new JSONArray(jsonArrStr);
			jsonList = (List<Map<String, Object>>) JSONHelper
					.jsonToList(jsonArr);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonList;
	}

	/**
	 * 
	 * 将json对象的键值存放在集合，映射table的column
	 * 
	 * 
	 * 
	 * @param map
	 * 
	 * @return
	 */

	public static List<String> jsonMapKeysList(Map<?, ?> map) {

		List<String> jsonjsonList = new ArrayList<String>();

		String columnStr = "column";

		for (int i = 0; i < map.keySet().size(); i++) {

			jsonjsonList.add(columnStr + (i + 1));

		}
		return jsonjsonList;

	}

	/**
	 * 
	 * 将传递近来的json数组转换为List集合
	 * 
	 * 
	 * 
	 * @param jsonArr
	 * 
	 * @return www.it.net
	 * 
	 * @throws JSONException
	 */

	private static List<?> jsonToList(JSONArray jsonArr)

	throws JSONException {

		List<Object> jsonToMapList = new ArrayList<Object>();

		for (int i = 0; i < jsonArr.length(); i++) {

			Object object = jsonArr.get(i);

			if (object instanceof JSONArray) {

				jsonToMapList.add(JSONHelper.jsonToList((JSONArray) object));

			} else if (object instanceof JSONObject) {

				jsonToMapList.add(JSONHelper.jsonToMap((JSONObject) object));

			} else {

				jsonToMapList.add(object);

			}

		}

		return jsonToMapList;

	}

	/**
	 * 
	 * 将传递近来的json对象转换为Map集合
	 * 
	 * 
	 * 
	 * @param jsonObj
	 * @return
	 * 
	 * @throws JSONException
	 */

	private static Map<String, Object> jsonToMap(JSONObject jsonObj)

	throws JSONException {

		Map<String, Object> jsonMap = new HashMap<String, Object>();

		@SuppressWarnings("unchecked")
		Iterator<String> jsonKeys = jsonObj.keys();

		while (jsonKeys.hasNext()) {

			String jsonKey = jsonKeys.next();

			Object jsonValObj = jsonObj.get(jsonKey);

			if (jsonValObj instanceof JSONArray) {

				jsonMap.put(jsonKey,
						JSONHelper.jsonToList((JSONArray) jsonValObj));

			} else if (jsonValObj instanceof JSONObject) {

				jsonMap.put(jsonKey,
						JSONHelper.jsonToMap((JSONObject) jsonValObj));

			} else {
				jsonMap.put(jsonKey, jsonValObj);

			}
		}
		return jsonMap;

	}

}
