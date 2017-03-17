package com.fable.dsp.common.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 计算任务流速的工具类
 * @author liuz
 *
 */
public class TaskSpeedUtil {
	/** 记录上次查询时间 */
	public static Date time = new Date();
	/** 记录上次任务流量 */
	public static List taskFlowList =  null;
	/** 记录当前正在运行任务的流速 */
	public static List taskSpeedList = new ArrayList();
	
	
}
