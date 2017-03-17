package com.fable.dsp.service.ftp.impl;

import org.apache.ftpserver.ftplet.FtpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fable.dsp.common.constants.SysConfigConstants;
import com.fable.dsp.common.exception.FtpManagerException;
import com.fable.dsp.common.util.SysConfigUtil;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dao.dataswitch.intf.FtpMappingDao;
import com.fable.dsp.dmo.ftp.FtpMapping;
import com.fable.dsp.service.dataswitch.impl.TaskServiceImpl;
import com.fable.dsp.service.ftp.intf.FtpMappingService;
import com.fable.hamal.shuttle.common.utils.rmi.RmiUtil;
import com.fable.hamal.shuttle.communication.client.Communication;
import com.fable.outer.rmi.event.ftpserver.FtpMappingDeleteEvent;
import com.fable.outer.rmi.event.ftpserver.FtpMappingEvent;
import com.fable.outer.rmi.event.ftpserver.FtpMappingUpdateEvent;
import com.fable.outer.rmi.event.ftpserver.FtpUserEvent;
import com.fable.outer.rmi.event.ftpserver.FtpUserOneEvent;
/**
 * FTP路径映射服务实现类
 * @author 邱爽
 *
 */
@Service("ftpMappingService")
public class FtpMappingServiceImpl implements FtpMappingService{
	private final static Logger logger = LoggerFactory.getLogger(FtpMappingServiceImpl.class);
	@Autowired
	private FtpMappingDao ftpMappingDao;
	
	@Autowired
    private Communication client;
	
	@Override
	public void insert(FtpMapping entity){
		try {
			ftpMappingDao.insert(entity);
		} catch (Exception e) {
			logger.error("保存FTP映射关系时出现异常，异常信息为："+e.getMessage());
			throw new FtpManagerException(e);
		}
	}

	@Override
	public void deleteById(Long id) {
		ftpMappingDao.deleteById(id);
	}

	@Override
	public void delete(FtpMapping entity) {
		ftpMappingDao.delete(entity);
	}

	@Override
	public void update(FtpMapping entity) {
		try {
			ftpMappingDao.update(entity);
		} catch (Exception e) {
			logger.error("修改FTP映射关系时出现异常，异常信息为："+e.getMessage());
			throw new FtpManagerException(e);
		}
	}

	@Override
	public FtpMapping getById(Long id) {
		return ftpMappingDao.getById(id);
	}

	@Override
	public PageList<FtpMapping> listFtpMapping(Page page, FtpMapping ftpMapping) {
		return ftpMappingDao.findFtpMappingList(page, ftpMapping);
	}

    @Override
    public boolean insertOutFtpMapping(FtpMapping ftpMapping) {

        com.fable.hamal.shuttle.common.model.config.FtpMapping outFtpMapping = createDtoFtpMapping(ftpMapping);
        
        try {
            client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.OUTER_RMI_ADDRESS)), new FtpMappingEvent(outFtpMapping));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }

    private com.fable.hamal.shuttle.common.model.config.FtpMapping createDtoFtpMapping(FtpMapping ftpMapping) {
        
        com.fable.hamal.shuttle.common.model.config.FtpMapping outFtpMapping = new com.fable.hamal.shuttle.common.model.config.FtpMapping();
        outFtpMapping.setId(ftpMapping.getId());
        outFtpMapping.setInnerAddress(ftpMapping.getInnerAddress());
        outFtpMapping.setInnerUserName(ftpMapping.getInnerUserName());
        outFtpMapping.setOuterAddress(ftpMapping.getOuterAddress());
        outFtpMapping.setOuterUserName(ftpMapping.getOuterUserName());
        outFtpMapping.setUserFlag(ftpMapping.getUserFlag());
        return outFtpMapping;
                        
    }

    @Override
    public boolean updateOutFtpMapping(FtpMapping ftpMapping) {
        com.fable.hamal.shuttle.common.model.config.FtpMapping ftpMappingDto = createDtoFtpMapping(ftpMapping);
        return (Boolean)client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.OUTER_RMI_ADDRESS)), new FtpMappingUpdateEvent(ftpMappingDto));
    }

    @Override
    public boolean deleteOutFtpMapping(FtpMapping ftpMapping) {
        com.fable.hamal.shuttle.common.model.config.FtpMapping ftpMappingDto = createDtoFtpMapping(ftpMapping);
        return (Boolean)client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.OUTER_RMI_ADDRESS)), new FtpMappingDeleteEvent(ftpMappingDto));
    }

}
