package com.fable.dsp.service.system.network.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fable.dsp.core.service.GenericServiceImpl;
import com.fable.dsp.dao.system.network.intf.NetGapDao;
import com.fable.dsp.dmo.system.networkcfg.NetGap;
import com.fable.dsp.service.system.network.intf.NetGapService;

/**
 * 网闸
 * 
 * @author majy
 * 
 */
@Service("netGapService")
public class NetGapServiceImpl extends GenericServiceImpl<NetGap> implements
		NetGapService {

	@Autowired
	NetGapDao netGapDao;

	@Override
	public NetGap getLastOne() {
		List<NetGap> gaps = netGapDao.listAll();
		NetGap netGap=null;
		if (null == gaps || gaps.isEmpty()) {
			netGap=new NetGap();
			netGapDao.save(netGap);
		}else{
			netGap=gaps.get(0);
		}
		return netGap;
	}

}
