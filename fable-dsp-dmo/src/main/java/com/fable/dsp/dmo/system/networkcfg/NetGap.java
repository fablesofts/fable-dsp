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
 * 网闸配置表
 * 
 * @author 吴浩
 * 
 */
@Entity
@Table(name = "NET_GAP_CONFIG")
public class NetGap extends AuditEntity implements Serializable {

	private static final long serialVersionUID = 8512128995707340637L;

	/**
	 * ID编号
	 */
	@TableGenerator(name = "netGapConfigGen", table = "SYS_ID_GEN", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "NET_GAP_CONFIG_ID", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "netGapConfigGen")
	protected Long id;

	@Column(name = "GAP_NO")
	protected Long gapNo;

	/**
     * 网闸名称
     */
    @Column(name = "GAP_NAME")
    protected String gapName;
	
	/**
	 * 网闸对内口设备名称
	 */
	@Column(name = "GAP_INNER_NAME")
	protected String inName;
	/**
	 * 网闸对外口设备名称
	 */
	@Column(name = "GAP_OUTER_NAME")
	protected String outName;
	/**
	 * 网闸对内口设备IP
	 */
	@Column(name = "INNER_IP")
	protected String inIp;
	/**
	 * 网闸对外口设备IP
	 */
	@Column(name = "OUTER_IP")
	protected String outIp;

	@Column(name = "FLOW")
	protected Long flow;

	@Column(name = "GAP_STATUS")
	protected String status;
	
	/**
     * 数据交换端口.
     */
    @Column(name="SERVICE_PORT")
    protected Long servicePort;
    
    /**
     * 应用服务代理对内端口.
     */
    @Column(name="INNER_PROXY_PORT")
    protected Long proxyInPort;
    
    /**
     * 应用服务代理对外端口.
     */
    @Column(name="OUTER_PROXY_PORT")
    protected Long proxyOutPort;
	
	@Column(name="EXT")
	protected String ext;

	@Column(name = "DEL_FLAG")
	protected String isDeleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGapNo() {
		return gapNo;
	}

	public void setGapNo(Long gapNo) {
		this.gapNo = gapNo;
	}



	public String getGapName() {
		return gapName;
	}

	public void setGapName(String gapName) {
		this.gapName = gapName;
	}

	public String getInName() {
		return inName;
	}

	public void setInName(String inName) {
		this.inName = inName;
	}

	public String getOutName() {
		return outName;
	}

	public void setOutName(String outName) {
		this.outName = outName;
	}

	public String getInIp() {
		return inIp;
	}

	public void setInIp(String inIp) {
		this.inIp = inIp;
	}

	public String getOutIp() {
		return outIp;
	}

	public void setOutIp(String outIp) {
		this.outIp = outIp;
	}

	public Long getFlow() {
		return flow;
	}

	public void setFlow(Long flow) {
		this.flow = flow;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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



}
