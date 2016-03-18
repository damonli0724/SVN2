package com.saltedfish.security.custom.session;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.util.Assert;

import com.saltedfish.dto.UserDTO;

/**
 * @description 自定义并发session控制策略
 * @author aokunsang
 * @date 2013-9-18
 */
public class CustomConcurrentSessionControlStrategy extends
		SessionFixationProtectionStrategy implements MessageSourceAware {

	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    private final SessionRegistry sessionRegistry;
    /*
     *若当前maximumSessions=1时，
     *   当设置为true，同一账户登录会抛出SessionAuthenticationException异常，异常信息为：Maximum sessions of {0} for this principal exceeded；
  	 *	   当设置为false，不会报错，则会让同一账户最先认证的session过期。
     */
    private boolean exceptionIfMaximumExceeded = false;
    private int maximumSessions = 1;    //允许的最大session数量

    public CustomConcurrentSessionControlStrategy(){
    	super.setAlwaysCreateSession(true);
    	this.sessionRegistry = new CustomSessionRegistryImpl();
    }
    
    /**
     * @param sessionRegistry the session registry which should be updated when the authenticated session is changed.
     */
    public CustomConcurrentSessionControlStrategy(SessionRegistry sessionRegistry) {
        Assert.notNull(sessionRegistry, "The sessionRegistry cannot be null");
        super.setAlwaysCreateSession(true);
        this.sessionRegistry = sessionRegistry;
    }

    /**
     * In addition to the steps from the superclass, the sessionRegistry will be updated with the new session information.
     */
    @Override
    public void onAuthentication(Authentication authentication, HttpServletRequest request,
            HttpServletResponse response) {
        checkAuthenticationAllowed(authentication, request);

        // Allow the parent to create a new session if necessary
        super.onAuthentication(authentication, request, response);
        
        //定义一个带IP的实体，并且注入session保存[下面会用到]
        CustomPrincipal pmcPrincipal = new CustomPrincipal(authentication);
        
        sessionRegistry.registerNewSession(request.getSession().getId(),pmcPrincipal);
    }

    private void checkAuthenticationAllowed(Authentication authentication, HttpServletRequest request)
            throws AuthenticationException {
    	
    	CustomPrincipal pmcPrincipal = new CustomPrincipal(authentication);
    	//获取当前用户一共多少个会话
        final List<SessionInformation> sessions = sessionRegistry.getAllSessions(pmcPrincipal, false);

        int sessionCount = sessions.size();
        int allowedSessions = getMaximumSessionsForThisUser(authentication);  //获取一个用户的最大会话数

        if (sessionCount < allowedSessions) {
            // They haven't got too many login sessions running at present
            return;
        }

        if (allowedSessions == -1) {
            // We permit unlimited logins
            return;
        }
        
        if (sessionCount == allowedSessions) {
            HttpSession session = request.getSession(false);

            if (session != null) {
                // Only permit it though if this request is associated with one of the already registered sessions
                for (SessionInformation si : sessions) {
                	 //如果当前用户的session和已经存在的会话id相同，则登录
                    if (si.getSessionId().equals(session.getId())) {  
                        return;
                    }
                }
            }
            // If the session is null, a new one will be created by the parent class, exceeding the allowed number
        }
        boolean sameip = false;
        //判断用户是否同一ip登录
        for(SessionInformation si : sessions){
        	if(pmcPrincipal.equals(si.getPrincipal()) && pmcPrincipal.equalsIp((CustomPrincipal)si.getPrincipal())){
        		sameip = true; break;
        	}
        }
        allowableSessionsExceeded(sessions, allowedSessions, sessionRegistry,sameip);
    }

    /**
     * Method intended for use by subclasses to override the maximum number of sessions that are permitted for
     * a particular authentication. The default implementation simply returns the <code>maximumSessions</code> value
     * for the bean.
     *
     * @param authentication to determine the maximum sessions for
     *
     * @return either -1 meaning unlimited, or a positive integer to limit (never zero)
     */
    protected int getMaximumSessionsForThisUser(Authentication authentication) {
        return maximumSessions;
    }

    /**
     * Allows subclasses to customise behaviour when too many sessions are detected.
     *
     * @param sessions either <code>null</code> or all unexpired sessions associated with the principal
     * @param allowableSessions the number of concurrent sessions the user is allowed to have
     * @param registry an instance of the <code>SessionRegistry</code> for subclass use
     *
     */
    protected void allowableSessionsExceeded(List<SessionInformation> sessions, int allowableSessions,
            SessionRegistry registry,boolean sameip) throws SessionAuthenticationException {
    	//如果IP不相同，则页面弹出错误窗口；如果IP相同，使最先登录的用户失效(防止用户非正常关闭浏览器导致登录不上)
    	if (!sameip && (exceptionIfMaximumExceeded || sessions == null)) {
            throw new SessionAuthenticationException(messages.getMessage("ConcurrentSessionControlStrategy.exceededAllowed",
                    new Object[] {Integer.valueOf(allowableSessions)},
                    "Maximum sessions of {0} for this principal exceeded"));
        }
        // Determine least recently used session, and mark it for invalidation
        SessionInformation leastRecentlyUsed = null;

        for (SessionInformation session : sessions) {
            if ((leastRecentlyUsed == null)
                    || session.getLastRequest().before(leastRecentlyUsed.getLastRequest())) {
                leastRecentlyUsed = session;
            }
        }
        //设置最先登录的账户失效[针对的都是同一账户]
        leastRecentlyUsed.expireNow();
    }

    /**
     * Sets the <tt>exceptionIfMaximumExceeded</tt> property, which determines whether the user should be prevented
     * from opening more sessions than allowed. If set to <tt>true</tt>, a <tt>SessionAuthenticationException</tt>
     * will be raised.
     *
     * @param exceptionIfMaximumExceeded defaults to <tt>false</tt>.
     */
    public void setExceptionIfMaximumExceeded(boolean exceptionIfMaximumExceeded) {
        this.exceptionIfMaximumExceeded = exceptionIfMaximumExceeded;
    }

    /**
     * Sets the <tt>maxSessions</tt> property. The default value is 1. Use -1 for unlimited sessions.
     *
     * @param maximumSessions the maximimum number of permitted sessions a user can have open simultaneously.
     */
    public void setMaximumSessions(int maximumSessions) {
        Assert.isTrue(maximumSessions != 0,
            "MaximumLogins must be either -1 to allow unlimited logins, or a positive integer to specify a maximum");
        this.maximumSessions = maximumSessions;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }

    @Override
    public final void setAlwaysCreateSession(boolean alwaysCreateSession) {
        if (!alwaysCreateSession) {
            throw new IllegalArgumentException("Cannot set alwaysCreateSession to false when concurrent session " +
                    "control is required");
        }
    }

}
