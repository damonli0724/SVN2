/*******************************************************************************
 * Project   : portal-common
 * Class Name: com.yyq.cloud.portal.common.util.BeanCopierUtils
 * Created By: Jonathan 
 * Created on: 2014-8-7 上午9:41:10
 * Copyright © 2008-2014 NATIE All rights reserved.
 ******************************************************************************/
package com.arch.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cglib.beans.BeanCopier;

/**
 * <P>静态BeanCopier类</P>
 * @author Jonathan
 */
public class BeanCopierUtils {
	
	public static Map<String, BeanCopier> beanCopierMap = new HashMap<String, BeanCopier>();
	public static void copyProperties(Object source, Object target) {

		String beanKey = generateKey(source.getClass(), target.getClass());
		BeanCopier copier = null;
		if (!beanCopierMap.containsKey(beanKey)) {
			copier = BeanCopier.create(source.getClass(), target.getClass(), false);
			beanCopierMap.put(beanKey, copier);
		} else {
			copier = beanCopierMap.get(beanKey);
		}
		copier.copy(source, target, null);
	}

	private static String generateKey(Class<?> class1, Class<?> class2) {
		return class1.toString() + class2.toString();
	}
}
