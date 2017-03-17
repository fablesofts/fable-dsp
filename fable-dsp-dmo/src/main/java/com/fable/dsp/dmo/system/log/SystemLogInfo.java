package com.fable.dsp.dmo.system.log;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fable.dsp.core.entity.IdEntity;
import com.fable.dsp.core.format.CustomDateTimeSerializer;
import com.fable.dsp.dmo.dataswitch.TaskEntity;

/**
 * 系统日志实体类
 * 
 * @author liuz
 * 
 */
@Entity
@Table(name = "SYS_LOG")
public class SystemLogInfo extends IdEntity {

	private static final long serialVersionUID = 3802572746637588718L;

	/**
	 * 系统日志ID
	 */
	@TableGenerator(name = "systemLogGen", table = "SYS_ID_GEN", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "SYSTEMLOG_ID", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "systemLogGen")
	private Long id;
	/**
	 * 任务id
	 */
	@Column(name = "TASK_ID", updatable = false)
    private Long taskId;
	/**
	 * 任务运行批次
	 */
	@Column(name = "TASK_SERIAL", updatable = false)
    private String taskSerial;
	
	/**
     * 添加属性 任务名称
     */
	@Column(name = "TASKNAME", updatable = false)
    private String taskName;
	
	/**
	 * 抽取条数
	 */
	@Column(name = "SELECT_COUNT", updatable = false)
	private Long selectCount;
	/**
	 * 抽取流量
	 */
	@Column(name = "SELECT_DATA", updatable = false)
	private Long selectData;
	/**
	 * 加载条数
	 */
	@Column(name = "LOAD_COUNT", updatable = false)
	private Long loadCount;
	/**
	 * 加载流量
	 */
	@Column(name = "LOAD_DATA", updatable = false)
	private Long loadData;
	/**
	 * 操作结果 （0：成功 ，1：失败）
	 */
	@Column(name = "OPERATION_RESULTS", updatable = false)
	private Character operationResults;
	/**
	 * 开始时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_TIME", updatable = false)
	private Date startTime;
	/**
	 * 结束时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_TIME", updatable = false)
	private Date endTime;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSelectCount() {
		return selectCount;
	}

	public void setSelectCount(Long selectCount) {
		this.selectCount = selectCount;
	}

	public Long getSelectData() {
		return selectData;
	}

	public void setSelectData(Long selectData) {
		this.selectData = selectData;
	}

	public Long getLoadCount() {
		return loadCount;
	}

	public void setLoadCount(Long loadCount) {
		this.loadCount = loadCount;
	}

	public Long getLoadData() {
		return loadData;
	}

	public void setLoadData(Long loadData) {
		this.loadData = loadData;
	}

	public Character getOperationResults() {
		return operationResults;
	}

	public void setOperationResults(Character operationResults) {
		this.operationResults = operationResults;
	}

	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskSerial() {
        return taskSerial;
    }

    public void setTaskSerial(String taskSerial) {
        this.taskSerial = taskSerial;
    }

    public String getTaskName() {
        return taskName;
    }
    
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
