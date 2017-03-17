package com.fable.dsp.dao.dataswitch.intf;

import com.fable.dsp.core.dao.GenericDao;
import com.fable.dsp.dmo.dataswitch.PipeLine;
import com.fable.dsp.dmo.schedule.Schedule;

/**
 * 调度信息的dao
 * @author Administrator
 *
 */
public interface ScheduleDao extends GenericDao<Schedule>{
    /**
     * 通过taskId查询schedule
     * @param id
     * @return
     */
    Schedule getScheduleByTaskId(Long id);
    /**
     * 通过taskId删除调度任务
     * @param id
     */
    void deleteByTaskId(Long id);
    /**
     * 判断调度是否存在
     * @param id
     * @return
     */
    boolean isExistsSchedule(Long id);
    /**
     * 判断作业是否存在
     * @param id
     * @return
     */
	boolean isExistsJob(Long id);
    
}
