package com.fable.dsp.service.ftp.intf;

import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.core.service.GenericService;
import com.fable.dsp.dmo.ftp.FtpMapping;
/**
 * FTP路径映射服务接口
 * @author 邱爽
 *
 */
public interface FtpMappingService extends GenericService<FtpMapping>{
	/**
	 * FTP映射列表
	 * @param page
	 * @param ftpMapping
	 * @return
	 */
	PageList<FtpMapping> listFtpMapping(Page page, FtpMapping ftpMapping);
	
	/**
	 * 插入外交换MAPPING数据.
	 * @param ftpMapping
	 * @return
	 */
	boolean insertOutFtpMapping(FtpMapping ftpMapping);
	
	/**
	 * 修改外交换MAPPING数据.
	 * @param ftpMapping
	 * @return
	 */
	boolean updateOutFtpMapping(FtpMapping ftpMapping);
	
	/**
	 * 删除外交换MAPPING数据.
	 * @param ftpMapping
	 * @return
	 */
	boolean deleteOutFtpMapping(FtpMapping ftpMapping);
}
