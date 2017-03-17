package com.fable.dsp.service.dashboard.impl;

import java.util.Properties;

import org.springframework.stereotype.Service;

import com.fable.dsp.common.util.WatchSysInfo;
import com.fable.dsp.service.dashboard.intf.DspInfoService;


@Service("dspInfoService")
public class DspInfoServiceImpl implements DspInfoService {

	@Override
	public Double findCpuinfo() {
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name");
		if (os.startsWith("win") || os.startsWith("Win")) {
			return WatchSysInfo.getCpuRatioForWindows();
		}else if (os.startsWith("lin") || os.startsWith("Lin")) {
			try {
				return WatchSysInfo.getLinuxCpuUsage();
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
		}
		return null;
	}
	
	@Override
	public Double findMeminfo() {
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name");
		if (os.startsWith("win") || os.startsWith("Win")) {
			return WatchSysInfo.getMemerypre();
		}else if (os.startsWith("lin") || os.startsWith("Lin")) {
			try {
				return WatchSysInfo.getLinuxMemUsage();
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
		}
		return null;
	}

	@Override
	public Double findDiskinfo() {
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name");
		if (os.startsWith("win") || os.startsWith("Win")) {
			return WatchSysInfo.getDisk();
		}else if (os.startsWith("lin") || os.startsWith("Lin")) {
			try {
				return WatchSysInfo.getLinuxDiskUsage();
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
		}
		return null;
	}

}
