package com.fable.dsp.dao.dataswitch.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.fable.dsp.core.dao.hibernate.GenericDaoHibernate;
import com.fable.dsp.dao.dataswitch.intf.TransEntityDao;
import com.fable.dsp.dmo.dataswitch.TransEntity;

@Repository("transEntityDao")
public class TransEntityDaoHibernate extends GenericDaoHibernate<TransEntity> implements TransEntityDao{

	@Override
	public void insert(TransEntity entity) {
		super.insert(entity);
	}

	@Override
	public Long getIdByName(String name) {
		String sql="SELECT e.id FROM TransEntity e WHERE e.name=?";
		return (Long)this.getHibernateTemplate().find(sql,name).get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TransEntity> getTargetByPipelineId(final Long id) {
		 
		 return this.getHibernateTemplate().executeFind(new HibernateCallback<List<TransEntity>>() {
			@Override
			public List<TransEntity> doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder sbsql=new StringBuilder();
				sbsql.append(" from TransEntity where id IN (");
				sbsql.append("	 SELECT target_id FROM dsp_pipeline WHERE ID="+id+"");
				sbsql.append(" )");
				return session.createQuery(sbsql.toString()).list();
			}
		});
	}

	@Override
	public List<BigDecimal> getEntitiesIdByTaskId(Long taskId) {
		final Session session = getHibernateTemplate().getSessionFactory().openSession();
		final StringBuilder sql = new StringBuilder();
		sql.append("  SELECT ID FROM DSP_TRANS_ENTITY WHERE ID IN ");
		sql.append(" (");
		sql.append(" SELECT SOURCE_ID FROM DSP_PIPELINE WHERE ID IN");
		sql.append("    (SELECT ID FROM DSP_PIPELINE WHERE TASK_ID="+taskId+")");
		sql.append(" ) OR ID IN");
		sql.append("(");
		sql.append(" SELECT TARGET_ID FROM DSP_PIPELINE WHERE ID IN");
		sql.append(" (SELECT ID FROM DSP_PIPELINE WHERE TASK_ID="+taskId+")");
		sql.append(")");
		final List<BigDecimal>list= session.createSQLQuery(sql.toString()).list();
		session.close();
		return list;
	}

	@Override
	public void deletePipeLineByTargetId(Long long1) {
		try {
			final Session session=this.getHibernateTemplate().getSessionFactory().openSession();
			Transaction transaction=session.beginTransaction();
			session.delete(session.createQuery(" from PipeLine p left join fetch p.targetEntity e where e.id=:id").setParameter("id", long1).uniqueResult());
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	
}
