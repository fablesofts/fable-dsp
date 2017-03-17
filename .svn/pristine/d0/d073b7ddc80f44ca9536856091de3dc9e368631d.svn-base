package com.fable.dsp.dao.dashboard.intf;

import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dmo.dataswitch.JobRunInfo;
import com.fable.dsp.dmo.dataswitch.TaskEntity;


public interface JobInfoDao {
    /**
     * 通过任务编号查询状态
     * @param id任务编号
     * @return
     */
    char queryStutusByTaskId(Long id);
    /**
     * 查询任务运行状态信息
     * @param pager
     * @param jobRunInfo
     * @return
     */
	PageList<JobRunInfo>listJobRunInfo(Page pager,JobRunInfo jobRunInfo);
}
