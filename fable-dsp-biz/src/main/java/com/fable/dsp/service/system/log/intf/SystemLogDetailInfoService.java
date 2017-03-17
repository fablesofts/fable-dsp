package com.fable.dsp.service.system.log.intf;

import java.util.List;

import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.core.service.GenericService;
import com.fable.dsp.dmo.system.log.RowLevelLogInfo;
import com.fable.dsp.dmo.system.log.SystemLogDetailInfo;

public interface SystemLogDetailInfoService extends GenericService<SystemLogDetailInfo> {

	/**
	 * 查询系统日志详细对应的行级日志
	 * @param systemLogDetailInfo
	 * @return
	 */
//	LIST<ROWLEVELLOGINFO> FINDROWLEVELLOGINFO(
//			SYSTEMLOGDETAILINFO SYSTEMLOGDETAILINFO);
	/**
	 * 分页查询系统日志详细对应的行级日志
	 * @param pager
	 * @param systemLogDetailInfo
	 * @return
	 */
	PageList<RowLevelLogInfo> findPageRowLevelLogInfo(Page pager,
			SystemLogDetailInfo systemLogDetailInfo);
	
}
