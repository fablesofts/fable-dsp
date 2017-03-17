package com.fable.dsp.service.ftp.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fable.dsp.common.constants.SysConfigConstants;
import com.fable.dsp.common.dto.dataswitch.FtpUserDto;
import com.fable.dsp.common.exception.FtpManagerException;
import com.fable.dsp.common.util.SysConfigUtil;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dmo.dataswitch.FtpBaseUser;
import com.fable.dsp.service.ftp.intf.FtpService;
import com.fable.hamal.shuttle.common.model.config.FtpUser;
import com.fable.hamal.shuttle.common.utils.rmi.RmiUtil;
import com.fable.hamal.shuttle.communication.client.Communication;
import com.fable.hamal.shuttle.communication.event.ftp.FtpStatusEvent;
import com.fable.hamal.shuttle.communication.event.ftp.FtpdResumeEvent;
import com.fable.hamal.shuttle.communication.event.ftp.FtpdStartEvent;
import com.fable.hamal.shuttle.communication.event.ftp.FtpdStopEvent;
import com.fable.hamal.shuttle.communication.event.ftp.FtpdSuspendEvent;
import com.fable.hamal.shuttle.communication.event.ftp.ListUserEvent;
import com.fable.hamal.shuttle.communication.event.ftp.UniqueQueryUserEvent;
import com.fable.hamal.shuttle.communication.event.ftp.UserDeleteEvent;
import com.fable.hamal.shuttle.communication.event.ftp.UserDoesExist;
import com.fable.hamal.shuttle.communication.event.ftp.UserEditEvent;
import com.fable.hamal.shuttle.communication.event.ftp.UserIsAdminEvent;
import com.fable.hamal.shuttle.communication.event.ftp.UserSaveEvent;
import com.fable.hamal.shuttle.communication.event.ftp.UserUpPwdEvent;
import com.fable.outer.rmi.event.ftpserver.FtpUserDeleteEvent;
import com.fable.outer.rmi.event.ftpserver.FtpUserEvent;
import com.fable.outer.rmi.event.ftpserver.FtpUserListEvent;
import com.fable.outer.rmi.event.ftpserver.FtpUserOneEvent;
import com.fable.outer.rmi.event.ftpserver.FtpUserUpdate;
import com.fable.outer.rmi.event.ftpserver.FtpUserUpdatePassEvent;
@Service("ftpService")
public class FtpServiceImpl implements FtpService {
	private final static Logger logger =LoggerFactory.getLogger(FtpServiceImpl.class);
	/**
	 * 内交换通信地址
	 */
//	private static String FTP_RMI_ADDRESS = "ftp.server.communication.address";
	/**
	 * 外交换通信地址
	 */
//	private static String OUTERFTP_RMI_ADDRESS = "ftp.outerserver.communication.address";
	@Autowired
    private Communication client;



