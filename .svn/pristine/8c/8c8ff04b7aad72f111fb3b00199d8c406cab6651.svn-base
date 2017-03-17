package com.fable.dsp.dao.system.config.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fable.dsp.core.dao.hibernate.GenericDaoHibernate;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dao.system.config.intf.SysConfigInfoDao;
import com.fable.dsp.dmo.system.config.SysConfigInfo;

/**
 * 
 * @author liuz
 * 
 */
@Repository("sysConfigInfoDao")
public class SysConfigInfoDaoHibernate extends
		GenericDaoHibernate<SysConfigInfo> implements SysConfigInfoDao {

	@Override
	public PageList<SysConfigInfo> findSysConfigInfoByPage(Page pager,
			SysConfigInfo sysConfigInfo) {
		PageList<SysConfigInfo> pageList = new PageList<SysConfigInfo>();
		// 首先查询数据库中配置信息记录的数量，并设置分页参数；
		// 然后根据记录的数量进行如下操作；
		// 如果数据库中有记录就分页查询，否则不查询。
		sysConfigInfo = sysConfigInfo == null ? new SysConfigInfo()
				: sysConfigInfo;
		Long count = Long.valueOf(this.getHibernateTemplate()
				.findByExample(sysConfigInfo).size());
		pager.setCount(count);
		pageList.setPage(pager);
		if (count > 0) {
			@SuppressWarnings("unchecked")
			List<SysConfigInfo> list = this.getHibernateTemplate()
					.findByExample(sysConfigInfo, pager.getIndex(),
							pager.getPageSize());
			pageList.setList(list);
		} else {
			pageList.setList(new ArrayList<SysConfigInfo>());
		}
		return pageList;
	}

	@Override
	public SysConfigInfo getSysConfigInfoByName(String sysConfigName) {
		if (sysConfigName == null || sysConfigName.isEmpty())
			return null;
		final String hql = "from SysConfigInfo where sysConfigName = ?";
		@SuppressWarnings("unchecked")
		List<SysConfigInfo> list = this.getHibernateTemplate().find(hql,
				sysConfigName);
		return list.isEmpty() ? null : list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysConfigInfo> findSysConfigInfoList() {
		return this.getHibernateTemplate().findByExample(new SysConfigInfo());
	}

}
