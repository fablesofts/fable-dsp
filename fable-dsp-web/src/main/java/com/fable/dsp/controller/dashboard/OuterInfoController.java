package com.fable.dsp.controller.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fable.dsp.service.dashboard.intf.OuterInfoService;

/**
 * 系统信息
 * 
 * 
 */
@Controller
@RequestMapping("/dashboard/outerinfo")
public class OuterInfoController {

	@RequestMapping("/maintain")
	public String maintain() {
		return "dashboard/outerinfo-maintain";
	}
	
	@Autowired
	OuterInfoService outerInfoService;

	/**
	 * 
	 * @return 返回RMI调用disk信息
	 */
	@RequestMapping("/findOuterDiskInfo")
	@ResponseBody
	public String findDiskInfo()  {
		return outerInfoService.findDiskinfo().toString();
	}

	/**
	 * 
	 * @return 返回RMI调用CPU信息
	 */
	@RequestMapping("/findOuterCpuInfo")
	@ResponseBody
	public String sysCpuInfo() {
		return outerInfoService.findCpuinfo().toString();
	}
	
	/**
	 * 
	 * @return 返回RMI调用内存信息
	 */
	@RequestMapping("/findOuterMemInfo")
	@ResponseBody
	public String sysMemInfo() {
		return outerInfoService.findMeminfo().toString();
	}
}
