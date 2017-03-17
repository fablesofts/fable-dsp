package com.fable.dsp.dao.dataswitch.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.fable.dsp.core.dao.hibernate.GenericDaoHibernate;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dao.dataswitch.intf.FtpMappingDao;
import com.fable.dsp.dmo.dataswitch.TaskEntity;
import com.fable.dsp.dmo.ftp.FtpMapping;
@Repository("ftpMappingDao")
public class FtpMappingDaoHibernate extends GenericDaoHibernate<FtpMapping> implements FtpMappingDao{

	@Override
	public FtpMapping getFtpByConditions(FtpMapping ftpMapping) {
		return null;
	}

	@Override
	public PageList<FtpMapping> findFtpMappingList(Page pager,
			FtpMapping ftpMapping) {
        final String countHql = "SELECT COUNT(*) FROM FtpMapping as f ";
        final PageList<FtpMapping> pageMapping = new PageList<FtpMapping>();
        Session session=getHibernateTemplate().getSessionFactory().openSession();
        final Long count = (Long) session.createQuery(countHql).uniqueResult();
        if (count > 0) {
            // 分页+条件：如果不为空，则
            @SuppressWarnings("unchecked")
            final List<FtpMapping> list =
                this.getHibernateTemplate().findByExample(
                		ftpMapping == null ? new FtpMapping() : ftpMapping,
                    pager.getIndex(), pager.getPageSize());
            pager.setCount(count);
            pageMapping.setList(list);
        }
        else {
        	pageMapping.setList(new ArrayList<FtpMapping>());
            pager.setCount(0);
        }
        pageMapping.setPage(pager);
        session.close();
        return pageMapping;
	}

	@Override
	public Long listCount(Page pager, FtpMapping ftpMapping) {
		return null;
	}
	
}
