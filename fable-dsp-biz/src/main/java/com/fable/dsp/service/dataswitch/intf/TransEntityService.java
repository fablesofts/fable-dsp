package com.fable.dsp.service.dataswitch.intf;

import java.util.List;

import com.fable.dsp.core.service.GenericService;
import com.fable.dsp.dmo.dataswitch.TransEntity;

public interface TransEntityService extends GenericService<TransEntity>{
	public Long getIdByName(String name);
	/**
	 * 根据子任务id查找端数据源
	 * @param id
	 * @return
	 */
	List<TransEntity>getTargetByPipelineId(Long id);
}
