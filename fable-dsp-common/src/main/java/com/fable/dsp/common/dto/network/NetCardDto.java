package com.fable.dsp.common.dto.network;

import java.io.Serializable;

public abstract class NetCardDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8489830554042609864L;
	private String name;
	private String ip;
	private String mask;
	private String gateway;

	public NetCardDto() {
		super();
	}

	public abstract String toLinuxShell();

	public NetCardDto(String ip, String name, String mask, String gateway) {
		super();
		this.ip = ip;
		this.name = name;
		this.mask = mask;
		this.gateway = gateway;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	@Override
	public String toString() {
		return "NetCardDto [name=" + name + ", ip=" + ip + ", mask=" + mask
				+ ", gateway=" + gateway + "]";
	}

}
