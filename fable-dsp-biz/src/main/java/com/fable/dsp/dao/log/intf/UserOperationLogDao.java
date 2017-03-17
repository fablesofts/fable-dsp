package com.fable.dsp.dao.log.intf;

import java.util.List;

import com.fable.dsp.core.annotation.UserLogEntity;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;


public interface UserOperationLogDao {
    
    /**
     * 通过条件查找多条
     * 
     * @param InterestInfo
     * @return
     */
    List<UserLogEntity> findUserLogListByConditions(UserLogEntity userLog);
    
    /**
     * 分页查询用户操作日志.
     * @param pager 分页对象
     * @param userLog 用户操作日志对象 
     * @return
     */
    PageList<UserLogEntity> findPageUserOperationLog(Page pager, UserLogEntity userLog);
}
