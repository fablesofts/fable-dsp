package com.fable.dsp.dao.dashboard.intf;

import java.util.List;

import com.fable.dsp.core.dao.GenericDao;
import com.fable.dsp.dmo.dataswitch.JobRunInfo;
import com.fable.dsp.dto.dashboard.JobRunInfoDto;

/**
 * 
 * @author liuz
 * 
 */
public interface TaskFlowDao extends GenericDao<JobRunInfo> {
	/**
	 * 查询任务历史总流量信息
	 * @return
	 */
	List<JobRunInfoDto> findTaskFlowList();

}
