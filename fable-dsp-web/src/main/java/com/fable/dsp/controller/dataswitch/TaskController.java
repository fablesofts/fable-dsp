package com.fable.dsp.controller.dataswitch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fable.dsp.common.constants.CommonConstants;
import com.fable.dsp.common.dto.dataswitch.AddTableDto;
import com.fable.dsp.common.dto.dataswitch.ColumnDto;
import com.fable.dsp.common.dto.dataswitch.ColumnMappingDto;
import com.fable.dsp.common.dto.dataswitch.ComboboxDto;
import com.fable.dsp.common.dto.dataswitch.CrontabDto;
import com.fable.dsp.common.dto.dataswitch.DataSourceDto;
import com.fable.dsp.common.dto.dataswitch.DataSourceInfoDto;
import com.fable.dsp.common.dto.dataswitch.FullTaskDto;
import com.fable.dsp.common.dto.dataswitch.ListColumnDataSourceDto;
import com.fable.dsp.common.dto.dataswitch.PipeLineDto;
import com.fable.dsp.common.dto.dataswitch.PropertyDto;
import com.fable.dsp.common.dto.dataswitch.SourceDSInfo;
import com.fable.dsp.common.dto.dataswitch.TableMappingDto;
import com.fable.dsp.common.dto.dataswitch.TaskDto;
import com.fable.dsp.common.dto.dataswitch.TaskEntityDto;
import com.fable.dsp.common.dto.dataswitch.TestDto;
import com.fable.dsp.common.dto.dataswitch.TransDto;
import com.fable.dsp.common.dto.dataswitch.TransEntityDto;
import com.fable.dsp.common.dto.dataswitch.TransTaskJsonDto;
import com.fable.dsp.common.dto.dataswitch.TreeDataDto;
import com.fable.dsp.common.exception.TaskException;
import com.fable.dsp.common.util.CrontabUtil;
import com.fable.dsp.common.util.DataBaseTypes;
import com.fable.dsp.common.util.DbTypeTransferUtil;
import com.fable.dsp.common.util.EncryptDES;
import com.fable.dsp.common.util.TaskUtil;
import com.fable.dsp.core.datagrid.DataGridModel;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.core.session.SessionData;
import com.fable.dsp.dmo.datasource.DataSourceInfo;
import com.fable.dsp.dmo.dataswitch.PipeLine;
import com.fable.dsp.dmo.dataswitch.TaskEntity;
import com.fable.dsp.dmo.dataswitch.TransEntity;
import com.fable.dsp.dmo.schedule.Schedule;
import com.fable.dsp.service.datasource.intf.DataSourceInfoService;
import com.fable.dsp.service.dataswitch.intf.PipeLineInfoService;
import com.fable.dsp.service.dataswitch.intf.ScheduleService;
import com.fable.dsp.service.dataswitch.intf.StrategyService;
import com.fable.dsp.service.dataswitch.intf.TaskService;
import com.fable.dsp.service.dataswitch.intf.TransEntityService;
import com.fable.dsp.spring.extend.Json;

/**
 * 任务管理控制层.
 * @author 邱爽
 */
@Controller
@RequestMapping("/dataswitch")
public class TaskController{
    /**
     * taskService属性注入.
     */
    @Autowired
    private TaskService taskService;
    /**
     * dataSourceInfoService属性注入.		
     */
    @Autowired
    private DataSourceInfoService dataSourceInfoService;
    /**
     *  pipeLineService属性注入。
     */
    @Autowired
    private PipeLineInfoService pipeLineService;
    /**
     * transEntityService属性注入。 
     */
    @Autowired
    private TransEntityService transEntityService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private StrategyService strategyService;
    
    @RequestMapping("/task-maintain")
    public String maintain() {
        return "dataswitch/taskmanager";
    }
    /**
     * datagrid分页常量
     */
    private static final String DGV_ROWS = "rows";
    /**
     * datagrid分页常量
     */
    private static final String DGV_TOTAL = "total";
    /**
     * 任务相关属性
     */
    private static final String TASK_NAME="name";
    private static final String DELETE_SOURCEDATA="deleteSourceData";
    private static final String REBUILD_TRIGGER="rebuildTrigger";
    /**
     * 任务相关属性
     */
    private static final String TASK_DSCRIPTION="description";
    /**
     * 任务相关属性
     */
    private static final String TASK_ID="id";
    /**
     * 任务同步类型
     */
    private static final String SYNCHROTYPE="synchroType";
    /**
     * 任务相关属性
     */
    private static final String TASK_TYPE="taskType";
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private static final String COMMA=",";
    
    /**
     * 判断是否有调度任务存在
     * @return
     */
    @RequestMapping("isExistsSchedule")
    @ResponseBody
    public Map<String,Object>isExistsSchedule(TaskEntity taskEntity){
        Map<String, Object> rootJson = new HashMap<String, Object>();
            if(this.scheduleService.isExistsSchedule(taskEntity.getId())){
                rootJson.put(CommonConstants.COL_DEALFLAG, true);
            }else{
                rootJson.put(CommonConstants.COL_DEALFLAG, false);
            }
        return rootJson;
    }
    
