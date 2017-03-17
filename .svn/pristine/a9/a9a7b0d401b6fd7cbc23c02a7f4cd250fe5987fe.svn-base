package com.fable.dsp.controller.dataswitch;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonHelper {
	private Gson gson=new Gson();
	/**
	 * 工具方法，将json对象转换为map集合，通过此方法获取存放map集合键的list集合
	 * @param map
	 * @return
	 */
	public static List<Object>mapKeys(Map<?,?>map){
		List<Object>keyList=new ArrayList<Object>();
		String columnStr="column";
		for(int i=0;i<map.keySet().size();i++) {
			keyList.add(columnStr+(i+1));
		}
		System.out.println(keyList.size());
		return keyList;
	}
	/**
	 * 将传入的json字符串解析为Map集合
	 * @param jsonArrStr
	 * @return 
	 */
	public static List<Map<String,Object>>jsonObjList(String jsonArrStr) {
		List<Map<String,Object>>jsonObjList=new ArrayList<Map<String,Object>>();
		List<?>jsonList=GsonHelper.jsonToList(jsonArrStr);
		Gson gson=new Gson();
		for (Object object:jsonList) {
			String jsonStr=gson.toJson(object);
			//转换
			Map<?,?>json=GsonHelper.jsonToMap(jsonStr);
			jsonObjList.add((Map<String,Object>)json);
		}
		return jsonObjList;
	}
	/**
	 * 将传入的json字符串解析为List集合
	 * @param jsonStr
	 * @return
	 */
	public static List<?>jsonToList(String jsonStr) {
		List<?>ObjectList=null;
		Gson gson=new Gson();
		Type type=new TypeToken<List<?>>() {}.getType();
		ObjectList=gson.fromJson(jsonStr,type);
		return ObjectList;
	}
	/**
	 * 将传入的json字符串解析为Map集合
	 * @param jsonStr
	 * @return
	 */
	public static Map<?,?>jsonToMap(String jsonStr) {
		Map<?,?>objectMap=null;
		Gson gson=new Gson();
		Type type=new TypeToken<Map<?,?>>() {}.getType();
		objectMap=gson.fromJson(jsonStr,type);
		return objectMap;
	}
}