	@Override
	public void runFtpServer() {
		try {
//			client.call(RmiUtil.getRmiUrl(DspPropertyConfigurer.getDspProperty(FTP_RMI_ADDRESS)), new FtpdStartEvent());
			// 直接从系统配置参数configMap中获取ftp服务器通信地址
			client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.FTP_SERVER_ADDRESS)), new FtpdStartEvent());
		} catch (Exception e) {
			logger.error("启动内交换ftp服务时出现异常，异常信息:{}",e.getMessage());
			throw new FtpManagerException(e);
		}
	}
	
	
	@Override
	public void runOuterFtpServer() {
		try {
//			client.call(RmiUtil.getRmiUrl(DspPropertyConfigurer.getDspProperty(OUTERFTP_RMI_ADDRESS)),new FtpdStartEvent());
			// 直接从系统配置参数configMap中获取ftp外服务器通信地址
			client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.FTP_OUTERSERVER_ADDRESS)),new FtpdStartEvent());
		} catch (Exception e) {
			logger.error("启动外交换ftp服务时出现异常，异常信息：{}",e.getMessage());
			throw new FtpManagerException(e);
		}
	}
	
	@Override
	public void stopFtpServer() {
		try {
//			client.call(RmiUtil.getRmiUrl(DspPropertyConfigurer.getDspProperty(FTP_RMI_ADDRESS)), new FtpdStopEvent());
			// 直接从系统配置参数configMap中获取ftp服务器通信地址
			client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.FTP_SERVER_ADDRESS)), new FtpdStopEvent());
		} catch (Exception e) {
			logger.error("停止内交换ftp服务时出现异常，异常信息：{}",e.getMessage());
			throw new FtpManagerException(e);
		}
	}

	@Override
	public void insert(FtpUser ftpUser) {
		try {
			if(logger.isDebugEnabled()){
//				client.call(RmiUtil.getRmiUrl(DspPropertyConfigurer.getDspProperty(FTP_RMI_ADDRESS)), new UserSaveEvent(ftpUser));
				// 直接从系统配置参数configMap中获取ftp服务器通信地址
				client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.FTP_SERVER_ADDRESS)), new UserSaveEvent(ftpUser));
				logger.debug("增加FTP用户成功");
			}
		} catch (Exception e) {
			logger.error("增加FTP用户失败，异常信息为："+e.getMessage());
			throw new FtpManagerException(e);
		}
	}
	
	
	public void insertOuter(FtpUser ftpUser){
	    
	    try {
            client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.OUTER_RMI_ADDRESS)), new FtpUserEvent(ftpUser));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	@Override
	public boolean editFtpUser(FtpUser ftpUser) {
		boolean flag = false;
		try {
			if(logger.isDebugEnabled()) {
//				client.call(RmiUtil.getRmiUrl(DspPropertyConfigurer.getDspProperty(FTP_RMI_ADDRESS)), new UserEditEvent(ftpUser));
				// 直接从系统配置参数configMap中获取ftp服务器通信地址
				client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.FTP_SERVER_ADDRESS)), new UserEditEvent(ftpUser));
				logger.debug("编辑FTP用户成功");
			}
			flag = true;
		} catch (Exception e) {
			logger.error("编辑FTP用户异常，异常信息为：" +e.getMessage());
			throw new FtpManagerException(e);
		}
		return flag;
	}

	@Override
	public boolean uppwd(FtpUser ftpUser) {
//		return (Boolean) this.client.call(RmiUtil.getRmiUrl(DspPropertyConfigurer.getDspProperty(FTP_RMI_ADDRESS)),new UserUpPwdEvent(ftpUser));
		return (Boolean) this.client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.FTP_SERVER_ADDRESS)),new UserUpPwdEvent(ftpUser));
	}

	@Override
	public String getFtpServerStatus() {
//		return (String) client.call(RmiUtil.getRmiUrl(DspPropertyConfigurer.getDspProperty(FTP_RMI_ADDRESS)), new FtpStatusEvent());
		return (String) client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.FTP_SERVER_ADDRESS)), new FtpStatusEvent());

	}
	@Override
	public String getOuterFtpServerStatus() {
//		return (String) client.call(RmiUtil.getRmiUrl(DspPropertyConfigurer.getDspProperty(OUTERFTP_RMI_ADDRESS)), new FtpStatusEvent());
		return (String) client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.FTP_OUTERSERVER_ADDRESS)), new FtpStatusEvent());
	}
	
	@Override
	public void suspendFtpServer() {
		try {
//			client.call(RmiUtil.getRmiUrl(DspPropertyConfigurer.getDspProperty(FTP_RMI_ADDRESS)),new FtpdSuspendEvent());
			client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.FTP_SERVER_ADDRESS)),new FtpdSuspendEvent());
		} catch (Exception e) {
			logger.error("暂停ftp服务时出现异常，异常信息：{}",e.getMessage());
			throw new FtpManagerException(e);
		}
	}

	@Override
	public void suspendOuterFtpServer() {
		try {
//			client.call(RmiUtil.getRmiUrl(DspPropertyConfigurer.getDspProperty(OUTERFTP_RMI_ADDRESS)),new FtpdSuspendEvent());
			client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.FTP_OUTERSERVER_ADDRESS)),new FtpdSuspendEvent());
		} catch (Exception e) {
			logger.error("暂停外交换ftp服务出现异常，异常信息：{}",e.getMessage());
			throw new FtpManagerException(e);
		}
	}
	
	@Override
	public void resumeFtpServer() {
		try {
//			client.call(RmiUtil.getRmiUrl(DspPropertyConfigurer.getDspProperty(FTP_RMI_ADDRESS)),new FtpdResumeEvent());
			client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.FTP_SERVER_ADDRESS)),new FtpdResumeEvent());
		} catch (Exception e) {
			logger.error("恢复内交换ftp服务时出现异常，异常信息：{}",e.getMessage());
			throw new FtpManagerException(e);
		}
	}
	
	@Override
	public void resumeOuterFtpServer() {
		try {
//			client.call(RmiUtil.getRmiUrl(DspPropertyConfigurer.getDspProperty(OUTERFTP_RMI_ADDRESS)),new FtpdResumeEvent());
			client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.FTP_OUTERSERVER_ADDRESS)),new FtpdResumeEvent());
		} catch (Exception e) {
			logger.error("恢复内交换ftp服务时出现异常，异常信息：{}",e.getMessage());
			throw new FtpManagerException(e);
		}
	}
	@Override
	public PageList<FtpUserDto> listFtpUser(Page page, FtpUserDto ftpUser) {
//		List<FtpUser> ftpUsers=(List<FtpUser>) client.call(RmiUtil.getRmiUrl(DspPropertyConfigurer.getDspProperty(FTP_RMI_ADDRESS)), new ListUserEvent());
		List<FtpUser> ftpUsers=(List<FtpUser>) client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.FTP_SERVER_ADDRESS)), new ListUserEvent());
		PageList<FtpUserDto> pageList = new PageList<FtpUserDto>();
		List<FtpUserDto> userlist = new ArrayList<FtpUserDto>();
		for(int i = 0;i< ftpUsers.size(); i++) {
			FtpUserDto user = new FtpUserDto();
			user.setFtpUsername(ftpUsers.get(i).getFtpUsername());
			user.setWebFlag("1");
			user.setHomedirectory(ftpUsers.get(i).getHomeDirectory());
			userlist.add(user);
		}
		List<FtpUserDto> outerFtpUserDto = null;
		try {
            List<FtpUser> ftpUserList = (List<FtpUser>)client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.OUTER_RMI_ADDRESS)), new FtpUserListEvent());
            outerFtpUserDto = convertFtpUserToFtpUserDto(ftpUserList);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
		userlist.addAll(outerFtpUserDto);
		
		pageList.setList(userlist);
		page.setCount(userlist.size());
		pageList.setPage(page);
		return pageList;
	}

	@Override
	public FtpUser getByUserName(String username) {
//		FtpUser ftpUser = (FtpUser) client.call(RmiUtil.getRmiUrl(DspPropertyConfigurer.getDspProperty(FTP_RMI_ADDRESS)), new UniqueQueryUserEvent(username));
		FtpUser ftpUser = (FtpUser) client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.FTP_SERVER_ADDRESS)), new UniqueQueryUserEvent(username));
		return ftpUser;
	}

	@Override
	public boolean deleteByUserName(FtpUser ftpUser) {
		boolean flag = false;
		try {
//			flag = (Boolean) this.client.call(RmiUtil.getRmiUrl(DspPropertyConfigurer.getDspProperty(FTP_RMI_ADDRESS)), new UserDeleteEvent(ftpUser.getFtpUsername()));
			flag = (Boolean) this.client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.FTP_SERVER_ADDRESS)), new UserDeleteEvent(ftpUser.getFtpUsername()));
		} catch (Exception e) {
			logger.error("删除FTP用户时出错，异常信息为：" +e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean doesExist(String userName) {
		boolean flag = false;
		try {
//			flag = (Boolean) client.call(RmiUtil.getRmiUrl(DspPropertyConfigurer.getDspProperty(FTP_RMI_ADDRESS)), new UserDoesExist(userName));
			flag = (Boolean) client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.FTP_SERVER_ADDRESS)), new UserDoesExist(userName));
		} catch (Exception e) {
			logger.error("error happens in check if exists this userName，detail：" + e.getMessage());
			throw new FtpManagerException(e);
		}
		return flag;
	}

	@Override
	public boolean isAdmin(String userName) {
		boolean flag = false;
//		flag = (Boolean) this.client.call(RmiUtil.getRmiUrl(DspPropertyConfigurer.getDspProperty(FTP_RMI_ADDRESS)), new UserIsAdminEvent(userName));
		flag = (Boolean) this.client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.FTP_SERVER_ADDRESS)), new UserIsAdminEvent(userName));
		return flag;
	}


	@Override
	public void insert(FtpBaseUser entity) {
		
	}


	@Override
	public void deleteById(Long id) {
		
	}


	@Override
	public void delete(FtpBaseUser entity) {
		
	}


	@Override
	public void update(FtpBaseUser entity) {
		
	}


	@Override
	public FtpBaseUser getById(Long id) {
		return null;
	}


    @Override
    public List<FtpUserDto> getOuterFtpUserList() {

        
        return null;
    }


    private List<FtpUserDto> convertFtpUserToFtpUserDto(final List<FtpUser> ftpUserList) {
        
        List<FtpUserDto> list = new ArrayList<FtpUserDto>();
        
        for (FtpUser ftp : ftpUserList) {
            
            FtpUserDto dto = new FtpUserDto();
            dto.setFtpUsername(ftp.getFtpUsername());
            dto.setFtpPassword(ftp.getFtpPassword());
            dto.setHomedirectory(ftp.getHomeDirectory());
            dto.setWebFlag(ftp.getWebFlag());
            list.add(dto);
            
        }
        
        return list;
    }


    @Override
    public boolean updateOuterFtpUser(FtpUser ftpUser) {
        
        return (Boolean)client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.OUTER_RMI_ADDRESS)), new FtpUserUpdate(ftpUser));
        
    }


    @Override
    public boolean updateOuterFtpUserPass(FtpUser ftpUser) {

        return (Boolean)client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.OUTER_RMI_ADDRESS)), new FtpUserUpdatePassEvent(ftpUser));
    }


    @Override
    public boolean deleteOuterFtpUser(FtpUser ftpUser) {

        return (Boolean)client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.OUTER_RMI_ADDRESS)), new FtpUserDeleteEvent(ftpUser));
    }


    @Override
    public FtpUser getOuterFtpUser(String userid) {
        FtpUser ftpUser = null;
        try {
            ftpUser = (FtpUser)client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.OUTER_RMI_ADDRESS)), new FtpUserOneEvent(userid));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return ftpUser;
    }


}
