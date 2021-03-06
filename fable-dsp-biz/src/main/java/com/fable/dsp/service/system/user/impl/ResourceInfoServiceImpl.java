package com.fable.dsp.service.system.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dao.system.user.intf.ResourceInfoDao;
import com.fable.dsp.dmo.system.user.MenuInfo;
import com.fable.dsp.dmo.system.user.ResourceInfo;
import com.fable.dsp.dmo.system.user.RoleInfo;
import com.fable.dsp.service.system.user.intf.ResourceInfoService;

/**
 * 
 * @author liuz
 * 
 */
@Service("resourceInfoService")
public class ResourceInfoServiceImpl implements ResourceInfoService {

	@Autowired
	ResourceInfoDao resourceInfoDao;

	@Override
	public PageList<ResourceInfo> findResourceInfoByPage(Page pager, Long menuId) {
		return this.resourceInfoDao.findResourceInfoByPage(pager, menuId);
	}

	@Override
	public void insert(ResourceInfo entity) {
		this.resourceInfoDao.insert(entity);
	}

	@Override
	public void deleteById(Long id) {
		this.resourceInfoDao.deleteById(id);
	}

	@Override
	public void delete(ResourceInfo entity) {
		this.resourceInfoDao.delete(entity);
	}

	@Override
	public void update(ResourceInfo entity) {
		this.resourceInfoDao.update(entity);
	}

	@Override
	public ResourceInfo getById(Long id) {
		return this.resourceInfoDao.getById(id);
	}

	@Override
	public ResourceInfo getResourceInfoByName(String name) {
		return this.resourceInfoDao.getResourceInfoByName(name);
	}

	@Override
	public void merge(ResourceInfo ri) {
		this.resourceInfoDao.merge(ri);
	}

	@Override
	public ResourceInfo getResourceInfoByURL(String url) {
		return this.resourceInfoDao.getResourceInfoByURL(url);
	}

	@Override
	public Map<String, Object> getResourceMap() {
		Map<String, Object> map=new HashMap<String,Object>();
		List<ResourceInfo> resources = this.resourceInfoDao.listAllResource();
		for (ResourceInfo resource : resources) {
			// 临时权限列表
			List<Object> list = new ArrayList<Object>();
			for (MenuInfo menu : resource.getMenus()) {
				for (RoleInfo role : menu.getRoles()) {
					list.add(role.getRoleName());
				}
			}
			map.put(resource.getUrl(), list);
		}
		return map;
	}

}
