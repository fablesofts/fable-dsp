package com.fable.dsp.service.system.network.intf;

import com.fable.dsp.core.service.GenericService;
import com.fable.dsp.dmo.system.networkcfg.NetGap;

public interface NetGapService extends GenericService<NetGap> {

	NetGap getLastOne();

}
