package com.fable.dsp.security;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.TextEscapeUtils;

import com.fable.dsp.common.constants.CommonConstants;
import com.fable.dsp.common.constants.SysConfigConstants;
import com.fable.dsp.common.util.SysConfigUtil;
import com.fable.dsp.common.util.UtilMD5;
import com.fable.dsp.core.session.SessionData;
import com.fable.dsp.dao.system.user.intf.UserInfoDao;
import com.fable.dsp.dmo.system.user.RoleInfo;
import com.fable.dsp.dmo.system.user.UserInfo;
import com.fable.dsp.service.sysauth.FableLicenceFilter;

/**
 * 处理用户登录时的认证
 * 
 * @author 汪朝 2013-9-30
 * 
 */
public class FableUsernamePasswordAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(FableUsernamePasswordAuthenticationFilter.class);

	/** session中的最后的错误信息KEY */
	private static final String SPRING_SECURITY_LAST_EX_MSG_KEY = "SPRING_SECURITY_LAST_EX_MSG";
	/** 验证授权 */
//	private static final String STRING_SECURITY_SYSTEM_AUTH = "STRING_SECURITY_SYSTEM_AUTH";

	/** session中的最后的用户名KEY */
	private static final String SPRING_SECURITY_LAST_USERNAME_KEY = "SPRING_SECURITY_LAST_USERNAME";
	/**
	 * 对应页面中的验证码
	 */
	public static final String CHECK_CODE = "checkCode";
	/**
	 * 对应SESSION中的验证码
	 */
	public static final String CHECK_CODE_SESSION = "sessionCheckCode";
	/**
	 * 对应页面中的用户名
	 */
	public static final String USERNAME = "username";
	/**
	 * 对应页面中的密码
	 */
	public static final String PASSWORD = "password";

	@Resource
	private UserInfoDao userInfoDao;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		LOGGER.info("系统登录开始!");
		// 判断登录页面的提交方式是否为POST
		if (!request.getMethod().equals("POST")) {
			LOGGER.error("登录页面提交方式错误!method : " + request.getMethod());
			throw new AuthenticationServiceException(
					"Authentication method not supported: "
							+ request.getMethod());
		}

		// 判断是否启用了验证码，如果启用了就对验证码进行校验，否则不需要校验
		String value = SysConfigUtil.getConfigMap().get(SysConfigConstants.CHECK_CODE);
		if (value == null || SysConfigConstants.CHECK_CODE_ON.equalsIgnoreCase(value.trim())) {
			checkValidateCode(request);// 对验证码进行校验
		}

		// 取得用户名和密码
		String username = obtainUsername(request);
		String password = obtainPassword(request);

		// 对用户名和密码后台进行非空验证
		if (StringUtils.isNotEmpty(username)
				&& StringUtils.isNotEmpty(password)) {

			// 通过用户名查询用户信息
			LOGGER.info("登录的用户名 username:" + username);
			UserInfo user = this.userInfoDao.getUserInfoByUsername(username);
			if (user == null
					|| !user.getPassword().equals(UtilMD5.String2Md5(password))) {
				// 前端页面会接收到这些错误信息
				request.getSession().setAttribute(
						SPRING_SECURITY_LAST_EX_MSG_KEY, "用户名或密码不正确!");
				// 控制台中显示
				throw new AuthenticationServiceException(
						"The password is incorrect!");
			}
			// 构造未认证的UsernamePasswordAuthenticationToken,UsernamePasswordAuthenticationToken实现
			// Authentication
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
					username, user.getPassword());

			// 取得SESSION
			HttpSession session = request.getSession(false);
			// session是否为空
			if (session != null || getAllowSessionCreation()) {
				// 设置USERNAME，前端页面${sessionScope['SPRING_SECURITY_LAST_USERNAME']}取出信息
				request.getSession().setAttribute(
						SPRING_SECURITY_LAST_USERNAME_KEY,
						TextEscapeUtils.escapeEntities(username));
				// 在SESSION中添加用户信息
				SessionData sessionData = new SessionData();
				sessionData.setUserId(user.getId().toString());
				sessionData.setUserName(user.getUsername());
				sessionData.setRealName(user.getRealname());
				// 判断系统是否授权
				sessionData.setIsAuth(checkLicence(request));
				// 取得用户的角色集合
				List<RoleInfo> roles = user.getRoles();
				if (null != roles && !roles.isEmpty()) {
					// 设置角色信息
					sessionData.setRoleId(roles.get(0).getId().toString());
					sessionData.setRoleName(roles.get(0).getRoleName());
				}
				// 添加到SESSION中,名称为CommonConstants.SESSION_DATA
				request.getSession().setAttribute(CommonConstants.SESSION_DATA,
						sessionData);
				//获取配置的主题，并将主题名称绑定到session中
				//如果没有配置主题，则使用默认主题
				String theme = SysConfigUtil.getConfigMap().get(SysConfigConstants.THEME);
				if(theme == null || !SysConfigConstants.themes.contains(theme.trim())) {
					request.getSession().setAttribute(SysConfigConstants.THEME, "default");
				} else {
					request.getSession().setAttribute(SysConfigConstants.THEME, theme.trim());
				}
			}

			// Allow subclasses to set the "details" property 允许子类设置详细属性
			// 设置details，这里就是设置org.springframework.security.web.authentication.WebAuthenticationDetails实例到details中
			setDetails(request, authRequest);
			LOGGER.info("登录成功!");
			// 由认证管理器完成认证工作(通过AuthenticationManager:ProviderManager完成认证任务):运行UserDetailsService的loadUserByUsername
			// 再次封装Authentication
			return this.getAuthenticationManager().authenticate(authRequest);
		} else {
			LOGGER.warn("登录失败!用户名或密码不能为空!");
			request.getSession().setAttribute(SPRING_SECURITY_LAST_EX_MSG_KEY,
					"用户名或密码不能为空!");
			throw new AuthenticationServiceException("用户名或密码不能为空!");
		}
	}

	/**
	 * 验证码的校验
	 * 
	 * @param request
	 */
	protected void checkValidateCode(HttpServletRequest request) {
		LOGGER.info("校验验证码开始!");
		HttpSession session = request.getSession();
		// 获取SESSION中的验证码
		String sessionValidateCode = obtainSessionValidateCode(session);
		// 让上一次的验证码失效
		session.setAttribute(CHECK_CODE_SESSION, null);
		// 获取request中的验证码（页面填写的验证码）
		String validateCodeParameter = obtainValidateCodeParameter(request);
		// 对验证码进行验证
		if (StringUtils.isEmpty(validateCodeParameter)
				|| !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {
			request.getSession().setAttribute(SPRING_SECURITY_LAST_EX_MSG_KEY,
					"请填写正确的验证码!");
			LOGGER.warn("校验验证码结束!验证失败!验证验不正确");
			throw new AuthenticationServiceException("validateCode.notEquals");
		}
		LOGGER.info("校验验证码结束!验证成功!");
	}

	/**
	 * 获取表单提交的验证码
	 * 
	 * @param request
	 * @return
	 */
	private String obtainValidateCodeParameter(HttpServletRequest request) {
		Object obj = request.getParameter(CHECK_CODE);
		return null == obj ? "" : obj.toString();
	}

	/**
	 * 获取Session中保存的验证码
	 * 
	 * @param session
	 * @return
	 */
	protected String obtainSessionValidateCode(HttpSession session) {
		Object obj = session.getAttribute(CHECK_CODE_SESSION);
		return null == obj ? "" : obj.toString();
	}

	/**
	 * 获取表单提交的用户名
	 */
	@Override
	protected String obtainUsername(HttpServletRequest request) {
		Object obj = request.getParameter(USERNAME);
		return null == obj ? "" : StringUtils.trim(obj.toString());
	}

	/**
	 * 获取表单提交的密码
	 */
	@Override
	protected String obtainPassword(HttpServletRequest request) {
		Object obj = request.getParameter(PASSWORD);
		return null == obj ? "" : obj.toString();
	}

	/**
	 * 验证授权
	 */
	protected boolean checkLicence(HttpServletRequest request) {
		return FableLicenceFilter.checkLicence(request);
	}

}
