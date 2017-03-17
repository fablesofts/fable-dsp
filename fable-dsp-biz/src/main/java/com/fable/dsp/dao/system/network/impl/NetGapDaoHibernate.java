package com.fable.dsp.dao.system.network.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fable.dsp.core.dao.hibernate.GenericDaoHibernate;
import com.fable.dsp.dao.system.network.intf.NetGapDao;
import com.fable.dsp.dmo.system.networkcfg.NetGap;

@Repository("netGapDao")
public class NetGapDaoHibernate extends GenericDaoHibernate<NetGap> implements
		NetGapDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<NetGap> listAll() {
		return queryListByCriteria(NetGap.class);
	}

	@Override
	public void save(NetGap netGap) {
		this.getHibernateTemplate().save(netGap);
	}

}
