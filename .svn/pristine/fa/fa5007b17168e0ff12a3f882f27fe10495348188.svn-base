package com.fable.dsp.dmo.system.log;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fable.dsp.core.entity.IdEntity;

/**
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "ROW_LEVEL_LOG")
public class RowLevelLogInfo extends IdEntity {

	private static final long serialVersionUID = 2781018488312692351L;
	/**
	 * ID
	 */
	@TableGenerator(name = "rowLevelLogGen", table = "SYS_ID_GEN", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "ROWLEVELLOG_ID", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "rowLevelLogGen")
	private Long id;
	/**
	 * 操作数据
	 */
	@Column(name = "OPERATION_DATA", updatable = false)
	private String operationData;
	
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
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOperationData() {
		return operationData;
	}

	public void setOperationData(String operationData) {
		this.operationData = operationData;
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
}
