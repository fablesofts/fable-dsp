package com.fable.dsp.controller.system;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fable.dsp.common.constants.CommonConstants;
import com.fable.dsp.common.constants.SysConfigConstants;
import com.fable.dsp.common.util.SysConfigUtil;
import com.fable.dsp.core.datagrid.DataGridModel;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.core.session.SessionData;
import com.fable.dsp.dmo.system.config.SysConfigInfo;
import com.fable.dsp.service.system.config.intf.SysConfigInfoService;

/**
 * 系统配置
 * 
 * @author liuz
 * 
 */
@Controller
@RequestMapping("/sysConfigInfo")
public class SysConfigInfoController {

	@Autowired
	SysConfigInfoService sysConfigInfoService;

	/**
	 * @return 返回系统配置界面地址
	 */
	@RequestMapping("/maintain")
	public String maintain() {
		return "configInfo/sysConfigInfo-maintain";
	}

	/**
	 * 查询系统配置信息
	 * 
	 * @param dgm
	 *            分页模型对象
	 * @return 返回查询的系统配置信息列表
	 * @throws Exception
	 */
	@RequestMapping("findSysConfigInfoList")
	@ResponseBody
	public Map<String, Object> findSysConfigInfoList(final DataGridModel dgm)
			throws Exception {
		// 分页属性
		final Page pager = new Page();
		// 当前页,对应分页中的pageNumber参数;
		final int currentPage = (dgm.getPage() == 0) ? 1 : dgm.getPage();
		// 每页显示条数,对应EASYUI分页中的pageSize参数;
		final int pageSize = (dgm.getRows() == 0) ? CommonConstants.COL_PAGESIZE
				: dgm.getRows();
		// 每页的开始记录 第一页为1 第二页为number +1
		final int index = (currentPage - 1) * pageSize;
		// 设置分页查询参数
		pager.setCurrentPage(currentPage);
		pager.setPageSize(pageSize);
		pager.setIndex(index);
		// 分页查询系统配置信息
		final PageList<SysConfigInfo> pageList = this.sysConfigInfoService
				.findSysConfigInfoByPage(pager, new SysConfigInfo());
		/**
		 * json数据容器
		 */
		final Map<String, Object> rootJson = new HashMap<String, Object>();
		rootJson.put("rows", pageList.getList());
		rootJson.put("total", pageList.getPage().getCount());
		return rootJson;
	}

	/**
	 * 添加系统配置参数信息
	 * 
	 * @param sysConfigInfo
	 *            添加的系统配置参数对象
	 * @param session
	 *            会话
	 * @return 返回添加成功或失败
	 */
	@RequestMapping("addSysConfigInfo")
	@ResponseBody
	public Map<String, Object> addSysConfigInfo(
			final SysConfigInfo sysConfigInfo, final HttpSession session)
			throws Exception {
		// json数据容器
		final Map<String, Object> rootJson = new HashMap<String, Object>();
		try {
			// 首先检查参数名是否已经存在，如果存在则不能添加，否则可以添加
			SysConfigInfo ci = this.sysConfigInfoService
					.getSysConfigInfoByName(sysConfigInfo.getSysConfigName());
			if (ci != null) {
				// 参数名已经存在，不能添加，向页面返回提示信息
				rootJson.put(CommonConstants.COL_DEALFLAG, false);
				rootJson.put(CommonConstants.COL_MSG, "添加配置参数失败，参数名已经存在");
				return rootJson;
			}
			// 从session中获取当前用户信息，设置参数创建人和创建时间
			final SessionData user = (SessionData) session
					.getAttribute(CommonConstants.SESSION_DATA);
			sysConfigInfo.setCreateUser(user.getUserId());
			sysConfigInfo.setCreateTime(new Date());
			sysConfigInfo.setDelFlag(CommonConstants.DEL_FLAG_NO);
			this.sysConfigInfoService.insert(sysConfigInfo);
			// 添加配置参数成功后，重新加载配置参数信息，只需要将configMap设置为null即可
			SysConfigUtil.setConfigMap(null);
			rootJson.put(CommonConstants.COL_DEALFLAG, true);
			rootJson.put(CommonConstants.COL_MSG, "添加配置参数成功");
		} catch (Exception e) {
			e.printStackTrace();
			rootJson.put(CommonConstants.COL_DEALFLAG, false);
			rootJson.put(CommonConstants.COL_MSG, "添加配置参数失败");
		}
		return rootJson;
	}