    /**
     * 立刻执行调度.
     * @param taskEntity 任务类型。
     * @return 是否调度成功信息.
     */
    @RequestMapping("execute")
    @ResponseBody
    public Map<String, Object> execute(TaskEntity taskEntity){
        Map<String, Object> rootJson = new HashMap<String, Object>();
        //先查看有没有，再查看能不能调：在JOB_RUN_INFO中记录状态
//           if(this.scheduleService.isExistsJob(taskEntity.getId())){
//              if(!this.taskService.canExecute(taskEntity.getId())){
//                  rootJson.put(CommonConstants.COL_DEALFLAG, false);
//                  rootJson.put(CommonConstants.COL_MSG, "调度正在运行中，不能调度");
//              }else{
                        try {
                            boolean flg=this.taskService.executeSchedul(taskEntity.getId());
                            if(flg){
                                rootJson.put(CommonConstants.COL_DEALFLAG,true);
                                rootJson.put(CommonConstants.COL_MSG, "调度成功");
                            }else{
                                rootJson.put(CommonConstants.COL_DEALFLAG,false);
                                rootJson.put(CommonConstants.COL_MSG, "调度失败");
                            }
                        } catch (Exception e) {
                            //打印日志
                            logger.error("调度过程中出现异常，异常信息为：{}",e.getMessage());
                            rootJson.put(CommonConstants.COL_DEALFLAG,false);
                            rootJson.put(CommonConstants.COL_MSG,"调度过程中出现异常");
                        }
                        return rootJson;
//              }
//        }else{
//          rootJson.put(CommonConstants.COL_DEALFLAG, false);
//            rootJson.put(CommonConstants.COL_MSG, "调度信息不存在");
//        }
//        return rootJson;
    }
    /**
     * 根据任务id查询下面的调度信息
     * @param taskEntity 任务实体
     * @return Crontab传输对象
     * @throws Exception 调度异常
     */
    @RequestMapping("queryScheduleByTask")
    @ResponseBody
    public CrontabDto queryScheduleByTask(TaskEntity taskEntity) throws Exception {
        Schedule schedule=this.scheduleService.getScheduleByTaskId(taskEntity.getId());
        CrontabDto  crontabDto=new CrontabDto();
        crontabDto.setScheduleId(schedule.getId().toString());
        String crontabExpression=schedule.getCronTabExpression();
        //根据crontab表达式封装crontabDto对象
        CrontabUtil.transToCrontabDto(crontabDto, crontabExpression);
        return crontabDto;
    }
    /**
     * 删除调度
     * @return
     */
    @RequestMapping("deleteSchedule")
    @ResponseBody
    public Map<String,Object>deleteSchedule(TaskEntity taskEntity){
        Map<String, Object> rootJson = new HashMap<String, Object>();
        try {
            this.taskService.deleteSchedule(taskEntity.getId());
            rootJson.put(CommonConstants.COL_DEALFLAG, true);
            rootJson.put(CommonConstants.COL_MSG, "删除调度成功");
        }
        catch (Exception e) {
        	logger.error("删除调度过程中出现异常，异常信息为：{}",e.getMessage());
            rootJson.put(CommonConstants.COL_DEALFLAG, false);
            rootJson.put(CommonConstants.COL_MSG, "删除调度失败");
        }
        return rootJson;
    }

    /**
     * 修改调度信息
     * @param session
     * @param crontabDto
     * @return
     */
    @RequestMapping("updateSchedule")
    @ResponseBody
    public Map<String,Object>updateSchedule(HttpSession session,CrontabDto crontabDto){
    	 Map<String, Object> rootJson = new HashMap<String, Object>();
         //格式为：0/10 0/30 9-17 * * ?   9-17内每半小时
         Schedule schedule=new Schedule();
         schedule.setId(Long.parseLong(crontabDto.getScheduleId()));
         schedule.setTaskEntity(new TaskEntity(new Long(crontabDto.getTaskId())));
         String crontabExpression=CrontabUtil.transFromCrontabDto(crontabDto);
         schedule.setCronTabExpression(crontabExpression);
         SessionData sdata = (SessionData) session
                         .getAttribute(CommonConstants.SESSION_DATA);
         schedule.setUpdateUser(sdata.getUserId());
         schedule.setUpdateTime(new Date());
         schedule.setDel_flag(0L);
         StringBuffer msg = new StringBuffer();
         boolean flag = false;
         try {
			this.scheduleService.update(schedule);
			msg.append("修改调度成功");
			flag = true;
			if (crontabDto.isExecuteimmediately()) {
                boolean executeSuccess = false;
                try {
                    executeSuccess = this.taskService.executeSchedul(new Long(crontabDto.getTaskId()));
                } catch (Exception e) {
                    msg.append("，执行调度出现异常");
                    logger.error("运行本次调度时发生异常，任务编号:{}，调度编号为:{}",schedule.getTaskEntity().getId(),schedule.getId());
                    flag = false;
                }
                if (executeSuccess) {
                    msg.append("，调度执行成功");
                    flag = true;
                }
			}
		} catch (Exception e) {
            msg.append("修改调度过程中出现异常");
            logger.error("修改调度过程中出现异常，异常信息为：{}",e.getMessage());
        }
        rootJson.put(CommonConstants.COL_DEALFLAG, flag);
        rootJson.put(CommonConstants.COL_MSG, msg);
        return rootJson;
    }
    
