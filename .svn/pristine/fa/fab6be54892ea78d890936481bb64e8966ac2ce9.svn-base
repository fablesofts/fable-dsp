package com.fable.dsp.service.system.log.intf;

import java.util.List;
import java.util.Map;

import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.core.service.GenericService;
import com.fable.dsp.dmo.system.log.SystemLogDetailInfo;
import com.fable.dsp.dmo.system.log.SystemLogInfo;

/**
 * 
 * @author liuz
 * 
 */
public interface SystemLogInfoService extends GenericService<SystemLogInfo> {

	/**
	 * 分页查询系统日志数据
	 * 
	 * @param pager
	 *            分页参数对象
	 * @param systemLogInfo
	 * @return
	 */
	PageList<SystemLogInfo> findPageSystemLogInfo(Page pager,
			SystemLogInfo systemLogInfo);
	/**
	 * 查询系统日志详细信息
	 * @param systemLogInfo
	 * @return
	 */
	List<SystemLogDetailInfo> findSystemLogDetailInfo(
			SystemLogInfo systemLogInfo);
	/**
	 * @author majy
	 * @param pager
	 * @param map查询信息
	 * @return
	 */
	PageList<SystemLogInfo> findPageSystemLogInfoByExample(Page pager,
			Map<String, Object> map);

	

}
