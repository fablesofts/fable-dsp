package com.fable.dsp.dao.system.network.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.fable.dsp.core.dao.hibernate.GenericDaoHibernate;
import com.fable.dsp.dao.system.network.intf.InServerDao;
import com.fable.dsp.dmo.system.networkcfg.InServer;

/**
 * 
 * @author majy
 * 
 */
@Repository("inServerDao")
public class InServerDaoHibernate extends GenericDaoHibernate<InServer>
		implements InServerDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<InServer> listAll() {
		return getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(InServer.class));
	}

	@Override
	public void save(InServer inServer) {
		getHibernateTemplate().save(inServer);
	}

}
