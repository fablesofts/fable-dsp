package com.fable.admin.common;

import java.io.IOException;
import java.util.List;

import com.fable.dsp.common.dto.network.NetCardDto;
import com.fable.dsp.common.util.NetcardDtoUtil;

public class TestNetcardUtil {
	public static void main(String[] args) throws IOException {
		List<NetCardDto> list = NetcardDtoUtil.getNetCard();
		for (NetCardDto dto : list) {
			System.out.println(dto.toString());
		}
	}
}
