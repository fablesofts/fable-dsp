package com.fable.dsp.service.dataswitch.intf;

import java.util.List;

import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.core.service.GenericService;
import com.fable.dsp.dmo.dataswitch.PipeLine;
import com.fable.dsp.dmo.dataswitch.TransEntity;

public interface PipeLineInfoService extends GenericService<PipeLine>{
	/**
	 * 通过条件查找多条
	 * @param page
	 * @param pipeLine
	 * @return
	 */
	PageList<PipeLine>listPipeLineListByConditions(Page page,PipeLine pipeLine);
	/**
	 * 通过条件查找1条
	 */
	PipeLine getPipeLineByConditions(PipeLine pipeLine);
	/**
	 * 通过多条件查询分页
	 * @param page
	 * @param pipeLine
	 * @return
	 */
	PageList<PipeLine>findPagePipeLineInfo(Page page,PipeLine pipeLine);
	List<PipeLine> listPipelinesById(Long id);
	/**
	 * 根据pipeline编号查找源交换实体
	 * @param id
	 * @return
	 */
	TransEntity getTargetByPipeId(Long id);
	/**
	 * 根据pipeline编号查找端交换实体
	 * @param id
	 * @return
	 */
	TransEntity getSourceByPipeId(Long id);
	/**
	 * 根据taskId找到PipeLine
	 * @return
	 */
	List<PipeLine>  findPipeLineByTaskId(Long taskId);
	List<Long>getpipidsByTaskId(Long taskId);
}	
