package com.fable.dsp.controller.dataswitch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * 最轻量级的org.json.jar实现json字符串和java对象之间的转换
 * @author Administrator
 *
 */
public class OrgJsonHelper {
	/**
	 * 将json字符串转换为List集合
	 * @param jsonArrStr
	 * @return
	 */
	public static List<Map<String,Object>>jsonObjList(String jsonArrStr) {
		List<Map<String,Object>>jsonList=new ArrayList<Map<String,Object>>();
		JSONArray jsonArr=null;
		try {
			jsonArr=new JSONArray(jsonArrStr);
			jsonList=(List<Map<String,Object>>)OrgJsonHelper.jsonToList(jsonArr);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonList;
	}
	/**
	 * 将传进来的json数组转换为List集合
	 * @param jsonArr
	 * @return
	 * @throws JSONException 
	 */
	private static List<?>jsonToList(JSONArray jsonArr) throws JSONException {
		List<Object>jsonToMapList=new ArrayList<Object>();
		for(int i=0;i<jsonArr.length();i++) {
			Object object=jsonArr.get(i);
			if(object instanceof JSONArray) {
				jsonToMapList.add(OrgJsonHelper.jsonToList((JSONArray)object));
			} else if (object instanceof JSONObject) {
				jsonToMapList.add(OrgJsonHelper.jsonToList((JSONArray)object));
			} else {
				jsonToMapList.add(object);
			}
		}
		return jsonToMapList;
	}
	/**
	 * 将传进来的json对象转换成Map集合
	 * @param jsonObj
	 * @return
	 * @throws JSONException 
	 */
	@SuppressWarnings("unchecked")
	private static Map<String,Object>jsonToMap(JSONObject jsonObj) 
			throws JSONException {
		Map<String,Object>jsonMap=new HashMap<String, Object>();
		Iterator<String>jsonKeys=jsonObj.keys();
		while(jsonKeys.hasNext()) {
			String jsonKey=jsonKeys.next();
			Object jsonValObj=jsonObj.get(jsonKey);
			if(jsonValObj instanceof JSONArray) {
				jsonMap.put(jsonKey,OrgJsonHelper.jsonToList((JSONArray)jsonValObj));
			} else if(jsonValObj instanceof JSONObject) {
				jsonMap.put(jsonKey,OrgJsonHelper.jsonToMap((JSONObject)jsonValObj));
			} else {
				jsonMap.put(jsonKey,jsonValObj);
			}
		}
		return jsonMap;
	}
}
