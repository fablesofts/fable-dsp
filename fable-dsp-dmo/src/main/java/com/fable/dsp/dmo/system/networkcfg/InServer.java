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

/**
 * 内交换网络配置表.
 * 
 * @author 吴浩
 * 
 */

@Entity
@Table(name = "NET_INNER_CONFIG")
public class InServer extends AuditEntity implements Serializable {

	private static final long serialVersionUID = -7575836489229045046L;

	/**
	 * ID编号
	 */
	@TableGenerator(name = "netInnerConfigGen", table = "SYS_ID_GEN", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "NET_INNER_CONFIG_ID", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "netInnerConfigGen")
	private Long id;

	@Column(name = "HOSTNAME")
	private String hostName;
	//对内服务
	/**
	 * 内交换对内网网卡IP
	 */
	@Column(name = "TO_INNERNET_IP")
	private String serviceIp;
	/**
	 * 内交换对内网网卡MASK
	 */
	@Column(name = "TO_INNERNET_MASK")
	private String serviceMask;

	/**
	 * 内交换对内网网卡GATEWAY
	 */
	@Column(name = "TO_INNERNET_GATEWAY")
	private String serviceGateway;

	/**
	 * 当前保存的内网网卡名称
	 */
	@Column(name = "NOW_INN_CARD")
	private String inCardName;

	//对网闸
	/**
	 * 内交换对网闸网卡IP
	 */
	@Column(name = "TO_GAP_IP")
	private String gapIp;
	/**
	 * 内交换对网闸网卡MASK
	 */
	@Column(name = "TO_GAP_MASK")
	private String gapMask;
	/**
	 * 内交换对网闸网卡GATEWAY
	 */
	@Column(name = "TO_GAP_GATEWAY")
	private String gapGateway;
	
	/**
	 * 当前保存的对网闸网卡名称
	 */
	@Column(name = "NOW_OUT_CARD")
	private String outCardName;

	/**
	 * 数据交换对内端口.
	 */
	@Column(name = "SERVICE_IN_PORT")
	private Long serviceInPort;

	/**
	 * 数据交换对外端口.
	 */
	@Column(name = "SERVICE_OUT_PORT")
	private Long serviceOutPort;

	/**
	 * 应用服务代理对内端口.
	 */
	@Column(name = "INNER_PROXY_PORT")
	private Long proxyInPort;

	/**
	 * 应用服务代理对外端口.
	 */
	@Column(name = "OUTER_PROXY_PORT")
	private Long proxyOutPort;

	@Column(name = "STATUS")
	private Long status;

	@Column(name = "TYPE")
	private Long type;

	@Column(name = "EXT")
	private String ext;

	public String getExt() {
		return ext;
	}

	public String getGapGateway() {
		return gapGateway;
	}

	public String getGapIp() {
		return gapIp;
	}

	public String getGapMask() {
		return gapMask;
	}

	public String getHostName() {
		return hostName;
	}

	public Long getId() {
		return id;
	}

	public String getInCardName() {
		return inCardName;
	}

	public String getOutCardName() {
		return outCardName;
	}

	public Long getProxyInPort() {
		return proxyInPort;
	}

	public Long getProxyOutPort() {
		return proxyOutPort;
	}

	public String getServiceGateway() {
		return serviceGateway;
	}

	public Long getServiceInPort() {
		return serviceInPort;
	}

	public String getServiceIp() {
		return serviceIp;
	}

	public String getServiceMask() {
		return serviceMask;
	}

	public Long getServiceOutPort() {
		return serviceOutPort;
	}

	public Long getStatus() {
		return status;
	}

	public Long getType() {
		return type;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public void setGapGateway(String gapGateway) {
		this.gapGateway = gapGateway;
	}

	public void setGapIp(String gapIp) {
		this.gapIp = gapIp;
	}

	public void setGapMask(String gapMask) {
		this.gapMask = gapMask;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setInCardName(String inCardName) {
		this.inCardName = inCardName;
	}

	public void setOutCardName(String outCardName) {
		this.outCardName = outCardName;
	}
	public void setProxyInPort(Long proxyInPort) {
		this.proxyInPort = proxyInPort;
	}
	public void setProxyOutPort(Long proxyOutPort) {
		this.proxyOutPort = proxyOutPort;
	}

	public void setServiceGateway(String serviceGateway) {
		this.serviceGateway = serviceGateway;
	}

	public void setServiceInPort(Long serviceInPort) {
		this.serviceInPort = serviceInPort;
	}

	public void setServiceIp(String serviceIp) {
		this.serviceIp = serviceIp;
	}

	public void setServiceMask(String serviceMask) {
		this.serviceMask = serviceMask;
	}

	public void setServiceOutPort(Long serviceOutPort) {
		this.serviceOutPort = serviceOutPort;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public void setType(Long type) {
		this.type = type;
	}

}
