package com.fable.dsp.dmo.dataswitch;

import com.fable.dsp.core.entity.AuditEntity;

public class FtpBaseUser extends AuditEntity{

	private static final long serialVersionUID = -3637935112074552101L;

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		
	}
	 private String name = null;

	 private String password = null;

	 private int maxIdleTimeSec = 0; // no limit

	 private String homeDir = null;

	 private boolean isEnabled = true;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMaxIdleTimeSec() {
		return maxIdleTimeSec;
	}

	public void setMaxIdleTimeSec(int maxIdleTimeSec) {
		this.maxIdleTimeSec = maxIdleTimeSec;
	}

	public String getHomeDir() {
		return homeDir;
	}

	public void setHomeDir(String homeDir) {
		this.homeDir = homeDir;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	 
}
