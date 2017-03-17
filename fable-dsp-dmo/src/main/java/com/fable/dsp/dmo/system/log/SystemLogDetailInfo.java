package com.fable.dsp.dmo.system.log;

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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fable.dsp.core.entity.IdEntity;

/**
 * 系统日志详情实体类
 * 
 * @author liuz
 * 
 */
@Entity
@Table(name = "SYS_LOG_DETAIL")
public class SystemLogDetailInfo extends IdEntity {

	private static final long serialVersionUID = -6899121306009650639L;

	/**
	 * 系统日志详情ID
	 */
	@TableGenerator(name = "systemLogDetailGen", table = "SYS_ID_GEN", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "SYSTEMLOGDETAIL_ID", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "systemLogDetailGen")
	private Long id;
	/**
	 * 任务id
	 */
	@Column(name = "TASK_ID", updatable = false)
    private Long taskId;
	
	/**
	 * 
	 */
	@Column(name = "TASK_SERIAL", updatable = false)
    private String taskSerial;
	
	/**
	 * 操作类型（0抽取、1过滤、2转换、3加载）
	 */
	@Column(name = "OPERATION_TYPE", updatable = false)
	private Character operationType;
	/**
	 * 操作详情 抽取- -select语句 加载 - -（insert、update、delete语句）过滤 转换 策略
	 */
	@Column(name = "OPERATION_DETAIL", updatable = false)
	private String operationDetail;
	/**
	 * 操作结果（0成功、1失败）
	 */
	@Column(name = "OPERATION_RESULTS", updatable = false)
	private Character operationResults;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Character getOperationType() {
		return operationType;
	}

	public void setOperationType(Character operationType) {
		this.operationType = operationType;
	}

	public String getOperationDetail() {
		return operationDetail;
	}

	public void setOperationDetail(String operationDetail) {
		this.operationDetail = operationDetail;
	}

	public Character getOperationResults() {
		return operationResults;
	}

	public void setOperationResults(Character operationResults) {
		this.operationResults = operationResults;
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
