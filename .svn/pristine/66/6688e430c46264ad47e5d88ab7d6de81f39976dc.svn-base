package com.fable.dsp.service.system.config.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dao.system.config.intf.SysConfigInfoDao;
import com.fable.dsp.dmo.system.config.SysConfigInfo;
import com.fable.dsp.service.system.config.intf.SysConfigInfoService;

/**
 * 
 * @author liuz
 * 
 */
@Service("sysConfigInfoService")
public class SysConfigInfoServiceImpl implements SysConfigInfoService {

	@Autowired
	SysConfigInfoDao sysConfigInfoDao;

	@Override
	public void insert(SysConfigInfo entity) {
		this.sysConfigInfoDao.insert(entity);
	}

	@Override
	public void deleteById(Long id) {
		this.sysConfigInfoDao.deleteById(id);
	}

	@Override
	public void delete(SysConfigInfo entity) {
		this.sysConfigInfoDao.delete(entity);
	}

	@Override
	public void update(SysConfigInfo entity) {
		this.sysConfigInfoDao.update(entity);
	}

	@Override
	public SysConfigInfo getById(Long id) {
		return this.sysConfigInfoDao.getById(id);
	}

	@Override
	public PageList<SysConfigInfo> findSysConfigInfoByPage(Page pager,
			SysConfigInfo sysConfigInfo) {
		return this.sysConfigInfoDao.findSysConfigInfoByPage(pager,sysConfigInfo);
	}

	@Override
	public SysConfigInfo getSysConfigInfoByName(String sysConfigName) {
		return this.sysConfigInfoDao.getSysConfigInfoByName(sysConfigName);
	}

	@Override
	public List<SysConfigInfo> findSysConfigInfoList() {
		return this.sysConfigInfoDao.findSysConfigInfoList();
	}

}
