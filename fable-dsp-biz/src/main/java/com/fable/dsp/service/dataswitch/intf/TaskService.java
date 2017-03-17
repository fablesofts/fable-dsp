package com.fable.dsp.service.dataswitch.intf;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import com.fable.dsp.common.dto.dataswitch.AddTableDto;
import com.fable.dsp.common.dto.dataswitch.ColumnDto;
import com.fable.dsp.common.dto.dataswitch.ColumnMappingDto;
import com.fable.dsp.common.dto.dataswitch.FullTaskDto;
import com.fable.dsp.common.dto.dataswitch.ListColumnDataSourceDto;
import com.fable.dsp.common.dto.dataswitch.TableMappingDto;
import com.fable.dsp.common.dto.dataswitch.TaskDto;
import com.fable.dsp.common.dto.dataswitch.TreeDataDto;
import com.fable.dsp.common.exception.TaskException;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.core.service.GenericService;
import com.fable.dsp.core.session.SessionData;
import com.fable.dsp.dmo.datasource.DataSourceInfo;
import com.fable.dsp.dmo.dataswitch.TaskEntity;
import com.fable.hamal.shuttle.common.model.config.metadata.Db;
/**
 * TaskService实现类
 * @author 邱爽
 *
 */
public interface TaskService extends GenericService<TaskEntity> {

    /**
     * 分页+条件查询.
     * @param page 分页Bean
     * @param taskEntity 条件Bean
     * @return 分页结果集
     */
    PageList<TaskEntity> listTaskByConditions(Page page,
        TaskEntity taskEntity);

   /**
    * 唯一查询.
    * @param taskEntity 分页查询
    * @return task对象
    */
    TaskEntity getTaskByConditions(TaskEntity taskEntity);

    /**
     * 分页+条件查询.
     * @param page 分页Bean
     * @param taskEntity 条件Bean
     * @return 分页结果集
     */
    PageList<TaskEntity> findTaskInfo(Page page, TaskEntity taskEntity);
    
    /**
     * 
     * @param userId
     * @param fullTaskDto
     * @return taskid
     * @throws TaskException
     * @throws IOException
     */
    Long addTask(String userId, FullTaskDto fullTaskDto) throws TaskException, IOException;

    /**
     * 新增任务.
     * @param sdata
     *        session信息
     * @param bigDto
     *        json数据
     * @exception 任务处理相关异常.
     */
    void saveTask(SessionData sdata, List<LinkedHashMap<String, ?>> bigDto)
        throws TaskException;

    /**
     * 查找相应的数据库下的表名.
     * @param dataSourceInfo
     *        数据源对象.
     * @return TreeDataDto列表
     * @throws Exception 
     * @throws IOException 
     */
    List<TreeDataDto> listTable(DataSourceInfo dataSourceInfo) throws IOException, Exception;

    /**
     * 修改任务.
     * @param sdata
     *        session信息
     * @param bigDto
     *        json数据
     * @return TaskId
     */
    Long editTask(SessionData sdata, FullTaskDto fullTaskDto);
    /**
     * 判断任务是否重名.
     * @param taskName
     *        任务名
     * 
     */
    boolean isExitsName(String taskName);

    /**
     * 以Dto方式呈现的结果集.
     * @param page 分页Bean
     * @param taskEntity 条件Bean
     * @return 封装过的task结果集
     */
    PageList<TaskDto> getEntitis(Page page, TaskEntity taskEntity);

    /**
     * 立刻执行调度.
     * @param taskId 任务编号
     */
    boolean executeSchedul(Long taskId);

    /**
     * 删除调度.
     * @param id 任务id
     */
    void deleteSchedul(Long id);

    /**
     * 修改调度.
     * @param id 任务编号
     */
    void updateSechedul(Long id);

    /**
     * 创建增量表.
     * @param id 任务编号
     * @throws Exception 
     */
    void createAddTable(Long id) throws Exception;
    /**
     * 删除指定id的调度信息
     * @param id
     */
    void deleteSchedule(Long id);
    /**
     * 判断这条任务是否能调度
     * @param id 任务编号
     * @return
     */
    boolean canExecute(Long id);
    /**
     * 根据数据源查询指定数据表下的所有字段信息
     * @param dto 数据源
     * @return 包含所有字段信息的集合
     */
    List<ColumnMappingDto> listColumnByDataSource(ListColumnDataSourceDto dto);
    /**
     * 根据源表和目标表加载字段映射的信息
     * @param dto
     * @return
     */
	PageList<ColumnMappingDto> listColumnDto(TableMappingDto dto,Page page);
	/**
	 * 根据目标实体编号删除子任务
	 * @param long1 目标实体编号
	 */
	void deletePipeLineByTargetId(Long long1);
	/**
	 * 根据数据源查询指定主表的从表
	 * @param ds 数据源
	 * @return 从表集合
	 */
	public List findFlTables(AddTableDto addTableDto);
	/**
	 * 时间戳字段
	 * @param columnDataSourceDto
	 * @return
	 */
	public List<ColumnDto> dateColumnName(ListColumnDataSourceDto columnDataSourceDto);
	/**
	 * 判断任务中是否包含主从表关系
	 * @param dsInfo 数据源信息
	 * @param tableNames 源端表名
	 * @return 返回是否完整的主从表关系 0 没有主从表关系 1有不健全的主从表关系 2主从表关系健全 
	 */
	public String isAssociation(DataSourceInfo dsInfo,List<String> tableNames);
	/**
	 * 修改任务管理回写信息
	 * @param taskID 任务id
	 * @return 返回任务所有信息 FullTaskDto
	 */
	public FullTaskDto editForShow(Long taskID);
}