	@RequestMapping("updateSysConfigInfo")
	@ResponseBody
	public Map<String, Object> updateSysConfigInfo(
			final SysConfigInfo sysConfigInfo, final HttpSession session)
			throws Exception {
		// json数据容器
		final Map<String, Object> rootJson = new HashMap<String, Object>();
		try {
			// 首先检查配置参数名是否重复
			SysConfigInfo ci = this.sysConfigInfoService
					.getSysConfigInfoByName(sysConfigInfo.getSysConfigName());
			if (ci != null && !ci.getId().equals(sysConfigInfo.getId())) {
				rootJson.put(CommonConstants.COL_DEALFLAG, false);
				rootJson.put(CommonConstants.COL_MSG, "修改配置参数失败，参数名已经存在");
				return rootJson;
			}
			ci = this.sysConfigInfoService.getById(sysConfigInfo.getId());
			// 从session中获取当前用户信息，设置参数修改人和修改时间
			final SessionData user = (SessionData) session
					.getAttribute(CommonConstants.SESSION_DATA);
			ci.setUpdateUser(user.getUserId());
			ci.setUpdateTime(new Date());
			// 设置修改的属性
			ci.setName(sysConfigInfo.getName());
			ci.setSysConfigName(sysConfigInfo.getSysConfigName());
			ci.setSysConfigValue(sysConfigInfo.getSysConfigValue());
			ci.setCategory(sysConfigInfo.getCategory());
			ci.setDescription(sysConfigInfo.getDescription());
			this.sysConfigInfoService.update(ci);
			// 修改配置参数成功后，重新加载配置参数信息，只需要将configMap设置为null即可
			SysConfigUtil.setConfigMap(null);
			// 如果修改的是系统主题，则将修改后的主题绑定到session中
			if (SysConfigConstants.THEME.equals(sysConfigInfo
					.getSysConfigName())) {
				session.removeAttribute(SysConfigConstants.THEME);
				session.setAttribute(SysConfigConstants.THEME,
						sysConfigInfo.getSysConfigValue());
			}
			rootJson.put(CommonConstants.COL_DEALFLAG, true);
			rootJson.put(CommonConstants.COL_MSG, "修改配置参数成功");
		} catch (Exception e) {
			e.printStackTrace();
			rootJson.put(CommonConstants.COL_DEALFLAG, false);
			rootJson.put(CommonConstants.COL_MSG, "修改配置参数失败");
		}
		return rootJson;
	}

	/**
	 * 删除配置参数
	 * 
	 * @param id
	 *            要删除的配置参数的ID
	 * @return 返回删除成功或失败
	 * @throws Exception
	 */
	@RequestMapping("deleteSysConfigInfo")
	@ResponseBody
	public Map<String, Object> deleteSysConfigInfo(final HttpSession session,
			final Long id) throws Exception {
		// json数据容器
		Map<String, Object> rootJson = new HashMap<String, Object>();
		try {
			// 删除之前要做其他的处理
			// 系统级别的配置参数不能删除
			SysConfigInfo ci = this.sysConfigInfoService.getById(id);
			if ("sys".equals(ci.getCategory())) {
				rootJson.put(CommonConstants.COL_DEALFLAG, false);
				rootJson.put(CommonConstants.COL_MSG, "删除配置参数失败，不能删除系统配置参数");
				return rootJson;
			}
			ci.setDelFlag(CommonConstants.DEL_FLAG_YES);
			this.sysConfigInfoService.deleteById(id);
			// 删除配置参数成功后，重新加载配置参数信息，只需要将configMap设置为null即可
			SysConfigUtil.setConfigMap(null);
			// 如果删除的是系统主题配置，则就把系统主题定为默认主题
			if (SysConfigConstants.THEME.equals(ci.getSysConfigName())) {
				session.removeAttribute(SysConfigConstants.THEME);
				session.setAttribute(SysConfigConstants.THEME, "default");
			}
			rootJson.put(CommonConstants.COL_DEALFLAG, true);
			rootJson.put(CommonConstants.COL_MSG, "删除配置参数成功");
		} catch (Exception e) {
			e.printStackTrace();
			rootJson.put(CommonConstants.COL_DEALFLAG, false);
			rootJson.put(CommonConstants.COL_MSG, "删除配置参数失败");
		}
		return rootJson;
	}

	/**
	 * 判断配置参数名是否重复
	 * 
	 * @param sysConfigName
	 *            参数名
	 * @param id
	 *            参数ID
	 * @return 参数名重复则返回false，否则返回true
	 * @throws Exception
	 */
	@RequestMapping("isSameConfigName")
	@ResponseBody
	public Map<String, Object> isSameConfigName(final String sysConfigName,
			final Long id) throws Exception {
		// json数据容器
		Map<String, Object> rootJson = new HashMap<String, Object>();
		SysConfigInfo ci = this.sysConfigInfoService
				.getSysConfigInfoByName(sysConfigName);
		// 如果参数名重复则返回false，否则返回true
		if (ci != null && !ci.getId().equals(id))
			rootJson.put(CommonConstants.COL_DEALFLAG, false);
		else
			rootJson.put(CommonConstants.COL_DEALFLAG, true);
		return rootJson;
	}

}
