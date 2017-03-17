package com.fable.dsp.service.dataswitch.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fable.dsp.dao.dataswitch.intf.ScheduleDao;
import com.fable.dsp.dmo.schedule.Schedule;
import com.fable.dsp.service.dataswitch.intf.ScheduleService;

@Service("scheduleService")
@Transactional(propagation = Propagation.REQUIRED,
    rollbackFor = Exception.class)
public class ScheduleServiceImpl implements ScheduleService{
    @Autowired
    private ScheduleDao scheduleDao;
    @Override
    public void insert(Schedule entity) {
        this.scheduleDao.insert(entity);
    }

    @Override
    public void deleteById(Long id) {
        this.scheduleDao.deleteById(id);
    }

    @Override
    public void delete(Schedule entity) {
        this.scheduleDao.delete(entity);
    }

    @Override
    public void update(Schedule entity) {
        this.scheduleDao.update(entity);
    }

    @Override
    public Schedule getById(Long id) {
        return this.scheduleDao.getById(id);
    }

    @Override
    public Schedule getScheduleByTaskId(Long id) {
        return this.scheduleDao.getScheduleByTaskId(id);
    }

    @Override
    public boolean isExistsSchedule(Long id) {
        return this.scheduleDao.isExistsSchedule(id);
    }

	@Override
	public boolean isExistsJob(Long id) {
		return this.scheduleDao.isExistsJob(id);
	}

}
