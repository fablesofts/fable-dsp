package com.fable.dsp.dao.dataswitch.impl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.fable.dsp.common.constants.CommonConstants;
import com.fable.dsp.common.dto.dataswitch.ColumnFilterInfo;
import com.fable.dsp.common.dto.dataswitch.ColumnMappingInfo;
import com.fable.dsp.common.dto.dataswitch.ColumnTransferInfo;
import com.fable.dsp.common.dto.dataswitch.ColumnVirusFilterInfo;
import com.fable.dsp.common.dto.dataswitch.FullTaskDto;
import com.fable.dsp.common.dto.dataswitch.SourceDSInfo;
import com.fable.dsp.common.dto.dataswitch.TableMappingInfo;
import com.fable.dsp.common.dto.dataswitch.TargetDSInfo;
import com.fable.dsp.common.dto.dataswitch.TimestampDto;
import com.fable.dsp.common.util.AssociationUtil;
import com.fable.dsp.common.util.DataBaseTypes;
import com.fable.dsp.common.util.EncryptDES;
import com.fable.dsp.core.dao.hibernate.GenericDaoHibernate;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dao.dataswitch.intf.TaskDao;
import com.fable.dsp.dmo.datasource.DataSourceInfo;
import com.fable.dsp.dmo.dataswitch.EtlStrategy;
import com.fable.dsp.dmo.dataswitch.PipeLine;
import com.fable.dsp.dmo.dataswitch.TaskEntity;
import com.fable.dsp.dmo.dataswitch.TimestampEntity;
import com.fable.dsp.dmo.dataswitch.TransEntity;
import com.fable.hamal.shuttle.common.model.envelope.et.EtlMacrocosm;
import com.fable.hamal.shuttle.common.model.envelope.et.converter.ColumnConverter;
import com.fable.hamal.shuttle.common.model.envelope.et.filter.ColumnFilter;
import com.fable.hamal.shuttle.common.model.envelope.et.mapping.ColumnMapping;
import com.fable.hamal.shuttle.common.utils.constant.DbType;


@Repository("taskDao")
public class TaskDaoHibernate extends GenericDaoHibernate<TaskEntity> implements TaskDao {
    
    private static final Logger logger  = LoggerFactory.getLogger(TaskDaoHibernate.class);
    
    @Override
    @SuppressWarnings("unchecked")
    public List<TaskEntity> listTaskByConditions(final Page pager,
        final TaskEntity taskEntity) {
        // 通过条件查询，不考虑分页
        final List<TaskEntity> list =
            this.getHibernateTemplate().executeFind(
                new HibernateCallback<List<TaskEntity>>() {

                    @Override
                    public List<TaskEntity> doInHibernate(
                        final Session session) throws HibernateException,
                        SQLException {
                        Query query = null;
                        final StringBuffer sb = new StringBuffer();
                        sb.append(" from TaskEntity t where 1=1");
                        if (taskEntity.getName() == null)
                            query = session.createQuery(sb.toString());
                        else {
                            if (taskEntity.getName() != null &&
                                (!taskEntity.equals(""))) {
                                taskEntity.setName("%" +
                                    taskEntity.getName() + "%");
                                sb.append(" and t.name like :name");
                            }
                            // 加条件的
                            query =
                                session.createQuery(sb.toString())
                                    .setProperties(taskEntity);
                        }
                        session.close();
                        return query.list();
                    }
                });
        return list;
    }

    @Override
    public TaskEntity getTaskByConditions(final TaskEntity taskEntity) {
        return null;
    }

