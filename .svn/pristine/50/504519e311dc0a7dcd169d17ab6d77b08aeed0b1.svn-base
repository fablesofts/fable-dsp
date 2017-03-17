package com.fable.dsp.dao.dashboard.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;

import com.fable.dsp.core.dao.hibernate.GenericDaoHibernate;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dao.dashboard.intf.JobInfoDao;
import com.fable.dsp.dmo.dataswitch.JobRunInfo;

/**
 * 操作子任务的DAO
 * 
 * @author Administrator
 */
@Repository("jobInfoDao")
public class JobInfoDaoHibernate extends GenericDaoHibernate<JobRunInfo>
implements JobInfoDao{

    @Override
    public char queryStutusByTaskId(Long id) {
       Session session=this.getHibernateTemplate().getSessionFactory().openSession();
       Character character= (Character)session.createSQLQuery("SELECT CURRENT_STATUS FROM JOB_RUN_INFO J WHERE TASK_ID="+id)
            .uniqueResult().toString().toCharArray()[0];
        session.close();
        return character;
    }

    @SuppressWarnings("unchecked")
	@Override
    public PageList<JobRunInfo> listJobRunInfo(final Page pager,
        final JobRunInfo jobRunInfo) {
        final String countHql = "select count(*) from JobRunInfo as j";
        final PageList<JobRunInfo> pageTask = new PageList<JobRunInfo>();
        final Long count =
            (Long)this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createQuery(countHql).uniqueResult();
        if (count > 0) {
            // 分页+条件：如果不为空，则
            List<JobRunInfo> list = this.getHibernateTemplate().getSessionFactory()
                    .getCurrentSession().createQuery("from JobRunInfo j left join fetch j.taskEntity")
                    .list();
            pager.setCount(count);
            pageTask.setList(list);
        }
        else {
            pageTask.setList(new ArrayList<JobRunInfo>());
            pager.setCount(0);
        }
        pageTask.setPage(pager);
        return pageTask;
    }
    
}
