package com.fable.dsp.controller.ftp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fable.dsp.common.constants.CommonConstants;
import com.fable.dsp.common.dto.dataswitch.FtpUserDto;
import com.fable.dsp.common.util.SysConfigUtil;
import com.fable.dsp.common.util.UtilMD5;
import com.fable.dsp.controller.dataswitch.TaskController;
import com.fable.dsp.controller.system.UserInfoController;
import com.fable.dsp.core.datagrid.DataGridModel;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.core.session.SessionData;
import com.fable.dsp.dmo.dataswitch.FtpBaseUser;
import com.fable.dsp.dmo.dataswitch.TaskEntity;
import com.fable.dsp.dmo.system.user.UserInfo;
import com.fable.dsp.service.ftp.intf.FtpService;
import com.fable.hamal.shuttle.common.model.config.FtpUser;
import com.fable.hamal.shuttle.common.utils.spring.HamalPropertyConfigurer;

@Controller
@RequestMapping("/ftpservice")
public class FtpController {

    /**
     * datagrid分页常量
     */
    private static final String DGV_ROWS = "rows";
    /**
     * datagrid分页常量
     */
    private static final String DGV_TOTAL = "total";
    /**
     * FTP用户根目录
     */
    private static final String FTP_HOME = "ftphome";
    
    private static final String SEPARATOR = "/";

    private String innerRootPath = null;

    private String outerRootPath = null;

    private final static Logger logger = LoggerFactory
        .getLogger(FtpController.class);
    @Autowired
    private FtpService ftpService;

    @RequestMapping("/ftp-maintain")
    public String maintain() {
        return "ftp/ftp-maintain";
    }



    public FtpController() {
        innerRootPath =
            SysConfigUtil.getSysConfigValue("FTP_SERVER_INNER_ROOT_PATH");
        outerRootPath =
            SysConfigUtil.getSysConfigValue("FTP_SERVER_OUTER_ROOT_PATH");
    }


    /**
     * 运行FTP服务
     * 
     * @return
     */
    @RequestMapping("runFtpServer")
    @ResponseBody
    public Map<String, Object> runFtpServer() {
        Map<String, Object> rootJson = new HashMap<String, Object>();
        try {
            this.ftpService.runFtpServer();
            rootJson.put(CommonConstants.COL_DEALFLAG, true);
            rootJson.put(CommonConstants.COL_MSG, "启动内交换FTP服务成功");
        }
        catch (Exception e) {
            rootJson.put(CommonConstants.COL_DEALFLAG, false);
            rootJson.put(CommonConstants.COL_MSG, "启动内交换FTP服务失败");
            logger.error("启动内交换FTP服务时出现异常");

        }
        return rootJson;
    }

    /**
     * 运行外交换服务
     * 
     * @return
     */
    @RequestMapping("runOuterFtpServer")
    @ResponseBody
    public Map<String, Object> runOuterFtpServer() {
        Map<String, Object> rootJson = new HashMap<String, Object>();
        try {
            this.ftpService.runOuterFtpServer();
            rootJson.put(CommonConstants.COL_DEALFLAG, true);
            rootJson.put(CommonConstants.COL_MSG, "启动外交换服务成功 ");
        }
        catch (Exception e) {
            rootJson.put(CommonConstants.COL_DEALFLAG, false);
            rootJson.put(CommonConstants.COL_MSG, "启动外交换服务失败");
            logger.error("启动外交换FTP服务时出现异常 ");
        }
        return rootJson;
    }

    /**
     * 停止FTP服务
     * 
     * @return
     */
    @RequestMapping("stopFtpServer")
    @ResponseBody
    public Map<String, Object> stopFtpServer() {
        Map<String, Object> rootJson = new HashMap<String, Object>();
        try {
            this.ftpService.stopFtpServer();
            rootJson.put(CommonConstants.COL_DEALFLAG, true);
            rootJson.put(CommonConstants.COL_MSG, "成功内交换FTP服务");
        }
        catch (Exception e) {
            rootJson.put(CommonConstants.COL_DEALFLAG, false);
            rootJson.put(CommonConstants.COL_MSG, "停止内交换FTP服务时出现异常");
            logger.error("停止内交换FTP服务时出现异常，异常信息：", e.getMessage());
        }
        return rootJson;
    }

    /**
     * 恢复FTP服务
     * 
     * @return
     */
    @RequestMapping("resumeFtpServer")
    @ResponseBody
    public Map<String, Object> resumeFtpServer() {
        Map<String, Object> rootJson = new HashMap<String, Object>();
        try {
            this.ftpService.resumeFtpServer();
            rootJson.put(CommonConstants.COL_DEALFLAG, true);
            rootJson.put(CommonConstants.COL_MSG, "成功恢复内交换FTP服务");
        }
        catch (Exception e) {
            rootJson.put(CommonConstants.COL_DEALFLAG, false);
            rootJson.put(CommonConstants.COL_MSG, "恢复内交换FTP服务中出现异常");
            logger.error("恢复内交换FTP服务中出现异常，异常信息：", e.getMessage());
        }
        return rootJson;
    }

