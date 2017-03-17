package com.fable.dsp.core.annotation;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fable.dsp.core.entity.IdEntity;

@Entity
@Table(name = "USER_OPERATION_LOG")
public class UserLogEntity extends IdEntity {

	private static final long serialVersionUID = 4660645273175855517L;

	@Id
	@TableGenerator(name = "userOperationLogGen", table = "SYS_ID_GEN", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "USER_OPERATION_LOG_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "userOperationLogGen")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OPERATION_TIME")
	private Date operationTime;

	@Column(name = "OPERATION_USER", length = 19)
	private String operationUser;

	@Lob
	@Column(name = "OPERATION_TYPE", length = 1)
	private String operationType;

	@Column(name = "OPERATION_DESCRIBE")
	private String operationDescribe;

	@Column(name = "TARGET_NAME", length = 128)
	private String targetName;

	@Column(name = "TARGET_ID", length = 19)
	private String targetId;

	@Lob
	@Column(name = "TARGET_DETAILL")
	private String targetDetaill;

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	public String getOperationUser() {
		return operationUser;
	}

	public void setOperationUser(String operationUser) {
		this.operationUser = operationUser;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getOperationDescribe() {
		return operationDescribe;
	}

	public void setOperationDescribe(String operationDescribe) {
		this.operationDescribe = operationDescribe;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getTargetDetaill() {
		return targetDetaill;
	}

	public void setTargetDetaill(String targetDetaill) {
		this.targetDetaill = targetDetaill;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
