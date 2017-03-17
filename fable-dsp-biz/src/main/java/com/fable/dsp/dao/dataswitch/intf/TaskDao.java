package com.fable.dsp.dao.dataswitch.intf;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fable.dsp.common.dto.dataswitch.FullTaskDto;
import com.fable.dsp.core.dao.GenericDao;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dmo.datasource.DataSourceInfo;
import com.fable.dsp.dmo.dataswitch.EtlStrategy;
import com.fable.dsp.dmo.dataswitch.TaskEntity;
import com.fable.dsp.dmo.dataswitch.TransEntity;


/**
 * 交换任务的单表操作
 * @author qiu
 *
 */

public interface TaskDao extends GenericDao<TaskEntity>{
	/**
	 * 通过条件查找多条
	 * @param myUserInfo findPagePersonInfoList
	 * @return
	 */
	List<TaskEntity>listTaskByConditions(Page pager,TaskEntity taskEntity);
	/**
	 * 通过条件查找1个
	 * @param myUserInfo
	 * @return
	 */
	TaskEntity getTaskByConditions(TaskEntity taskEntity);
	/**
	 * 通过条件查询分页数据
	 * @param pager
	 * @param myUserInfo
	 *  为空查询全部数据
	 * @return
	 */
	PageList<TaskEntity>findTaskList(Page pager,TaskEntity taskEntity);
	/**
	 * 计算总记录数
	 * @param pager
	 * @param myUserInfo
	 * @return
	 */
	public Long listCount(Page pager,final TaskEntity taskEntity);
	/**
	 * 判断是否是重名任务
	 * @param taskName
	 * @return
	 */
	boolean isExitsName(String taskName);
	/**
	 * 判断主从表关系
	 * @param dsInfo
	 * @param tableNames
	 * @return 0 没有主从表关系   1有不健全的主从表关系    2主从表关系健全 
	 */
	public String isAssociation(DataSourceInfo dsInfo,List<String> tableNames);
	/**
	 * 修改任务管理回写信息
     * @param taskID 任务id
     * @return 返回任务所有信息 FullTaskDto
	 */
	public FullTaskDto editForShow(Long taskID);
}