    /**
     * 恢复外交换FTP服务
     * 
     * @return
     */
    @RequestMapping("resumeOuterFtpServer")
    @ResponseBody
    public Map<String, Object> resumeOuterFtpServer() {
        Map<String, Object> rootJson = new HashMap<String, Object>();
        try {
            this.ftpService.resumeOuterFtpServer();
            rootJson.put(CommonConstants.COL_DEALFLAG, true);
            rootJson.put(CommonConstants.COL_MSG, "成功恢复外交换FTP服务");
        }
        catch (Exception e) {
            rootJson.put(CommonConstants.COL_DEALFLAG, false);
            rootJson.put(CommonConstants.COL_MSG, "恢复外交换FTP服务中出现异常");
            logger.error("恢复外交换FTP服务中出现异常，异常信息：", e.getMessage());
        }
        return rootJson;
    }

    /**
     * 暂停FTP服务
     * 
     * @return
     */
    @RequestMapping("suspendFtpServer")
    @ResponseBody
    public Map<String, Object> suspendFtpServer() {
        Map<String, Object> rootJson = new HashMap<String, Object>();
        try {
            this.ftpService.suspendFtpServer();
            rootJson.put(CommonConstants.COL_DEALFLAG, true);
            rootJson.put(CommonConstants.COL_MSG, "成功暂停内交换FTP服务");
        }
        catch (Exception e) {
            rootJson.put(CommonConstants.COL_DEALFLAG, true);
            rootJson.put(CommonConstants.COL_MSG, "暂停内交换FTP服务出现异常");
            logger.error("暂停内交换FTP服务出现异常，异常信息：", e.getMessage());
        }
        return rootJson;
    }

    /**
     * 暂停FTP服务
     * 
     * @return
     */
    @RequestMapping("suspendOuterFtpServer")
    @ResponseBody
    public Map<String, Object> suspendOuterFtpServer() {
        Map<String, Object> rootJson = new HashMap<String, Object>();
        try {
            this.ftpService.suspendOuterFtpServer();
            rootJson.put(CommonConstants.COL_DEALFLAG, true);
            rootJson.put(CommonConstants.COL_MSG, "成功暂停外交换FTP服务");
        }
        catch (Exception e) {
            rootJson.put(CommonConstants.COL_DEALFLAG, true);
            rootJson.put(CommonConstants.COL_MSG, "暂停外交换FTP服务出现异常");
            logger.error("暂停外交换FTP服务出现异常，异常信息：", e.getMessage());
        }
        return rootJson;
    }

    /**
     * 当前内交换状态
     * 
     * @return
     */
    @RequestMapping("getFtpServerStatus")
    @ResponseBody
    public String getFtpServerStatus() {
        return ftpService.getFtpServerStatus();
    }

    /**
     * 当前外交换状态
     * 
     * @return
     */
    @RequestMapping("getOuterFtpServerStatus")
    @ResponseBody
    public String getOuterFtpServerStatus() {
        return ftpService.getOuterFtpServerStatus();
    }

    /**
     * 保存FTP用户
     * 
     * @param ftpUserDto
     * @param session
     * @return
     */
    @RequestMapping(value = "saveFtpUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveFtpUser(FtpUser ftpUser,
        HttpSession session) {



        // ftpUser.setHomeDirectory("/" + FTP_HOME +
        // ftpUser.getFtpUsername());
        Map<String, Object> rootJson = new HashMap<String, Object>();

            String path = formatRootPath(ftpUser.getWebFlag(), ftpUser.getHomeDirectory());
            ftpUser.setHomeDirectory(path);
            if (Integer.parseInt(ftpUser.getWebFlag()) == 0) {

                try {
                    ftpUser.setWebFlag("1");
                    ftpService.insert(ftpUser);
                    rootJson.put(CommonConstants.COL_DEALFLAG, true);
                    rootJson.put(CommonConstants.COL_MSG, "保存FTP用户成功，用户名为：" +
                        ftpUser.getFtpUsername());
                }
                catch (Exception e) {
                    rootJson.put(CommonConstants.COL_DEALFLAG, false);
                    rootJson.put(CommonConstants.COL_MSG,
                        "保存FTP用户失败，异常原因为：" + e.getMessage());
                    logger.error("保存FTP用户失败，异常信息：", e.getMessage());
                }
            }
            else {

                // 插入到OUTER RMI
                try {
                    ftpUser.setWebFlag("0");
                    ftpService.insertOuter(ftpUser);
                    rootJson.put(CommonConstants.COL_DEALFLAG, true);
                    rootJson.put(CommonConstants.COL_MSG, "保存FTP用户成功，用户名为：" +
                        ftpUser.getFtpUsername());
                }
                catch (Exception e) {
                    rootJson.put(CommonConstants.COL_DEALFLAG, false);
                    rootJson.put(CommonConstants.COL_MSG,
                        "保存FTP用户失败，异常原因为：" + e.getMessage());
                    logger.error("保存FTP用户失败，异常信息：", e.getMessage());
                }


            }

        return rootJson;
    }


