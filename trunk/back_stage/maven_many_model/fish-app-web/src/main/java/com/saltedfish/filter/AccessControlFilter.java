package com.saltedfish.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.saltedfish.constants.Constants;





/**
  * 
  * <P> 权限控制过滤器  </P>
  * 
  * 当登录的时候,会返回一个sessionId 和一个accountId 放到本地
  * 这个过滤器 会给一个列表(白名单:如果在这个列表里面则不需要 传sessionId 和accountId 即不需要权限验证,一般是不重要的信息)
  * 验证规则:登录时，将sessionId作为key，accountId 作为value，根据传入的sessionId 找value 看看是否有，是否相等 有通过验证，没有不能继续执行下一步
  * 
  * @author lkd
  * @return
 */
public class AccessControlFilter implements Filter {

	protected final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	private String[] unSecuredUrls;
	private final String URL_SPLIT_PATTERN = "[, ;\r\n]";
	private final PathMatcher pathMatcher = new AntPathMatcher();
	RedisTemplate<String, Serializable> redisTemplate;
	private ValueOperations<String, Serializable> valueOps;
	private String PARAM_NAME_SESSION_ID = "sessionId";
	private String PARAM_NAME_ACCOUNT_ID = "accountId";
    
	private long SESSION_TIMEOUT = 7L;
    
	
	public void destroy() {
		 
	}

	 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String sessionId = null;
		String accountId = null;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		MultipartHttpServletRequest multipartRequest = null;
		if (ServletFileUpload.isMultipartContent(httpRequest)) {
			CommonsMultipartResolver resolver = new CommonsMultipartResolver();
			multipartRequest = resolver.resolveMultipart(httpRequest);
			sessionId = multipartRequest.getParameter(PARAM_NAME_SESSION_ID);
			accountId = multipartRequest.getParameter(PARAM_NAME_ACCOUNT_ID);
			logger.debug(sessionId);
			logger.debug(accountId);
			logger.debug("MultipartHttpServletRequest请求参数sessionId:{}, accountId:{}", sessionId, accountId);
		} else {
			sessionId = httpRequest.getParameter(PARAM_NAME_SESSION_ID);
			accountId = httpRequest.getParameter(PARAM_NAME_ACCOUNT_ID);
			logger.debug("请求参数sessionId:{}, accountId:{}", sessionId, accountId);
		}
		String targetUrl = httpRequest.getServletPath();
		Boolean isSecured = Boolean.TRUE;
		for (String url : unSecuredUrls) {
			if (pathMatcher.match(url, targetUrl)) {
				isSecured = Boolean.FALSE;
				break;
			}
		}
		if (isSecured) {
			logger.info("请求URL{}未在许可列表中", targetUrl);
			if (StringUtils.isBlank(sessionId) || StringUtils.isBlank(accountId)) {
				logger.error("非法请求:sessionId或accountId为空");
				PrintWriter printWrite = response.getWriter();
				printWrite.print("Your request is illegal, the current request need certification.");
				return;
			}
			
			String storedAccountId = (String) valueOps.get(sessionId);
			if (StringUtils.isBlank(storedAccountId) || !storedAccountId.equals(accountId)) {
				logger.error("根据sessionId:{}未取到匹配的accountId:{}", sessionId,accountId);
				PrintWriter printWrite = response.getWriter();
				printWrite.print("Your request is illegal, the current request need certification.");
				return;
			}
			String isFreeze = (String) valueOps.get(Constants.ACCOUNT_FREEZE+storedAccountId);
			if(Constants.ACCOUNT_FREEZE_Y.equals(isFreeze)){
				logger.error("该账户已经冻结，不允许操作");
				PrintWriter printWrite = response.getWriter();
				printWrite.print("Your account is freezed");
				return;
			}
			redisTemplate.expire(sessionId, SESSION_TIMEOUT, TimeUnit.DAYS);
		}
		if (multipartRequest != null) {
			chain.doFilter(multipartRequest, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	 
	public void init(FilterConfig fConfig) throws ServletException {
		String urlStr = fConfig.getInitParameter("urls");
		unSecuredUrls = strToArray(urlStr);
		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(fConfig.getServletContext());
		redisTemplate = context.getBean("redisTemplate", RedisTemplate.class);
		valueOps = redisTemplate.opsForValue();
	}
    
	private String[] strToArray(String urlStr) {
		if (urlStr == null) {
			return new String[0];
		}
		String[] urlArray = urlStr.split(URL_SPLIT_PATTERN);

		List<String> urlList = new ArrayList<String>();

		for (String url : urlArray) {
			url = url.trim();
			if (url.length() == 0) {
				continue;
			}
			urlList.add(url);
		}
		unSecuredUrls = new String[urlList.size()];
		return urlList.toArray(unSecuredUrls);
	}
	
	
}
