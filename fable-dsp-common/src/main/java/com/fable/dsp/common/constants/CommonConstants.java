package com.fable.dsp.common.constants;

/**
 * 通用常量
 * 
 * @author 汪朝
 */
public final class CommonConstants {
	public static String ADMIN = "admin";
	/** SESSION 数据 */
	public static String SESSION_DATA = "SESSION_DATA";

	/** DES加密的KEY */
	public static String DES_KEY = "c3d4=%5g7";

	/**
	 * Controller 返回页面操作flag
	 */
	public static final String COL_DEALFLAG = "dealFlag";

	/**
	 * Controller 返回页面操作信息
	 */
	public static final String COL_MSG = "msg";

	/**
	 * session 中的用户名
	 */
	public static final String SESSION_USERNAME = "username";

	/**
	 * 分页大小
	 */
	public static final int COL_PAGESIZE = 10;
	/**
	 * ORACLE类型
	 */
	public static final String ORACLE_TYPE = "o";
	/**
	 * MYSQL类型
	 */
	public static final String MYSQL_TYPE = "m";
	/**
	 * SQLSERVER类型
	 */
	public static final String SQLSERVER_TYPE = "s";
	/**
	 * 达梦6类型
	 */
	public static final String DM_TYPE = "d";
	/**
	 * 达梦7类型
	 */
	public static final String DM_TYPE_7 = "e";
	/**
	 * FTP类型
	 */
	public static final String FTP_TYPE = "f";
	/**
	 * 文件类型
	 */
	public static final String FILE_TYPE = "1";
	/**
	 * 数据库类型
	 */
	public static final String DB_TYPE = "0";
	/**
	 * 数据源默认展示密码
	 */
	public static final String DS_PASSWORD = "c3d4=%";

	/**
	 * Windows
	 */
	public static final String OS_TYPE_WINDOWS = "Windows";

	/**
	 * Linux
	 */
	public static final String OS_TYPE_LINUX = "Linux";

	/**
	 * Linux 设置IP脚本路径
	 */
	public static final String IP_VAILD_PATH_LINUX_IPSET = "/root/networkmodify.sh ipset ";
	/**
	 * Linux 读取网卡脚本路径
	 */
	public static final String IP_VAILD_PATH_LINUX_READ = "sh /root/networkmodify.sh read";
	/**
	 * Windows 设置IP脚本路径
	 */
	public static final String IP_VAILD_PATH_WINDWOS = "C:\\networkmodify.bat ";

	public static final String INNERCARD_TO_IN = "innercard_i";

	public static final String INNERCARD_TO_OUT = "innercard_o";

	public static final String OUTERCARD_TO_IN = "outercard_i";

	public static final String OUTERCARD_TO_OUT = "outercard_o";

	public static final String GAPCARD_TO_IN = "gapcard_i";

	public static final String GAPCARD_TO_OUT = "gapcard_o";

	/**
	 * 设配对内口
	 */
	public static final String DEVICE_INNER = "inner";
	/**
	 * 设备对外口
	 */
	public static final String DEVICE_OUTER = "outer";

	/**
	 * 0
	 */
	public static final String ZERO = "0";

	/**
	 * 1
	 */
	public static final String ONE = "1";
	/**
	 * 分页常量
	 */
	public static final String DGV_ROWS = "rows";
	/**
	 * datagrid分页常量
	 */
	public static final String DGV_TOTAL = "total";
	/**
	 * 菜单最大级别常量
	 */
	public static final Integer MENU_MAX_LEVEL = 3;
	/**
	 * 菜单排位的默认值
	 */
	public static final Integer MENU_SORT_ORDER = 1;
	/** 删除标志，默认不删除 */
	public static final char DEL_FLAG_NO = '0';
	/** 删除标志，删除 */
	public static final char DEL_FLAG_YES = '1';
	
	/** 计算任务流速设定的最大时间间隔,设定为6秒 */
	public static final Integer MAX_TIME_INTERVAL = 6;
}
