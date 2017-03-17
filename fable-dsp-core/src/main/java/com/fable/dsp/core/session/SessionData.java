package com.fable.dsp.core.session;

import java.io.Serializable;

/**
 * 
 * 保存SESSION中的一些基本数据
 * 
 * @author 汪朝
 * 
 */
public class SessionData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1316982917541623865L;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 角色名
	 */
	private String roleName;

	/**
	 * 角色ID
	 */
	private String roleId;

	/**
	 * 真实姓名
	 */
	private String realName;
	/** 系统是否授权 */
	private Boolean isAuth;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Boolean getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(Boolean isAuth) {
		this.isAuth = isAuth;
	}

}
