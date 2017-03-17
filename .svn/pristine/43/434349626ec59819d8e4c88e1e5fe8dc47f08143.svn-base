package com.fable.dsp.dao.dashboard.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fable.dsp.common.constants.CommonConstants;
import com.fable.dsp.common.util.TaskSpeedUtil;
import com.fable.dsp.core.dao.hibernate.GenericDaoHibernate;
import com.fable.dsp.dao.dashboard.intf.TaskSpeedDao;
import com.fable.dsp.dmo.dataswitch.JobRunInfo;
import com.fable.dsp.dto.dashboard.JobRunInfoDto;

/**
 * 
 * @author liuz
 * 
 */
@Repository("taskSpeedDao")
public class TaskSpeedDaoHibernate extends GenericDaoHibernate<JobRunInfo>
		implements TaskSpeedDao {

	@Override
	public Long getTaskTotalSpeed() {
		// 查询每个任务的历史总流量的hql
		String hql = "select taskEntity.id as taskId, MAX(taskEntity.name) as taskName, SUM(loadRate) as loadRate from JobRunInfo group by taskEntity.id order by taskEntity.id";
		@SuppressWarnings("unchecked")
		List<Object[]> list = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createQuery(hql).list();
		List<JobRunInfoDto> dtos = new ArrayList<JobRunInfoDto>();
		for (Object[] objs : list) {
			JobRunInfoDto dto = new JobRunInfoDto();
			dto.setTaskId(new Long(objs[0].toString()));
			dto.setTaskName(objs[1].toString());
			dto.setLoadRate(new Long(objs[2].toString()));
			dtos.add(dto);
		}
		Long taskSpeed = 0L;// 定义当前正在运行任务的总流速
		// 判断当前时间与上次查询的时间间隔是否超过最大时间间隔，
		// 如果超过了，就先清空taskSpeedList，再把当前dtos直接放入taskFlowList，
		// 如果没有超过，就结合上次查询得到的流量数据（即taskFlowList中的数据）计算每个任务的流速，
		// 把流速大于0的任务流速数据存入taskSpeedList中
		Date now = new Date();
		// 当前时间与上次查询的时间间隔
		long interval = now.getTime() - TaskSpeedUtil.time.getTime();
		if (interval < CommonConstants.MAX_TIME_INTERVAL * 1000) {
			if (TaskSpeedUtil.taskFlowList != null) {
				List<JobRunInfoDto> taskSpeedList = getTaskSpeedList(dtos);
				TaskSpeedUtil.taskSpeedList = taskSpeedList;
				for (JobRunInfoDto dto : taskSpeedList) {
					taskSpeed += dto.getLoadRate();
				}
			} else {
				TaskSpeedUtil.taskSpeedList.clear();
			}
		} else {
			TaskSpeedUtil.taskSpeedList.clear();
		}
		TaskSpeedUtil.time = now;
		TaskSpeedUtil.taskFlowList = dtos;
		return taskSpeed;
	}

	/**
	 * 计算当前正在运行任务的流速
	 * 
	 * @param dtos
	 * @return
	 */
	private List<JobRunInfoDto> getTaskSpeedList(List<JobRunInfoDto> dtos) {
		List<JobRunInfoDto> taskSpeedList = new ArrayList<JobRunInfoDto>();
		// 获取上次查询的任务流量列表
		@SuppressWarnings("unchecked")
		List<JobRunInfoDto> lastDtos = TaskSpeedUtil.taskFlowList;
		for (JobRunInfoDto dto : dtos) {
			JobRunInfoDto jDto = new JobRunInfoDto();
			jDto.setTaskId(dto.getTaskId());
			jDto.setTaskName(dto.getTaskName());
			jDto.setLoadRate(dto.getLoadRate() / 5);
			for (JobRunInfoDto lastDto : lastDtos) {
				if (dto.getTaskId().equals(lastDto.getTaskId())) {
					// 设置任务在某段时间内的加载流量
					jDto.setLoadRate((dto.getLoadRate() - lastDto.getLoadRate()) / 5);
					break;
				}
			}
			// 把流速大于0的任务流速数据存入taskSpeedList中
			if (jDto.getLoadRate() > 0) {
				taskSpeedList.add(jDto);
			}
		}
		return taskSpeedList;
	}

}
