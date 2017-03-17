package com.fable.dsp.dmo.dataswitch;

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

import com.fable.dsp.core.entity.AuditEntity;

/**
 * 交换子任务
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "DSP_PIPELINE")
public class PipeLine extends AuditEntity {
	private static final long serialVersionUID = -2237598595959481266L;
	/**
	 * 子任务id
	 */
	@TableGenerator(name = "dspPipelineGen", table = "SYS_ID_GEN", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "DSP_PIPELINE_ID", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "dspPipelineGen")
	private Long id;
	/**
	 * 关联的交换父任务
	 */

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "TASK_ID", nullable = false)
	private TaskEntity taskEntity;
	/**
	 * 交换实体源
	 */
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "SOURCE_ID", nullable = false)
	private TransEntity sourceEntity;
	/**
	 * 交换实体目标
	 */

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "TARGET_ID", nullable = false)
	private TransEntity targetEntity;

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	/**
	 * 交换实体描述
	 */
	@Column(name = "DESCRIPTION", length = 1000)
	private String description;
	/**
	 * 子任务逻辑删除，默认值为'0'
	 */
	@Column(name = "DEL_FLAG", length = 1, nullable = false)
	private char del_flag = '0';

	public char getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(char del_flag) {
		this.del_flag = del_flag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskEntity getTaskEntity() {
		return taskEntity;
	}

	public void setTaskEntity(TaskEntity taskEntity) {
		this.taskEntity = taskEntity;
	}

	public TransEntity getSourceEntity() {
		return sourceEntity;
	}

	public void setSourceEntity(TransEntity sourceEntity) {
		this.sourceEntity = sourceEntity;
	}

	public TransEntity getTargetEntity() {
		return targetEntity;
	}

	public void setTargetEntity(TransEntity targetEntity) {
		this.targetEntity = targetEntity;
	}

	public PipeLine(TransEntity sourceEntity, TransEntity targetEntity) {
		super();
		this.sourceEntity = sourceEntity;
		this.targetEntity = targetEntity;
	}

	public PipeLine() {
		super();
	}

}
