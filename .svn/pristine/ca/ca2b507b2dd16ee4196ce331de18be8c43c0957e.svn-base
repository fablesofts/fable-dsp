package com.fable.dsp.controller.dashboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fable.dsp.common.constants.CommonConstants;
import com.fable.dsp.common.util.TaskSpeedUtil;
import com.fable.dsp.core.datagrid.DataGridModel;
import com.fable.dsp.dto.dashboard.JobRunInfoDto;
import com.fable.dsp.service.dashboard.intf.TaskSpeedService;

/**
 * 任务流速
 * 
 * @author liuz
 * 
 */
@Controller
@RequestMapping("/dashboard/taskSpeed")
public class TaskSpeedController {

	@Autowired
	TaskSpeedService taskSpeedService;

	/**
	 * @return 返回界面地址
	 */
	@RequestMapping("/maintain")
	public String maintain() {
		return "dashboard/taskSpeed-maintain";
	}

	/**
	 * 获取所有任务的总加载流量
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getTaskTotalSpeed")
	@ResponseBody
	public Long getTaskTotalSpeed() throws Exception {
		return this.taskSpeedService.getTaskTotalSpeed();
	}

	/**
	 * 获取当前正在运行任务的流速列表
	 * 
	 * @param dgm
	 *            分页模型实体
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("findTaskSpeedList")
	@ResponseBody
	public Map<String, Object> findTaskSpeedList(final DataGridModel dgm)
			throws Exception {
		// 当前页,对应分页中的pageNumber参数;
		final int currentPage = (dgm.getPage() == 0) ? 1 : dgm.getPage();
		// 每页显示条数,对应EASYUI分页中的pageSize参数;
		final int pageSize = dgm.getRows() == 0 ? CommonConstants.COL_PAGESIZE
				: dgm.getRows();
		// 每页的开始记录 第一页为1 第二页为number +1
		final int index = (currentPage - 1) * pageSize;
		List<JobRunInfoDto> list = TaskSpeedUtil.taskSpeedList.subList(index,
				index + pageSize);
		/**
		 * json数据容器
		 */
		final Map<String, Object> rootJson = new HashMap<String, Object>();
		rootJson.put("rows", list);
		rootJson.put("total", TaskSpeedUtil.taskSpeedList.size());
		return rootJson;
	}
}
