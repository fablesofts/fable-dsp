package com.fable.dsp.dmo.dataswitch;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fable.dsp.core.entity.AuditEntity;

/**
 * 交换任务
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "DSP_TASK")
public class TaskEntity extends AuditEntity {
	private static final long serialVersionUID = -2237598595959481267L;
	/**
	 * 任务id
	 */
	@TableGenerator(name = "dspTaskGen", table = "SYS_ID_GEN", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "DSP_TASK_ID", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "dspTaskGen")
	private Long id;
	/**
	 * 任务名称
	 */
	@Column(name = "NAME", unique = true, nullable = false, length = 32)
	private String name;
	/**
	 * 目标资源：默认是1
	 */
	@Column(name = "NEED_RESOURCE")
	private Integer needResource;

	/**
	 * 交换实体描述
	 */
	@Column(name = "DESCRIPTION", length = 1000)
	private String description;
	/**
	 * 删除标示，默认值为'0'
	 */
	@Column(name = "DEL_FLAG", length = 1, nullable = false)
	private Character delFlag = '0';
	/**
	 * 时间戳同步时，是否删除源数据(0:不删，1删除)，默认值为'0'
	 */
	@Column(name = "DELETE_SOURCEDATA", length = 1)
	private Character deleteSourceData;
	/**
	 * 触发器同步时，是否重建触发器（0：不重建，1重建），默认值为'0'
	 */
	@Column(name = "REBUILD_TRIGGER", length = 1)
	private Character rebuildTrigger;
	/** 默认值为'0' */
	@Column(name = "SYNCHROTYPE", length = 1)
	private Character synchroType;
	/**是否存在依赖关系*/
	@Column(name = "ASSOCIATION", length = 1)
	private Character association;
	/**是否关联行级日志*/
    @Column(name = "ROWLOG_FLAG", length = 1)
    private Character rowLevelLog;
    /**是否输出syslog*/
    @Column(name = "SYSLOG_FLAG", length = 1)
    private Character sysLogPrint;
    
    @Column(name = "SOURCE_FILE_DEAL")
    private Character sourceFile;
    
    @Column(name = "TARGET_FILE_DEAL")
    private Character targetFile;
    
	public Character getSynchroType() {
		return synchroType;
	}

	public void setSynchroType(Character synchroType) {
		this.synchroType = synchroType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNeedResource() {
		return needResource;
	}

	public void setNeedResource(Integer needResource) {
		this.needResource = needResource;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Character getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Character delFlag) {
		this.delFlag = delFlag;
	}

	public Character getDeleteSourceData() {
		return deleteSourceData;
	}

	public void setDeleteSourceData(Character deleteSourceData) {
		this.deleteSourceData = deleteSourceData;
	}

	public Character getRebuildTrigger() {
		return rebuildTrigger;
	}

	public void setRebuildTrigger(Character rebuildTrigger) {
		this.rebuildTrigger = rebuildTrigger;
	}

	public TaskEntity(Long id) {
		super();
		this.id = id;
	}

	public TaskEntity() {
		super();
	}
    
    public Character getAssociation() {
        return association;
    }

    public void setAssociation(Character association) {
        this.association = association;
    }
    
    public Character getRowLevelLog() {
        return rowLevelLog;
    }
    
    public void setRowLevelLog(Character rowLevelLog) {
        this.rowLevelLog = rowLevelLog;
    }

    public Character getSysLogPrint() {
        return sysLogPrint;
    }

    public void setSysLogPrint(Character sysLogPrint) {
        this.sysLogPrint = sysLogPrint;
    }
    
    public Character getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(Character sourceFile) {
        this.sourceFile = sourceFile;
    }
    
    public Character getTargetFile() {
        return targetFile;
    }
    
    public void setTargetFile(Character targetFile) {
        this.targetFile = targetFile;
    }
    
    
}
