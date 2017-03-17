package com.fable.dsp.service.log.intf;

import com.fable.dsp.core.annotation.UserLogEntity;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;


public interface UserOperationLogService {
    
    PageList<UserLogEntity> findPageUserLogListByConditions(Page pager,UserLogEntity userlog);
    
    PageList<UserLogEntity> findPageSysLogListByConditions(Page pager,UserLogEntity userlog);
    
    
}
