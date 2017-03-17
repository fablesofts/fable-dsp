package com.fable.dsp.dao.system.log.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.fable.dsp.core.dao.hibernate.GenericDaoHibernate;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dao.system.log.intf.SystemLogDetailInfoDao;
import com.fable.dsp.dmo.system.log.RowLevelLogInfo;
import com.fable.dsp.dmo.system.log.SystemLogDetailInfo;

@Repository("systemLogDetailInfoDao")
public class SystemLogDetailInfoDaoHibernate extends
		GenericDaoHibernate<SystemLogDetailInfo> implements
		SystemLogDetailInfoDao {

	@Override
	@SuppressWarnings("unchecked")
	public PageList<RowLevelLogInfo> findPageRowLevelLogInfo(Page pager,
			SystemLogDetailInfo systemLogDetailInfo) {
	    Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		PageList<RowLevelLogInfo> pageList = new PageList<RowLevelLogInfo>();
		// 获取总记录数
		String sql = "SELECT COUNT(*) FROM ROW_LEVEL_LOG WHERE TASK_ID=" + systemLogDetailInfo.getTaskId()+" AND TASK_SERIAL='"+systemLogDetailInfo.getTaskSerial()+"'";
		Long count = Long.valueOf(session.createSQLQuery(sql).uniqueResult()+"").longValue();
		if (count != null && count > 0) {
			// 有记录，分页查询
			String sql1 = "SELECT * from ROW_LEVEL_LOG where TASK_ID=" + systemLogDetailInfo.getTaskId()+" AND TASK_SERIAL='"+systemLogDetailInfo.getTaskSerial()+"'";
			List<RowLevelLogInfo> list = (List<RowLevelLogInfo>)session.createSQLQuery(sql1)
			        .addEntity(RowLevelLogInfo.class)
					.setFirstResult(pager.getIndex())
					.setMaxResults(pager.getPageSize())
					.list();
			pageList.setList(list);
			pager.setCount(count);
		} else {
			// 没有记录
			pageList.setList(new ArrayList<RowLevelLogInfo>());
			pager.setCount(0);
		}
		pageList.setPage(pager);
		session.close();
		return pageList;
	}

}
