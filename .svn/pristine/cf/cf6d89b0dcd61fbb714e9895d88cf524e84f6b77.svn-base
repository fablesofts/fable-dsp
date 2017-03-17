package com.fable.dsp.service.system.log.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dao.system.log.intf.SystemLogDetailInfoDao;
import com.fable.dsp.dmo.system.log.RowLevelLogInfo;
import com.fable.dsp.dmo.system.log.SystemLogDetailInfo;
import com.fable.dsp.service.system.log.intf.SystemLogDetailInfoService;

/**
 * 
 * @author Administrator
 * 
 */
@Service("systemLogDetailInfoService")
public class SystemLogDetailInfoServiceImpl implements
		SystemLogDetailInfoService {

	@Autowired
	SystemLogDetailInfoDao systemLogDetailInfoDao;

	@Override
	public PageList<RowLevelLogInfo> findPageRowLevelLogInfo(Page pager,
			SystemLogDetailInfo systemLogDetailInfo) {
		return systemLogDetailInfoDao.findPageRowLevelLogInfo(pager,systemLogDetailInfo);
	}

//	@Override
//	public List<RowLevelLogInfo> findRowLevelLogInfo(
//			SystemLogDetailInfo systemLogDetailInfo) {
//		systemLogDetailInfo = getById(systemLogDetailInfo.getId());
//		return systemLogDetailInfo.getRowLevelLogs();
//	}

	@Override
	public void insert(SystemLogDetailInfo entity) {
		systemLogDetailInfoDao.insert(entity);
	}

	@Override
	public void deleteById(Long id) {
		systemLogDetailInfoDao.deleteById(id);
	}

	@Override
	public void delete(SystemLogDetailInfo entity) {
		systemLogDetailInfoDao.delete(entity);
	}

	@Override
	public void update(SystemLogDetailInfo entity) {
		systemLogDetailInfoDao.update(entity);
	}

	@Override
	public SystemLogDetailInfo getById(Long id) {
		return systemLogDetailInfoDao.getById(id);
	}

}
