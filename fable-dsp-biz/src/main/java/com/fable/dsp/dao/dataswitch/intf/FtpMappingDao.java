package com.fable.dsp.dao.dataswitch.intf;

import com.fable.dsp.core.dao.GenericDao;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dmo.ftp.FtpMapping;

public interface FtpMappingDao  extends GenericDao<FtpMapping>{
	/**
	 * 唯一查询
	 * @param myUserInfo
	 * @return
	 */
	FtpMapping getFtpByConditions(FtpMapping ftpMapping);
	/**
	 * 通过条件查询分页数据
	 * @param pager
	 * @param myUserInfo
	 *  为空查询全部数据
	 * @return
	 */
	PageList<FtpMapping>findFtpMappingList(Page pager,FtpMapping ftpMapping);
	/**
	 * 计算总记录数
	 * @param pager
	 * @param 
	 * @return
	 */
	public Long listCount(Page pager,final FtpMapping ftpMapping);
}
