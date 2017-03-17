package com.fable.dsp.service.dashboard.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fable.dsp.dao.dashboard.intf.TaskFlowDao;
import com.fable.dsp.dmo.dataswitch.JobRunInfo;
import com.fable.dsp.dto.dashboard.JobRunInfoDto;
import com.fable.dsp.service.dashboard.intf.TaskFlowService;

/**
 * 
 * @author liuz
 * 
 */
@Service("taskFlowService")
public class TaskFlowServiceImpl implements TaskFlowService {

	@Autowired
	TaskFlowDao taskFlowDao;

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
	public List<JobRunInfoDto> findTaskFlowList() {
		return this.taskFlowDao.findTaskFlowList();
	}

}
