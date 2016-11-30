/*******************************************************************************
 * Project   : portal-common
 * Class Name: com.yyq.cloud.portal.common.constant.FileTypeEnum
 * Created By: Jonathan 
 * Created on: 2014年12月9日 下午11:12:46
 * Copyright © 2013-2014 YYQ All rights reserved.
 ******************************************************************************/
package com.saltedfish.constants;

/**
 * <P>上传文件枚举</P>
 * @author lkd
 */
public enum FileTypeEnum {
	TEST(1, "测试");

	private Integer value;
	private String name;

	private FileTypeEnum(Integer value, String name) {
		this.value = value;
		this.name = name;
	}

	public static FileTypeEnum getFileType(Integer value) {
		for (FileTypeEnum e : FileTypeEnum.values()) {
			if (e.value.equals(value)) {
				return e;
			}
		}
		return null;
	}

	public Integer getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
}
