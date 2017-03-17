package com.fable.dsp.dao.system.log.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.fable.dsp.core.dao.hibernate.GenericDaoHibernate;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dao.system.log.intf.SystemLogInfoDao;
import com.fable.dsp.dmo.dataswitch.PipeLine;
import com.fable.dsp.dmo.system.log.SystemLogDetailInfo;
import com.fable.dsp.dmo.system.log.SystemLogInfo;

/**
 * 
 * @author liuz
 * 
 */
@Repository("systemLogInfoDao")
public class SystemLogInfoDaoHibernate extends
		GenericDaoHibernate<SystemLogInfo> implements SystemLogInfoDao {

	@Override
	public PageList<SystemLogInfo> findPageSystemLogInfo(Page pager,
			SystemLogInfo systemLogInfo) {
		PageList<SystemLogInfo> pageList = new PageList<SystemLogInfo>();
		// 获取总记录数
		Long count = Long.valueOf(getHibernateTemplate().findByExample(
				systemLogInfo).size());
		if (count > 0) {
			// 查询指定页码的日志数据
			@SuppressWarnings("unchecked")
			List<SystemLogInfo> list = getHibernateTemplate()
					.findByExample(
							systemLogInfo == null ? new SystemLogInfo()
									: systemLogInfo, pager.getIndex(),
							pager.getPageSize());
			pageList.setList(list);
			pager.setCount(count);
		} else {
			pageList.setList(new ArrayList<SystemLogInfo>());
			pager.setCount(0);
		}
		pageList.setPage(pager);
		return pageList;
	}

	public PageList<SystemLogInfo> findPageSystemLogInfoByExample(Page pager,
			Map<String, Object> map) {
		PageList<SystemLogInfo> pageList = new PageList<SystemLogInfo>();
		DetachedCriteria criteria = DetachedCriteria
				.forClass(SystemLogInfo.class);
		bindExample(map, criteria);
		@SuppressWarnings("unchecked")
		List<SystemLogInfo> sysList = getHibernateTemplate().findByCriteria(
				criteria);
		if (sysList.isEmpty()) {
			pager.setCount(0);
			pageList.setList(new LinkedList<SystemLogInfo>());
		} else {
			Long count = (long) sysList.size();
			pager.setCount(count);
			int start = pager.getPageSize() * (pager.getCurrentPage() - 1);
			int end = (int) Math.min(start + pager.getPageSize(), count);
			pageList.setList(sysList.subList(start, end));
		}
		pageList.setPage(pager);
		return pageList;
	}

	private void bindExample(Map<String, Object> map, DetachedCriteria criteria) {
		if (null != map.get("operationResults")) {
			criteria.add(Restrictions.eq("operationResults",
					map.get("operationResults")));
		}
		if (null != map.get("loadData_end")) {
			criteria.add(Restrictions.le("loadData", map.get("loadData_end")));
		}
		if (null != map.get("loadCount_end")) {
			criteria.add(Restrictions.le("loadCount", map.get("loadCount_end")));
		}
		if (null != map.get("endTime")) {
			criteria.add(Restrictions.le("endTime", (Date) map.get("endTime")));
		}
		if (null != map.get("selectCount_end")) {
			criteria.add(Restrictions.le("selectCount",
					map.get("selectCount_end")));
		}
		if (null != map.get("selectData_end")) {
			criteria.add(Restrictions.le("selectData",
					map.get("selectData_end")));
		}
		if (null != map.get("taskName")) {
			criteria.createCriteria("task").add(
					Restrictions.like("name",
							String.valueOf(map.get("taskName")),
							MatchMode.ANYWHERE));
		}
		if (null != map.get("loadData_start")) {
			criteria.add(Restrictions.ge("loadData", map.get("loadData_start")));
		}
		if (null != map.get("startTime")) {
			criteria.add(Restrictions.ge("startTime",
					(Date) map.get("startTime")));
		}
		if (null != map.get("selectCount_start")) {
			criteria.add(Restrictions.ge("selectCount",
					map.get("selectCount_start")));
		}
		if (null != map.get("selectData_start")) {
			criteria.add(Restrictions.ge("selectData",
					map.get("selectData_start")));
		}
		if (null != map.get("loadCount_start")) {
			criteria.add(Restrictions.ge("loadCount",
					map.get("loadCount_start")));
		}
	}

    @Override
    @SuppressWarnings("unchecked")
    public List<SystemLogDetailInfo> findSystemLogDetail(
        SystemLogInfo systemLogInfo) {
        final Session session = this.getHibernateTemplate().getSessionFactory().openSession();
        List<SystemLogDetailInfo> list =(List<SystemLogDetailInfo>)session.createSQLQuery(
            "SELECT * FROM SYS_LOG_DETAIL WHERE TASK_ID=" + systemLogInfo.getTaskId()+" AND TASK_SERIAL='"+systemLogInfo.getTaskSerial()+"'")
            .addEntity(SystemLogDetailInfo.class).list();
            session.close();
        return list;
    }

}
