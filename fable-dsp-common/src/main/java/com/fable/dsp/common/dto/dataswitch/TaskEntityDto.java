package com.fable.dsp.common.dto.dataswitch;

import java.io.Serializable;


/**
 * 交换任务
 * @author Administrator
 *
 */

public class TaskEntityDto implements Serializable{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 7940794569518146277L;
		/**
		 * 任务id
		 */
	
		private Long taskId;
		/**
		 * 任务名称
		 */
		
		private String taskName;
		/**
		 * 任务类型
		 */
		private String taskType;
		/**
		 * 目标资源
		 */
		
		private Integer needResource;
		
		/**
		 * 交换实体描述
		 */
		
		private String taskDescrption;
		/**
		 * 同步类型
		 */
		private String synchroType;
		/**
		 * 重建触发器
		 */
		private String rebuildTrigger;
		/**
		 * 删除源
		 */
		private String deleteSourceData;
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
		public String getTaskType() {
			return taskType;
		}
		public void setTaskType(String taskType) {
			this.taskType = taskType;
		}
		public Integer getNeedResource() {
			return needResource;
		}
		public void setNeedResource(Integer needResource) {
			this.needResource = needResource;
		}
		public String getTaskDescrption() {
			return taskDescrption;
		}
		public void setTaskDescrption(String taskDescrption) {
			this.taskDescrption = taskDescrption;
		}
		public String getSynchroType() {
			return synchroType;
		}
		public void setSynchroType(String synchroType) {
			this.synchroType = synchroType;
		}
		public String getRebuildTrigger() {
			return rebuildTrigger;
		}
		public void setRebuildTrigger(String rebuildTrigger) {
			this.rebuildTrigger = rebuildTrigger;
		}
		public String getDeleteSourceData() {
			return deleteSourceData;
		}
		public void setDeleteSourceData(String deleteSourceData) {
			this.deleteSourceData = deleteSourceData;
		}
		
		
	
}
