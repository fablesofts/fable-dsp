package com.fable.dsp.controller.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fable.dsp.service.dashboard.intf.DspInfoService;

/**
 * 系统信息
 * 
 * 
 */
@Controller
@RequestMapping("/dashboard/dspinfo")
public class DspInfoController {

	@RequestMapping("/maintain")
	public String maintain() {
		return "dashboard/dspinfo-maintain";
	}
	
	@Autowired
	DspInfoService dspInfoService;

	/**
	 * 
	 * @return 返回RMI调用disk信息
	 */
	@RequestMapping("/findDspDiskInfo")
	@ResponseBody
	public String findDiskInfo() {
		return dspInfoService.findDiskinfo().toString();
	}

	/**
	 * 
	 * @return 返回RMI调用CPU信息
	 */
	@RequestMapping("/findDspCpuInfo")
	@ResponseBody
	public String sysCpuInfo() {
		return dspInfoService.findCpuinfo().toString();
	}
	
	/**
	 * 
	 * @return 返回RMI调用内存信息
	 */
	@RequestMapping("/findDspMemInfo")
	@ResponseBody
	public String sysMemInfo() {
		return dspInfoService.findMeminfo().toString();
	}
}
