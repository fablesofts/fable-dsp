package com.fable.dsp.service.system.network.intf;

import com.fable.dsp.core.service.GenericService;
import com.fable.dsp.dmo.system.networkcfg.InServer;
/**
 * 内服务器的Service
 * @author majy
 *
 */
public interface InServerService extends GenericService<InServer>   {

	/**
	 * 
	 * @return 获取最后一个网卡信息
	 */
	InServer getLastOne();
	
	
}