    /**
     * 格式化FTP路径
     * @param flag
     * @param homeDir
     * @return
     */
    private String formatRootPath(String flag, String homeDir) {
        
        StringBuffer path = new StringBuffer();
        if (Integer.parseInt(flag) == 0) {
            path.append(innerRootPath).append(SEPARATOR).append(homeDir);
        }
        else {
            path.append(outerRootPath).append(SEPARATOR).append(homeDir);
        }

        String formatPath = path.toString().replaceAll(SEPARATOR+SEPARATOR, SEPARATOR);
        
        if(formatPath.lastIndexOf(SEPARATOR) == formatPath.length() - 1){
            formatPath = formatPath.substring(0,formatPath.length() -1);
        }
        
        return formatPath;

    }


    /**
     * 修改FTP用户
     * 
     * @param ftpUser
     * @return
     */
    @RequestMapping(value = "editFtpUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editFtpUser(FtpUser ftpUserDto) {
        Map<String, Object> rootJson = new HashMap<String, Object>();
        if ("0".equals(ftpUserDto.getWebFlag())) {

            boolean flag = ftpService.updateOuterFtpUser(ftpUserDto);
            if (flag) {
                rootJson.put(CommonConstants.COL_DEALFLAG, true);
                rootJson.put(CommonConstants.COL_MSG, "修改FTP用户成功");
            }
            else {
                rootJson.put(CommonConstants.COL_DEALFLAG, false);
                rootJson.put(CommonConstants.COL_MSG, "修改FTP用户失败");
            }

        }
        else {
            try {
                ftpService.editFtpUser(ftpUserDto);
                rootJson.put(CommonConstants.COL_DEALFLAG, true);
                rootJson.put(CommonConstants.COL_MSG, "修改FTP用户成功");
            }
            catch (Exception e) {
                rootJson.put(CommonConstants.COL_DEALFLAG, false);
                rootJson.put(CommonConstants.COL_MSG, "修改FTP用户失败");
            }
        }



        return rootJson;
    }

    /**
     * 查询所有FTP用户
     * 
     * @param dgm
     * @return
     */
    @RequestMapping("findFtpUsersInfoList")
    @ResponseBody
    public Map<String, Object> findFtpUsersInfoList(DataGridModel dgm) {
        Page page = new Page();
        int currentPage = (dgm.getPage() == 0) ? 1 : dgm.getPage();
        // 每页显示条数，对应EASYUI分页中的pageSize参数
        int pageSize =
            dgm.getRows() == 0 ? CommonConstants.COL_PAGESIZE : dgm
                .getRows();
        // 每页的开始记录 第一页为1，第二页为number+1
        int index = (currentPage - 1) * pageSize;
        // 设置分页查询参数
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setIndex(index);
        // 查询分页结果，一开始没有条件的分页查询
        FtpUserDto ftpUser = new FtpUserDto();
        Map<String, Object> rootJson = new HashMap<String, Object>();
        try {
            PageList<FtpUserDto> pageList =
                ftpService.listFtpUser(page, ftpUser);
            rootJson.put(this.DGV_ROWS, pageList.getList());
            rootJson.put(this.DGV_TOTAL, pageList.getPage().getCount());
        }
        catch (Exception e) {
            logger.error("加载FTP用户列表时出现异常，异常信息为： ", e.getMessage());
            rootJson.put(CommonConstants.COL_DEALFLAG, false);
            rootJson.put(CommonConstants.COL_MSG, "加载ftp列表失败");
        }
        return rootJson;
    }

    /**
     * 根据ID查询数据
     * 
     * @param ftpUserDto
     * @return
     * @throws Exception
     */
    @RequestMapping("findFtpUserInfo")
    @ResponseBody
    public FtpUser findFtpUserInfo(FtpUser ftpUserDto) {

        FtpUser ftpUser = null;

        if ("1".equals(ftpUserDto.getWebFlag())) {
            try {
                ftpUser =
                    ftpService.getByUserName(ftpUserDto.getFtpUsername());
            }
            catch (Exception e) {
                logger.error("加载任务信息时失败，异常信息：" + e.getMessage());
            }
        }
        else {

            ftpUser =
                ftpService.getOuterFtpUser(ftpUserDto.getFtpUsername());

        }

        return ftpUser;

    }

