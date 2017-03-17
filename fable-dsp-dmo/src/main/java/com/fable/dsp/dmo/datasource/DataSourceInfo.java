package com.fable.dsp.dmo.datasource;

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
 * 数据源信息表
 * 
 * @author 吴浩
 * 
 */

@Entity
@Table(name = "DSP_DATA_SOURCE")
public class DataSourceInfo extends AuditEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -803806302481401033L;

	/**
	 * ID编号
	 */
	@Id
	@TableGenerator(name = "dspDataSourceGen", table = "SYS_ID_GEN", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "DSP_DATA_SOURCE_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "dspDataSourceGen")
	protected Long id;
	
	/**
	 * 数据资源名称
	 */
	@Column(name="NAME")
	protected String name;
	
	/**
	 * 数据资源IP地址 
	 */
	@Column(name="SERVER_IP")
	protected String server_ip;
	
	/**
	 * 数据资源端口
	 */
	@Column(name="PORT")
	protected Long port;
	
	/**
	 * 数据资源用户名
	 */
	@Column(name="USERNAME")
	protected String username;
	
	/**
	 * 数据资源密码
	 */
	@Column(name="PASSWORD")
	protected String password;
	
	/**
	 * 数据资源逻辑删除
	 */
	@Column(name="DEL_FLAG")
	protected Long del_flag;
	
	/**
	 * 数据资源描述
	 */
	@Column(name="DESCRIPTION")
	protected String description;
	
	/**
	 * FTP资源路径
	 */
	@Column(name="ROOT_PATH")
	protected String root_path;
	
	/**
	 * 数据资源类型
	 */
	@Column(name="SOURCE_TYPE")
	protected String source_type;
	
	/**
	 * 数据库类型
	 */
	@Column(name="DB_TYPE")
	protected String db_type;
	
	/**
	 * 数据库名称
	 */
	@Column(name="DB_NAME")
	protected String db_name;
	
	/**
	 * 数据库连接字符串
	 */
	@Column(name="CONNECT_URL")
	protected String connect_url;
	
	/**
	 * 设备区分，是内外还是外网IP
	 */
	@Column(name="DEVICE_TYPE")
	protected String device_type;
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Long getPort() {
		return port;
	}

	public void setPort(Long port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(Long del_flag) {
		this.del_flag = del_flag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServer_ip() {
		return server_ip;
	}

	public void setServer_ip(String server_ip) {
		this.server_ip = server_ip;
	}

	public String getRoot_path() {
		return root_path;
	}

	public void setRoot_path(String root_path) {
		this.root_path = root_path;
	}

	public String getSource_type() {
		return source_type;
	}

	public void setSource_type(String source_type) {
		this.source_type = source_type;
	}

	public String getDb_type() {
		return db_type;
	}

	public void setDb_type(String db_type) {
		this.db_type = db_type;
	}

	public String getConnect_url() {
		return connect_url;
	}

	public void setConnect_url(String connect_url) {
		this.connect_url = connect_url;
	}

	public String getDb_name() {
		return db_name;
	}

	public void setDb_name(String db_name) {
		this.db_name = db_name;
	}


	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}


	public DataSourceInfo(Long id) {
		super();
		this.id = id;
	}

	public DataSourceInfo() {
		super();
	}
	
}
