package com.fable.dsp.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fable.dsp.common.dto.etl.ColumnsMappingDto;
import com.fable.dsp.common.dto.etl.ContentFilterDto;
import com.fable.dsp.common.dto.etl.PretreatmentDto;

public class JSONHelper {
	/**
	 * 将JSONArray对象转换成Map-List集合
	 * @param jsonArray
	 * @return
	 */
	public static Object JsonToList(JSONArray jsonArray) {
		List<Object>jsonObjList=new ArrayList<Object>();
		for(Object obj:jsonObjList) {
			if(obj instanceof JSONArray) {
				//是集合的情况
				jsonObjList.add(JsonToList((JSONArray)obj));
				//
			}else if(obj instanceof JSONObject){
				jsonObjList.add(JsonToMap((JSONObject)obj));
			}else {
				jsonObjList.add(obj);
			}
		}
		return jsonObjList;
	}
	/**
	 * 将JSONObject对象转换成Map-List集合
	 * @param json
	 * @return
	 */
	public static Map<String,Object>JsonToMap(JSONObject json) {
		Map<String,Object>columnValMap=new HashMap<String, Object>();
		Set<Object>jsonKeys=json.keySet();
		for(Object key:jsonKeys) {
			Object JsonValObj=json.get(key);
			if(JsonValObj instanceof JSONArray) {
				columnValMap.put((String)key, JsonToList((JSONArray)JsonValObj));
			}else if(key instanceof JSONObject) {
				columnValMap.put((String)key, JsonToMap((JSONObject)JsonValObj));
			}else {
				columnValMap.put((String)key,JsonValObj);
			}
		}
		return columnValMap;
	}
	/**
	 * 将json对象转换成map集合，通过此方法获取存放map集合键的List集合
	 * @param obj
	 * @return
	 */
	public static List<Object>mapKeys(Object obj){
		List<Object>keyList=new ArrayList<Object>();
		Map<String,Object>columnValMap=new HashMap<String, Object>();
		String columnStr="column";
		if(obj instanceof JSONArray) {
			List<Map<String,Object>>jsonObjList=new ArrayList<Map<String,Object>>();//new一个List
			jsonObjList=(List<Map<String, Object>>) JsonToList((JSONArray)obj);//将obj转换成
			columnValMap=(Map<String,Object>)(jsonObjList.get(0));
		}else if(obj instanceof JSONObject) {
			columnValMap=JsonToMap((JSONObject)obj);
		}else {
			keyList.add(obj);
		}
		for(int i=0;i<columnValMap.keySet().size();i++) {
			keyList.add(columnStr+(i+1));
		}
		System.out.println(keyList.size());
		return keyList;
	}
	public static JSONArray transferFromArrayToList(Object[]obj){
		boolean[]boolArray=new boolean[]{true,false,true};
		JSONArray jsonArray=JSONArray.fromObject(boolArray);
		System.out.println(jsonArray);
		return jsonArray;
	}
	public static JSONArray transferFromListToList(List list) {
		JSONArray jsonArray=JSONArray.fromObject(list);
		JSONArray jsonArray2=JSONArray.fromObject("['json','is','easy']");
		return jsonArray2;
	}
	public static String transferFromMap(Map<?,?>map2) {
		try {
			JSONObject json=JSONObject.fromObject(map2);
			System.out.println(json);
			return json.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 传到后台的方法
	 * @return
	 */
	public static String transferFromMap1(Map<String,Object>map) {
		//map.put("sourceColumns", new ColumnDto[]{new ColumnDto("col_1",1,1), new ColumnDto("col_2", 2,2)});
		//map.put("targetColumns", new ColumnDto[]{new ColumnDto("col_2", 2,2)});
		//map.put("columnsMap",new ColumnMapDto[]{new ColumnMapDto("source_id","target_id"),new ColumnMapDto("source_name","target_name")});
		try {
//			JSONArray json=JSONArray.fromObject(map);
			 JSONObject json=JSONObject.fromObject(map);
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		ContentFilterDto contentFilterDto=new ContentFilterDto();
		PretreatmentDto pretreatmentDto=new PretreatmentDto();
//		pretreatmentDto.setSourceColumns(new ColumnDto[]{new ColumnDto("id",1,"Integer"),new ColumnDto("name",2,"Varchar")});
//		pretreatmentDto.setTargetColumns(new ColumnDto[]{new ColumnDto("id",1,"Integer"),new ColumnDto("name",2,"Varchar")});
		contentFilterDto.setPretreatmentDto(pretreatmentDto);
		contentFilterDto.setColumnsMapping(new ColumnsMappingDto[]{new ColumnsMappingDto("id", "id2"),new ColumnsMappingDto("name", "name2")});
		System.out.println(transferContentFilterDto(contentFilterDto));
	}
	public static String transferContentFilterDto(ContentFilterDto contentFilterDto){
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("pretreatment", contentFilterDto.getPretreatmentDto());
//		Map<String,Object>mapping=new HashMap<String, Object>();
//		mapping.put("columnsMapping",contentFilterDto.getColumnsMapping());
//		map.put("mapping", mapping);
		JSONArray json=JSONArray.fromObject(map);
		return json.toString();
	}
}
