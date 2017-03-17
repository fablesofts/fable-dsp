package com.fable.dsp.service.dashboard.intf;

import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dmo.dataswitch.JobRunInfo;

public interface JobRunInfoService {
	/**
     * 查询任务运行状态信息
     * @param pager
     * @param jobRunInfo
     * @return
     */
	PageList<JobRunInfo>listJobRunInfo(Page pager,JobRunInfo jobRunInfo);
}
