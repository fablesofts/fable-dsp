package com.fable.dsp.dao.system.network.intf;

import java.util.List;

import com.fable.dsp.core.dao.GenericDao;
import com.fable.dsp.dmo.system.networkcfg.InServer;
/**
 * 
 * @author majy
 *
 */
public interface InServerDao extends GenericDao<InServer> {

	/**
	 * 查询全部内服务器的实体
	 * @return
	 */
	List<InServer> listAll();
	
	void save(InServer inServer);
}