    /**
     * 删除FTP用户0
     * 
     * @param ftpUserDto
     * @param session
     * @return
     */
    @RequestMapping("deleteFtpUser")
    @ResponseBody
    public Map<String, Object> deleteUsersInfo(FtpUser ftpUser,
        HttpSession session) {
        Map<String, Object> rootJson = new HashMap<String, Object>();
        boolean flag = false;
        if ("0".equals(ftpUser.getWebFlag())) {
            flag = ftpService.deleteOuterFtpUser(ftpUser);
        }
        else {
            flag = ftpService.deleteByUserName(ftpUser);
        }

        if (flag) {
            rootJson.put(CommonConstants.COL_DEALFLAG, true);
            rootJson.put(CommonConstants.COL_MSG, "删除FTP用户成功");
        }
        else {
            rootJson.put(CommonConstants.COL_DEALFLAG, false);
            rootJson.put(CommonConstants.COL_MSG, "请先删除映射关系，再删除该用户");
        }
        return rootJson;
    }

    /**
     * 判断该用户是否存在
     * 
     * @param ftpUserDto
     * @return
     */
    @RequestMapping("doesExist")
    @ResponseBody
    public Map<String, Object> doesExist(FtpUser ftpUser) {
        Map<String, Object> rootJson = new HashMap<String, Object>();
        boolean flag = ftpService.doesExist(ftpUser.getFtpUsername());
        if (flag) {
            rootJson.put(CommonConstants.COL_DEALFLAG, true);
        }
        else {
            rootJson.put(CommonConstants.COL_DEALFLAG, false);
        }
        return rootJson;
    }

    /**
     * 修改数据
     * 
     * @param ftpUserDto
     * @param session
     * @return
     */
    @RequestMapping("updateFtpUsersInfo")
    @ResponseBody
    public Map<String, Object> updateFtpUsersInfo(FtpUser ftpUser,
        HttpSession session) {
        Map<String, Object> rootJson = new HashMap<String, Object>();
        return rootJson;
    }

    /**
     * 判断同名FTP用户
     * 
     * @param ftpUserDto
     * @return
     * @throws Exception
     */
    @RequestMapping("isSameFtpName")
    @ResponseBody
    public Map<String, Object> isSameFtpName(FtpUser ftpUser)
        throws Exception {
        Map<String, Object> rootJson = new HashMap<String, Object>();
        boolean isFtpSame = false;
        rootJson.put(CommonConstants.COL_DEALFLAG, isFtpSame);
        return rootJson;
    }

    /**
     * 修改密码
     * 
     * @param usernameforup
     * @param newpassword
     * @param session
     * @return
     */
    @RequestMapping("updatepasswordbyadmin")
    @ResponseBody
    public Map<String, Object> updatepasswordbyadmin(
        final String usernameforup, final String newpassword,
        final String webFlag, final HttpSession session) {
        /**
         * json数据容器
         */
        final Map<String, Object> rootJson = new HashMap<String, Object>();

        FtpUser ftpUser = new FtpUser();
        ftpUser.setFtpUsername(usernameforup);
        ftpUser.setFtpPassword(newpassword);

        if ("0".equals(webFlag)) {
            try {
                boolean bool = ftpService.updateOuterFtpUserPass(ftpUser);
                if (bool) {
                    rootJson.put(CommonConstants.COL_DEALFLAG, true);
                    rootJson.put(CommonConstants.COL_MSG, "修改密码成功");
                }
                else {
                    rootJson.put(CommonConstants.COL_DEALFLAG, false);
                    rootJson.put(CommonConstants.COL_MSG, "修改密码失败");
                }
            }
            catch (Exception e) {
                logger.error("修改FTP用户密码时出现异常，异常原因为：" + e.getMessage());
                rootJson.put(CommonConstants.COL_DEALFLAG, false);
                rootJson.put(CommonConstants.COL_MSG, "修改FTP密码失败");
            }
        }
        else {
            try {
                // 先判断当前登录用户是否是管理员
                ftpService.uppwd(ftpUser);
                // 如果是管理员，则将该用户的密码重置
                rootJson.put(CommonConstants.COL_DEALFLAG, true);
                rootJson.put(CommonConstants.COL_MSG, "修改密码成功");
            }
            catch (final Exception e) {
                logger.error("修改FTP用户密码时出现异常，异常原因为：" + e.getMessage());
                rootJson.put(CommonConstants.COL_DEALFLAG, false);
                rootJson.put(CommonConstants.COL_MSG, "修改FTP密码失败");
            }
        }

        return rootJson;
    }
}
