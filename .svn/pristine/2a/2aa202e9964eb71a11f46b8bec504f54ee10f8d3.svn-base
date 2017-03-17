package com.fable.dsp.service.log.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fable.dsp.core.annotation.UserLogEntity;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dao.log.intf.UserOperationLogDao;
import com.fable.dsp.service.log.intf.UserOperationLogService;

/**
 * @author zhangl
 */
@Service("userOperationLogService")
public class UserOperationLogServiceImpl implements UserOperationLogService {
    
    @Autowired 
    UserOperationLogDao userOperationLogDao;
    

    @Override
    public PageList<UserLogEntity> findPageUserLogListByConditions(
        Page pager, UserLogEntity userlog) {
        return userOperationLogDao.findPageUserOperationLog(pager, userlog);
    }

    @Override
    public PageList<UserLogEntity> findPageSysLogListByConditions(
        Page pager, UserLogEntity userlog) {
        // TODO Auto-generated method stub
        return null;
    }

}
