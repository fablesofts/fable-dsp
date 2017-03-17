package com.fable.dsp.dao.dataswitch.impl;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.fable.dsp.core.dao.hibernate.GenericDaoHibernate;
import com.fable.dsp.dao.dataswitch.intf.ScheduleDao;
import com.fable.dsp.dmo.schedule.Schedule;
/**
 * 调度信息DAO的实现类
 * @author Administrator
 *
 */
@Repository("scheduleDao")
public class ScheduleDaoHibernate extends GenericDaoHibernate<Schedule>implements ScheduleDao{

    @Override
    public Schedule getScheduleByTaskId(final Long id) {
        return this.getHibernateTemplate().execute(new HibernateCallback<Schedule>() {

            @Override
            public Schedule doInHibernate(Session session)
                throws HibernateException, SQLException {
                return (Schedule)session.createQuery(" from Schedule s left join fetch s.taskEntity t where t.id=:id")
                                .setParameter("id",id).uniqueResult();
            }
            
        });
    }

    @Override
    public void deleteByTaskId(Long id) {
        final Session session =
                        this.getHibernateTemplate().getSessionFactory().openSession();
        Transaction tr=session.beginTransaction(); 
        Schedule schedule=(Schedule)session.createQuery(" from Schedule s left join fetch s.taskEntity t where t.id=:id")
        .setParameter("id", id).uniqueResult();
        session.delete(schedule);
        tr.commit();
        session.close();
    }

    @Override
    public boolean isExistsSchedule(Long id) {
        Session session=this.getHibernateTemplate().getSessionFactory().openSession();
        int count=new Integer(session.createSQLQuery("SELECT COUNT(*) FROM DSP_SCHEDULE_CONFIG WHERE TASK_ID="+id)
        		.uniqueResult().toString());
        session.close();
        if(count>0){
            return true;
        }else{
            return false;
        }
    }

	@Override
	public boolean isExistsJob(Long id) {
		Session session=this.getHibernateTemplate().getSessionFactory().openSession();
        int count=new Integer(session.createSQLQuery("SELECT COUNT(*) FROM JOB_RUN_INFO WHERE TASK_ID="+id)
        		.uniqueResult().toString());
        session.close();
        if(count>0){
            return true;
        }else{
            return false;
        }
	}

}