    @Override
    public PageList<TaskEntity> findTaskList(final Page pager,
        TaskEntity taskEntity) {
        String countHql = "select count(*) from TaskEntity as t";
        PageList<TaskEntity> pageTask = new PageList<TaskEntity>();
        Session session = this.getHibernateTemplate().getSessionFactory().openSession();
        Long count = (Long)session.createQuery(countHql).uniqueResult();
        if (count > 0) {
            // 分页+条件：如果不为空，则
            @SuppressWarnings("unchecked")
            final List<TaskEntity> list =
                this.getHibernateTemplate().findByExample(
                    taskEntity == null ? new TaskEntity() : taskEntity,
                    pager.getIndex(), pager.getPageSize());
            pager.setCount(count);
            pageTask.setList(list);
        }
        else {
            pageTask.setList(new ArrayList<TaskEntity>());
            pager.setCount(0);
        }
        pageTask.setPage(pager);
        session.close();
        return pageTask;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Long listCount(final Page pager, final TaskEntity taskEntity) {
        return this.getHibernateTemplate().execute(
            new HibernateCallback<Long>() {

                @Override
                public Long doInHibernate(final Session session)
                    throws HibernateException, SQLException {
                    Long count = null;
                    final StringBuffer sbcount = new StringBuffer();
                    sbcount
                        .append(" select count(t.id) from TaskEntity t where 1=1");
                    // 没有拼条件的
                    if (taskEntity.getName() == null)
                        // 计算总记录数
                        count =
                            (Long)session.createQuery(sbcount.toString())
                                .uniqueResult();
                    else {
                        if (taskEntity.getName() != null &&
                            (!taskEntity.equals(""))) {
                            taskEntity.setName("%" + taskEntity.getName() +
                                "%");
                            sbcount.append(" and t.name like :name");
                        }
                        count =
                            (Long)session.createQuery(sbcount.toString())
                                .setProperties(taskEntity).uniqueResult();
                    }
                    return count;
                }
            });
    }

    @Override
    public void insert(final TaskEntity entity) {
        super.insert(entity);
    }

    @Override
    public void deleteById(final Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(final TaskEntity entity) {
       this.getHibernateTemplate().delete(entity);
    }

    @Override
    public void update(final TaskEntity entity) {
        super.update(entity);
    }
    
    @Override
    public boolean isExitsName(String taskName) {
        Session session=this.getHibernateTemplate().getSessionFactory().openSession();
        int count=new Integer(session.createSQLQuery("SELECT COUNT(*) FROM DSP_TASK WHERE NAME='"+taskName+"'")
            .uniqueResult().toString());
        session.close();
      if(count>0){
          return true;
      }else{
          return false;
      }
    }
    
    //0 没有主从表关系 1有不健全的主从表关系 2主从表关系健全 
    @Override
    public String isAssociation(DataSourceInfo dsInfo, List<String> tableNames) {
        Statement stat = null;
        Connection conn = null;
        ResultSet rs = null;
        //默认没有主从表关系
        String Association = "0";
        try {
            conn = this.getConnection(dsInfo);
            stat = conn.createStatement();
            for(int i=0;i<tableNames.size();i++){
                //查询每张表的主表是否健全
                String PSql = AssociationUtil.findPTableSQL(dsInfo.getDb_type(), dsInfo.getDb_name(), tableNames.get(i));
                rs = stat.executeQuery(PSql);
                while(rs.next()){
                    //假设有健全的主从表关系
                    Association = "2";
                    String ptableName = rs.getString("ptable");
                    if(!tableNames.contains(ptableName)){
                        Association = "1";
                        return Association;
                    }
                }
            }
            for(int i=0;i<tableNames.size();i++){
                //查询每张表的主表是否健全
                String FSql = AssociationUtil.findFTableSQL(dsInfo.getDb_type(), dsInfo.getDb_name(), tableNames.get(i));
                rs = stat.executeQuery(FSql);
                while(rs.next()){
                    //假设有健全的主从表关系
                    Association = "2";
                    String ptableName = rs.getString("ftable");
                    if(!tableNames.contains(ptableName)){
                        Association = "1";
                        return Association;
                    }
                }
            }
        }
        catch (final Exception e) {
                    logger.error("Failed to acquire tables of a datasource the error message is:{}",e.getMessage());
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                }
                catch (SQLException e) {
                    logger.error("ResultSet close error the error message is:{}",e.getMessage());
                }
            }
            if (stat != null)
                try {
                    stat.close();
                }
                catch (final SQLException e) {
                    logger.error("Statement close error the error message is:{}",e.getMessage());
                }
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (final SQLException e) {
                    logger.error("Connection commit error the error message is:{}",e.getMessage());
                }
            }
        }
        return Association;
    }
    
    private Connection getConnection(final DataSourceInfo ds) {
        Connection connection = null;
        String driver = "";
        String dbtype = ds.getDb_type();
        if (DbType.ORACLE.equals(dbtype)){
            driver = "oracle.jdbc.driver.OracleDriver";
        }
        else if (DbType.MYSQL.equals(dbtype)){
            driver = "com.mysql.jdbc.Driver";
        }
        else if (DbType.MSSQL.equals(dbtype)){
            driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        }
        else if (DbType.DAMENG6.equals(dbtype)){
            driver = "dm6.jdbc.driver.DmDriver";
        }
        else if (DbType.DAMENG7.equals(dbtype) || 
                        DataBaseTypes.OTHER.equals(dbtype) ){
            driver = "dm.jdbc.driver.DmDriver";
        }
        else{
            if (logger.isDebugEnabled()) {
                logger.debug("数据库类型不支持");
            }
        }
        try {
            Class.forName(driver);
            if (DbType.DAMENG6.equals(ds.getDb_type())){
                Thread.sleep(1000);
            }
            String password = EncryptDES.decrypt(ds.getPassword(),CommonConstants.DES_KEY);
            connection = DriverManager.getConnection(ds.getConnect_url(),ds.getUsername(), password);
        }
        catch (Exception e) {
            logger.error("Connection get failed the error message is: {}",e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    @SuppressWarnings("unchecked")
    public FullTaskDto editForShow(Long taskID) {

        SourceDSInfo sourceDSInfo = new SourceDSInfo();
        List<TargetDSInfo> targetDSInfo = new ArrayList<TargetDSInfo>();
        List<TimestampDto> timestamplist = new ArrayList<TimestampDto>();
        List<TableMappingInfo> tableMappingInfo = new ArrayList<TableMappingInfo>();
        
        List<ColumnMappingInfo> columnMappingInfo = new ArrayList<ColumnMappingInfo>();
        List<ColumnVirusFilterInfo> columnVirusFilterInfo = new ArrayList<ColumnVirusFilterInfo>();
        List<ColumnFilterInfo> columnFilterInfo = new ArrayList<ColumnFilterInfo>();
        List<ColumnTransferInfo> columnTransferInfo = new ArrayList<ColumnTransferInfo>();
        Session session = this.getHibernateTemplate().getSessionFactory().openSession();
        
        TaskEntity task = (TaskEntity)session
                        .createSQLQuery("SELECT * FROM DSP_TASK WHERE ID ="+taskID)
                        .addEntity(TaskEntity.class)
                        .uniqueResult();
        List<PipeLine> pipeLines = (List<PipeLine>)session
                        .createQuery(" from PipeLine p left join fetch p.taskEntity t where t.id=:id")
                        .setParameter("id", taskID)
                        .list();
        List<Map<String,List<EtlStrategy>>> etlStrategysList = 
                        new ArrayList<Map<String,List<EtlStrategy>>>();
        if(null != pipeLines){
            Long sourceId = pipeLines.get(0).getSourceEntity().getDataSourceInfo().getId();
            String tablenames = pipeLines.get(0).getSourceEntity().getTableName();
            sourceDSInfo.setLocation(pipeLines.get(0).getSourceEntity().getLocation());
            sourceDSInfo.setTableName(tablenames);
            sourceDSInfo.setId(pipeLines.get(0).getSourceEntity().getId());
            sourceDSInfo.setSourceId(sourceId);
            sourceDSInfo.setSourceName(pipeLines.get(0).getSourceEntity().getDataSourceInfo().getName());
            List<String> tables = new ArrayList<String>();
            //不是文件
            if(null != tablenames) {
                if(tablenames.contains(",")){
                    tables = Arrays.asList(tablenames.split(","));
                } else {
                    tables.add(tablenames);
                }
                
                TimestampDto timestampdto = new TimestampDto();
                if("4".equals(task.getSynchroType().toString())){
                    for(int i=0;i<tables.size();i++){
                        //timestamp
                        TimestampEntity timestamps = (TimestampEntity)session
                                        .createSQLQuery("SELECT * FROM DSP_TIMESTAMP where TASK_ID="+taskID
                                            +" AND DATA_SOURCE_ID="+ sourceId 
                                            +" AND TABLE_NAME='"+tables.get(i)+"'")
                                            .addEntity(TimestampEntity.class)
                                            .uniqueResult();
                        timestampdto.setColumnNames(timestamps.getDataColumn());
                        timestampdto.setSourceid(sourceId);
                        timestampdto.setTableName(tables.get(i));
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String time = sdf.format(timestamps.getSwitchTime());
                        timestampdto.setTimestamp(time);
                        timestamplist.add(timestampdto);
                    }
                }
            }
            
            
            for(int i=0;i<pipeLines.size();i++){
                
                TargetDSInfo tdi = new TargetDSInfo();
                tdi.setTargetId(pipeLines.get(i).getTargetEntity().getDataSourceInfo().getId());
                tdi.setId(pipeLines.get(i).getTargetEntity().getId());
                tdi.setTableName(pipeLines.get(i).getTargetEntity().getTableName());
                tdi.setLocation(pipeLines.get(i).getTargetEntity().getLocation());
                tdi.setTargetName(pipeLines.get(i).getTargetEntity().getDataSourceInfo().getName());
                targetDSInfo.add(tdi);
                
                List<EtlStrategy> etlStrategys = (List<EtlStrategy>)session
                                .createQuery(" from EtlStrategy e where e.pipeLine.id=:id")
                                .setParameter("id", pipeLines.get(i).getId())
                                .list();
                String key = pipeLines.get(i).getSourceEntity().getId()
                                +"->"+pipeLines.get(i).getTargetEntity().getId();
                Map<String,List<EtlStrategy>> map = new HashMap<String, List<EtlStrategy>>();
                map.put(key, etlStrategys);
                etlStrategysList.add(map);
            }
        }
        
        //多个pipeline
        for(int i=0;i<etlStrategysList.size();i++){
            //每个pipeline有多对ETL策略
            Map<String,List<EtlStrategy>> map = etlStrategysList.get(i);
            for (String key : map.keySet()) {
                for(int j=0;j<map.get(key).size();j++){
                    Long sourceId = Long.valueOf(key.split("->")[0]).longValue();
                    Long targetId = Long.valueOf(key.split("->")[1]).longValue();
                    String sourceTable = map.get(key).get(j).getFromTable();
                    String targetTable = map.get(key).get(j).getToTable();
                    
                    TableMappingInfo tmi = new TableMappingInfo();
                    tmi.setFromTable(sourceTable);
                    tmi.setToTable(targetTable);
                    tmi.setSourceId(sourceId);
                    tmi.setTargetId(targetId);
                    tableMappingInfo.add(tmi);
                    
                    String ContentFilter = map.get(key).get(j).getContentFilter();
                    ObjectMapper objectMapper = new ObjectMapper();
                    EtlMacrocosm emm = new EtlMacrocosm();
                    try {
                        emm = objectMapper.readValue(ContentFilter, EtlMacrocosm.class);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    //预处理&映射
                    List<ColumnMapping> cms = emm.getMapping().getColumnsMapping();
                    if(null != cms){
                        for(int x=0;x<cms.size();x++){
                            ColumnMappingInfo cmi = new ColumnMappingInfo();
                            cmi.setSourceColumnName(cms.get(x).getSource());
                            cmi.setTargetColumnName(cms.get(x).getTarget());
                            cmi.setSourceId(sourceId);
                            cmi.setTargetId(targetId);
                            cmi.setSourceTableName(sourceTable);
                            cmi.setTargetTableName(targetTable);
                            columnMappingInfo.add(cmi);
                        }
                    }
                    //字段过滤
                    List<ColumnFilter> cfs = emm.getFilter().getColumnFilter();
                    if(null != cfs){
                        for(int x=0;x<cfs.size();x++){
                            ColumnFilterInfo cfi = new ColumnFilterInfo();
                            cfi.setColumnValue(cfs.get(x).getValue().toString());
                            cfi.setOperator(cfs.get(x).getOperator());
                            cfi.setSourceColumnName(cfs.get(x).getName());
                            cfi.setSourceId(sourceId);
                            cfi.setSourceTableName(sourceTable);
                            cfi.setTargetId(targetId);
                            cfi.setTargetTableName(targetTable);
                            columnFilterInfo.add(cfi);
                        }
                    }
                    //病毒过滤
                    List<String> cvs = emm.getFilter().getVirusFilter().getColumns();
                    if(null != cvs){
                        for(int x=0;x<cvs.size();x++){
                            ColumnVirusFilterInfo cvfi = new ColumnVirusFilterInfo();
                            cvfi.setSourceColumnName(cvs.get(x));
                            cvfi.setSourceId(sourceId);
                            cvfi.setSourceTableName(sourceTable);
                            columnVirusFilterInfo.add(cvfi);
                        }
                    }
                    //转换
                    List<ColumnConverter> converters = emm.getConverter().getColumnConverter();
                    if(null != converters){
                        for(int x=0;x<converters.size();x++){
                            ColumnTransferInfo ctfi = new ColumnTransferInfo();
                            ctfi.setOriginals(converters.get(x).getPairs().getOriginals().toString());
                            ctfi.setReplacement(converters.get(x).getPairs().getReplacement());
                            ctfi.setSourceColumnName(converters.get(x).getName());
                            ctfi.setSourceId(sourceId);
                            ctfi.setSourceTableName(sourceTable);
                            ctfi.setTargetId(targetId);
                            ctfi.setTargetTableName(targetTable);
                            columnTransferInfo.add(ctfi);
                        }
                    }
                }
            }
        }
        
        FullTaskDto fulltask = new FullTaskDto();
        //ETL
        fulltask.setColumnFilterInfo(columnFilterInfo);
        fulltask.setColumnMappingInfo(columnMappingInfo);
        fulltask.setColumnTransferInfo(columnTransferInfo);
        fulltask.setColumnVirusFilterInfo(columnVirusFilterInfo);
        
        fulltask.setTaskId(task.getId());
        fulltask.setTaskName(task.getName());
        
        
        
        
        TransEntity sourceEntity = pipeLines.get(0).getSourceEntity();
        
        List<TransEntity> targetEntity = new ArrayList<TransEntity>();
        for(int i=0;i<pipeLines.size();i++){
            targetEntity.add(pipeLines.get(i).getTargetEntity());
        }
        
        //还要根据源和端的类型判断下拉框的类型
        if (CommonConstants.DB_TYPE
                        .equals(sourceEntity.getDataSourceInfo().getSource_type())&&
            CommonConstants.DB_TYPE.
                        equals(targetEntity.get(0).getDataSourceInfo().getSource_type())){
            //数据库-数据库
            fulltask.setTaskType("0");
        } else if (CommonConstants.FILE_TYPE
                        .equals(sourceEntity.getDataSourceInfo().getSource_type())&&
                   CommonConstants.FILE_TYPE
                        .equals(targetEntity.get(0).getDataSourceInfo().getSource_type())){
            //文件 →文件
            fulltask.setTaskType("1");
            fulltask.setTargetFile(task.getTargetFile().toString());
            fulltask.setSourceFile(task.getSourceFile().toString());
        } else if (CommonConstants.FILE_TYPE
                        .equals(sourceEntity.getDataSourceInfo().getSource_type())&&
                   CommonConstants.DB_TYPE
                        .equals(targetEntity.get(0).getDataSourceInfo().getSource_type())){
            //文件 →数据库
            fulltask.setTaskType("2");
        } else {
            //数据库 →文件
            fulltask.setTaskType("3");
        }
        fulltask.setSynchroType(task.getSynchroType()+"");
        fulltask.setTaskDescription(task.getDescription());
        fulltask.setAssociation("1".equals(task.getAssociation()));
        fulltask.setDeleteSourceData("1".equals(task.getDeleteSourceData()));
        fulltask.setRebuildTrigger("1".equals(task.getRebuildTrigger()));
        fulltask.setRowLevelLog("1".equals(task.getRowLevelLog()));
        fulltask.setTimestamps(timestamplist);
        fulltask.setSourceDSInfo(sourceDSInfo);
        fulltask.setTargetDSInfo(targetDSInfo);
        fulltask.setTableMappingInfo(tableMappingInfo);
        
        session.close();
        return fulltask;
    }

    
}
