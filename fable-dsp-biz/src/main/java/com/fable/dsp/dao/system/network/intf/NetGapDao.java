package com.fable.dsp.dao.system.network.intf;

import java.util.List;

import com.fable.dsp.core.dao.GenericDao;
import com.fable.dsp.dmo.system.networkcfg.NetGap;

public interface NetGapDao extends GenericDao<NetGap> {

	List<NetGap> listAll();

	void save(NetGap netGap);
	
}
