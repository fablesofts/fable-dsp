package com.fable.dsp.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.fable.dsp.common.dto.network.NetCardDto;

public class NetcardDtoUtil {

	/**
	 * majy 获取本地网卡信息
	 * 
	 * @return
	 * @throws IOException
	 */
	public static List<NetCardDto> getNetCard() throws IOException {
		List<NetCardDto> stack = new LinkedList<NetCardDto>();
		String sysName = System.getProperty("os.name").toLowerCase();
		if (sysName.contains("windows")) {
			Process process = Runtime.getRuntime().exec("ipconfig");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					process.getInputStream(), Charset.forName("GBK")));
			String temp = null;
			Queue<String> queue = new LinkedList<String>();
			while ((temp = reader.readLine()) != null) {
				queue.add(temp);
				if (queue.size() > 5) {
					queue.poll();
				}
				if (temp.contains("IPv4")) {
					NetCardDto card = new NetCardDto() {
						private static final long serialVersionUID = -813739877060956749L;

						@Override
						public String toLinuxShell() {
							return null;
						}
					};
					card.setIp(temp.split(":")[1].trim());
					card.setMask(reader.readLine().split(":")[1].trim());
					card.setGateway(reader.readLine().split(":")[1].trim());
					String[] nameStrs = queue.poll().split(":")[0].trim()
							.split(" ");
					card.setName(nameStrs[1] + "-" + nameStrs[2]);
					stack.add(card);
				}
			}
			queue.clear();
			reader.close();
		} else if (sysName.contains("linux")) {
			Process process = Runtime.getRuntime().exec("ifconfig");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String temp = null;
			while ((temp = reader.readLine()) != null) {
				NetCardDto dto = new NetCardDto() {
					private static final long serialVersionUID = 1L;

					@Override
					public String toLinuxShell() {
						return null;
					}
				};
				if (temp.contains("mtu") || temp.contains("link")) {
					dto.setName(temp.split(":")[0].trim());
					if (!dto.getName().toLowerCase().equals("lo")) {
						temp = reader.readLine();
						if (temp.contains("inet") && !temp.contains("inet6")) {
							temp = temp.replace(" ", "").toLowerCase()
									.replace("inet", "");
							if (!temp.contains("netmask")) {
								dto.setIp(temp);
							} else {
								String[] subTemps = temp.split("netmask");
								dto.setIp(subTemps[0]);
								dto.setMask(subTemps[1].split("b")[0]);
							}
							stack.add(dto);
						}
					}
				}
			}
		}
		return stack;
	}

	/**
	 * @author majy 使网卡信息生效
	 */
	public static void effictNetCard(NetCardDto cardDto) throws Exception {
		String cmd = null;
		String sysName = System.getProperty("os.name").toLowerCase();
		if (sysName.contains("windows")) {
			String name = cardDto.getName().replace("-", " ");
			cmd = "netsh interface ip set address \"" + name + "\" stat "
					+ cardDto.getIp() + " " + cardDto.getMask() + " "
					+ cardDto.getGateway() + " 1";

			Runtime.getRuntime().exec(cmd);

		} else if (sysName.contains("linux")) {
			File file = new File("/etc/sysconfig/network-scripts/ifcfg-"
					+ cardDto.getName());
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(cardDto.toLinuxShell());
			writer.flush();
			writer.close();
			cmd = "service network restart";
			Runtime.getRuntime().exec(cmd);
		} else {
			throw new Exception("操作系统不匹配");
		}
	}

}