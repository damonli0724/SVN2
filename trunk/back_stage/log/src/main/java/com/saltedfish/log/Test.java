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

/** 
 * @ClassName: Test 
 * @Description: TODO
 * @author: lkd
 * @date: 2016年6月12日 上午9:38:11  
 */
import org.apache.log4j.Logger;
/**
 * 
 * @ClassName: Test 
 * @Description: TODO
 * @author: lkd
 * @date: 2016年6月12日 上午9:38:26
 */
public class Test {
	private static Logger logger = Logger.getLogger(Test.class);  

    public static void main(String[] args) {  
        // 记录debug级别的信息  
        logger.debug("This is debugfefew纷纷热舞fewfew是 message.");  
        // 记录info级别的信息  
        logger.info("This is info message.");  
        // 记录error级别的信息  
//    	while(true){
//    		logger.debug("This is error message.");  
//    	}
    	
    }  

}