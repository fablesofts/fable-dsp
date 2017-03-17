package com.fable.dsp.service.dataswitch.intf;

import com.fable.dsp.core.service.GenericService;
import com.fable.dsp.dmo.dataswitch.PipeLine;
import com.fable.dsp.dmo.schedule.Schedule;


public interface ScheduleService extends GenericService<Schedule>{

    Schedule getScheduleByTaskId(Long id);

    boolean isExistsSchedule(Long id);

	boolean isExistsJob(Long id);

}
