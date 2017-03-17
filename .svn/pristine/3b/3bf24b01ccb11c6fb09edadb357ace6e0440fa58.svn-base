package com.fable.dsp.dao.dataswitch.intf;

import java.math.BigDecimal;
import java.util.List;

import com.fable.dsp.core.dao.GenericDao;
import com.fable.dsp.dmo.dataswitch.TransEntity;

public interface TransEntityDao extends GenericDao<TransEntity>{

	Long getIdByName(String name);
	/**
	 * 根据子任务id查找端数据源
	 * @param id
	 * @return
	 */
	List<TransEntity>getTargetByPipelineId(Long id);
	List<BigDecimal>getEntitiesIdByTaskId(Long taskId);
	void deletePipeLineByTargetId(Long long1);
}
