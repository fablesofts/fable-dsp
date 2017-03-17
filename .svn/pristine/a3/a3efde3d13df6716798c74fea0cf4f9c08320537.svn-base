package com.fable.dsp.controller.log;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fable.dsp.common.constants.CommonConstants;
import com.fable.dsp.core.annotation.UserLogEntity;
import com.fable.dsp.core.datagrid.DataGridModel;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;

import com.fable.dsp.service.log.intf.UserOperationLogService;

/**
 * 系统信息
 * 
 * 
 */
@Controller
@RequestMapping("/userOperationLog")
public class LogController {
    
    @Autowired
    UserOperationLogService userOperationLogService;
    
    /**
     * @return 返回用户管理首页 地址
     */
    @RequestMapping("/maintain")
    public String maintain() {
        return "log/userLog";
    }
    
    /**
     * 
     * @param dgm 
     * @param userinfo 
     * @return 
     * @throws Exception
     */
    @RequestMapping("findUsersOperationLog")
    @ResponseBody
    public Map<String, Object> findUsersInfoList(final DataGridModel dgm,
        final UserLogEntity userLog) throws Exception {
        // 分页属性
        final Page pager = new Page();

        // 当前页,对应分页中的pageNumber参数;
        final int currentPage = (dgm.getPage() == 0) ? 1 : dgm.getPage();
        // 每页显示条数,对应EASYUI分页中的pageSize参数;
        final int pageSize =
            dgm.getRows() == 0 ? CommonConstants.COL_PAGESIZE : dgm
                .getRows();
        // 每页的开始记录 第一页为1 第二页为number +1
        final int index = (currentPage - 1) * pageSize;
        // 设置分页查询参数
        pager.setCurrentPage(currentPage);
        pager.setPageSize(pageSize);
        pager.setIndex(index);
        // 查询分页结果
        final PageList<UserLogEntity> pageuserinfo =
            this.userOperationLogService.findPageUserLogListByConditions(pager, new UserLogEntity());
        /**
         * json数据容器
         */
        final Map<String, Object> rootJson = new HashMap<String, Object>();

        rootJson.put("rows", pageuserinfo.getList());
        rootJson.put("total", pageuserinfo.getPage().getCount());
        return rootJson;

    }
}
