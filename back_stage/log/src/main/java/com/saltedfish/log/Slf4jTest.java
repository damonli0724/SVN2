/**   
 * Copyright © 2016 公司名. All rights reserved.
 * 
 * @Title: Test.java 
 * @Prject: log
 * @Package: com.saltedfish.log 
 * @Description: TODO
 * @author: mjy   
 * @date: 2016年6月12日 上午9:38:11 
 * @version: V1.0   
 */
package com.saltedfish.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.Driver;

/**
 * 
 * @ClassName: Test 
 * @Description: TODO
 * @author: lkd
 * @date: 2016年6月12日 上午9:38:26
 */
public class Slf4jTest {
	private static Logger logger = LoggerFactory.getLogger(Slf4jTest.class);  

    public static void main(String[] args) {  
    	
    	
    	logger.debug("开始进入测试...");
    	try {
			int a = 1/0; 
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
        
    }   

}