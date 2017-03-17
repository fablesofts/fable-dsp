package com.fable.dsp.dto.dashboard;

public class JobRunInfoDto {
	/**
	 * 任务ID
	 */
	private Long taskId;
	/**
	 * 任务名
	 */
	private String taskName;
	/**
	 * 加载流量
	 */
	private Long loadRate;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Long getLoadRate() {
		return loadRate;
	}

	public void setLoadRate(Long loadRate) {
		this.loadRate = loadRate;
	}

}
