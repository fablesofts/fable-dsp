package com.fable.dsp.dao.system.log.intf;

import java.util.List;
import java.util.Map;

import com.fable.dsp.core.dao.GenericDao;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dmo.system.log.SystemLogDetailInfo;
import com.fable.dsp.dmo.system.log.SystemLogInfo;

/**
 * 
 * @author liuz
 * 
 */
public interface SystemLogInfoDao extends GenericDao<SystemLogInfo> {

	/**
	 * 分页查询系统日志数据
	 * @param pager 分页参数对象
	 * @param systemLogInfo
	 * @return
	 */
	PageList<SystemLogInfo> findPageSystemLogInfo(Page pager,
			SystemLogInfo systemLogInfo);

	/**
	 * @author majy
	 * @param pager
	 * @param map
	 * @return
	 */
	PageList<SystemLogInfo> findPageSystemLogInfoByExample(Page pager,
			Map<String, Object> map);
	
	List<SystemLogDetailInfo> findSystemLogDetail(SystemLogInfo systemLogInfo);

}
