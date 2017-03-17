package com.fable.dsp.dto.dataswitch;

import java.io.Serializable;


import java.util.List;

import com.fable.dsp.dmo.dataswitch.PipeLine;
import com.fable.dsp.dmo.dataswitch.TaskEntity;
import com.fable.dsp.dmo.dataswitch.TransEntity;

/**
 * 包含所有交换任务信息的大对象
 * @author Administrator
 *
 */
public class TransDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8413789703243508998L;
//	//任务
//	private Long taskId;
//	private String taskName;
//	private String taskDescription;
//	private Integer taskNeedResource;
//	//子任务
//	private Long pipeId;
//	private TaskEntity pipeTaskEntity;
//	private DataSourceInfo pipeSourceId;
//	private DataSourceInfo pipeTargetId;
//	private String pipeDescription;
//	//交换实体
//	private Long entityId;
//	private String entityTransProtocol;
//	private String entityLocation;
//	private String entityTableName;
//	private char entityType; 
//	private String entityDescription;
	private PipeLine pipeLine;
	private TaskEntity taskEntity;
	private TransEntity transSourceEntity;
	//不止有一个端，所以用集合
	private TransEntity[]transTargetEntities;
	private String typeA;
	private String typeB;
	
	public String getTypeA() {
		return typeA;
	}
	public void setTypeA(String typeA) {
		this.typeA = typeA;
	}
	public String getTypeB() {
		return typeB;
	}
	public void setTypeB(String typeB) {
		this.typeB = typeB;
	}
	public PipeLine getPipeLine() {
		return pipeLine;
	}
	public void setPipeLine(PipeLine pipeLine) {
		this.pipeLine = pipeLine;
	}
	public TaskEntity getTaskEntity() {
		return taskEntity;
	}
	public void setTaskEntity(TaskEntity taskEntity) {
		this.taskEntity = taskEntity;
	}
	public TransEntity getTransSourceEntity() {
		return transSourceEntity;
	}
	public void setTransSourceEntity(TransEntity transSourceEntity) {
		this.transSourceEntity = transSourceEntity;
	}
	public TransEntity[] getTransTargetEntities() {
		return transTargetEntities;
	}
	public void setTransTargetEntities(TransEntity[] transTargetEntities) {
		this.transTargetEntities = transTargetEntities;
	}
	
	
	
}
