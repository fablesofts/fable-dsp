package com.fable.dsp.common.util;

import com.fable.hamal.shuttle.common.utils.constant.DbType;

/**
 * 
 * @author 邱爽
 *
 */
public class DbTypeTransferUtil {
	/**
	 * 将数据库查出的dbtype字段转换为FullDbType
	 * @param dbtype
	 * @return
	 */
	public static String transferToFullType(String dbtype) {
	String fulltype="";
		if(DbType.MYSQL.equals(dbtype)) {
			fulltype="MYSQL";
		}else if(DbType.MSSQL.equals(dbtype)) {
			fulltype="SQLSERVER";
		}else if(DbType.ORACLE.equals(dbtype)) {
			fulltype="ORACLE";
		}else if(DbType.DAMENG6.equals(dbtype)) {
			fulltype="DM";
		}else if(DbType.DAMENG7.equals(dbtype)) {
			fulltype="DM7";
		}
		return fulltype;
	}
}
