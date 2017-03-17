package com.fable.dsp.dao.system.network.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fable.dsp.core.dao.hibernate.GenericDaoHibernate;
import com.fable.dsp.dao.system.network.intf.OutServerDao;
import com.fable.dsp.dmo.system.networkcfg.OutServer;

@Repository("outServerDao")
public class OutServerDaoHibernate extends GenericDaoHibernate<OutServer>
		implements OutServerDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<OutServer> listAll() {
		return queryListByCriteria(OutServer.class);
	}

	@Override
	public void save(OutServer server) {
		getHibernateTemplate().save(server);
	}

}
