/**   
 * Copyright © 2017 公司名. All rights reserved.
 * 
 * @Title: xx.java 
 * @Prject: design-pattern
 * @Package: com.saltedfish.design.抽象工厂模式 
 * @Description: TODO
 * @author: mjy   
 * @date: 2017年1月9日 下午2:11:12 
 * @version: V1.0   
 */
package com.saltedfish.design.抽象工厂模式;

/** 
 * @ClassName: Provider 
 * @Description: TODO
 * @author: lkd
 * @date: 2017年1月9日 下午2:11:12  
 */
public interface Provider {  
    public Sender produce();  
}  