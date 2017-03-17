package com.fable.dsp.service.system.log.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dao.system.log.intf.SystemLogInfoDao;
import com.fable.dsp.dmo.system.log.SystemLogDetailInfo;
import com.fable.dsp.dmo.system.log.SystemLogInfo;
import com.fable.dsp.service.system.log.intf.SystemLogInfoService;

/**
 * 
 * @author liuz
 * 
 */
@Service("systemLogInfoService")
public class SystemLogInfoServiceImpl implements SystemLogInfoService {

	@Autowired
	SystemLogInfoDao systemLogInfoDao;

	// @Autowired
	// SystemLogDetailInfoDao systemLogDetailInfoDao;

	@Override
	public PageList<SystemLogInfo> findPageSystemLogInfo(Page pager,
			SystemLogInfo systemLogInfo) {
		return systemLogInfoDao.findPageSystemLogInfo(pager, systemLogInfo);
	}

	@Override
	public List<SystemLogDetailInfo> findSystemLogDetailInfo(
			SystemLogInfo systemLogInfo) {
		return systemLogInfoDao.findSystemLogDetail(systemLogInfo);
	}

	@Override
	public void insert(SystemLogInfo entity) {
		systemLogInfoDao.insert(entity);
	}

	@Override
	public void deleteById(Long id) {
		systemLogInfoDao.deleteById(id);
	}

	@Override
	public void delete(SystemLogInfo entity) {
		systemLogInfoDao.delete(entity);
	}

	@Override
	public void update(SystemLogInfo entity) {
		systemLogInfoDao.update(entity);
	}

	public SystemLogInfo getByCondition(SystemLogInfo entity) {
	    
	    return null;
	}

	@Override
	public PageList<SystemLogInfo> findPageSystemLogInfoByExample(Page pager,
			Map<String, Object> map) {
		return this.systemLogInfoDao.findPageSystemLogInfoByExample(pager, map);
	}

    @Override
    public SystemLogInfo getById(Long id) {
        return null;
    }

}
