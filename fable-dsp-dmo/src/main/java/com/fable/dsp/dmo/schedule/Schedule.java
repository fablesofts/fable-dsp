package com.fable.dsp.dmo.schedule;

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
import com.fable.dsp.dmo.dataswitch.TaskEntity;

/**
 * 调度作息
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "DSP_SCHEDULE_CONFIG")
public class Schedule extends AuditEntity {
	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	/**
	 * ID编号
	 */
	@Id
	@TableGenerator(name = "dspScheduleConfigGen", table = "SYS_ID_GEN", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "DSP_SCHEDULE_CONFIG_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "dspScheduleConfigGen")
	protected Long id;

	/**
	 * 数据资源逻辑删除
	 */
	@Column(name = "DEL_FLAG")
	protected Long del_flag;

	/**
	 * 数据资源描述
	 */
	@Column(name = "DESCRIPTION", length = 512)
	protected String description;
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "TASK_ID", nullable = false)
	private TaskEntity taskEntity;
	@Column(name = "CRONTAB_EXPRESSION", length = 255)
	private String cronTabExpression;
	@Column(name = "XML_EXPRESSION", length = 3000)
	private String xmlExpression;

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

	public Long getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(Long del_flag) {
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

	public String getCronTabExpression() {
		return cronTabExpression;
	}

	public void setCronTabExpression(String cronTabExpression) {
		this.cronTabExpression = cronTabExpression;
	}

	public String getXmlExpression() {
		return xmlExpression;
	}

	public void setXmlExpression(String xmlExpression) {
		this.xmlExpression = xmlExpression;
	}

}
