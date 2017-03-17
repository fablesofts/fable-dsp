package com.fable.dsp.controller.network;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fable.dsp.common.dto.network.NetCardDto;
import com.fable.dsp.common.util.NetcardDtoUtil;
import com.fable.dsp.common.util.SysConfigUtil;
import com.fable.dsp.dmo.system.networkcfg.InServer;
import com.fable.dsp.dmo.system.networkcfg.NetGap;
import com.fable.dsp.dmo.system.networkcfg.OutServer;
import com.fable.dsp.service.system.config.intf.SysConfigInfoService;
import com.fable.dsp.service.system.network.intf.InServerService;
import com.fable.dsp.service.system.network.intf.NetGapService;
import com.fable.dsp.service.system.network.intf.OutServerService;

/**
 * 数据源信息
 * 
 * @author 马健原
 */
@Controller
@RequestMapping("/networkCfg/new")
public class NetworkController {
	@Autowired
	SysConfigInfoService sysConfigInfoService;

	private static final Logger logger = LoggerFactory
			.getLogger(NetworkController.class);

	@Autowired
	InServerService inServerService;

	@Autowired
	NetGapService netGapService;
	@Autowired
	OutServerService outServerService;

	@RequestMapping("/network_new")
	public String maintain() {
		return "networkcfg/network-config-new";
	}

	// 内服务器业务
	/**
	 * 从数据库中查询内服务器
	 * 
	 * @return
	 */
	@RequestMapping("getInServerFromDB")
	@ResponseBody
	public InServer getInServerFromDB() {
		return this.inServerService.getLastOne();
	}

	/**
	 * 从本机查询网卡信息列表
	 * 
	 * @return
	 */
	@RequestMapping("getCardListFromServer")
	@ResponseBody
	public Map<String, Object> getCardListFromServer() {
		Map<String, Object> rt = new HashMap<String, Object>();
		List<NetCardDto> cardList = null;
		try {
			cardList = NetcardDtoUtil.getNetCard();
			rt.put("list", cardList);
			logger.debug("网卡数：" + cardList.size());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return rt;
	}

	/**
	 * 跟新内服务器对内网卡信息
	 * 
	 * @param netCardDto
	 */
	@RequestMapping("updateInServiceCard")
	public void updateInServiceCard(NetCardDto netCardDto) {
		InServer inServer = this.inServerService.getLastOne();
		inServer.setServiceGateway(netCardDto.getGateway());
		inServer.setServiceMask(netCardDto.getMask());
		inServer.setServiceIp(netCardDto.getIp());
		inServer.setInCardName(netCardDto.getName());
		this.inServerService.update(inServer);
		try {
			NetcardDtoUtil.effictNetCard(netCardDto);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		SysConfigUtil.setConfigMap(null);
	}

	/**
	 * 跟新内服务器对网闸网卡信息
	 * 
	 * @param netCardDto
	 */
	@RequestMapping("updateInGapCard")
	public void updateInGapCard(NetCardDto netCardDto) {
		InServer inServer = this.inServerService.getLastOne();
		inServer.setGapGateway(netCardDto.getGateway());
		inServer.setGapMask(netCardDto.getMask());
		inServer.setGapIp(netCardDto.getIp());
		inServer.setOutCardName(netCardDto.getName());
		try {
			NetcardDtoUtil.effictNetCard(netCardDto);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		this.inServerService.update(inServer);
		SysConfigUtil.setConfigMap(null);
	}

	/**
	 * 更新内服务器的端口信息
	 */
	@RequestMapping("updateInPort")
	public void updateInPort(InServer inServer) {
		InServer temp = this.inServerService.getLastOne();
		temp.setHostName(inServer.getHostName());
		temp.setProxyInPort(inServer.getProxyInPort());
		temp.setProxyOutPort(inServer.getProxyOutPort());
		temp.setServiceInPort(inServer.getServiceInPort());
		temp.setServiceOutPort(inServer.getServiceOutPort());
		this.inServerService.update(temp);
		SysConfigUtil.setConfigMap(null);
	}

	// 外服务器业务
	/**
	 * 获取一个外服务器实体的信息
	 */
	@RequestMapping("getOutServerFromDB")
	@ResponseBody
	public OutServer getOutServerFromDB() {
		return this.outServerService.getLastOne();
	}

	/**
	 * 通过rmi获取网卡信息
	 * 
	 * @return
	 */
	@RequestMapping("getCardListFromRmi")
	@ResponseBody
	public List<NetCardDto> getCardListFromRmi() {
		List<NetCardDto> cardList = null;
		cardList = this.outServerService.getRmiNetCardList();
		logger.debug("rmi网卡数：" + cardList.size());
		return cardList;
	}

	/**
	 * 通过rmi更新网闸
	 * 
	 * @param cardDto
	 * @return
	 */
	@RequestMapping("updateOutGapCard")
	public void updateOutGapCard(NetCardDto cardDto) {
		this.outServerService.updateRmiCard(cardDto);
		OutServer outServer = outServerService.getLastOne();
		outServer.setGapGateway(cardDto.getGateway());
		outServer.setGapIp(cardDto.getIp());
		outServer.setGapMask(cardDto.getMask());
		outServer.setSelectedGapCardName(cardDto.getName());
		this.outServerService.update(outServer);
		SysConfigUtil.setConfigMap(null);
	}

	/**
	 * 通过rmi更新外网卡
	 * 
	 * @param cardDto
	 * @return
	 */
	@RequestMapping("updateOutServiceCard")
	public void updateOutServiceCard(NetCardDto cardDto) {
		this.outServerService.updateRmiCard(cardDto);
		OutServer outServer = outServerService.getLastOne();
		outServer.setOutGateway(cardDto.getGateway());
		outServer.setOutIp(cardDto.getIp());
		outServer.setOutMask(cardDto.getMask());
		outServer.setSelectedOutCardName(cardDto.getName());
		this.outServerService.update(outServer);
		SysConfigUtil.setConfigMap(null);
	}

	@RequestMapping("updateOutPort")
	public void updateOutPort(OutServer outServer) {
		OutServer server = this.outServerService.getLastOne();
		server.setNetName(outServer.getNetName());
		server.setProxyInPort(outServer.getProxyInPort());
		server.setProxyOutPort(outServer.getProxyOutPort());
		server.setServicePort(outServer.getServicePort());
		this.outServerService.update(server);
		SysConfigUtil.setConfigMap(null);
	}

	// 网闸
	@RequestMapping("getGapInfoFromDB")
	@ResponseBody
	public NetGap getGapInfoFromDB() {
		return this.netGapService.getLastOne();
	}

	@RequestMapping("updateNetGapIp")
	public void updateNetGapIp(NetGap netGap) {
		NetGap temp = this.netGapService.getLastOne();
		temp.setOutIp(netGap.getOutIp());
		temp.setOutName(netGap.getOutName());
		temp.setInIp(netGap.getInIp());
		temp.setInName(netGap.getInName());
		netGapService.update(temp);
		SysConfigUtil.setConfigMap(null);
	}

	@RequestMapping("updateNetGapPort")
	public void updateNetGapPort(NetGap netGap) {
		NetGap temp = this.netGapService.getLastOne();
		temp.setProxyInPort(netGap.getProxyInPort());
		temp.setProxyOutPort(netGap.getProxyOutPort());
		temp.setServicePort(netGap.getServicePort());
		netGapService.update(temp);
		SysConfigUtil.setConfigMap(null);
	}
}
