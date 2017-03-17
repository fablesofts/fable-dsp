package com.fable.dsp.service.dashboard.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fable.dsp.common.constants.SysConfigConstants;
import com.fable.dsp.common.util.SysConfigUtil;
import com.fable.dsp.service.dashboard.intf.OuterInfoService;
import com.fable.hamal.shuttle.common.utils.rmi.RmiUtil;
import com.fable.hamal.shuttle.communication.client.Communication;
import com.fable.outer.rmi.event.sysinfo.CpuInfoWatchEvent;
import com.fable.outer.rmi.event.sysinfo.DiskWatchEvent;
import com.fable.outer.rmi.event.sysinfo.MemInfoWatchEvent;

@Service("outerInfoService")
public class OuterInfoServiceImpl implements OuterInfoService {

	@Autowired
	Communication client;

	@Override
	public Double findCpuinfo() {
		Double cpuinfo = (Double) client.call(
				RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.OUTER_RMI_ADDRESS)),
				new CpuInfoWatchEvent());
		return cpuinfo;
	}
	
	@Override
	public Double findMeminfo() {
		Double meminfo = (Double) client.call(
				RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.OUTER_RMI_ADDRESS)),
				new MemInfoWatchEvent());
		return meminfo;
	}

	@Override
	public Double findDiskinfo() {
		Double diskinfo = (Double) client.call(
				RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.OUTER_RMI_ADDRESS)),
				new DiskWatchEvent());
		return diskinfo;
	}

}
