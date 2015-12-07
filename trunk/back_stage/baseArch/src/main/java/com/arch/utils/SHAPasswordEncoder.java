/*******************************************************************************
 * Project   : portal-common
 * Class Name: com.yyq.cloud.portal.common.service.SHA1PasswordEncoder
 * Created By: Jonathan 
 * Created on: 2014-8-29 下午4:58:57
 * Copyright © 2008-2014 NATIE All rights reserved.
 ******************************************************************************/
package com.arch.utils;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

/**
 * <P>TODO</P>
 * @author Jonathan
 */
public class SHAPasswordEncoder extends MessageDigestPasswordEncoder {

    public SHAPasswordEncoder() {
        super("SHA", true);
    }
}