package com.fable.dsp.service.dataswitch.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fable.dsp.common.constants.SysConfigConstants;
import com.fable.dsp.common.dto.dataswitch.AddTableDto;
import com.fable.dsp.common.dto.dataswitch.ColumnDto;
import com.fable.dsp.common.dto.dataswitch.ColumnFilterInfo;
import com.fable.dsp.common.dto.dataswitch.ColumnMappingDto;
import com.fable.dsp.common.dto.dataswitch.ColumnMappingInfo;
import com.fable.dsp.common.dto.dataswitch.ColumnTransferInfo;
import com.fable.dsp.common.dto.dataswitch.ColumnVirusFilterInfo;
import com.fable.dsp.common.dto.dataswitch.DataSourceDto;
import com.fable.dsp.common.dto.dataswitch.FullTaskDto;
import com.fable.dsp.common.dto.dataswitch.ListColumnDataSourceDto;
import com.fable.dsp.common.dto.dataswitch.TableMapDto;
import com.fable.dsp.common.dto.dataswitch.TableMappingDto;
import com.fable.dsp.common.dto.dataswitch.TableMappingInfo;
import com.fable.dsp.common.dto.dataswitch.TargetDSInfo;
import com.fable.dsp.common.dto.dataswitch.TaskDto;
import com.fable.dsp.common.dto.dataswitch.TimestampDto;
import com.fable.dsp.common.dto.dataswitch.TransEntityJsonDto;
import com.fable.dsp.common.dto.dataswitch.TransTaskJsonDto;
import com.fable.dsp.common.dto.dataswitch.TreeDataDto;
import com.fable.dsp.common.exception.CreateAddTableException;
import com.fable.dsp.common.exception.TaskException;
import com.fable.dsp.common.util.JSONHelper;
import com.fable.dsp.common.util.SysConfigUtil;
import com.fable.dsp.common.util.TaskType;
import com.fable.dsp.common.util.TaskUtil;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.core.session.SessionData;
import com.fable.dsp.dao.dashboard.intf.JobInfoDao;
import com.fable.dsp.dao.datasource.intf.DataSourceInfoDao;
import com.fable.dsp.dao.dataswitch.intf.PipeLineDao;
import com.fable.dsp.dao.dataswitch.intf.ScheduleDao;
import com.fable.dsp.dao.dataswitch.intf.StrategyDao;
import com.fable.dsp.dao.dataswitch.intf.TaskDao;
import com.fable.dsp.dao.dataswitch.intf.TimestampDao;
import com.fable.dsp.dao.dataswitch.intf.TransEntityDao;
import com.fable.dsp.dmo.datasource.DataSourceInfo;
import com.fable.dsp.dmo.dataswitch.EtlStrategy;
import com.fable.dsp.dmo.dataswitch.PipeLine;
import com.fable.dsp.dmo.dataswitch.TaskEntity;
import com.fable.dsp.dmo.dataswitch.TimestampEntity;
import com.fable.dsp.dmo.dataswitch.TimestampPK;
import com.fable.dsp.dmo.dataswitch.TransEntity;
import com.fable.dsp.service.dataswitch.intf.NetWorkCommunicationService;
import com.fable.dsp.service.dataswitch.intf.TaskService;
import com.fable.hamal.shuttle.common.model.envelope.et.EtlMacrocosm;
import com.fable.hamal.shuttle.common.model.envelope.et.converter.ColumnConverter;
import com.fable.hamal.shuttle.common.model.envelope.et.converter.ColumnConverter.Verter;
import com.fable.hamal.shuttle.common.model.envelope.et.filter.ColumnFilter;
import com.fable.hamal.shuttle.common.model.envelope.et.mapping.ColumnMapping;
import com.fable.hamal.shuttle.common.model.envelope.et.pretreatment.ColumnPretreatment;
import com.fable.hamal.shuttle.common.utils.rmi.RmiUtil;
import com.fable.hamal.shuttle.communication.client.Communication;
import com.fable.hamal.shuttle.communication.event.task.TaskDeleteEvent;
import com.fable.hamal.shuttle.communication.event.task.TaskRunEvent;
import com.fable.hamal.shuttle.communication.event.task.TaskUpdateEvent;

@Service("taskService")
@Transactional(propagation = Propagation.REQUIRED,
    rollbackFor = Exception.class)
public class TaskServiceImpl implements TaskService {
    private final static Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
    private static String TRUESTR="true";
//    private static String OUTER_RMI_ADDRESSES = "outer.rmi.addresses";
//    private static String MANAGER_RMI_ADDRESSES = "manager.rmi.addresses";
    private final static char NOT_DELETE = '0';
	private final static char DELETED = '1';
	private final static String FILLER = "->";
	private final static String COMMA = ",";
	@Autowired
	private StrategyDao strategyDao;
	@Autowired
	private ScheduleDao scheduleDao;
	@Autowired
	private JobInfoDao jobInfoDao;
	@Autowired
	private TaskDao taskDao;
	@Autowired
	private PipeLineDao pipeLineDao;
	@Autowired
	private TransEntityDao transEntityDao;
	@Autowired
	DataSourceInfoDao dataSourceInfoDao;
	@Autowired
    TimestampDao timestampDao;
	
	@Autowired
	NetWorkCommunicationService networkcommunication;
	
	
	
	@Autowired
	Communication client;

	/** 增加task. */
	@Override
	public void insert(final TaskEntity entity) {
		this.taskDao.insert(entity);
	}

	/** 根据id删除实体. */
	@Override
	public void deleteById(final Long id) {
		// 先删实体，再删pipeline,再删任务
		// 根据taskid查到pipeline,再查到pipeline对应的源和端
		this.taskDao.deleteById(id);
	}

	/**
	 * 删除实体.
	 * 
	 * @param entity
	 *            实体
	 */
	@Override
	public void delete(final TaskEntity entity) {
		this.taskDao.delete(entity);
	}

	/**
	 * 编辑实体.
	 * 
	 * @param entity
	 *            实体
	 */
	@Override
	public void update(final TaskEntity entity) {
		this.taskDao.update(entity);
	}

	/**
	 * 唯一查询
	 * 
	 * @param id
	 *            编号
	 */
	@Override
	public TaskEntity getById(final Long id) {
		return this.taskDao.getById(id);
	}

	@Override
	public TaskEntity getTaskByConditions(final TaskEntity taskEntity) {
		return this.taskDao.getTaskByConditions(taskEntity);
	}

	@Override
	public PageList<TaskEntity> findTaskInfo(final Page pager,
			final TaskEntity taskEntity) {
		return this.taskDao.findTaskList(pager, taskEntity);
	}

