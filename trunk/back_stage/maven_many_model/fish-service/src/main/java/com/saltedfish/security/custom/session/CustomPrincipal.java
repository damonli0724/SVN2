package com.saltedfish.security.custom.session;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.Assert;
import com.saltedfish.dto.UserDTO;

/**
 * @description 保存用户信息(主要是IP地址)
 * @author aokunsang
 * @date 2013-9-18
 */
public class CustomPrincipal {

	private String username;  //用户名
	private String ip;   //ip地址
	
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

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        return "SmartPrincipal:{username=" + username + ",ip=" + ip + "}";
    }
}
