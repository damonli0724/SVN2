/*******************************************************************************
 * Project   : saltedFish
 * Class Name: com.saltedfish.dto.security.ResourceQueryByParentId
 * Created By: LKD 
 * Created on: 2016年1月22日 下午2:22:55
 * Copyright © 2013-2014 saltedFish All rights reserved.
 ******************************************************************************/
package com.saltedfish.dto.security;

/**
 * <P>根据parentId查询子资源</P>
 * @author LKD
 */
public class ResourceQueryByParentIdDTO {
	private Integer resId;  // 资源Id
	private String resName; // 资源名称
	private Integer parentId; // 父Id

	public Integer getResId() {
		return resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "ResourceQueryByParentIdDTO [resId=" + resId + ", resName=" + resName + ", parentId=" + parentId + "]";
	}

}
