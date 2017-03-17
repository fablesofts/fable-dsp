package com.fable.dsp.dao.system.log.intf;

import com.fable.dsp.core.dao.GenericDao;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dmo.system.log.RowLevelLogInfo;
import com.fable.dsp.dmo.system.log.SystemLogDetailInfo;

public interface SystemLogDetailInfoDao extends GenericDao<SystemLogDetailInfo> {
	/**
	 * 分页查询系统日志详细对应的行级日志
	 * @param pager 分页参数对象
	 * @param systemLogDetailInfo
	 * @return
	 */
	PageList<RowLevelLogInfo> findPageRowLevelLogInfo(Page pager,
			SystemLogDetailInfo systemLogDetailInfo);
	
}
