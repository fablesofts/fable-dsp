package com.fable.dsp.service.dashboard.intf;

import com.fable.dsp.core.service.GenericService;
import com.fable.dsp.dmo.dataswitch.JobRunInfo;
/**
 * 任务流速业务层接口
 * @author liuz
 *
 */
public interface TaskSpeedService extends GenericService<JobRunInfo> {
	/**
	 * 获取所有任务的总加载流量
	 * @return
	 */
	Long getTaskTotalSpeed();

}
