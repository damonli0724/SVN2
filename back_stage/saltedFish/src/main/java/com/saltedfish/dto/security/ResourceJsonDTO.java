/*******************************************************************************
 * Project   : baseArch
 * Class Name: com.saltedfish.dto.security.ResourceJsonDTO
 * Created By: mjy 
 * Created on: 2016年1月11日 上午11:44:59
 * Copyright © 2013-2014 YYQ All rights reserved.
 ******************************************************************************/
package com.saltedfish.dto.security;

/**
 * <P>加载Ztree返回的json数据</P>
 * @author lkd
 */
public class ResourceJsonDTO {
	private Integer id;  // id
	private Integer pId;  // 父Id
	private String name;  // 名称
	private Boolean open = true; // 是否展开
	private Boolean checked = false; // 是否选中
	private Integer type;  // 0菜单 1 子菜单 2 按钮

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public ResourceJsonDTO(Integer id, Integer pId, String name, Boolean open, Boolean checked) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.open = open;
		this.checked = checked;
	}

	/**
	 * <p>Method for constructor</p>
	 */
	public ResourceJsonDTO() {
	}

	@Override
	public String toString() {
		return "ResourceJsonDTO [id=" + id + ", pId=" + pId + ", name=" + name + ", open=" + open + ", checked=" + checked + "]";
	}

}
