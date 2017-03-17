package com.fable.dsp.dmo.dataswitch;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fable.dsp.core.entity.AuditEntity;
import com.fable.dsp.core.format.CustomDateSerializer;
import com.fable.dsp.core.format.CustomDateTimeSerializer;

@Entity
@Table(name = "JOB_RUN_INFO")
public class JobRunInfo extends AuditEntity {
	private static final long serialVersionUID = -2237598595959481266L;
	/**
	 * 子任务id
	 */
	@TableGenerator(name = "jobRunGen", table = "SYS_ID_GEN", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "JOB_RUN_ID", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "jobRunGen")
	private Long id;
	/**
	 * 关联的交换父任务
	 */

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "TASK_ID", nullable = false)
	private TaskEntity taskEntity;

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 批次
	 */
	@Column(name = "BATCH")
	private int batch;
	/**
	 * 加载流量
	 */
	@Column(name = "LOAD_RATE")
	private int loadRate;
	/**
	 * 查询流量
	 */
	@Column(name = "SELECT_RATE")
	private int selectRate;
	
	/**
	 * 关联的子任务对象
	 */
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "PIPELINE_ID", nullable = false)
	private PipeLine pipeLine;

	/**
	 * 开始时间
	 */
	
	@Column(name = "START_TIME")
	private Date startTime;

	/**
	 * 结束时间
	 */
	@Column(name = "FINISH_TIME")
	private Date finishTime;

	/**
	 * 当前状态，默认值为'0'
	 */
	@Column(name = "CURRENT_STATUS", length = 1, nullable = false)
	private char currentStatus;

	/**
	 * 日志路径
	 */
	@Column(name = "LOG_PATH", length = 255)
	private String logPath;

	public TaskEntity getTaskEntity() {
		return taskEntity;
	}

	public void setTaskEntity(TaskEntity taskEntity) {
		this.taskEntity = taskEntity;
	}

	public PipeLine getPipeLine() {
		return pipeLine;
	}

	public void setPipeLine(PipeLine pipeLine) {
		this.pipeLine = pipeLine;
	}
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public char getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(char currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getLogPath() {
		return logPath;
	}

	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}

	public int getBatch() {
		return batch;
	}

	public void setBatch(int batch) {
		this.batch = batch;
	}

	public int getLoadRate() {
		return loadRate;
	}

	public void setLoadRate(int loadRate) {
		this.loadRate = loadRate;
	}

	public int getSelectRate() {
		return selectRate;
	}

	public void setSelectRate(int selectRate) {
		this.selectRate = selectRate;
	}
	
}
