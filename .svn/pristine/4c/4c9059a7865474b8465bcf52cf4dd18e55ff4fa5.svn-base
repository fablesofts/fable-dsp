package com.fable.dsp.service.datasource.intf;

import java.util.List;

import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.core.service.GenericService;
import com.fable.dsp.dmo.datasource.DataSourceInfo;

public interface DataSourceInfoService extends GenericService<DataSourceInfo> {
	
	/**
	 * 通过条件查找多条
	 * 
	 * @param InterestInfo
	 * @return
	 */
	List<DataSourceInfo> listDataSourceInfoListByConditions(DataSourceInfo source);
	
	/**
	 * 查询file的数据源
	 * 
	 * @param InterestInfo
	 * @return
	 */
	List<DataSourceInfo> listDataSourceInfoListByFile(DataSourceInfo source);
	
	
	/**
	 * 查询DB的数据源
	 * 
	 * @param InterestInfo
	 * @return
	 */
	List<DataSourceInfo> listDataSourceInfoListByDb(DataSourceInfo source);
	
	
	/**
	 * 通过条件查询分页数据
	 * 
	 * @param pager
	 * @param DataSourceInfo
	 *            为空查询全部数据
	 * @return PageList<DataSourceInfo>
	 */
	PageList<DataSourceInfo> findPageDataSourceInfo(Page pager, DataSourceInfo source);
	
	/**
	 * 判断IP设备的类型（是内网IP还是外网IP）
	 * @param DataSourceInfo
	 * @return boolean
	 */
	boolean getDeviceType(DataSourceInfo source);
	
	/**
	 * 数据源网络测试
	 * @param DataSourceInfo
	 * @return String
	 */
	String testNetwork(DataSourceInfo source,String datatype);
	
	
	/**
	 * 查询是否有源名称相同的数据
	 * 
	 * @param DataSourceInfo
	 * @return
	 */
	boolean getSameSourceName(DataSourceInfo source);
	
	/**
	 * 不解密getById
	 * 
	 * @param DataSourceInfo
	 * @return
	 */
	DataSourceInfo getNoEncryptById(Long id);
	
	/**
     * 通过ID获取
     * 
     * @param DataSourceInfo
     * @return
     */
	DataSourceInfo getByOne(Long id);
	
	/**
     * 判断数据源是否已经进行任务配置
     * @param id
     * @return
     */
	String isDelete(Long id);
}
