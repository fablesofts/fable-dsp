package com.fable.dsp.dao.system.network.intf;

import java.util.List;

import com.fable.dsp.core.dao.GenericDao;
import com.fable.dsp.dmo.system.networkcfg.OutServer;
/**
 * 
 * @author majy
 *
 */
public interface OutServerDao extends GenericDao<OutServer> {

	List<OutServer> listAll();

	void save(OutServer server);
	
}