	// 没有分页条件的
	@Override
	public PageList<TaskEntity> listTaskByConditions(final Page page,
			final TaskEntity taskEntity) {
		// 在业务层进行分页处理
		final PageList<TaskEntity> pageTask = new PageList<TaskEntity>();
		// 得到list
		pageTask.setList(this.taskDao.listTaskByConditions(page, taskEntity));
		// 得到总记录数
		page.setCount(this.taskDao.listCount(page, taskEntity));
		pageTask.setPage(page);
		return pageTask;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TreeDataDto> listTable(final DataSourceInfo sourceInfo) {
		DataSourceDto dataSourceDto = null;
		try {
			dataSourceDto = TaskTransUtil.transferDataSource(sourceInfo);
		} catch (IOException e) {
			logger.error("加载外网数据源时出现异常，异常信息为：" + e.getMessage());
			throw new TaskException(e);
		} catch (Exception e) {
			logger.error("加载外网数据源时出现异常，异常信息为:" + e.getMessage());
			throw new TaskException(e);
		}
		
		final List<TreeDataDto> list = networkcommunication.listTableWithoutFl(dataSourceDto);
		return list;
	}

	/**
	 * 编辑任务.
	 * 
	 * @param sdata
	 *            Session信息
	 * @param bigDto
	 *            json数据
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Long editTask(final SessionData sdata, FullTaskDto fullTaskDto) {
		// 任务实体
		TaskEntity taskEntity = new TaskEntity();
		// 是否存在主从关系
		taskEntity.setAssociation(fullTaskDto.isAssociation() ? '1' : '0');
		// 是否删除源数据
		taskEntity.setDeleteSourceData(fullTaskDto.isDeleteSourceData() ? '1': '0');
		// 任务描述
		taskEntity.setDescription(fullTaskDto.getTaskDescription());
		// 任务id
		taskEntity.setId(fullTaskDto.getTaskId());
		// 任务名称
		taskEntity.setName(fullTaskDto.getTaskName());
		taskEntity.setNeedResource(1);
		// 是否重建触发器
		taskEntity.setRebuildTrigger(fullTaskDto.isRebuildTrigger() ? '1' : '0');
		// 是否关联行级日志
		taskEntity.setRowLevelLog(fullTaskDto.isRowLevelLog() ? '1' : '0');
		// 任务类型
		taskEntity.setSysLogPrint(fullTaskDto.isSysLogPrint() ? '1' : '0');
		taskEntity.setUpdateTime(new Date());
		taskEntity.setUpdateUser(sdata.getUserId());
		if("1".equals(fullTaskDto.getTaskType())){
            taskEntity.setSourceFile(fullTaskDto.getSourceFile().charAt(0));
            taskEntity.setTargetFile(fullTaskDto.getTargetFile().charAt(0));
        } else if("0".equals(fullTaskDto.getTaskType())){
            taskEntity.setSynchroType(fullTaskDto.getSynchroType().charAt(0));
        }
		try {
			this.taskDao.update(taskEntity);
			if (logger.isDebugEnabled()) {
				logger.debug("修改任务信息成功，任务编号为：{}", taskEntity.getId());
			}
		} catch (Exception e) {
			logger.error("修改任务信息出现异常，异常信息为：  {}" + e.getMessage());
			throw new TaskException(e.getMessage());
		}
		// 得到pipeLine
		// 删除相应taskid的dsp_trans_entity、dsp_pipeline、dsp_etl_strategy三张表中的内容
		PipeLine pipeline = new PipeLine();
		pipeline.setTaskEntity(taskEntity);
		List<Long> transEntityId = new ArrayList<Long>();
		List<PipeLine> pipeLineList = pipeLineDao
				.findPipeLineByTaskId(taskEntity.getId());
		if (null != pipeLineList) {
			if (pipeLineList.size() > 0) {
				// 一个源id
				transEntityId.add(pipeLineList.get(0).getSourceEntity().getId());
				// 遍历pipelines获得多个端id
				for (int i = 0; i < pipeLineList.size(); i++) {
					transEntityId.add(pipeLineList.get(i).getTargetEntity()
							.getId());
					// 删除etl
					strategyDao.deleteEtlByPipeLineId(pipeLineList.get(i)
							.getId());
					// 删除pipeline
					pipeLineDao.deleteById(pipeLineList.get(i).getId());
				}
				// 删除entity
				for (int i = 0; i < transEntityId.size(); i++) {
					transEntityDao.deleteById(transEntityId.get(i));
				}
			}
		}
		//数据库任务才有时间戳
        if(null != fullTaskDto.getSynchroType()){
            //如果是时间戳
            if("4".equals(taskEntity.getSynchroType().toString())) {
                List<TimestampDto> listTimestamp = fullTaskDto.getTimestamps();
                
                TimestampEntity timestamp = new TimestampEntity();
                TimestampPK tspk = new TimestampPK();
                tspk.setTaskId(taskEntity.getId());
                for(int i=0;i<listTimestamp.size();i++){
                    tspk.setDataSourceId(listTimestamp.get(i).getSourceid());
                    tspk.setTableName(listTimestamp.get(i).getTableName());
                    timestamp.setTimestampPK(tspk);
                    timestamp.setDataColumn(listTimestamp.get(i).getColumnNames());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date date= new Date();
                    try {
                        date = sdf.parse(listTimestamp.get(i).getTimestamp());
                    }
                    catch (ParseException e) {
                        logger.error("修改任务信息时间戳出现异常，异常信息为：{}" + e.getMessage());
                    }
                    timestamp.setSwitchTime(date);
                    timestampDao.update(timestamp);
                }
            }
        }
		// 重新新增dsp_trans_entity、dsp_pipeline、dsp_etl_strategy
		Date now = new Date();
		TransEntity sourceEntity = new TransEntity();
		List<PipeLine> list = new ArrayList<PipeLine>();
		String taskType = fullTaskDto.getTaskType();
		sourceEntity.setCreateUser(sdata.getUserId());
		Long sourceId = Long.valueOf(fullTaskDto.getSourceDSInfo().getSourceId());
		sourceEntity.setDataSourceInfo(new DataSourceInfo(sourceId));
		sourceEntity.setDel_flag('0');
		sourceEntity.setUpdateTime(now);
		sourceEntity.setUpdateUser(sdata.getUserId());
		sourceEntity.setType(TaskType.getSourceEntityType(taskType));
		// 根据类型设置tablename还是location
		if ("0".equals(taskType)) {
			sourceEntity.setTableName(fullTaskDto.getSourceDSInfo()
					.getTableName());
		} else if ("1".equals(taskType)) {
			sourceEntity.setLocation(fullTaskDto.getSourceDSInfo()
					.getLocation());
		}
		this.transEntityDao.insert(sourceEntity);

		Map<Long, PipeLine> targetPipeline = new HashMap<Long, PipeLine>();
		List<TargetDSInfo> targetEntities = fullTaskDto.getTargetDSInfo();
		for (int i = 0; i < targetEntities.size(); i++) {
			TransEntity targetEntity = new TransEntity();
			Long targetSourceId = targetEntities.get(i).getId();
			targetEntity.setDataSourceInfo(new DataSourceInfo(targetSourceId));
			targetEntity.setTableName(targetEntities.get(i).getTableName());
			targetEntity.setLocation(targetEntities.get(i).getLocation());
			targetEntity.setType(TaskType.getTargetEntityType(taskType));
			targetEntity.setUpdateTime(now);
			targetEntity.setUpdateUser(sdata.getUserId());
			targetEntity.setDel_flag('0');
			this.transEntityDao.insert(targetEntity);

			PipeLine newpipeline = new PipeLine();
			newpipeline.setTaskEntity(taskEntity);
			newpipeline.setSourceEntity(sourceEntity);
			newpipeline.setTargetEntity(targetEntity);
			newpipeline.setDel_flag('0');
			newpipeline.setUpdateUser(sdata.getUserId());
			newpipeline.setUpdateTime(now);
			pipeLineDao.insert(newpipeline);
			targetPipeline.put(targetSourceId, newpipeline);
		}

		// {"targetId":{"fromTable->toTable":{}}}
		Map<Long, Map<String, List<ColumnMappingInfo>>> columnMappingMap = new HashMap<Long, Map<String, List<ColumnMappingInfo>>>();
		List<ColumnMappingInfo> columnMappingList = fullTaskDto
				.getColumnMappingInfo();
		StringBuffer key = new StringBuffer();
		for (int i = 0; i < columnMappingList.size(); i++) {
			ColumnMappingInfo columnMapping = columnMappingList.get(i);
			Long targetId = columnMapping.getTargetId();
			key.append(columnMapping.getSourceTableName()).append(FILLER)
					.append(columnMapping.getTargetTableName());
			Map<String, List<ColumnMappingInfo>> tempMap = columnMappingMap
					.get(targetId);
			if (null == tempMap) {
				tempMap = new HashMap<String, List<ColumnMappingInfo>>();
				columnMappingMap.put(targetId, tempMap);
			}
			List<ColumnMappingInfo> tempList = tempMap.get(key.toString());
			if (null == tempList) {
				tempList = new ArrayList<ColumnMappingInfo>();
				tempMap.put(key.toString(), tempList);
			}
			tempList.add(columnMapping);
			key.delete(0, key.length() - 1);
		}
		// {"#targetId":{"#fromTable->toTable":{}}}
		Map<Long, Map<String, List<ColumnFilterInfo>>> columnFilterMap = new HashMap<Long, Map<String, List<ColumnFilterInfo>>>();
		List<ColumnFilterInfo> columnFilterList = fullTaskDto
				.getColumnFilterInfo();
		for (int i = 0; i < columnFilterList.size(); i++) {
			ColumnFilterInfo columnFilter = columnFilterList.get(i);
			Long targetId = columnFilter.getTargetId();
			key.append(columnFilter.getSourceTableName()).append(FILLER)
					.append(columnFilter.getTargetTableName());
			Map<String, List<ColumnFilterInfo>> tempMap = columnFilterMap
					.get(targetId);
			if (null == tempMap) {
				tempMap = new HashMap<String, List<ColumnFilterInfo>>();
				columnFilterMap.put(targetId, tempMap);
			}
			List<ColumnFilterInfo> tempList = tempMap.get(key.toString());
			if (null == tempList) {
				tempList = new ArrayList<ColumnFilterInfo>();
				tempMap.put(key.toString(), tempList);
			}
			tempList.add(columnFilter);
			key.delete(0, key.length() - 1);
		}

		// {"#fromTable":{}}
		Map<String, List<ColumnVirusFilterInfo>> columnVirusFilterMap = new HashMap<String, List<ColumnVirusFilterInfo>>();
		List<ColumnVirusFilterInfo> columnVirusFilterList = fullTaskDto
				.getColumnVirusFilterInfo();
		for (int i = 0; i < columnVirusFilterList.size(); i++) {
			ColumnVirusFilterInfo columnVirusFilter = columnVirusFilterList
					.get(i);
			String sourceTable = columnVirusFilter.getSourceTableName();
			List<ColumnVirusFilterInfo> tempList = columnVirusFilterMap
					.get(sourceTable);
			if (null == tempList) {
				tempList = new ArrayList<ColumnVirusFilterInfo>();
				columnVirusFilterMap.put(sourceTable, tempList);
			}
			tempList.add(columnVirusFilter);
		}

		Map<Long, Map<String, List<ColumnTransferInfo>>> columnTransferMap = new HashMap<Long, Map<String, List<ColumnTransferInfo>>>();
		List<ColumnTransferInfo> columnTransferList = fullTaskDto
				.getColumnTransferInfo();
		for (int i = 0; i < columnTransferList.size(); i++) {
			ColumnTransferInfo columnTransfer = columnTransferList.get(i);
			Long targetId = columnTransfer.getTargetId();
			key.append(columnTransfer.getSourceTableName()).append(FILLER)
					.append(columnTransfer.getTargetTableName());
			Map<String, List<ColumnTransferInfo>> tempMap = columnTransferMap
					.get(targetId);
			if (null == tempMap) {
				tempMap = new HashMap<String, List<ColumnTransferInfo>>();
				columnTransferMap.put(targetId, tempMap);
			}
			List<ColumnTransferInfo> tempList = tempMap.get(key.toString());
			if (null == tempList) {
				tempList = new ArrayList<ColumnTransferInfo>();
				tempMap.put(key.toString(), tempList);
			}
			tempList.add(columnTransfer);
			key.delete(0, key.length() - 1);
		}

		List<TableMappingInfo> tableMappingList = fullTaskDto
				.getTableMappingInfo();
		for (int i = 0; i < tableMappingList.size(); i++) {
			TableMappingInfo tmi = tableMappingList.get(i);
			key.append(tmi.getFromTable()).append(FILLER)
					.append(tmi.getToTable());
			Long targetId = tmi.getTargetId();
			List<ColumnMappingInfo> mappings = new ArrayList<ColumnMappingInfo>();
			if (null != columnMappingMap.get(targetId)) {
				mappings = columnMappingMap.get(targetId).get(key.toString());
			}
			List<ColumnFilterInfo> filters = new ArrayList<ColumnFilterInfo>();
			if (null != columnFilterMap.get(targetId)) {
				filters = columnFilterMap.get(targetId).get(key.toString());
			}
			List<ColumnVirusFilterInfo> virus = new ArrayList<ColumnVirusFilterInfo>();
			if (null != columnVirusFilterMap.get(tmi.getFromTable())) {
				virus = columnVirusFilterMap.get(tmi.getFromTable());
			}
			List<ColumnTransferInfo> transfers = new ArrayList<ColumnTransferInfo>();
			if (null != columnTransferMap.get(targetId)) {
				transfers = columnTransferMap.get(targetId).get(key.toString());
			}

			EtlStrategy strategy = new EtlStrategy();
			strategy.setPipeLine(null);
			EtlMacrocosm emm = new EtlMacrocosm();

			// 预处理&列映射
			for (int j = 0; j < mappings.size(); j++) {
				ColumnMappingInfo ci = mappings.get(j);
				List<ColumnPretreatment> cpss = emm.getPretreatment()
						.getSourceColumns();
				List<ColumnPretreatment> cpts = emm.getPretreatment()
						.getTargetColumns();
				List<ColumnMapping> cms = emm.getMapping().getColumnsMapping();
				if (null == cms) {
					cms = new ArrayList<ColumnMapping>();
					emm.getMapping().setColumnsMapping(cms);
				}
				ColumnPretreatment cps = new ColumnPretreatment();
				ColumnPretreatment cpt = new ColumnPretreatment();
				cps.setName(ci.getSourceColumnName());
				cpt.setName(ci.getTargetColumnName());

				ColumnMapping cm = new ColumnMapping();
				cm.setSource(ci.getSourceColumnName());
				cm.setTarget(ci.getTargetColumnName());
				cpss.add(cps);
				cpts.add(cpt);
				cms.add(cm);
			}

			// 列值过滤
			for (int k = 0; k < filters.size(); k++) {
				ColumnFilterInfo cfi = filters.get(k);
				List<ColumnFilter> cfs = emm.getFilter().getColumnFilter();
				if (null == cfs) {
					cfs = new ArrayList<ColumnFilter>();
					emm.getFilter().setColumnFilter(cfs);
				}
				ColumnFilter cf = new ColumnFilter();
				cf.setName(cfi.getSourceColumnName());
				cf.setOperator(cfi.getOperator());
				cf.setValue(Arrays.asList(cfi.getColumnValue().split(",")));
				cfs.add(cf);
			}

			// 病毒过滤
			for (int x = 0; x < virus.size(); x++) {
				ColumnVirusFilterInfo cvfi = virus.get(x);
				List<String> cvs = emm.getFilter().getVirusFilter()
						.getColumns();
				cvs.add(cvfi.getSourceColumnName());
			}

			// 转换
			for (int y = 0; y < transfers.size(); y++) {
                ColumnTransferInfo cti = transfers.get(i);
                List<ColumnConverter> converters =
                emm.getConverter().getColumnConverter();
                if (null == converters) {
                    converters = new ArrayList<ColumnConverter>();
                    emm.getConverter().setColumnConverter(converters);
                }
                ColumnConverter cc = new ColumnConverter();
                Verter pairs =  cc.new Verter();
                List<String> Originals = new ArrayList<String>();
                String orig = cti.getOriginals();
                if(orig.contains(",")){
                    String[] a = orig.split(",");
                    for(int z = 0;z<a.length;z++){
                        Originals.add(a[z]);
                    }
                } else {
                    Originals.add(orig);
                }
                pairs.setOriginals(Originals);
                pairs.setReplacement(cti.getReplacement());
                cc.setPairs(pairs);
                cc.setName(cti.getSourceColumnName());
                converters.add(cc);
            }

			strategy.setPipeLine(targetPipeline.get(targetId));
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				strategy.setContentFilter(objectMapper.writeValueAsString(emm));
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			strategy.setFromTable(tmi.getFromTable());
			strategy.setToTable(tmi.getToTable());
			strategy.setDelFlag('0');
			strategy.setCreateUser(sdata.getUserId());
			strategy.setCreateTime(now);
			this.strategyDao.insert(strategy);
		}
		return taskEntity.getId();
	}

	@Override
	public void deletePipeLineByTargetId(Long long1) {
		try {
			this.transEntityDao.deletePipeLineByTargetId(long1);
		} catch (Exception e) {
		    logger.error("delete PipeLine failed the error message is ：{}" + e.getMessage());
		}
	}

	@Override
	public Long addTask(String userId, FullTaskDto fullTaskDto)
			throws TaskException, IOException {
		Date now = new Date();
		TaskEntity taskEntity = new TaskEntity();
		TransEntity sourceEntity = new TransEntity();
		List<PipeLine> list = new ArrayList<PipeLine>();
		// 0:d-d,1:f-f,2:f-d,3:d-f
		String taskType = fullTaskDto.getTaskType();
		// 插入dsp_task表
		taskEntity.setCreateUser(userId);
		taskEntity.setCreateTime(now);
		taskEntity.setDeleteSourceData(fullTaskDto.isDeleteSourceData() ? '1': '0');
		taskEntity.setDelFlag('0');
		taskEntity.setDescription(fullTaskDto.getTaskDescription());
		taskEntity.setName(fullTaskDto.getTaskName());
		taskEntity.setRowLevelLog(fullTaskDto.isRowLevelLog() ? '1' : '0');
		taskEntity.setSysLogPrint(fullTaskDto.isSysLogPrint()? '1' : '0');
		taskEntity.setAssociation(fullTaskDto.isAssociation() ? '1' : '0');
		taskEntity.setNeedResource(1);
		taskEntity.setRebuildTrigger(fullTaskDto.isRebuildTrigger() ? '1' : '0');
		if("1".equals(fullTaskDto.getTaskType())){
		    taskEntity.setSourceFile(fullTaskDto.getSourceFile().charAt(0));
		    taskEntity.setTargetFile(fullTaskDto.getTargetFile().charAt(0));
		} else if("0".equals(fullTaskDto.getTaskType())){
		    taskEntity.setSynchroType(fullTaskDto.getSynchroType().charAt(0));
		}
		taskDao.insert(taskEntity);
		
		//数据库任务才有时间戳
		if(null != fullTaskDto.getSynchroType()){
		    //如果是时间戳
		    if("4".equals(taskEntity.getSynchroType().toString())) {
		        List<TimestampDto> listTimestamp = fullTaskDto.getTimestamps();
		        
		        TimestampEntity timestamp = new TimestampEntity();
		        TimestampPK tspk = new TimestampPK();
		        tspk.setTaskId(taskEntity.getId());
		        for(int i=0;i<listTimestamp.size();i++){
		            tspk.setDataSourceId(listTimestamp.get(i).getSourceid());
		            tspk.setTableName(listTimestamp.get(i).getTableName());
		            timestamp.setTimestampPK(tspk);
		            timestamp.setDataColumn(listTimestamp.get(i).getColumnNames());
		            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		            Date date= new Date();
		            try {
		                date = sdf.parse(listTimestamp.get(i).getTimestamp());
		            }
		            catch (ParseException e) {
		                logger.error("修改任务信息时间戳出现异常，异常信息为：{}" + e.getMessage());
		            }
		            timestamp.setSwitchTime(date);
		            timestampDao.insert(timestamp);
		        }
		    }
		}
		
		sourceEntity.setCreateUser(userId);
		Long sourceId = Long.valueOf(fullTaskDto.getSourceDSInfo()
				.getSourceId());
		sourceEntity.setDataSourceInfo(new DataSourceInfo(sourceId));
		sourceEntity.setDel_flag('0');
		sourceEntity.setType(TaskType.getSourceEntityType(taskType));
		// 根据类型设置tablename还是location
		if ("0".equals(taskType)) {
			sourceEntity.setTableName(fullTaskDto.getSourceDSInfo()
					.getTableName());
		} else if ("1".equals(taskType)) {
			sourceEntity.setLocation(fullTaskDto.getSourceDSInfo()
					.getLocation());
		}
		transEntityDao.insert(sourceEntity);

		Map<Long, PipeLine> targetPipeline = new HashMap<Long, PipeLine>();
		List<TargetDSInfo> targetEntities = fullTaskDto.getTargetDSInfo();
		for (int i = 0; i < targetEntities.size(); i++) {
			TransEntity targetEntity = new TransEntity();
			Long targetSourceId = targetEntities.get(i).getId();
			targetEntity.setDataSourceInfo(new DataSourceInfo(targetSourceId));
			targetEntity.setTableName(targetEntities.get(i).getTableName());
			targetEntity.setLocation(targetEntities.get(i).getLocation());
			targetEntity.setType(TaskType.getTargetEntityType(taskType));
			targetEntity.setCreateTime(now);
			targetEntity.setCreateUser(userId);
			targetEntity.setDel_flag('0');
			transEntityDao.insert(targetEntity);
			
			PipeLine pipeline = new PipeLine();
			pipeline.setTaskEntity(taskEntity);
			pipeline.setSourceEntity(sourceEntity);
			pipeline.setTargetEntity(targetEntity);
			pipeline.setDel_flag('0');
			pipeline.setCreateUser(userId);
			pipeline.setCreateTime(now);
			pipeLineDao.insert(pipeline);
			targetPipeline.put(targetSourceId, pipeline);
		}

//		{"targetId":{"fromTable->toTable":{}}}
		Map<Long, Map<String, List<ColumnMappingInfo>>> columnMappingMap = 
		                new HashMap<Long, Map<String, List<ColumnMappingInfo>>>();
		//columnMappingList 包含所有的 字段映射  
		List<ColumnMappingInfo> columnMappingList = fullTaskDto.getColumnMappingInfo();
		StringBuffer key = new StringBuffer();
		for (int i = 0; i < columnMappingList.size(); i++) {
			ColumnMappingInfo columnMapping = columnMappingList.get(i);
			Long targetId = columnMapping.getTargetId();
			key.append(columnMapping.getSourceTableName()).append(FILLER)
					.append(columnMapping.getTargetTableName());
			Map<String, List<ColumnMappingInfo>> tempMap = columnMappingMap.get(targetId);
			if (null == tempMap) {
				tempMap = new HashMap<String, List<ColumnMappingInfo>>();
				columnMappingMap.put(targetId, tempMap);
			}
			List<ColumnMappingInfo> tempList = tempMap.get(key.toString());
			if (null == tempList) {
				tempList = new ArrayList<ColumnMappingInfo>();
				tempMap.put(key.toString(), tempList);
			}
			tempList.add(columnMapping);
			key.delete(0, key.length());
		}
		// {"#targetId":{"#fromTable->toTable":{}}}
		Map<Long, Map<String, List<ColumnFilterInfo>>> columnFilterMap = new HashMap<Long, Map<String, List<ColumnFilterInfo>>>();
		List<ColumnFilterInfo> columnFilterList = fullTaskDto
				.getColumnFilterInfo();
		for (int i = 0; i < columnFilterList.size(); i++) {
			ColumnFilterInfo columnFilter = columnFilterList.get(i);
			Long targetId = columnFilter.getTargetId();
			key.append(columnFilter.getSourceTableName()).append(FILLER)
					.append(columnFilter.getTargetTableName());
			Map<String, List<ColumnFilterInfo>> tempMap = columnFilterMap
					.get(targetId);
			if (null == tempMap) {
				tempMap = new HashMap<String, List<ColumnFilterInfo>>();
				columnFilterMap.put(targetId, tempMap);
			}
			List<ColumnFilterInfo> tempList = tempMap.get(key.toString());
			if (null == tempList) {
				tempList = new ArrayList<ColumnFilterInfo>();
				tempMap.put(key.toString(), tempList);
			}
			tempList.add(columnFilter);
			key.delete(0, key.length());
		}

		// {"#fromTable":{}}
		Map<String, List<ColumnVirusFilterInfo>> columnVirusFilterMap = new HashMap<String, List<ColumnVirusFilterInfo>>();
		List<ColumnVirusFilterInfo> columnVirusFilterList = fullTaskDto
				.getColumnVirusFilterInfo();
		for (int i = 0; i < columnVirusFilterList.size(); i++) {
			ColumnVirusFilterInfo columnVirusFilter = columnVirusFilterList
					.get(i);
			String sourceTable = columnVirusFilter.getSourceTableName();
			List<ColumnVirusFilterInfo> tempList = columnVirusFilterMap
					.get(sourceTable);
			if (null == tempList) {
				tempList = new ArrayList<ColumnVirusFilterInfo>();
				columnVirusFilterMap.put(sourceTable, tempList);
			}
			tempList.add(columnVirusFilter);
		}

		Map<Long, Map<String, List<ColumnTransferInfo>>> columnTransferMap = new HashMap<Long, Map<String, List<ColumnTransferInfo>>>();
		List<ColumnTransferInfo> columnTransferList = fullTaskDto
				.getColumnTransferInfo();
		for (int i = 0; i < columnTransferList.size(); i++) {
			ColumnTransferInfo columnTransfer = columnTransferList.get(i);
			Long targetId = columnTransfer.getTargetId();
			key.append(columnTransfer.getSourceTableName()).append(FILLER)
					.append(columnTransfer.getTargetTableName());
			Map<String, List<ColumnTransferInfo>> tempMap = columnTransferMap
					.get(targetId);
			if (null == tempMap) {
				tempMap = new HashMap<String, List<ColumnTransferInfo>>();
				columnTransferMap.put(targetId, tempMap);
			}
			List<ColumnTransferInfo> tempList = tempMap.get(key.toString());
			if (null == tempList) {
				tempList = new ArrayList<ColumnTransferInfo>();
				tempMap.put(key.toString(), tempList);
			}
			tempList.add(columnTransfer);
			key.delete(0, key.length());
		}

		List<TableMappingInfo> tableMappingList = fullTaskDto.getTableMappingInfo();
		for (int i = 0; i < tableMappingList.size(); i++) {
			TableMappingInfo tmi = tableMappingList.get(i);
			key.append(tmi.getFromTable()).append(FILLER).append(tmi.getToTable());
			Long targetId = tmi.getTargetId();
			List<ColumnMappingInfo> mappings = new ArrayList<ColumnMappingInfo>();
			if (null != columnMappingMap.get(targetId)) {
			    if(null != columnMappingMap.get(targetId).get(key.toString())){
			        mappings = columnMappingMap.get(targetId).get(key.toString());
			    }
			}
			List<ColumnFilterInfo> filters = new ArrayList<ColumnFilterInfo>();
			if (null != columnFilterMap.get(targetId)) {
			    if(null != columnFilterMap.get(targetId).get(key.toString())){
			        filters = columnFilterMap.get(targetId).get(key.toString());
			    }
			}
			List<ColumnVirusFilterInfo> virus = new ArrayList<ColumnVirusFilterInfo>();
			if (null != columnVirusFilterMap.get(tmi.getFromTable())) {
				virus = columnVirusFilterMap.get(tmi.getFromTable());
			}
			List<ColumnTransferInfo> transfers = new ArrayList<ColumnTransferInfo>();
			if (null != columnTransferMap.get(targetId)) {
			    if(null != columnTransferMap.get(targetId).get(key.toString())){
			        transfers = columnTransferMap.get(targetId).get(key.toString());
			    }
			}

			EtlStrategy strategy = new EtlStrategy();
			strategy.setPipeLine(null);
			EtlMacrocosm emm = new EtlMacrocosm();

			// 预处理&列映射
			for (int j = 0; j < mappings.size(); j++) {
				ColumnMappingInfo ci = mappings.get(j);
				List<ColumnPretreatment> cpss = emm.getPretreatment()
						.getSourceColumns();
				List<ColumnPretreatment> cpts = emm.getPretreatment()
						.getTargetColumns();
				List<ColumnMapping> cms = emm.getMapping().getColumnsMapping();
				if (null == cms) {
					cms = new ArrayList<ColumnMapping>();
					emm.getMapping().setColumnsMapping(cms);
				}
				ColumnPretreatment cps = new ColumnPretreatment();
				ColumnPretreatment cpt = new ColumnPretreatment();
				cps.setName(ci.getSourceColumnName());
				cpt.setName(ci.getTargetColumnName());

				ColumnMapping cm = new ColumnMapping();
				cm.setSource(ci.getSourceColumnName());
				cm.setTarget(ci.getTargetColumnName());
				cpss.add(cps);
				cpts.add(cpt);
				cms.add(cm);
			}

			// 列值过滤
			for (int k = 0; k < filters.size(); k++) {
				ColumnFilterInfo cfi = filters.get(k);
				List<ColumnFilter> cfs = emm.getFilter().getColumnFilter();
				if (null == cfs) {
					cfs = new ArrayList<ColumnFilter>();
					emm.getFilter().setColumnFilter(cfs);
				}
				ColumnFilter cf = new ColumnFilter();
				cf.setName(cfi.getSourceColumnName());
				cf.setOperator(cfi.getOperator());
				cf.setValue(Arrays.asList(cfi.getColumnValue().split(",")));
				cfs.add(cf);
			}

			// 病毒过滤
			for (int x = 0; x < virus.size(); x++) {
				ColumnVirusFilterInfo cvfi = virus.get(x);
				List<String> cvs = emm.getFilter().getVirusFilter()
						.getColumns();
				cvs.add(cvfi.getSourceColumnName());
			}

			// 转换
			for (int y = 0; y < transfers.size(); y++) {
				ColumnTransferInfo cti = transfers.get(y);
				List<ColumnConverter> converters =
				emm.getConverter().getColumnConverter();
				if (null == converters) {
				    converters = new ArrayList<ColumnConverter>();
				    emm.getConverter().setColumnConverter(converters);
				}
				ColumnConverter cc = new ColumnConverter();
				Verter pairs =  cc.new Verter();
				List<String> Originals = new ArrayList<String>();
				String orig = cti.getOriginals();
				if(orig.contains(",")){
				    Originals = Arrays.asList(orig.split(","));
				} else {
				    Originals.add(orig);
				}
				pairs.setOriginals(Originals);
				pairs.setReplacement(cti.getReplacement());
				cc.setPairs(pairs);
				cc.setName(cti.getSourceColumnName());
				converters.add(cc);
			}

			strategy.setPipeLine(targetPipeline.get(targetId));
			ObjectMapper objectMapper = new ObjectMapper();
			strategy.setContentFilter(objectMapper.writeValueAsString(emm));
			strategy.setFromTable(tmi.getFromTable());
			strategy.setToTable(tmi.getToTable());
			strategy.setDelFlag('0');
			strategy.setCreateUser(userId);
			strategy.setCreateTime(now);
			key.delete(0, key.length());
			this.strategyDao.insert(strategy);
		}
		return taskEntity.getId();
	}

	/**
	 * 新增任务.
	 * 
	 * @param sdata
	 *            HttpSession信息
	 * @param bigDto
	 *            json数据
	 * @exception TaskException
	 *                任务处理异常
	 */
	@Override
	public final void saveTask(final SessionData sdata,
			final List<LinkedHashMap<String, ?>> bigDto) throws TaskException {
		int hasToTable = 0;
		String toTable = "";
		final List<PipeLine> list = new ArrayList<PipeLine>(); // 子任务实体
		TransEntity sourceEntity = new TransEntity();
		final TaskEntity taskEntity = new TaskEntity();
		taskEntity.setDeleteSourceData(Character.valueOf('0'));
		taskEntity.setRebuildTrigger(Character.valueOf('0'));
		taskEntity.setDelFlag(NOT_DELETE);
		taskEntity.setCreateUser(sdata.getUserId());
		LinkedHashMap<String, Object> taskMp = (LinkedHashMap<String, Object>) bigDto
				.get(0);
		TransTaskJsonDto transTaskJsonDto = new TransTaskJsonDto();
		// 将第一个mp转换成task
		TaskUtil.transMap2Bean(taskMp, transTaskJsonDto);
		// 包含有书否重建触发器
		taskEntity.setName(transTaskJsonDto.getName());

		if (!StringUtils.isEmpty(transTaskJsonDto.getDescription())) {
			taskEntity.setDescription(transTaskJsonDto.getDescription());
		}
		if (!StringUtils.isEmpty(transTaskJsonDto.getSynchroType())) {
			// 同步类型
			taskEntity.setSynchroType(transTaskJsonDto.getSynchroType()
					.toCharArray()[0]);
		}
		//
		if (transTaskJsonDto.isIsdeleteSourceData()) {
			// 删除源
			taskEntity.setDeleteSourceData(Character.valueOf('1'));
		}
		if (transTaskJsonDto.isIsrebuildTrigger()) {
			// 重建触发器
			taskEntity.setRebuildTrigger(Character.valueOf('1'));
		}
		try {
			this.taskDao.insert(taskEntity);
			if (logger.isDebugEnabled()) {
				logger.debug("插入编号为:{}的任务成功，", taskEntity.getId());
			}
		} catch (Exception e1) {
			logger.error("插入任务出现异常:{}"+e1.getMessage());
			throw new TaskException("插入任务出现异常，异常信息:" + e1.getMessage());
		}
		bigDto.remove(taskMp);
		EtlStrategy strategy = null;
		if (transTaskJsonDto.isHasMappingTable()) {
			strategy = new EtlStrategy();
			strategy.setDelFlag(NOT_DELETE);
			// 表映射信息
			LinkedHashMap<String, Object> tableMap = (LinkedHashMap<String, Object>) bigDto
					.get(bigDto.size() - 1);
			TableMapDto tableMapDto = new TableMapDto();
			TaskUtil.transMap2Bean(tableMap, tableMapDto);
			strategy.setFromTable(tableMapDto.getFromTable());
			strategy.setToTable(tableMapDto.getToTable());
			strategy.setCreateUser(sdata.getUserId());
			bigDto.remove(tableMap);
			if (transTaskJsonDto.isHasMappingColumn()) {
				// etl策略
				LinkedHashMap<String, Object> etlMap = (LinkedHashMap<String, Object>) bigDto
						.get(bigDto.size() - 1);
				// 将其转换成JSON串
				String contentFilter = JSONHelper.transferFromMap1(etlMap);
				System.out.println(contentFilter);
				strategy.setContentFilter(contentFilter);
				// from_table to_table--transEntity里面--pipeline
				bigDto.remove(etlMap);
			}
		}
		for (int i = 0; i < bigDto.size(); i++) {
			// 外层是每一个交换实体
			LinkedHashMap<String, Object> entityMp = (LinkedHashMap<String, Object>) bigDto
					.get(i);
			TransEntityJsonDto entityJsonDto = new TransEntityJsonDto();
			TaskUtil.transMap2Bean(entityMp, entityJsonDto);
			final PipeLine pipeLine = new PipeLine(); // 每一次有一个交换子任务
			pipeLine.setCreateUser(sdata.getUserId());
			pipeLine.setDel_flag(NOT_DELETE);
			pipeLine.setTaskEntity(taskEntity);
			if (TRUESTR.equals(entityJsonDto.getIsSource())) {
				sourceEntity.setDataSourceInfo(new DataSourceInfo(new Long(
						entityJsonDto.getSourceid())));
				sourceEntity.setType(entityJsonDto.getType().toCharArray()[0]);
				if (StringUtils.isNotEmpty(entityJsonDto.getTbstr())) {
					String tableName = entityJsonDto.getTbstr().substring(0,
							entityJsonDto.getTbstr().lastIndexOf(COMMA));
					entityJsonDto.setTbstr(tableName);
					sourceEntity.setTableName(tableName);
				} else {
					sourceEntity.setLocation(entityJsonDto.getLocation());
				}
				sourceEntity.setDel_flag(NOT_DELETE);
				sourceEntity.setCreateUser(sdata.getUserId());
				try {
					this.transEntityDao.insert(sourceEntity);
					if (logger.isDebugEnabled()) {
						logger.debug("新增编号为{}的源实体成功", sourceEntity.getId());
					}
				} catch (Exception e) {
					throw new TaskException("新增交换源出现异常，异常信息为：" + e.getMessage());
				}
			} else {

				final TransEntity transEntity = new TransEntity(); // 交换任务（端）
				transEntity.setDataSourceInfo(new DataSourceInfo(new Long(
						entityJsonDto.getSourceid())));
				transEntity.setType(entityJsonDto.getType().toCharArray()[0]);
				if (!StringUtils.isEmpty(entityJsonDto.getTbstr())) {
					String tableName = entityJsonDto.getTbstr().substring(0,
							entityJsonDto.getTbstr().lastIndexOf(COMMA));

					transEntity.setTableName(tableName);
				} else {
					transEntity.setLocation(entityJsonDto.getLocation());
				}
				transEntity.setCreateUser(sdata.getUserId());
				transEntity.setDel_flag(NOT_DELETE);
				try {
					this.transEntityDao.insert(transEntity);
					if (logger.isDebugEnabled()) {
						logger.debug("新增编号为{}的端实体成功", transEntity.getId());
					}
				} catch (Exception e) {
					throw new TaskException("新增交换端实体出现异常，异常信息为："
							+ e.getMessage());
				}
				pipeLine.setTargetEntity(transEntity);
				list.add(pipeLine);
				if (transEntity.getTableName() != null
						&& transEntity.getTableName().indexOf(toTable) != -1) {
					hasToTable = list.size() - 1;
				}
			}
		}

		for (int i = 0; i < list.size(); i++) {
			list.get(i).setSourceEntity(sourceEntity);
			try {
				this.pipeLineDao.insert(list.get(i));
				if (transTaskJsonDto.isHasMappingTable() && i == hasToTable) {
					strategy.setPipeLine(list.get(i));
					try {
						strategyDao.insert(strategy);
						if (logger.isDebugEnabled()) {
							logger.debug("插入一条ETL策略信息成功，etlId:{}",
									strategy.getId());
						}
					} catch (Exception e) {
						logger.error("插入ETL策略信息报错，{}" + e.getMessage());
					}
				}
				// logger日志是在info级别的
				logger.debug("插入 sourceId:{},targetId:{}的子任务成功", list.get(i)
						.getSourceEntity().getId(), list.get(i)
						.getTargetEntity().getId());
			} catch (final Exception e) {
				logger.error("插入子任务时出现异常{}" + e.getMessage());
				throw new TaskException("插入子任务时出现异常" + e.getMessage());
			}
		}
	}

	@Override
	public boolean isExitsName(String taskName) {
		return this.taskDao.isExitsName(taskName);
	}

	@Override
	public PageList<TaskDto> getEntitis(Page page, TaskEntity taskEntity) {
		// 得到所有原始的task集合
		PageList<TaskEntity> pageList = this.taskDao.findTaskList(page,
				taskEntity);
		List<TaskDto> dtos = new ArrayList<TaskDto>();
		for (int i = 0; i < pageList.getList().size(); i++) {
			TaskEntity task = pageList.getList().get(i);
			// 判断该任务的源和端是什么类型可能有多个子任务，
			TransEntity sourceEntity = null;
			TransEntity transEntity = null;
			try {
				List<PipeLine> pipelines = null;
				pipelines = this.pipeLineDao.listPipelinesById(task.getId());
				sourceEntity = pipelines.get(0).getSourceEntity();
				transEntity = pipelines.get(0).getTargetEntity();
			} catch (java.lang.IndexOutOfBoundsException e) {
				e.printStackTrace();
				logger.error("查询任务记录时发生异常，出现问题的任务编号为：", task.getId());
				throw new TaskException("查询任务记录时发生异常，出现问题的任务编号为："
						+ task.getId());
			}
			TaskDto taskDto = new TaskDto();
			taskDto.setName(task.getName());
			taskDto.setId(task.getId().toString());
			String taskType = TaskUtil.setTaskDtoType(sourceEntity
					.getDataSourceInfo().getSource_type(), transEntity
					.getDataSourceInfo().getSource_type());
			taskDto.setType(taskType);
			dtos.add(taskDto);
		}
		PageList<TaskDto> pagedtos = new PageList<TaskDto>();
		pagedtos.setList(dtos);
		pagedtos.setPage(pageList.getPage());
		return pagedtos;
	}

	@Override
	public boolean executeSchedul(Long taskId) {
		TaskRunEvent taskRunEvent = new TaskRunEvent();
		taskRunEvent.setData(taskId);
		boolean flag = false;
		try {
			// Boolean obj = (Boolean)
			// client.call(RmiUtil.getRmiUrl(DspPropertyConfigurer.getDspProperty(MANAGER_RMI_ADDRESSES)),
			// taskRunEvent);
			// 从系统配置参数map中获取外服务manager rmi地址
			Boolean obj = (Boolean) client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.MANAGER_RMI_ADDRESS)),taskRunEvent);
			flag = obj.booleanValue();
			if (flag) {
				if (logger.isDebugEnabled()) {
					logger.debug("本次调度运行成功，任务编号为：{}", taskId);
				}
			} else {
				logger.error("本次调度运行失败，任务编号为：{}", taskId);
			}
		} catch (Throwable e) {
			logger.error("调度过程中出现异常,异常信息为：{}", e.getMessage());
			throw new TaskException("调度过程中出现异常，异常信息为：" + e.getMessage());
		}
		return flag;
	}

	@Override
	public void deleteSchedul(Long id) {
		try {
			TaskDeleteEvent taskDeleteEvent = new TaskDeleteEvent();
			taskDeleteEvent.setData(id);
			boolean flag = false;
//			Boolean obj = (Boolean) client.call(RmiUtil.getRmiUrl(DspPropertyConfigurer.getDspProperty(MANAGER_RMI_ADDRESSES)),taskDeleteEvent);
			// 从系统配置参数map中获取外服务manager rmi地址
			Boolean obj = (Boolean) client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.MANAGER_RMI_ADDRESS)),taskDeleteEvent);
			flag = obj.booleanValue();
			if (flag) {
				if (logger.isDebugEnabled()) {
					logger.debug("删除调度信息成功，任务编号为：{}", id);
				}
			}
		} catch (Exception e) {
			logger.error("删除调度信息过程中出现异常,异常信息为：", e.getMessage());
			throw new TaskException(e);
		}
	}

	@Override
	public void updateSechedul(Long id) {
		try {
			TaskUpdateEvent updateEvent = new TaskUpdateEvent();
			updateEvent.setData(id);
			boolean flag = false;
//			Boolean obj = (Boolean) client.call(RmiUtil.getRmiUrl(DspPropertyConfigurer.getDspProperty(MANAGER_RMI_ADDRESSES)),updateEvent);
			// 从系统配置参数map中获取外服务manager rmi地址
			Boolean obj = (Boolean) client.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.MANAGER_RMI_ADDRESS)),updateEvent);
			flag = obj.booleanValue();
			if (flag) {
				if (logger.isDebugEnabled()) {
					logger.debug("修改调度信息成功，任务编号为：{}", id);
				}
			}
		} catch (Exception e) {
			logger.error("修改调度信息出现异常，异常信息为：", e.getMessage());
			throw new TaskException(e);
		}
	}

	@Override
	public void createAddTable(Long id) throws Exception {
		AddTableDto dto = this.pipeLineDao.getAddTables(id);
//		this.client.call(RmiUtil.getRmiUrl(DspPropertyConfigurer.getDspProperty(OUTER_RMI_ADDRESSES)), addEvent);
		//直接从系统配置参数map中获取外服务rmi地址
		AddDBTable addTable = new AddDBTable();
		try {
            addTable.createAddTable(dto);
            String [] tablenames;
            if(dto.isRebuildTrigger()){
                if(dto.getTablename().contains(",")) {
                    tablenames = dto.getTablename().split(",");
                    for(int i = 0;i<tablenames.length;i++) {
                        addTable.dropTrigger(addTable.createDataSourceDto(dto),tablenames[i]);
                    }
                } else {
                    addTable.dropTrigger(addTable.createDataSourceDto(dto),dto.getTablename());
                }
            }
            addTable.createTableTrigger(dto);
        }
        catch (SQLException e) {
            logger.error("the create AddTable or TableTrigger error the error is : {}",e.getMessage());
            throw new CreateAddTableException(e.getMessage());
        }
        catch (ClassNotFoundException e) {
            logger.error("the driver not find the error is : {}",e.getMessage());
        }
	}

	@Override
	public void deleteSchedule(Long id) {
		this.scheduleDao.deleteByTaskId(id);
	}

	@Override
	public boolean canExecute(Long id) {
		char status = this.jobInfoDao.queryStutusByTaskId(id);
		if (status == '0' || status == '2') {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<ColumnMappingDto> listColumnByDataSource(
			ListColumnDataSourceDto dto) {
		List<ColumnMappingDto> list = null;
		try {
//			list = (List) client.call(RmiUtil.getRmiUrl(DspPropertyConfigurer.getDspProperty(OUTER_RMI_ADDRESSES)),new ListColumnByTableEvent(dto));
			//直接从系统配置参数map中获取外服务rmi地址
		    list = networkcommunication.listColumnByTable(dto);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List findFlTables(AddTableDto addTableDto) {
		List list = null;
		try {
//			list = (List) this.client.call(RmiUtil.getRmiUrl(DspPropertyConfigurer.getDspProperty(OUTER_RMI_ADDRESSES)),new FindFlTableEvent(addTableDto));
			//直接从系统配置参数map中获取外服务rmi地址
		    list = networkcommunication.listFTable(addTableDto);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public PageList<ColumnMappingDto> listColumnDto(TableMappingDto dto,
			Page page) {
		PageList<ColumnMappingDto> pageList = new PageList<ColumnMappingDto>();
		DataSourceInfo sourceInfo = dataSourceInfoDao.getById(Long.valueOf(dto
				.getSourceid()));
		DataSourceInfo targetInfo = dataSourceInfoDao.getById(Long.valueOf(dto
				.getTargetid()));
		DataSourceDto dataSourceDto = null;
		DataSourceDto dataTargetDto = null;
		try {
			dataSourceDto = TaskTransUtil.transferDataSource(sourceInfo);
			dataTargetDto = TaskTransUtil.transferDataSource(targetInfo);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// dataSourceDto,dto.getSource()
		// dataTargetDto,dto.getTarget()
		ListColumnDataSourceDto tt = new ListColumnDataSourceDto();
		tt.setSourceDto(dataSourceDto);
		tt.setTargetDto(dataTargetDto);
		tt.setSourceTable(dto.getSource());
		tt.setTargetTable(dto.getTarget());
		List<ColumnMappingDto> list = this.listColumnByDataSource(tt);
		for (int i = 0; i < list.size(); i++) {
			ColumnMappingDto cdt = list.get(i);
			cdt.setTargetId(new Long(dto.getTargetid()));
			cdt.setSourceTableName(dto.getSource());
			cdt.setTargetTableName(dto.getTarget());
		}
		pageList.setList(list);
		page.setCount(list.size());
		pageList.setPage(page);
		return pageList;
	}

	@Override
	public List<ColumnDto> dateColumnName(
			ListColumnDataSourceDto columnDataSourceDto) {
		List<ColumnDto> list = null;
		try {
//			list = (List) this.client.call(RmiUtil.getRmiUrl(DspPropertyConfigurer.getDspProperty(OUTER_RMI_ADDRESSES)),new ListDateColumnEvent(columnDataSourceDto));
			//直接从系统配置参数map中获取外服务rmi地址
		    list = networkcommunication.dateColumnName(columnDataSourceDto);
		} catch (Exception e) {
			logger.error("查询时间戳字段出现异常，异常信息为：{}", e.getMessage());
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public String isAssociation(DataSourceInfo dsInfo, List<String> tableNames) {
		return taskDao.isAssociation(dsInfo, tableNames);
	}

    @Override
    public FullTaskDto editForShow(Long taskID) {
        return taskDao.editForShow(taskID);
    }
}
