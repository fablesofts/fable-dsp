package com.fable.dsp.dmo.dataswitch;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fable.dsp.core.entity.AuditEntity;

@Entity
@Table(name = "DSP_ETL_STRATEGY")
public class EtlStrategy extends AuditEntity {
	private static final long serialVersionUID = -2237598595959481266L;
	/**
	 * 编号
	 */
	@TableGenerator(name = "dspEtlStrategyGen", table = "SYS_ID_GEN", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "DSP_ETL_STRATEGY_ID", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "dspEtlStrategyGen")
	private Long id;
	/**
	 * 源表
	 */
	@Column(name = "FROM_TABLE", length = 255, nullable = false)
	private String fromTable;
	/**
	 * 目标表
	 */
	@Column(name = "TO_TABLE", length = 255, nullable = false)
	private String toTable;
	/**
	 * 过滤策略
	 */
	@Lob
	@Column(name = "CONTENT_FILTER", nullable = false)
	private String contentFilter;
	/**
	 * 描述信息
	 */
	@Column(name = "DESCRIPTION", length = 1000)
	private String description;

	/**
	 * 策略标识，默认值为'0'
	 */
	@Column(name = "DEL_FLAG", length = 1, nullable = false)
	private char delFlag = '0';
	/**
	 * pipeline属性
	 */
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "PIPELINE_ID", nullable = false)
	private PipeLine pipeLine;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromTable() {
		return fromTable;
	}

	public void setFromTable(String fromTable) {
		this.fromTable = fromTable;
	}

	public String getToTable() {
		return toTable;
	}

	public void setToTable(String toTable) {
		this.toTable = toTable;
	}

	public String getContentFilter() {
		return contentFilter;
	}

	public void setContentFilter(String contentFilter) {
		this.contentFilter = contentFilter;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public char getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(char delFlag) {
		this.delFlag = delFlag;
	}

	public PipeLine getPipeLine() {
		return pipeLine;
	}

	public void setPipeLine(PipeLine pipeLine) {
		this.pipeLine = pipeLine;
	}

}
