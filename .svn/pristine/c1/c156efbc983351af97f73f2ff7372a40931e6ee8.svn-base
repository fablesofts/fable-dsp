package com.fable.dsp.dao.dashboard.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fable.dsp.core.dao.hibernate.GenericDaoHibernate;
import com.fable.dsp.dao.dashboard.intf.TaskFlowDao;
import com.fable.dsp.dmo.dataswitch.JobRunInfo;
import com.fable.dsp.dto.dashboard.JobRunInfoDto;

/**
 * 
 * @author liuz
 * 
 */
@Repository("taskFlowDao")
public class TaskFlowDaoHibernate extends GenericDaoHibernate<JobRunInfo>
		implements TaskFlowDao {

	@Override
	public List<JobRunInfoDto> findTaskFlowList() {
		final String hql = "select taskEntity.id as taskId, MAX(taskEntity.name) as taskName, SUM(loadRate) as loadRate from JobRunInfo group by taskEntity.id";
		@SuppressWarnings("unchecked")
		List<Object[]> list = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createQuery(hql).list();
		List<JobRunInfoDto> dtos = new ArrayList<JobRunInfoDto>();
		for(Object[] objs : list){
			JobRunInfoDto dto = new JobRunInfoDto();
			dto.setTaskId(new Long(objs[0].toString()));
			dto.setTaskName(objs[1].toString());
			dto.setLoadRate(new Long(objs[2].toString()));
			dtos.add(dto);
		}
		return dtos;
	}
}
