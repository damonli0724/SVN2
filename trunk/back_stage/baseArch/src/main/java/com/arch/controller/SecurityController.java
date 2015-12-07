package com.arch.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arch.constants.Constants;
import com.arch.constants.Url;
import com.arch.constants.View;
import com.arch.service.other.UserService;
@Controller
public class SecurityController {
	
	protected final Logger  logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;

	/**
	 * 跳转首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value=Url.INDEX ,method=RequestMethod.GET)
	public String index(HttpServletRequest request) {
		
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute(Constants.SPRING_SECURITY_CONTEXT);
		// 登录名
		System.out.println("登陆名-->:"+ securityContextImpl.getAuthentication().getName());
		// 登录密码，未加密的
		System.out.println("登录密码，未加密的-->" + securityContextImpl.getAuthentication().getCredentials());
	
		WebAuthenticationDetails details = (WebAuthenticationDetails) securityContextImpl.getAuthentication().getDetails();
		// 获得访问地址
		System.out.println("RemoteAddress" + details.getRemoteAddress());
		// 获得sessionid
		System.out.println("SessionId-->" + details.getSessionId());
		// 获得当前用户所拥有的权限
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) securityContextImpl.getAuthentication().getAuthorities();

		for (GrantedAuthority grantedAuthority : authorities) {
			System.out.println("Authority-->" + grantedAuthority.getAuthority());
		}

		
		logger.info("=======================log日志登陆首页=============================");
		
		return View.INDEX;
	}

	/**
	 * 跳转登陆页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value=Url.LOGIN ,method =RequestMethod.GET)
	public String login(HttpServletRequest request) {

		return View.LOGIN;
	}

	@RequestMapping("getUsersCount")
	@ResponseBody
	public int getUsersCount() {

		System.out.println(userService.getUsersCount()
				+ "=========================================");
		return userService.getUsersCount();

	}

	@RequestMapping(value=Url.LOING_OUT , method=RequestMethod.GET)
	public String loginOut(HttpServletRequest request) {
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute(Constants.SPRING_SECURITY_CONTEXT);
		// 登录名
		String userName = securityContextImpl.getAuthentication().getName();
	
		
		logger.debug("====================="+userName+"==退出登陆====================");
		return View.LOGIN;
	};
	
	@RequestMapping(value = Url.TIME_OUT_URL ,method= RequestMethod.GET)
	public String timeOut() {
		System.out.println("=========超时跳转页面==============");
		return View.TIME_OUT_VIEW;
	};

	@RequestMapping(value=Url._403 )
	public String accessDenied(ModelMap map,HttpServletRequest request){
		
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute(Constants.SPRING_SECURITY_CONTEXT);
		if (securityContextImpl!=null){
			String userName = securityContextImpl.getAuthentication().getName();
			map.addAttribute("userName", userName);
		}
		
		return View._403;
	}
	
}
