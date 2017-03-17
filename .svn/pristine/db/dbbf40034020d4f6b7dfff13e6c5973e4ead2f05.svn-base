package com.fable.dsp.dmo.dataswitch;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fable.dsp.core.entity.AuditEntity;
import com.fable.dsp.dmo.datasource.DataSourceInfo;

/**
 * 交换实体表
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "DSP_TRANS_ENTITY")
public class TransEntity extends AuditEntity {
	private static final long serialVersionUID = -2237598595959481267L;
	/**
	 * 实体id
	 */
	@TableGenerator(name = "dspTransEntityGen", table = "SYS_ID_GEN", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "DSP_TRANS_ENTITY_ID", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "dspTransEntityGen")
	private Long id;

	/**
	 * 文件的路径
	 */
	@Column(name = "LOCATION")
	private String location;
	/**
	 * 关联的数据源属性
	 */
	@ManyToOne
	@JoinColumn(name = "DATA_SOURCE_ID", nullable = false)
	private DataSourceInfo dataSourceInfo;

	/**
	 * 表名
	 */
	@Column(name = "TABLE_NAME")
	private String tableName;
	/**
	 * 类型 0db 1ftp，默认值为'0'
	 */
	@Column(name = "TYPE", length = 1, nullable = false)
	private char type = '0';
	/**
	 * 交换实体描述
	 */
	@Column(name = "DESCRIPTION", length = 1000)
	private String description;

	/**
	 * 交换实体逻辑删除，默认值为'0'
	 */
	@Column(name = "DEL_FLAG", length = 1, nullable = false)
	private char del_flag = '0';

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DataSourceInfo getDataSourceInfo() {
		return dataSourceInfo;
	}

	public void setDataSourceInfo(DataSourceInfo dataSourceInfo) {
		this.dataSourceInfo = dataSourceInfo;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public char getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(char del_flag) {
		this.del_flag = del_flag;
	}

	public TransEntity(Long id) {
		super();
		this.id = id;
	}

	public TransEntity() {
		super();
	}

}
