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

@Entity
@Table(name = "NET_OUTER_CONFIG")
public class OutServer extends AuditEntity implements Serializable {

	private static final long serialVersionUID = 6876861026595154798L;

	/**
	 * ID编号
	 */
	@TableGenerator(name = "netOuterConfigGen", table = "SYS_ID_GEN", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "NET_OUTER_CONFIG_ID", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "netOuterConfigGen")
	protected Long id;

	@Column(name = "NET_NAME")
	protected String netName;
	/**
	 * 外交换对网闸IP
	 */
	@Column(name = "TO_GAP_IP")
	protected String gapIp;
	/**
	 * 外交换对网闸MASK
	 */
	@Column(name = "TO_GAP_MASK")
	protected String gapMask;
	/**
	 * 外交换对网闸GATEWAY
	 */
	@Column(name = "TO_GAP_GATEWAY")
	protected String gapGateway;
	/**
	 * 内交换对外网IP
	 */
	@Column(name = "TO_OUTERNET_IP")
	protected String outIp;
	/**
	 * 内交换对外网MASK
	 */
	@Column(name = "TO_OUTERNET_MASK")
	protected String outMask;
	/**
	 * 内交换对外网GATEWAY
	 */
	@Column(name = "TO_OUTERNET_GATEWAY")
	protected String outGateway;
	/**
	 * 保存对网闸的网卡名称
	 */
	@Column(name = "NOW_GAP_CARD")
	protected String selectedGapCardName;
	/**
	 * 保存对外网的网卡名称
	 */
	@Column(name = "NOW_OUTER_CARD")
	protected String selectedOutCardName;

	/**
	 * 数据交换端口.
	 */
	@Column(name = "SERVICE_PORT")
	protected Long servicePort;

	/**
	 * 应用服务代理对内端口.
	 */
	@Column(name = "INNER_PROXY_PORT")
	protected Long proxyInPort;

	/**
	 * 应用服务代理对外端口.
	 */
	@Column(name = "OUTER_PROXY_PORT")
	protected Long proxyOutPort;

	@Column(name = "STATUS")
	protected String status;

	@Column(name = "TYPE")
	protected String type;

	@Column(name = "EXT")
	protected String ext;

	@Column(name = "DEL_FLAG")
	protected String isDeleted;

	@Override
	public Long getId() {
		return id;
	}

	public String getNetName() {
		return netName;
	}

	public void setNetName(String netName) {
		this.netName = netName;
	}

	public String getGapIp() {
		return gapIp;
	}

	public void setGapIp(String gapIp) {
		this.gapIp = gapIp;
	}

	public String getGapMask() {
		return gapMask;
	}

	public void setGapMask(String gapMask) {
		this.gapMask = gapMask;
	}

	public String getGapGateway() {
		return gapGateway;
	}

	public void setGapGateway(String gapGateway) {
		this.gapGateway = gapGateway;
	}

	public String getOutIp() {
		return outIp;
	}

	public void setOutIp(String outIp) {
		this.outIp = outIp;
	}

	public String getOutMask() {
		return outMask;
	}

	public void setOutMask(String outMask) {
		this.outMask = outMask;
	}

	public String getOutGateway() {
		return outGateway;
	}

	public void setOutGateway(String outGateway) {
		this.outGateway = outGateway;
	}

	public String getSelectedGapCardName() {
		return selectedGapCardName;
	}

	public void setSelectedGapCardName(String selectedGapCardName) {
		this.selectedGapCardName = selectedGapCardName;
	}

	public String getSelectedOutCardName() {
		return selectedOutCardName;
	}

	public void setSelectedOutCardName(String selectedOutCardName) {
		this.selectedOutCardName = selectedOutCardName;
	}

	public Long getServicePort() {
		return servicePort;
	}

	public void setServicePort(Long servicePort) {
		this.servicePort = servicePort;
	}

	public Long getProxyInPort() {
		return proxyInPort;
	}

	public void setProxyInPort(Long proxyInPort) {
		this.proxyInPort = proxyInPort;
	}

	public Long getProxyOutPort() {
		return proxyOutPort;
	}

	public void setProxyOutPort(Long proxyOutPort) {
		this.proxyOutPort = proxyOutPort;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
