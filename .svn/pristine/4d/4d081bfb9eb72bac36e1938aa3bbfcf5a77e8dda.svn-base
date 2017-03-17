package com.fable.dsp.controller.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fable.dsp.dto.dashboard.JobRunInfoDto;
import com.fable.dsp.service.dashboard.intf.TaskFlowService;

/**
 * 任务流量
 * 
 * @author liuz
 * 
 */
@Controller
@RequestMapping("/dashboard/taskFlow")
public class TaskFlowController {

	@Autowired
	TaskFlowService taskFlowService;

	/**
	 * @return 返回界面地址
	 */
	@RequestMapping("/maintain")
	public String maintain() {
		return "dashboard/taskFlow-maintain";
	}

	/**
	 * 查询任务历史总流量信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findTaskFlowList")
	@ResponseBody
	public List<JobRunInfoDto> findTaskFlowList() throws Exception {
		List<JobRunInfoDto> dtos = this.taskFlowService.findTaskFlowList();
		return dtos;
	}

}
