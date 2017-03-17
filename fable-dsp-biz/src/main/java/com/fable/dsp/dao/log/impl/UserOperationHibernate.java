package com.fable.dsp.dao.log.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.fable.dsp.core.annotation.UserLogEntity;
import com.fable.dsp.core.dao.hibernate.GenericDaoHibernate;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dao.log.intf.UserOperationLogDao;

@Repository("userOperationLogDao")
public class UserOperationHibernate extends GenericDaoHibernate<UserLogEntity> implements UserOperationLogDao {

    @Override
    public PageList<UserLogEntity> findPageUserOperationLog(Page pager,
        UserLogEntity userLog) {
        PageList<UserLogEntity> pageUserLog = new PageList<UserLogEntity>();
        Long count = Long.valueOf(this.findUserLogListByConditions(userLog).size());
        if (count > 0) {
            @SuppressWarnings("unchecked")
            List<UserLogEntity> list = getHibernateTemplate().findByExample(userLog == null ? new UserLogEntity() : userLog, pager.getIndex(),
                    pager.getPageSize());
            pager.setCount(count);
            pageUserLog.setList(formatUserLogList(list));
        } else {
            pageUserLog.setList(new ArrayList<UserLogEntity>());
            pager.setCount(0);
        }
        pageUserLog.setPage(pager);
        return pageUserLog;
    }

    @Override
    public List<UserLogEntity> findUserLogListByConditions(
        UserLogEntity userLog) {
        @SuppressWarnings("unchecked")
        List<UserLogEntity> list = getHibernateTemplate().findByExample(userLog);
        return list;
    }

    
    /**
     * 关联查询用户操作日志
     * @param userLogList 用户操作日志记录
     * @return 
     */
    public List<UserLogEntity> formatUserLogList(List<UserLogEntity> userLogList){
        List<UserLogEntity> formatUserLogList = new ArrayList<UserLogEntity>();
        
        for(UserLogEntity userLogEntity :userLogList) {
            UserLogEntity formatUserEntity = new UserLogEntity();
            String id = userLogEntity.getTargetId();
            String targetName = userLogEntity.getTargetName();
            String tableName = "";
            String columnName = "";
            if(targetName.contains("-")) {
                String[] userOperation = targetName.split("-");
                tableName = userOperation[0];
                columnName = userOperation[1];
            }
            final Session session =
                           this.getHibernateTemplate().getSessionFactory().openSession();
            StringBuffer sql = new StringBuffer("select ")
                           .append(columnName)
                           .append(" from ")
                           .append(tableName)
                           .append(" where id = ")
                           .append(id);
            String SqlUser = "select username from sys_user_info where id = "
                                + userLogEntity.getOperationUser();
            
            @SuppressWarnings("unchecked")
            List<String> listUser = session.createSQLQuery(SqlUser).list();
            if(listUser.size()>0) {
                formatUserEntity.setOperationUser(listUser.get(0));
            } else {
                //被删除了
            }
            @SuppressWarnings("unchecked")
            List<String> list = session.createSQLQuery(sql.toString()).list();
            if(list.size()>0) {
                formatUserEntity.setTargetName(columnName + " : " + list.get(0));
            } else {
                //被删除了
            }
            formatUserEntity.setOperationDescribe(userLogEntity.getOperationDescribe());
            formatUserEntity.setOperationTime(userLogEntity.getOperationTime());
            formatUserLogList.add(formatUserEntity);
            session.close();
       }
       return formatUserLogList;
    }
}