    /**
     * 根据主表的数据源查询其从表信息
     * @param addTableDto
     * @return
     */
    @RequestMapping("findFlTables")
    @ResponseBody
    public List findFlTables(TableMappingDto dto){
    	DataSourceInfo sourceInfo =
                this.dataSourceInfoService.
                getById(Long.valueOf(dto.getSourceid()));
    	AddTableDto addTableDto=new AddTableDto();
    	String fulldbtype=DbTypeTransferUtil.transferToFullType(sourceInfo.getDb_type()).toUpperCase();
    	DataBaseTypes type = Enum.valueOf(DataBaseTypes.class,fulldbtype);
		addTableDto.setDbtype(type);
		addTableDto.setDbname(sourceInfo.getDb_name());
		addTableDto.setUsername(sourceInfo.getUsername());
		try {
			//数据源密码解密
			addTableDto.setPassword(EncryptDES.decrypt(sourceInfo.getPassword(), CommonConstants.DES_KEY));
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		addTableDto.setConnect_url(sourceInfo.getConnect_url());
		//将原生的dbtype做转换
		String fulldbType=DbTypeTransferUtil.transferToFullType(sourceInfo.getDb_type());
		addTableDto.setDbType(fulldbType);
		addTableDto.setTablename(dto.getSource());
		List list=null;
		try {
			list = taskService.findFlTables(addTableDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return list;
    }
    /**
     * 新增任务调度
     * @return 是否增加成功
     */
    @RequestMapping("schedule")
    @ResponseBody
    public Map<String,Object>schedule(HttpSession session,CrontabDto crontabDto){
        Map<String, Object> rootJson = new HashMap<String, Object>();
        StringBuffer msg = new StringBuffer();
        boolean flag = false;
        //格式为：0/10 0/30 9-17 * * ?   9-17内每半小时
        Schedule schedule=new Schedule();
        schedule.setTaskEntity(new TaskEntity(new Long(crontabDto.getTaskId())));
        //定期模式
        String crontabExpression=CrontabUtil.transFromCrontabDto(crontabDto);
        schedule.setCronTabExpression(crontabExpression);
        SessionData sdata = (SessionData) session
                        .getAttribute(CommonConstants.SESSION_DATA);
        schedule.setCreateUser(sdata.getUserId());
        schedule.setDel_flag(0L);
        try {
            this.scheduleService.insert(schedule);
            msg.append("新增调度成功");
            flag = true;
            if(crontabDto.isExecuteimmediately()) {
                boolean executeSuccess = false;
                try {
                    executeSuccess = this.taskService.executeSchedul(new Long(crontabDto.getTaskId()));
                } catch (Exception e) {
                    msg.append("，执行调度失败");
                    logger.error("运行本次调度时发生异常，任务编号:{}，调度编号为:{}",schedule.getTaskEntity().getId(),schedule.getId());
                    flag = false;
                }
                if (executeSuccess) {
                    msg.append("，执行调度成功");
                    flag = true;
                }else {
                    msg.append("，执行调度失败");
                    flag = false;
                }
            }
        }
        catch (Exception e) {
            msg.append("增加调度过程中出现异常");
            logger.error("增加调度过程中出现异常，异常信息为：{}",e.getMessage());
        }
        rootJson.put(CommonConstants.COL_DEALFLAG, flag);
        rootJson.put(CommonConstants.COL_MSG, msg);
        return rootJson;
    }
    /**
     * 创建增量表.
     * @param taskEntity 任务信息
     * @return 是否创建成功
     */
    @RequestMapping("createAddTable")
    @ResponseBody
    public Map<String, Object> createAddTable(TaskEntity taskEntity){
        Map<String, Object> rootJson = new HashMap<String, Object>();
        try {
            this.taskService.createAddTable(taskEntity.getId());
            rootJson.put(CommonConstants.COL_DEALFLAG, true);
            rootJson.put(CommonConstants.COL_MSG, "创建增量表成功");
        }
        catch (Exception e) {
            logger.error("createAddTable failed ，the error message is：{}",e.getMessage());
            rootJson.put(CommonConstants.COL_DEALFLAG, true);
            rootJson.put(CommonConstants.COL_MSG, "创建增量表失败");
        }
        return rootJson;
    }
    /**
     * 查询时间戳字段
     * @param dataSourceDto
     * @return
     */
    @RequestMapping("dateColumnName")
    @ResponseBody
    public List<ComboboxDto> dateColumnName(TableMappingDto tableMappingDto) {
    	try {
    		DataSourceInfo dsi =
                    this.dataSourceInfoService.
                    getById(Long.valueOf(tableMappingDto.getSourceid()));
    		DataSourceDto dsdto = new DataSourceDto();
            String password = dsi.getPassword();
            password =
                EncryptDES.decrypt(password,
                    CommonConstants.DES_KEY);
            dsi.setPassword(password);
            dsdto.setServer_ip(dsi.getServer_ip());
            dsdto.setPort(dsi.getPort());
            dsdto.setUsername(dsi.getUsername());
            dsdto.setPassword(dsi.getPassword());
            dsdto.setConnect_url(dsi.getConnect_url());
            ListColumnDataSourceDto columnDataSourceDto = new ListColumnDataSourceDto();
            columnDataSourceDto.setSourceTable(tableMappingDto.getSource());
            columnDataSourceDto.setSourceDto(dsdto);
			List<ColumnDto> list = null;
			List<ComboboxDto>combolist = new ArrayList<ComboboxDto>();
			try {
				list = taskService.dateColumnName(columnDataSourceDto);
				if (list.size()>0) {
					for (int i =0;i<list.size();i++) {
					    ComboboxDto dataDto = new ComboboxDto();
						dataDto.setId(String.valueOf(list.get(i).getColumnIndex()));
						dataDto.setName(list.get(i).getColumnName());
						combolist.add(dataDto);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return combolist;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    @RequestMapping("listColumnDto")
    @ResponseBody
    public Map<String,Object>listColumnDto(TableMappingDto dto){
        Page page = new Page();
        // 当前页，对应分页中的pageNumber参数
        int currentPage = (dto.getPage() == 0) ? 1 : dto.getPage();
        // 每页显示条数，对应EASYUI分页中的pageSize参数
        int pageSize = dto.getRows() == 0 ?
            CommonConstants.COL_PAGESIZE : dto.getRows();
        // 每页的开始记录 第一页为1，第二页为number+1
        int index = (currentPage - 1) * pageSize;
        // 设置分页查询参数
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setIndex(index);
        // 查询分页结果，一开始没有条件的分页查询
        Map<String, Object> rootJson = new HashMap<String, Object>();
        try {
            PageList<ColumnMappingDto> pageList =this.taskService.listColumnDto(dto,page);
            rootJson.put(this.DGV_ROWS, pageList.getList());
            rootJson.put(this.DGV_TOTAL, pageList.getPage().getCount());
            rootJson.put(CommonConstants.COL_DEALFLAG,true);
        }
        catch (Exception e) {
            rootJson.put(CommonConstants.COL_DEALFLAG, false);
            rootJson.put(CommonConstants.COL_MSG, "加载数据表字段信息失败");
        }
        return rootJson;
    }
    /**
     * 加载任务列表.
     * @param dgm datagrid对象
     * @return 是否加载成功
     */
    @RequestMapping("listTaskDto")
    @ResponseBody
    public Map<String, Object> listTaskDto(DataGridModel dgm) {
        Page page = new Page();
        // 当前页，对应分页中的pageNumber参数
        int currentPage = (dgm.getPage() == 0) ? 1 : dgm.getPage();
        // 每页显示条数，对应EASYUI分页中的pageSize参数
        int pageSize = dgm.getRows() == 0 ?
            CommonConstants.COL_PAGESIZE : dgm.getRows();
        // 每页的开始记录 第一页为1，第二页为number+1
        int index = (currentPage - 1) * pageSize;
        // 设置分页查询参数
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setIndex(index);
        // 查询分页结果，一开始没有条件的分页查询
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setDelFlag('0');
        Map<String, Object> rootJson = new HashMap<String, Object>();
        try {
            PageList<TaskDto> pageList = this.taskService.getEntitis(page,
                taskEntity);
            rootJson.put(this.DGV_ROWS, pageList.getList());
            rootJson.put(this.DGV_TOTAL, pageList.getPage().getCount());
        }
        catch (Exception e) {
        	logger.error(e.getMessage());
            rootJson.put(CommonConstants.COL_DEALFLAG, false);
            rootJson.put(CommonConstants.COL_MSG, "加载任务列表失败");
            throw new TaskException(e.getMessage());
        }
        return rootJson;
    }
    @RequestMapping("testlist")
    @ResponseBody
    public Map<String,Object>testlist(DataGridModel dgm){
    	 Page page = new Page();
         // 当前页，对应分页中的pageNumber参数
         int currentPage = (dgm.getPage() == 0) ? 1 : dgm.getPage();
         // 每页显示条数，对应EASYUI分页中的pageSize参数
         int pageSize = dgm.getRows() == 0 ?
             CommonConstants.COL_PAGESIZE : dgm.getRows();
         // 每页的开始记录 第一页为1，第二页为number+1
         int index = (currentPage - 1) * pageSize;
         // 设置分页查询参数
         page.setCurrentPage(currentPage);
         page.setPageSize(pageSize);
         page.setIndex(index);
         Map<String, Object> rootJson = new HashMap<String, Object>();
         PageList<TestDto> pageList=new PageList<TestDto>();
         List<TestDto>list=new ArrayList<TestDto>();
         list.add(new TestDto("aaa",1,"p",
			90.0,"aaa1","bb1"));
         list.add(new TestDto("bbb",2,"p",
     			200.0,"aaa2","bb2"));
         list.add(new TestDto("ccc",2,"p",
      			100.0,"aaa3","bb3"));
         pageList.setList(list);
         pageList.setPage(page);
             rootJson.put(this.DGV_ROWS, pageList.getList());
             rootJson.put(this.DGV_TOTAL, pageList.getPage().getCount());
             return rootJson;        
    }
    /**
     * 新增任务
     * @param session HttpSession
     * @param bigDto 前台传递的JSON
     * @return 是否保存成功
     */
    @RequestMapping("saveTask")
    @ResponseBody
    public Map<String, Object> saveTask(HttpSession session,
            @RequestBody List<LinkedHashMap<String, ?>> bigDto) {
        SessionData sdata = (SessionData) session
                .getAttribute(CommonConstants.SESSION_DATA);
        Map<String, Object> rootJson = new HashMap<String, Object>();
        try {
            this.taskService.saveTask(sdata, bigDto);
            rootJson.put(CommonConstants.COL_DEALFLAG, true);
            rootJson.put(CommonConstants.COL_MSG, "保存成功");
        }
        catch (TaskException e) {
            rootJson.put(CommonConstants.COL_DEALFLAG, false);
            rootJson.put(CommonConstants.COL_MSG, "保存失败");
        }
        return rootJson;
    }
    /**
     * 编辑任务
     * @param session HttpSession信息。
     * @param FullTaskDto  任务信息
     * @return 是否修改成功
     */
    @RequestMapping("editTask")
    @ResponseBody
    public Map<String, Object> editTask(HttpSession session, @Json FullTaskDto fullTaskDto) {
        SessionData sdata = (SessionData) session.getAttribute(CommonConstants.SESSION_DATA);
        Map<String, Object> rootJson = new HashMap<String, Object>();
        Long taskId = null;
        try {
            taskId = taskService.editTask(sdata, fullTaskDto);
            rootJson.put(CommonConstants.COL_DEALFLAG, true);
            rootJson.put(CommonConstants.COL_MSG, "修改成功");
            rootJson.put("id", taskId);
        }
        catch (Exception e) {
            rootJson.put("id", taskId);
            rootJson.put(CommonConstants.COL_DEALFLAG, false);
            rootJson.put(CommonConstants.COL_MSG, "修改失败");
        }
        return rootJson;
    }
   
    /**
     * 判断任务名是否重名
     * @param name 任务名
     * @return 是否存在
     */
    @RequestMapping("checkTaskName")
    @ResponseBody
    public Map<String, Object> checkTaskName(String name){
        Map<String, Object> rootJson = new HashMap<String, Object>();
        if (this.taskService.isExitsName(name)){
            //有
            rootJson.put(CommonConstants.COL_DEALFLAG, true);
            rootJson.put(CommonConstants.COL_MSG, "任务名已经存在"); 
        } 
        else {
            //有
            rootJson.put(CommonConstants.COL_DEALFLAG, false);
            rootJson.put(CommonConstants.COL_MSG, "任务名可用"); 
        }
        return rootJson;
    }
    /**
     * 条件查询的方法，传递对象.
     * @param taskEntity 任务实体
     * @return 集合
     */
    @RequestMapping("listTaskByConditions")
    @ResponseBody
    public Map<String, Object> listTaskByConditions(TaskEntity taskEntity) {
        // 分页属性
        Page pager = new Page();
        pager.setCurrentPage(1);
        pager.setPageSize(3);
        pager.setIndex(1);
        PageList<TaskEntity> pageList =
                        this.taskService.listTaskByConditions(pager,
                taskEntity);
    
        Map<String, Object> rootJson = new HashMap<String, Object>();
        rootJson.put(this.DGV_ROWS, pageList.getList());
        rootJson.put(this.DGV_TOTAL, pageList.getPage().getCount());
        return rootJson;
    }
    /**
     * 删除任务，删除任务时触发.
     * @param taskEntity 任务对象
     * @return   是否删除成功
     */
    @RequestMapping("deleteTask")
    @ResponseBody
    public Map<String, Object> deleteTask(TaskEntity taskEntity) {
        Map<String, Object> rootJson = new HashMap<String, Object>();
        try {
            List<PipeLine> pipeLines = pipeLineService.
                            findPipeLineByTaskId(taskEntity.getId());
                if(null != pipeLines){
                    for(int i=0;i<pipeLines.size();i++){
                        try {
                            strategyService.deleteEtlByPipeLineId(pipeLines.get(i).getId());
                            pipeLineService.delete(pipeLines.get(i));
                        } catch (Exception e) {
                            rootJson.put(CommonConstants.COL_DEALFLAG, false);
                            rootJson.put(CommonConstants.COL_MSG,"该任务已经建有ETL策略，无法删除");
                            return rootJson;
                        }
                        transEntityService.deleteById(pipeLines.get(i).getSourceEntity().getId());
                        taskService.deleteById(taskEntity.getId());
                        transEntityService.deleteById(pipeLines.get(i).getTargetEntity().getId());
                        rootJson.put(CommonConstants.COL_DEALFLAG, true);
                    }
                }
        } 
        catch (Exception e) {
            rootJson.put(CommonConstants.COL_DEALFLAG, false);
            rootJson.put(CommonConstants.COL_MSG,"删除任务过程中出现异常");
        }
        return rootJson;
    }
    /**
     * 删除任务，删除任务时触发.
     * @param taskEntity 任务对象
     * @return   是否删除成功
     */
    @RequestMapping("editTask2")
    @ResponseBody
    public Map<String, Object> editTask2(HttpSession session,
            @RequestBody List<LinkedHashMap<String, ?>> bigDto) {
        SessionData sdata = (SessionData) session  
                .getAttribute(CommonConstants.SESSION_DATA);
        Map<String, Object> rootJson = new HashMap<String, Object>();
        final TaskEntity taskEntity = new TaskEntity();
        LinkedHashMap<String, Object> taskMp = (LinkedHashMap<String, Object>)
                   bigDto.get(0);
       	    TransTaskJsonDto transTaskJsonDto = new TransTaskJsonDto();
       	     TaskUtil.transMap2Bean(taskMp, transTaskJsonDto);
       	     taskEntity.setId(new Long(transTaskJsonDto.getId()));
       	     try {
				//先全部删除
				 this.deleteTask(taskEntity);
				 //再做新增
				 this.saveTask(session, bigDto);
				 rootJson.put(CommonConstants.COL_DEALFLAG, true);
		            rootJson.put(CommonConstants.COL_MSG, "修改成功");
			} catch (Exception e) {
				e.printStackTrace();
				rootJson.put(CommonConstants.COL_DEALFLAG, false);
	            rootJson.put(CommonConstants.COL_MSG, "修改失败");
			}
        return rootJson;
    }
    @RequestMapping("getTaskDetail")
    @ResponseBody
    public Map<String,Object>getTaskDetail(TaskEntity taskEntity){
    	TaskEntity queryEntity = this.taskService.getById(taskEntity.getId());
    	Map<String, Object> rootJson = new HashMap<String, Object>();
    	List<PipeLine> pipelines = 
                this.pipeLineService.listPipelinesById(taskEntity.getId());
      //多个端
    	List<TransEntity> entities = new ArrayList<TransEntity>();
//一个源,源一定是第二个加载
    	TransEntity sourceEntity = pipelines.get(0).getSourceEntity();
  //源也存放进
    	entities.add(sourceEntity);
  //多个子任务
		for (int i = 0; i < pipelines.size(); i++) {      
		entities.add(pipelines.get(i).getTargetEntity());
		    }
		 String taskType=TaskUtil.setTaskDtoType(sourceEntity.getDataSourceInfo().getSource_type(), 
				 entities.get(1).getDataSourceInfo().getSource_type());
		try {
			List<PropertyDto>list=getPropertyTask(queryEntity,taskType,sourceEntity,entities);
			 rootJson.put(this.DGV_ROWS, list);
			 rootJson.put(this.DGV_TOTAL,list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	 return rootJson;
    }


	/**
     * 封装task
     * @param list
     * @param taskEntity
     */
    private static List<PropertyDto> getPropertyTask(TaskEntity taskEntity,String type,TransEntity sourceEntity,List<TransEntity>entities){
    	List<PropertyDto>list=new ArrayList<PropertyDto>();
        list.add(new PropertyDto("任务名称",taskEntity.getName(),"任务信息","text"));
        list.add(new PropertyDto("任务类型", type,"任务信息","text"));
        if(taskEntity.getDescription()!=null&&!"".equals(taskEntity.getDescription())){
        	list.add(new PropertyDto("任务描述", taskEntity.getDescription(),"任务信息","text"));
        }
        list.add(new PropertyDto("数据源",sourceEntity.getDataSourceInfo().getName(),"数据源信息","text"));
		if(sourceEntity.getTableName()!=null&&!"".equals(sourceEntity.getTableName())){
			list.add(new PropertyDto("数据表",sourceEntity.getTableName(),"数据源信息","text"));
		}else{
			list.add(new PropertyDto("文件路径", sourceEntity.getLocation(),"数据源信息","text"));
		}
		for(int i=1;i<entities.size();i++){
			TransEntity transEntity=entities.get(i);
			list.add(new PropertyDto("数据源",transEntity.getDataSourceInfo().getName(),"端信息","text"));
			if(transEntity.getTableName()!=null&&!"".equals(transEntity.getTableName())){
				list.add(new PropertyDto("数据表",transEntity.getTableName(),"端信息","text"));
			}else{
				list.add(new PropertyDto("文件路径", transEntity.getLocation(),"端信息","text"));
			}
		}
		return list;
    }
    @RequestMapping("getTransTask")
    public @ResponseBody TransDto getTransTask(TaskEntity taskEntity) {  
    	    TaskEntity queryEntity = this.taskService.getById(taskEntity.getId());
            TransDto transDto  = new TransDto();
            TaskEntityDto taskEntityDto = new TaskEntityDto();
            taskEntityDto.setTaskId(queryEntity.getId());
            taskEntityDto.setTaskName(queryEntity.getName());
            taskEntityDto.setSynchroType(queryEntity.getSynchroType().toString());
            taskEntityDto.setTaskDescrption(queryEntity.getDescription());
            taskEntityDto.setRebuildTrigger(("".equals(queryEntity.getRebuildTrigger())?0:queryEntity.getRebuildTrigger())+"");
            taskEntityDto.setDeleteSourceData(("".equals(queryEntity.getDeleteSourceData())?0:queryEntity.getDeleteSourceData())+"");
            List<PipeLine> pipelinelist = pipeLineService.listPipelinesById(taskEntity.getId());
            //一个源,源一定是第二个加载
            TransEntity sourceEntity = pipelinelist.get(0).getSourceEntity();
            transDto.setTaskEntity(taskEntityDto);	//任务
            //源
            TransEntityDto transSourceEntity = new TransEntityDto();
            transSourceEntity.setDataSourceInfoDto(
            		new DataSourceInfoDto(pipelinelist.get(0).getSourceEntity().getDataSourceInfo().getId(),pipelinelist.get(0).getSourceEntity().getDataSourceInfo().getName()));
            if (pipelinelist.get(0).getSourceEntity().getType()== '0') {
            	transSourceEntity.setTableName(sourceEntity.getTableName());
            }else{
            	transSourceEntity.setLocation(sourceEntity.getLocation());
            }
            transSourceEntity.setType(pipelinelist.get(0).getSourceEntity().getType());
            
            PipeLineDto[] pipelines = new PipeLineDto[pipelinelist.size()];
            //多个端
            for (int i = 0;i<pipelinelist.size(); i++) {
            	TransEntityDto transEntityDto = new TransEntityDto();
            	TransEntity targetEntity =pipelinelist.get(i).getTargetEntity();
            	transEntityDto.setDataSourceInfoDto(new DataSourceInfoDto(targetEntity.getDataSourceInfo().getId(),targetEntity.getDataSourceInfo().getName()));
            	if(targetEntity.getType()=='0') {
            		transEntityDto.setTableName(targetEntity.getTableName());
            	}else {
            		transEntityDto.setLocation(targetEntity.getLocation());
            	}
            	transEntityDto.setType(targetEntity.getType());
//            }
//            //
//            for (int i = 0;i<transTargetDtos.length;i++) {
            	PipeLineDto pipeLineDto = new PipeLineDto();
            	pipeLineDto.setTransSourceDto(transSourceEntity);	//源实体
            	pipeLineDto.setTransTargetDto(transEntityDto);	//多个端
            	pipelines[i] = pipeLineDto;	//多个子任务
            }
            if (CommonConstants.DB_TYPE.equals(sourceEntity.getDataSourceInfo().getSource_type()) &&
                          CommonConstants.DB_TYPE.equals(pipelines[0].getTransTargetDto().getType()+"")){
                //数据库-数据库
                taskEntityDto.setTaskType(CommonConstants.DB_TYPE);
            } else if(CommonConstants.FILE_TYPE.equals(sourceEntity.getDataSourceInfo().getSource_type()) &&
                             CommonConstants.FILE_TYPE.equals(pipelines[0].getTransTargetDto().getType()+"")){
                //文件 →文件
                taskEntityDto.setTaskType(CommonConstants.FILE_TYPE);
            } else if(CommonConstants.FILE_TYPE.equals(sourceEntity.getDataSourceInfo().getSource_type()) &&
                             CommonConstants.DB_TYPE.equals(pipelines[0].getTransTargetDto().getType()+"")){
                //文件 →数据库
                taskEntityDto.setTaskType(2+"");
            } else {
                //数据库 →文件
                taskEntityDto.setTaskType(3+"");
            }
            transDto.setPipeLines(pipelines);
            return transDto;
      } 

    /**
     * 任务信息唯一查询
     * @param taskid 任务编号
     * @return 包含任务，其子任务以及交换实体的list
     */
    @RequestMapping("editTaskForShow")
    @ResponseBody
    public Map<String, Object> editTaskForShow(String taskId){
        FullTaskDto fullTaskDto = taskService.editForShow(Long.valueOf(taskId).longValue());
        JSONObject jsonObject = JSONObject.fromObject(fullTaskDto);
        return parserToMap(jsonObject.toString());  
   }
    
    //将Json string 转化为Map
    public static Map<String, Object> parserToMap(String jsonObject){
        Map<String, Object> map=new HashMap<String, Object>();  
        JSONObject json =JSONObject.fromObject(jsonObject);  
        Iterator keys=json.keys();  
        while(keys.hasNext()){  
            String key=(String) keys.next();  
            String value=json.get(key).toString();  
            if(value.startsWith("{")&&value.endsWith("}")){  
                map.put(key, parserToMap(value));  
            }else{  
                map.put(key, value);  
            }  
        }  
        return map;  
    }  
    
    
    /**
     * 加载所有数据源
     * @param dataSourceInfo 数据源对象
     * @return 数据源列表
     */
    @RequestMapping("listDataSource")
    @ResponseBody
    public List<DataSourceInfo> listDataSourceSelect
    (DataSourceInfo dataSourceInfo) {
        return this.dataSourceInfoService
                .listDataSourceInfoListByConditions(dataSourceInfo);
    }
    /**
     * 加载list.
     * @param dgm datagrid 对象
     * @return 是否加载成功
     * @throws Exception 加载失败
     */
    @RequestMapping("listDataSourceGrid")
    @ResponseBody
    public Map<String, Object> listDataSource(DataGridModel dgm) throws Exception {
        Page pager = new Page();
        // 当前页,对应分页中的pageNumber参数;
        int currentPage = (dgm.getPage() == 0) ? 1 : dgm.getPage();
        // 每页显示条数,对应EASYUI分页中的pageSize参数;
        int pageSize = dgm.getRows() == 0 ? CommonConstants.COL_PAGESIZE : dgm.getRows();
        // 每页的开始记录 第一页为1 第二页为number +1
        int index = (currentPage - 1) * pageSize;
        // 设置分页查询参数
        pager.setCurrentPage(currentPage);
        pager.setPageSize(pageSize);
        pager.setIndex(index);
        // 查询分页结果
        DataSourceInfo ds = new DataSourceInfo();
        ds.setDel_flag(Long.parseLong(CommonConstants.DB_TYPE));
        PageList<DataSourceInfo> pageSource = dataSourceInfoService
                .findPageDataSourceInfo(pager, ds);
        Map<String, Object> rootJson = new HashMap<String, Object>();
        rootJson.put(this.DGV_ROWS, pageSource.getList());
        rootJson.put(this.DGV_TOTAL, pageSource.getPage().getCount());

        return rootJson;
    }
    /**
     * 根据数据源编号加载下面所有的数据表.
     * @param dataSourceInfo   数据源对象
     * @return 数据表Dto
     * @throws Exception RMI调用异常
     */
    @RequestMapping("getSourceById")
    @ResponseBody
    public List<TreeDataDto> getSourceById(DataSourceInfo dataSourceInfo)
            throws Exception {
        DataSourceInfo sourceInfo =
                        this.dataSourceInfoService.
                        getNoEncryptById(dataSourceInfo.getId());
        List<TreeDataDto> list=new ArrayList <TreeDataDto>();
        try {
              list = this.taskService.listTable(sourceInfo);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    /**保存任务*/
    @RequestMapping("addTask")
    @ResponseBody
    public  Map<String, Object> addTask(HttpSession session, @Json FullTaskDto fullTaskDto) throws Exception {
    	SessionData sdata = (SessionData) session.getAttribute(CommonConstants.SESSION_DATA);
    	final Map<String, Object> rootJson = new HashMap<String, Object>();
    	Long id = null;
    	try {
    		id = taskService.addTask(sdata.getUserId(), fullTaskDto);
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	    rootJson.put("dealFlag", false);
    	    rootJson.put("id", id);
            rootJson.put("msg", "添加任务失败");
    	}
    	rootJson.put("dealFlag", true);
    	rootJson.put("id", id);
        rootJson.put("msg", "添加任务成功");
       
    	return rootJson;
    }
    
    /**
     * 判断源数据端是否包含主从表关系
     * @param session
     * @param fullTaskDto task复合对象
     * @return 返回是否包含完整的主从表关系   0 没有主从表关系 1有不健全的主从表关系 2主从表关系健全 
     * @throws Exception
     */
    @RequestMapping("forAssocication")
    @ResponseBody
    public  Map<String, Object> forAssocication(HttpSession session, @Json FullTaskDto fullTaskDto) throws Exception {
        final Map<String, Object> rootJson = new HashMap<String, Object>();
        try {
            List<String> tableNamesList = new ArrayList<String>();
            SourceDSInfo dsInfo = fullTaskDto.getSourceDSInfo();
            if(null != dsInfo){
                Long id = dsInfo.getSourceId();
                DataSourceInfo ds = dataSourceInfoService.getById(id);
                String tableName = dsInfo.getTableName();
                if(tableName.contains(COMMA)){
                    String tableNameArr[] = tableName.split(COMMA);
                    for(int i=0;i<tableNameArr.length;i++){
                        tableNamesList.add(tableNameArr[i]);
                    }
                    String flag = taskService.isAssociation(ds, tableNamesList);
                    rootJson.put("isAssociation", flag);
                } else{
                    rootJson.put("isAssociation", "0");
                }
            }
        } catch (Exception ex) {
        }
        return rootJson;
    }
}