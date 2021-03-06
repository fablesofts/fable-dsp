package com.fable.dsp.service.system.network.impl;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fable.hamal.shuttle.communication.client.Communication;
import com.fable.outer.rmi.event.network.NetworkCardShowEvent;

public class Clinet {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hamal.shuttle.communication-client.xml");
		Communication communication = (Communication)context.getBean("client");
//		List countList = (List)communication.call("rmi://192.168.0.25:1099/eventService", new NetworkCardCountEvent());
		Map netMap = (Map)communication.call("rmi://192.168.0.25:1099/eventService", new NetworkCardShowEvent());
		
//		System.out.println(countList.size());
//		System.out.println(netMap.size());
//		for (int i = 0; i < countList.size(); i++) {
//			System.out.println(countList.get(i));
//		}
		
		Set<Map.Entry<String, String[]>> set = netMap.entrySet();
        for (Iterator<Map.Entry<String, String[]>> it = set.iterator(); it.hasNext();) {
            Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) it.next();
            System.out.println(entry.getKey() + "--->" + entry.getValue()[0]+","+entry.getValue()[1]+","+entry.getValue()[2]);
            try {
				String returnStr = new String(entry.getKey().getBytes("ISO-8859-1"), "GBK");
				System.out.println("***"+returnStr+"***");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
}
