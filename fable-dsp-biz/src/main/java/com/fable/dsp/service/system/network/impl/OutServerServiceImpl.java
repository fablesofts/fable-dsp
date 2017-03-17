package com.fable.dsp.service.system.network.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fable.dsp.common.dto.network.NetCardDto;
import com.fable.dsp.core.service.GenericServiceImpl;
import com.fable.dsp.core.spring.DspPropertyConfigurer;
import com.fable.dsp.dao.system.network.intf.OutServerDao;
import com.fable.dsp.dmo.system.networkcfg.OutServer;
import com.fable.dsp.service.system.network.intf.OutServerService;
import com.fable.hamal.shuttle.common.utils.rmi.RmiUtil;
import com.fable.hamal.shuttle.communication.client.Communication;
import com.fable.outer.rmi.event.network.NetCardEvent;

/**
 * 
 * @author majy
 * 
 */
@Service("outServerService")
public class OutServerServiceImpl extends GenericServiceImpl<OutServer>
		implements OutServerService {

	@Autowired
	private OutServerDao outServerDao;

	@Override
	public OutServer getLastOne() {
		List<OutServer> serverList = this.outServerDao.listAll();
		OutServer server =null;
		if (serverList == null || serverList.isEmpty()) {
			server= new OutServer();
			outServerDao.save(server);
		} else {
			// 默认一条数据
			server = serverList.get(0);
		}
		return server;
	}

	@Override
	public List<NetCardDto> getRmiNetCardList() {
		@SuppressWarnings("unchecked")
		List<NetCardDto> cardDto = (List<NetCardDto>) communication.call(
				RmiUtil.getRmiUrl(DspPropertyConfigurer
						.getDspProperty(OUTER_RMI_ADDRESSES)),
				new NetCardEvent.ListAll());
		return cardDto;
	}

	@Resource(name = "client")
	Communication communication;
	private static String OUTER_RMI_ADDRESSES = "outer.rmi.addresses";

	@Override
	public void updateRmiCard(NetCardDto cardDto) {
		communication.call(RmiUtil.getRmiUrl(DspPropertyConfigurer
				.getDspProperty(OUTER_RMI_ADDRESSES)),
				new NetCardEvent.Effiect(cardDto));
		
	}
}
