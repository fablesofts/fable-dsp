package com.fable.dsp.controller.log;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fable.dsp.core.datagrid.DataGridModel;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dmo.system.log.RowLevelLogInfo;
import com.fable.dsp.dmo.system.log.SystemLogDetailInfo;
import com.fable.dsp.dmo.system.log.SystemLogInfo;
import com.fable.dsp.service.system.log.intf.SystemLogDetailInfoService;
import com.fable.dsp.service.system.log.intf.SystemLogInfoService;

/**
 * 系统日志
 * 
 * @author liuz
 * 
 */
@Controller
@RequestMapping("/systemLog")
public class SystemLogInfoController {

	@Autowired
	SystemLogInfoService systemLogInfoService;
	@Autowired
	SystemLogDetailInfoService systemLogDetailInfoService;

	@RequestMapping("/maintain")
	public String maintain() {
		return "log/systemLog-maintain";
	}

	/**
	 * 分页查询系统日志信息（SYS_LOG）
	 * 
	 * @param dgm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listSystemLog")
	@ResponseBody
	public Map<String, Object> listSystemLog(DataGridModel dgm)
			throws Exception {
		Page pager = new Page();
		// 设置当前页码，对应分页中的pageNumber参数
		int currentPage = (dgm.getPage() == 0) ? 1 : dgm.getPage();
		// 设置每页显示条数，对应分页中的pageSize参数
		int pageSize = (dgm.getRows() == 0) ? 10 : dgm.getRows();
		// 每页的开始记录，第一页为1
		int index = (currentPage - 1) * pageSize;
		pager.setCurrentPage(currentPage);
		pager.setPageSize(pageSize);
		pager.setIndex(index);

		SystemLogInfo systemLogInfo = new SystemLogInfo();
		// 查询分页结果
		PageList<SystemLogInfo> pageList = this.systemLogInfoService
				.findPageSystemLogInfo(pager, systemLogInfo);

		Map<String, Object> rootJson = new HashMap<String, Object>();
		rootJson.put("rows", pageList.getList());
		rootJson.put("total", pageList.getPage().getCount());
		return rootJson;
	}

	@RequestMapping("listSystemLogByExample")
	@ResponseBody
	public Map<String, Object> listSystemLogByExample(DataGridModel dgm,
			boolean isAll, String taskName, Date startTime, Date endTime,
			Long selectCount_start, Long selectCount_end,
			Long selectData_start, Long selectData_end, Long loadCount_start,
			Long loadCount_end, Long loadData_start, Long loadData_end,
			Character operationResults) throws Exception {
		if (isAll) {
			return this.listSystemLog(dgm);
		}
		Page pager = new Page();
		// 设置当前页码，对应分页中的pageNumber参数
		int currentPage = (dgm.getPage() == 0) ? 1 : dgm.getPage();
		// 设置每页显示条数，对应分页中的pageSize参数
		int pageSize = (dgm.getRows() == 0) ? 10 : dgm.getRows();
		// 每页的开始记录，第一页为1
		int index = (currentPage - 1) * pageSize;
		pager.setCurrentPage(currentPage);
		pager.setPageSize(pageSize);
		pager.setIndex(index);
		// 查询分页结果
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("operationResults", operationResults);
		map.put("loadData_end", loadData_end);
		map.put("loadData_start", loadData_start);
		map.put("loadCount_end", loadCount_end);
		map.put("loadCount_start", loadCount_start);
		map.put("selectData_end", selectData_end);
		map.put("selectData_start", selectData_start);
		map.put("taskName", taskName);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("selectCount_start", selectCount_start);
		map.put("selectCount_end", selectCount_end);
		PageList<SystemLogInfo> pageList = this.systemLogInfoService
				.findPageSystemLogInfoByExample(pager, map);

		Map<String, Object> rootJson = new HashMap<String, Object>();
		rootJson.put("rows", pageList.getList());
		rootJson.put("total", pageList.getPage().getCount());
		return rootJson;
	}

	/**
	 * 查询系统日志对应的日志详细
	 * 
	 * @param systemLogInfo
	 * @return
	 */
	@RequestMapping("findSystemLogDetailInfo")
	@ResponseBody
	public List<SystemLogDetailInfo> findSystemLogDetailInfo(
			SystemLogInfo systemLogInfo) {
		List<SystemLogDetailInfo> list = this.systemLogInfoService
				.findSystemLogDetailInfo(systemLogInfo);
		return list;
	}

	/**
	 * 分页查询系统日志详细对应的行级日志(ROW_LEVEL_LOG)
	 * 
	 * @param systemLogDetailInfo
	 * @return
	 */
	@RequestMapping("listRowLevelLog")
	@ResponseBody
	public Map<String, Object> listRowLevelLog(
			SystemLogDetailInfo systemLogDetailInfo, DataGridModel dgm)
			throws Exception {
		Page pager = new Page();
		// 设置当前页码，对应分页中的pageNumber参数
		int currentPage = (dgm.getPage() == 0) ? 1 : dgm.getPage();
		// 设置每页显示条数，对应分页中的pageSize参数
		int pageSize = (dgm.getRows() == 0) ? 10 : dgm.getRows();
		// 每页的开始记录，第一页为1
		int index = (currentPage - 1) * pageSize;
		pager.setCurrentPage(currentPage);
		pager.setPageSize(pageSize);
		pager.setIndex(index);

		// 查询分页结果
		PageList<RowLevelLogInfo> pageList = this.systemLogDetailInfoService
				.findPageRowLevelLogInfo(pager, systemLogDetailInfo);

		Map<String, Object> rootJson = new HashMap<String, Object>();
		rootJson.put("rows", pageList.getList());
		rootJson.put("total", pageList.getPage().getCount());
		return rootJson;
	}

	/**
	 * 查询系统日志详细对应的行级日志
	 * 
	 * @param systemLogDetailInfo
	 * @return
	 */
//	@RequestMapping("findRowLevelLogInfo")
//	@ResponseBody
//	public List<RowLevelLogInfo> findRowLevelLogInfo(
//			SystemLogDetailInfo systemLogDetailInfo) {
//		List<RowLevelLogInfo> list = this.systemLogDetailInfoService
//				.findRowLevelLogInfo(systemLogDetailInfo);
//		return list;
//
//	}

}
