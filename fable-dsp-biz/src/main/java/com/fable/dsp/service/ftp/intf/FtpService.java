package com.fable.dsp.service.ftp.intf;

import java.util.List;

import com.fable.dsp.common.dto.dataswitch.FtpUserDto;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.core.service.GenericService;
import com.fable.dsp.dmo.dataswitch.FtpBaseUser;
import com.fable.hamal.shuttle.common.model.config.FtpUser;
/**
 * FTP用户管理接口，调RMI服务的入口
 * @author 邱爽
 *
 */
public interface FtpService extends GenericService<FtpBaseUser>{
	/**
	 * 启动FTP服务
	 */
	public void runFtpServer();
	/**
	 * 启动外交换服务
	 */
	public void runOuterFtpServer();
	/**
	 * 停止FTP服务
	 */
	public void stopFtpServer();
	/**
	 * 新增FTP用户
	 * @param ftpUserDto
	 */
	public void insert(FtpUser ftpUser);
	public boolean editFtpUser(FtpUser ftpUser);
	public boolean uppwd(FtpUser ftpUser);
	/**
	 * 获取FTP服务当前的状态
	 * @return 
	 */
	public String getFtpServerStatus();
	/**
	 * 获取外交换服务当前状态
	 * @return
	 */
	public String getOuterFtpServerStatus();
	/**
	 * 暂停FTP服务
	 */
	public void suspendFtpServer();
	/**
	 * 恢复FTP服务
	 */
	public void resumeFtpServer();
	/**
	 * 加载所有FTP用户
	 * @param page
	 * @param ftpUserDto
	 * @return
	 */
	public PageList<FtpUserDto> listFtpUser(Page page, FtpUserDto ftpUser);
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	public FtpUser getByUserName(String username);
	/**
	 * 根据名字删除
	 * @param ftpUserDto
	 * @return
	 */
	public boolean deleteByUserName(FtpUser ftpUser);
	/**
	 * 是否存在用户
	 * @param userName
	 * @return
	 */
	public boolean doesExist(String userName);
	/**
	 * 是否是管理员
	 * @param userName
	 * @return
	 */
	public boolean isAdmin(String userName);
	/**
	 * 暂停外交换服务
	 */
	public void suspendOuterFtpServer();
	/**
	 * 恢复外交换FTP服务
	 */
	public void resumeOuterFtpServer();
	
	public void insertOuter(FtpUser ftpUser);
	
	/**
	 * 查询外交换FTP用户列表.
	 * @return
	 */
	public List<FtpUserDto> getOuterFtpUserList();
	
	/**
     * 修改FTP用户
     * @param ftpUser
     * @return
     */
    boolean updateOuterFtpUser(FtpUser ftpUser);
    
    /**
     * 修改FTP用户密码.
     * @param ftpUser
     * @return
     */
    boolean updateOuterFtpUserPass(FtpUser ftpUser);
    
    /**
     * 删除FTP用户.
     * @param ftpUser
     * @return
     */
    boolean deleteOuterFtpUser(FtpUser ftpUser);
    
    
    FtpUser getOuterFtpUser(String userid);
	
}
