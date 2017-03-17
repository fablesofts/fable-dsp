package com.fable.dsp.common.dto.dataswitch;

import java.io.Serializable;

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
	private TaskEntityDto taskEntity;	//任务
	private PipeLineDto[] pipeLines;	//子任务
	private TransEntityDto transSourceEntity;//交换源实体
	//不止有一个端，所以用集合
	private TransEntityDto[]transTargetEntities;	//交换端实体
	public TaskEntityDto getTaskEntity() {
		return taskEntity;
	}
	public void setTaskEntity(TaskEntityDto taskEntity) {
		this.taskEntity = taskEntity;
	}
	public PipeLineDto[] getPipeLines() {
		return pipeLines;
	}
	public void setPipeLines(PipeLineDto[] pipeLines) {
		this.pipeLines = pipeLines;
	}
	public TransEntityDto getTransSourceEntity() {
		return transSourceEntity;
	}
	public void setTransSourceEntity(TransEntityDto transSourceEntity) {
		this.transSourceEntity = transSourceEntity;
	}
	public TransEntityDto[] getTransTargetEntities() {
		return transTargetEntities;
	}
	public void setTransTargetEntities(TransEntityDto[] transTargetEntities) {
		this.transTargetEntities = transTargetEntities;
	}
	
}
