/*
 * Copyright (C) 2013-2033 Fable Limited.
 */
package com.fable.dsp.common.util;

/**
 * 
 * @author xieruidong 2014年3月13日 下午6:04:17
 */
public class TaskType {

	public final static String DB_TO_DB = "0";
	public final static String FILE_TO_FILE = "1";
	public final static String FILE_TO_DB = "2";
	public final static String DB_TO_FILE = "3";
	public final static char FILE_ENTITY_TYPE = '1';
	public final static char DB_ENTITY_TYPE = '0';
	public final static char UNKNOW_ENTITY_TYPE = '9';
	
	public static char getSourceEntityType(String taskType) {
		if (DB_TO_DB.equals(taskType)) {
			return DB_ENTITY_TYPE;
		} else if (FILE_TO_FILE.equals(taskType)) {
			return FILE_ENTITY_TYPE;
		} else if (FILE_TO_DB.equals(taskType)) {
			return FILE_ENTITY_TYPE;
		} else if (DB_TO_FILE.equals(taskType)) {
			return DB_ENTITY_TYPE;
		}
		
		return UNKNOW_ENTITY_TYPE;
	}
	
	public static char getTargetEntityType(String taskType) {
		if (DB_TO_DB.equals(taskType)) {
			return DB_ENTITY_TYPE;
		} else if (FILE_TO_FILE.equals(taskType)) {
			return FILE_ENTITY_TYPE;
		} else if (FILE_TO_DB.equals(taskType)) {
			return DB_ENTITY_TYPE;
		} else if (DB_TO_FILE.equals(taskType)) {
			return FILE_ENTITY_TYPE;
		}
		
		return UNKNOW_ENTITY_TYPE;
	}
}
