package com.fable.dsp.service.dashboard.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dao.dashboard.impl.JobInfoDaoHibernate;
import com.fable.dsp.dmo.dataswitch.JobRunInfo;
import com.fable.dsp.service.dashboard.intf.JobRunInfoService;
@Service("jobRunInfoService")
public class JobRunInfoServiceImpl implements JobRunInfoService{
	@Autowired
	private JobInfoDaoHibernate jobInfoDaoHibernate;
	@Override
	public PageList<JobRunInfo> listJobRunInfo(Page pager, JobRunInfo jobRunInfo) {
		return jobInfoDaoHibernate.listJobRunInfo(pager, jobRunInfo);
	}

}
