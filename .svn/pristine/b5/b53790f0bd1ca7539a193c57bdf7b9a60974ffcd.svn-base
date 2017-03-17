package com.fable.dsp.dmo.system.config;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fable.dsp.core.entity.AuditEntity;

/**
 * 
 * @author liuz
 * 
 */
@Entity
@Table(name = "SYS_CONFIG_INFO")
public class SysConfigInfo extends AuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7607388042995101629L;

	/** ID */
	@Id
	@TableGenerator(name = "sysConfigGen", table = "SYS_ID_GEN", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "SYS_CONFIG_ID", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sysConfigGen")
	private Long id;
	/** 参数名(key) */
	@Column(name = "SYS_CONFIG_NAME", length = 64, nullable = false, unique = true)
	private String sysConfigName;
	/** 参数值(value) */
	@Column(name = "SYS_CONFIG_VALUE", length = 32, nullable = false)
	private String sysConfigValue;
	/** 描述 */
	@Column(name = "DESCRIPTION", length = 512)
	private String description;
	/** 参数类别 */
	@Column(name = "CATEGORY", length = 32)
	private String category;
	/** 删除标志,1表示删除,0表示不删除 */
	@Column(name = "DEL_FLAG", length = 1)
	private char delFlag = '0';
	/** 参数别名 */
	@Column(name = "NAME", length = 32)
	private String name;
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getSysConfigName() {
		return sysConfigName;
	}

	public void setSysConfigName(String sysConfigName) {
		this.sysConfigName = sysConfigName;
	}

	public String getSysConfigValue() {
		return sysConfigValue;
	}

	public void setSysConfigValue(String sysConfigValue) {
		this.sysConfigValue = sysConfigValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public char getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(char delFlag) {
		this.delFlag = delFlag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
