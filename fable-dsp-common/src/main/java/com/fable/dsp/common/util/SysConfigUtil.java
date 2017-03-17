package com.fable.dsp.common.util;

import java.util.Map;

/**
 * 定义系统配置参数map的工具类
 * 
 * @author liuz
 * 
 */
public class SysConfigUtil {

	/** 保存系统配置参数信息的map */
	private static Map<String, String> configMap = null;
	/** 系统主题组成的集合 */

	public static Map<String, String> getConfigMap() {
		return configMap;
	}

	public static void setConfigMap(Map<String, String> configMap) {
		SysConfigUtil.configMap = configMap;
	}
	/**
	 * 根据配置参数名获取参数值
	 * @param name 参数名
	 * @return 返回参数值，如果没有则返回null
	 */
	public static String getSysConfigValue(String name) {
		return configMap.get(name);
	}
	/**
	 * 设置系统配置参数
	 * @param name 参数名(key)
	 * @param value 参数值(value)
	 */
	public static void setSysConfigParamer(String name, String value) {  
		configMap.put(name, value);  
    }
	
}
