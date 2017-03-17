package com.fable.dsp.service.system.network.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fable.dsp.core.service.GenericServiceImpl;
import com.fable.dsp.dao.system.network.intf.InServerDao;
import com.fable.dsp.dmo.system.networkcfg.InServer;
import com.fable.dsp.service.system.network.intf.InServerService;

/**
 * 网络配置（内交换）.
 * 
 */
@Service("inServerService")
public class InServerServiceImpl extends GenericServiceImpl<InServer> implements
		InServerService {
	@Autowired
	private InServerDao inServerDao;

	@Override
	public InServer getLastOne() {
		List<InServer> inServers = this.inServerDao.listAll();
		InServer server = null;
		if (inServers == null || inServers.isEmpty()) {
			server = new InServer();
			inServerDao.save(server);
		} else {
			// 默认一条数据
			server = inServers.get(0);
		}
		return server;
	}

}
