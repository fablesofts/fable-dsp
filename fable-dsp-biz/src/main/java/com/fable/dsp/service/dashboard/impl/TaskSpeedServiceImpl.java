package com.fable.dsp.service.dashboard.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fable.dsp.dao.dashboard.intf.TaskSpeedDao;
import com.fable.dsp.dmo.dataswitch.JobRunInfo;
import com.fable.dsp.service.dashboard.intf.TaskSpeedService;
/**
 * 
 * @author liuz
 *
 */
@Service("taskSpeedService")
public class TaskSpeedServiceImpl implements TaskSpeedService {

	@Autowired
	TaskSpeedDao taskSpeedDao;
	
	@Override
	public void insert(JobRunInfo entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(JobRunInfo entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(JobRunInfo entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JobRunInfo getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTaskTotalSpeed() {
		return this.taskSpeedDao.getTaskTotalSpeed();
	}

}
