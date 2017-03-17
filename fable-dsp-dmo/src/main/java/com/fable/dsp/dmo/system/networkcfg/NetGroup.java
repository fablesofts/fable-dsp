package com.fable.dsp.dmo.system.networkcfg;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fable.dsp.core.entity.AuditEntity;

/**
 * 网络组信息表
 * 
 * @author 吴浩
 * 
 */

@Entity
@Table(name = "NET_GROUP_CONFIG")
public class NetGroup extends AuditEntity implements Serializable {

	private static final long serialVersionUID = -3034006008294493318L;

	/**
	 * ID编号
	 */
	@TableGenerator(name = "netGroupConfigGen", table = "SYS_ID_GEN", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "NET_GROUP_CONFIG_ID", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "netGroupConfigGen")
	private Long id;

	/**
	 * 组名
	 */
	@Column(name = "GROUP_NAME")
	private String name;
	/**
	 * 外交换ID
	 */
	@Column(name = "OUTER_ID")
	private Long outServerId;

	/**
	 * 内交换ID
	 */
	@Column(name = "INNER_ID")
	private Long inServerId;

	/**
	 * 网闸ID
	 */
	@Column(name = "GAP_ID")
	private Long gapId;

	@Column(name = "EXT")
	private String ext;

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

	public Long getOutServerId() {
		return outServerId;
	}

	public void setOutServerId(Long outServerId) {
		this.outServerId = outServerId;
	}

	public Long getInServerId() {
		return inServerId;
	}

	public void setInServerId(Long inServerId) {
		this.inServerId = inServerId;
	}

	public Long getGapId() {
		return gapId;
	}

	public void setGapId(Long gapId) {
		this.gapId = gapId;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

}
