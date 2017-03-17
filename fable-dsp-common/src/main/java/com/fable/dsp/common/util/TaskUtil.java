package com.fable.dsp.common.util;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.fable.dsp.common.constants.CommonConstants;
import com.fable.dsp.common.exception.TaskException;



/**
 * 处理task相关信息的工具类
 * @author 邱爽
 *
 */
public class TaskUtil {
	 private final static String DB_DB = "数据库-数据库";
	 private final static String DB_FTP = "数据库-文件";
	 private final static String FTP_DB = "文件-数据库";
	 private final static String FTP_FTP = "文件-文件";
    /**
     * 将Map转换成Object的通用方法
     * @param map 要转换的map
     * @param obj 要转换的目标实体
     */
    public static void transMap2Bean(Map<String, Object> map, Object obj) {  
        if (map == null || obj == null) {  
            return;  
        }  
        try {  
            BeanUtils.populate(obj, map);  
        } 
        catch (Exception e) {  
        	e.printStackTrace();
            throw new TaskException("将map转换为对象时出现异常：所属dto为："+obj.getClass()+",异常信息为： "+e.getMessage());
        }  
    }
    /**
     * 根据源和端判断任务类型
     * @param sourceEntity 交换源实体
     * @param transEntity 交换端实体
     * @return 任务类型
     */
    public static String setTaskDtoType(String sourceType,String targetType){
    	String taskType="";
    	 if (CommonConstants.DB_TYPE.equals(sourceType) &&
                 CommonConstants.DB_TYPE.equals(targetType)) {
             //数据库-数据库
    		 taskType=DB_DB;
         }
         else if (CommonConstants.FILE_TYPE.equals(sourceType) &&
                         CommonConstants.FILE_TYPE.equals(targetType)) {
             //文件 →文件
        	 taskType=FTP_FTP;
         }
         else if (CommonConstants.FILE_TYPE.equals(sourceType) &&
                         CommonConstants.DB_TYPE.equals(targetType)) {
             //文件 →数据库
           taskType=FTP_DB;
         } else {
             //数据库 →文件
           taskType=DB_FTP;
         }
    	 return taskType;
    }
}
