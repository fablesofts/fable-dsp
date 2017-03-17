package com.fable.dsp.controller.dashboard;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fable.dsp.common.constants.CommonConstants;
import com.fable.dsp.controller.dataswitch.TaskController;
import com.fable.dsp.core.datagrid.DataGridModel;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dmo.dataswitch.JobRunInfo;
import com.fable.dsp.dmo.dataswitch.PipeLine;
import com.fable.dsp.dmo.dataswitch.TaskEntity;
import com.fable.dsp.service.dashboard.intf.JobRunInfoService;

@Controller
@RequestMapping("/dashboard/jobRunInfo")
public class JobRunInfoController {
    /**
     * datagrid分页常量
     */
    private static final String DGV_ROWS = "rows";
    /**
     * datagrid分页常量
     */
    private static final String DGV_TOTAL = "total";
	@Autowired
	private JobRunInfoService jobRunInfoService;
	@RequestMapping("/maintain")
	public String maintain() {
		return "dashboard/jobruninfo-maintain";
	}
	private static final Logger logger = LoggerFactory.getLogger(JobRunInfoController.class);
	@RequestMapping("listJobRunInfo")
	@ResponseBody
	public Map<String,Object> listJobRunInfo (DataGridModel dgm) throws Exception {
		Page page = new Page();
		int currentPage = (dgm.getPage() == 0) ? 1 : dgm.getPage();
		int pageSize = dgm.getRows() == 0 ? CommonConstants.COL_PAGESIZE : dgm.getRows();
		int index = (currentPage - 1) * pageSize;
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setIndex(index);
        Map<String, Object> rootJson = new HashMap<String, Object>();
        try {
        	JobRunInfo jobRunInfo = new JobRunInfo();
        	jobRunInfo.setPipeLine(new PipeLine());
        	jobRunInfo.setTaskEntity(new TaskEntity());
			PageList<JobRunInfo> pageList = jobRunInfoService.listJobRunInfo(page, jobRunInfo);
			rootJson.put(DGV_ROWS,pageList.getList());
			rootJson.put(DGV_TOTAL,pageList.getPage().getCount());
		} catch (Exception e) {
			logger.error("查询任务状态出现异常，异常信息为：{} ",e.getMessage());
			rootJson.put(CommonConstants.COL_DEALFLAG, false);
            rootJson.put(CommonConstants.COL_MSG, "加载任务状态信息失败");
            throw e;
		}
        return rootJson;
	}
}
