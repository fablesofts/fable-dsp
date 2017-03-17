package com.fable.dsp.common.dto.dataswitch;


/**
 * 交换实体表
 * @author 邱爽
 *
 */

public class TransEntityDto {
	
	/**
	 * 实体id
	 */
	
	private Long id;


	/**
	 * 文件的路径
	 */
	
	
	private String location;
	/**
	 * 关联的数据源属性
	 */
	
	private DataSourceInfoDto dataSourceInfoDto; 

	/**
	 * 表名
	 */

	private String tableName;
	/**
	 * 类型 0db 1ftp
	 */
	
	private char type; 
	
	public Long getId() {
		return this.id;
	}

	
	public void setId(Long id) {
		this.id=id;
	}

	public DataSourceInfoDto getDataSourceInfoDto() {
		return dataSourceInfoDto;
	}


	public void setDataSourceInfoDto(DataSourceInfoDto dataSourceInfoDto) {
		this.dataSourceInfoDto = dataSourceInfoDto;
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
	public TransEntityDto(Long id) {
		super();
		this.id = id;
	}


	public TransEntityDto() {
		super();
	}
	
}
