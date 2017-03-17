package com.fable.dsp.controller.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fable.dsp.common.constants.CommonConstants;
import com.fable.dsp.common.constants.SysConfigConstants;
import com.fable.dsp.common.util.SysConfigUtil;

/**
 * 登陆
 * 
 * @author liuz
 * 
 */
@Controller
@RequestMapping("/")
public class LoginController {

	/**
	 * 判断是否启用验证码
	 * 
	 * @throws Exception
	 */
	@RequestMapping("login")
	@ResponseBody
	public Map<String, Object> getCheckCodeStatus() throws Exception {
		Map<String, Object> rootJson = new HashMap<String, Object>();
		// 获取配置参数map
		Map<String, String> configMap = SysConfigUtil.getConfigMap();
		if (configMap != null) {
			// 获取验证码配置参数值，如果没有配置该参数，则默认显示验证码；
			// 如果配置了验证码参数，并且参数值为启用状态时，则显示验证码；
			// 其他情况则不显示验证码，在登陆界面不需要验证
			String value = configMap.get(SysConfigConstants.CHECK_CODE);
			if (value == null
					|| SysConfigConstants.CHECK_CODE_ON.equalsIgnoreCase(value
							.trim())) {
				rootJson.put(CommonConstants.COL_DEALFLAG, true);
			} else {
				rootJson.put(CommonConstants.COL_DEALFLAG, false);
			}
		}
		return rootJson;
	}

}
