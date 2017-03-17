package com.fable.dsp.controller.ftp;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fable.dsp.common.constants.CommonConstants;
import com.fable.dsp.core.datagrid.DataGridModel;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.core.session.SessionData;
import com.fable.dsp.dmo.ftp.FtpMapping;
import com.fable.dsp.service.ftp.intf.FtpMappingService;
@Controller
@RequestMapping("/ftp")
public class FtpMappingController {
	@Autowired
	private FtpMappingService ftpMappingService;
	
	private final static Logger logger = LoggerFactory.getLogger(FtpMappingController.class);
	@RequestMapping("/ftp-mapping")
	public String maintain() {
		return "ftp/ftp-mapping";
	}
	
	/**
	 * 保存映射关系
	 * @param ftpMapping
	 * @param session
	 * @return
	 */
	@RequestMapping(value="saveFtpMapping",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveFtpMapping(FtpMapping ftpMapping,HttpSession session) {
		/**
		 * json数据容器
		 */
		Map<String, Object> rootJson = new HashMap<String, Object>();
		try {
		    
		        
		        final SessionData user =
		                        (SessionData)session.getAttribute(CommonConstants.SESSION_DATA);
		                ftpMapping.setCreateUser(user.getUserId());
		                ftpMappingService.insert(ftpMapping);
		                
		                ftpMappingService.insertOutFtpMapping(ftpMapping);
		                
		                rootJson.put(CommonConstants.COL_DEALFLAG, true);
		                rootJson.put(CommonConstants.COL_MSG, "映射关系保存成功");
			
		} catch (Exception e) {
			rootJson.put(CommonConstants.COL_DEALFLAG, false);
            rootJson.put(CommonConstants.COL_MSG, "映射关系保存失败");
		}
		return rootJson;
	}
	
    /**
	 * 删除映射关系
	 * @param ftpMapping
	 * @param session
	 * @return
	 */
	@RequestMapping("deleteFtpMapping")
	@ResponseBody
	public Map<String,Object> deleteUserMapping (FtpMapping ftpMapping,HttpSession session) {
		Map<String, Object> rootJson = new HashMap<String, Object>();
		try {
		    boolean bool = ftpMappingService.deleteOutFtpMapping(ftpMapping);
		    if(bool){
		        ftpMappingService.deleteById(ftpMapping.getId());
	            rootJson.put(CommonConstants.COL_DEALFLAG, true);
	            rootJson.put(CommonConstants.COL_MSG, "删除成功");
		    }else{
		        rootJson.put(CommonConstants.COL_DEALFLAG, false);
	            rootJson.put(CommonConstants.COL_MSG, "删除失败");
		    }
			
		} catch (Exception e) {
			rootJson.put(CommonConstants.COL_DEALFLAG, false);
			rootJson.put(CommonConstants.COL_MSG, "删除失败");
		}
		return rootJson;
	}
	@RequestMapping("getLoginUser")
	@ResponseBody
	public String getLoginUser (HttpSession session) {
		final SessionData user =(SessionData)session.getAttribute(CommonConstants.SESSION_DATA);
    	String userName = user.getUserName();
    	return userName;
	}
	/**
	 * 根据ID查询数据
	 * @param ftpUserDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findFtpMappingInfo")
	@ResponseBody
	public FtpMapping findFtpMapping (FtpMapping ftpMapping) {
		return ftpMappingService.getById(ftpMapping.getId());
		
	}
	/**
	 * 修改映射关系
	 * @param ftpMapping
	 * @param session
	 * @return
	 */
	@RequestMapping("updateFtpUserMapping")
	@ResponseBody
	public Map<String,Object> updateFtpUserMapping (FtpMapping ftpMapping,HttpSession session) {
		Map<String, Object> rootJson = new HashMap<String, Object>();
		final SessionData user =(SessionData)session.getAttribute(CommonConstants.SESSION_DATA);
		ftpMapping.setUpdateUser(user.getUserId());
		ftpMapping.setUpdateTime(new Date());
		try {
		    boolean bool = ftpMappingService.updateOutFtpMapping(ftpMapping);
		    if(bool){
		        ftpMappingService.update(ftpMapping);
		        rootJson.put(CommonConstants.COL_DEALFLAG, true);
	            rootJson.put(CommonConstants.COL_MSG, "修改FTP映射成功");
		    }else{
		        rootJson.put(CommonConstants.COL_DEALFLAG, false);
	            rootJson.put(CommonConstants.COL_MSG, "修改FTP映射失败");
		    }
		} catch (Exception e) {
			rootJson.put(CommonConstants.COL_DEALFLAG, false);
			rootJson.put(CommonConstants.COL_MSG, "修改FTP映射失败");
		}
		return rootJson;
	}
	/**
	 * 查询映射列表
	 * @param dgm
	 * @return
	 */
	@RequestMapping("findFtpMappingList")
    @ResponseBody
    public Map<String, Object> findFtpMappingList(DataGridModel dgm) {
 	 Page page = new Page();
	 int currentPage = (dgm.getPage() == 0) ? 1 : dgm.getPage();
        // 每页显示条数，对应EASYUI分页中的pageSize参数
     int pageSize = dgm.getRows() == 0 ?
            CommonConstants.COL_PAGESIZE : dgm.getRows();
        // 每页的开始记录 第一页为1，第二页为number+1
     int index = (currentPage - 1) * pageSize;
        // 设置分页查询参数
     page.setCurrentPage(currentPage);
     page.setPageSize(pageSize);
     page.setIndex(index);
        // 查询分页结果，一开始没有条件的分页查询
     FtpMapping ftpMapping = new FtpMapping();
     Map<String, Object> rootJson = new HashMap<String, Object>();
     try {
    	  PageList<FtpMapping> pageList = ftpMappingService.listFtpMapping(page,ftpMapping);
          rootJson.put(CommonConstants.DGV_ROWS, pageList.getList());
          rootJson.put(CommonConstants.DGV_TOTAL, pageList.getPage().getCount());
        }
     catch (Exception e) {
          logger.error("加载FTP用户列表时出现异常，异常信息为：{} "+ e.getMessage());
          rootJson.put(CommonConstants.COL_DEALFLAG, false);
          rootJson.put(CommonConstants.COL_MSG, "加载任务列表失败");
        }
     return rootJson;
 }
}
