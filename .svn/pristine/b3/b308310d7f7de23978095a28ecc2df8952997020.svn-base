package com.fable.dsp.dao.datasource.intf;

import java.util.List;

import com.fable.dsp.core.dao.GenericDao;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dmo.datasource.DataSourceInfo;

public interface DataSourceInfoDao extends GenericDao<DataSourceInfo> {
	
	
	/**
	 * 通过条件查找多条
	 * 
	 * @param DataSourceInfo
	 * @return
	 */
	List<DataSourceInfo> listDataSourceInfoListByConditions(DataSourceInfo source);
	
	
	/**
	 * 通过条件查询分页数据
	 * 
	 * @param pager
	 * @param DataSourceInfo
	 *            为空查询全部数据
	 * @return
	 */
	PageList<DataSourceInfo> findPageDataSourceInfo(Page pager, DataSourceInfo source);
	
	/**
	 * 获取内交换，内网的网卡
	 * 
	 * @param InterestInfo
	 * @return
	 */
	String getDrviceType();
	
	/**
	 * 查询是否有源名称相同的数据
	 * 
	 * @param DataSourceInfo
	 * @return
	 */
	boolean getSameSourceName(DataSourceInfo source);
	
	/**
	 * 获取老数据源
	 * @param source
	 */
	void getOldDataSourceInfo(DataSourceInfo source);
	

	/**
	 * 判断数据源是否已经进行任务配置
	 * @param id
	 * @return
	 */
	String isDelete(Long id);
	
}