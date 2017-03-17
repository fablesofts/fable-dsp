package com.fable.dsp.service.system.network.intf;

import java.util.List;

import com.fable.dsp.common.dto.network.NetCardDto;
import com.fable.dsp.core.service.GenericService;
import com.fable.dsp.dmo.system.networkcfg.OutServer;
/**
 * 
 * @author majy
 *
 */
public interface OutServerService extends GenericService<OutServer> {

	OutServer getLastOne();

	List<NetCardDto> getRmiNetCardList();

	void updateRmiCard(NetCardDto cardDto);
	
}
