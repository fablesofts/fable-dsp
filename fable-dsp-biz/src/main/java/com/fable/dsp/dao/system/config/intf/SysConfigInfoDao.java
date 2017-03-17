package com.fable.dsp.dao.system.config.intf;

import java.util.List;

import com.fable.dsp.core.dao.GenericDao;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dmo.system.config.SysConfigInfo;

/**
 * 
 * @author liuz
 * 
 */
public interface SysConfigInfoDao extends GenericDao<SysConfigInfo> {
	/**
	 * 分页查询系统配置信息
	 * @param pager 分页参数对象
	 * @param sysConfigInfo 系统配置信息对象
	 * @return 返回指定页的系统配置信息列表
	 */
	PageList<SysConfigInfo> findSysConfigInfoByPage(Page pager,
			SysConfigInfo sysConfigInfo);
	/**
	 * 根据参数名查询系统配置参数信息
	 * @param sysConfigName 配置参数名
	 * @return 返回查询的配置参数信息
	 */
	SysConfigInfo getSysConfigInfoByName(String sysConfigName);
	/**
	 * 查询配置参数信息列表
	 * @return
	 */
	List<SysConfigInfo> findSysConfigInfoList();

}
