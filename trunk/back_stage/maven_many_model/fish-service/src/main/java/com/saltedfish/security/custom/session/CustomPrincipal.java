package com.saltedfish.security.custom.session;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.Assert;

import com.saltedfish.dto.UserDTO;
import com.saltedfish.entity.security.SysUsers;

/**
 * @description 保存用户信息(主要是IP地址和用户登录信息)
 * @author lkd
 * @date 2013-9-18
 */
public class CustomPrincipal {

	private String username;  //用户名
	private String ip;   //ip地址
	private Object principal; //认证过的对象
    public CustomPrincipal(String username, String ip) {
        Assert.notNull(username,"username cannot be null (violation of interface contract)");
        Assert.notNull(ip,"username cannot be null (violation of interface contract)");
        this.username = username;
        this.ip = ip;
    }

    public CustomPrincipal(Authentication authentication) {
        Assert.notNull(authentication,"authentication cannot be null (violation of interface contract)");

        String username = null;
        if (authentication.getPrincipal() instanceof UserDTO) {
            username = ((UserDTO) authentication.getPrincipal()).getName();   //获取登录名
            principal = authentication.getPrincipal();
        } else {
            username = (String) authentication.getPrincipal();
        }

        String ip = ((WebAuthenticationDetails) authentication.getDetails()).getRemoteAddress();
        this.username = username;
        this.ip = ip;
    }

    public boolean equalsIp(CustomPrincipal smartPrincipal) {
        return this.ip.equals(smartPrincipal.ip);
    }

    @Override
    public boolean equals(Object obj) {
    	if(obj.getClass() == CustomPrincipal.class){
    		return true;
    	}
        if (obj instanceof CustomPrincipal) {
        	CustomPrincipal smartPrincipal = (CustomPrincipal) obj;
            return username.equals(smartPrincipal.username);
        }
        return false;
    }
    
	/**
	 * @return the principal
	 */
	public Object getPrincipal() {
		return principal;
	}

	/**
	 * @param principal the principal to set
	 */
	public void setPrincipal(Object principal) {
		this.principal = principal;
	}

	@Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        return "SmartPrincipal:{username=" + username + ",ip=" + ip + "}";
    }

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
    
    
}
