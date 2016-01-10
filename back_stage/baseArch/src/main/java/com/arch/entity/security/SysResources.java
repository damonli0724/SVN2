package com.arch.entity.security;

import java.util.HashSet;
import java.util.Set;


public class SysResources implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private Integer parentId; // 父类Id
	private String parentName;
	private String resKey;// 这个权限KEY是唯一的，新增时要注意，
	private String resUrl;// URL地址．例如：/videoType/query　　不需要项目名和http://xxx:8080
	private Integer level;
	private String type;// 权限类型，0．表示目录　1，表示菜单．2，表示按扭．．在spring security3安全权限中，涉及精确到按扭控制
	private String description;
	private Set<SysRoles> roles = new HashSet<SysRoles>(0);
	private Set<SysResources> childs = new HashSet<SysResources>(0);

	public SysResources() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<SysRoles> getRoles() {
		return roles;
	}

	public void setRoles(Set<SysRoles> roles) {
		this.roles = roles;
	}

	public String getResUrl() {
		return resUrl;
	}

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getResKey() {
		return resKey;
	}

	public void setResKey(String resKey) {
		this.resKey = resKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Set<SysResources> getChilds() {
		return childs;
	}

	public void setChilds(Set<SysResources> childs) {
		this.childs = childs;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	@Override
	public String toString() {
		return "SysResources [id=" + id + ", name=" + name + ", parentId=" + parentId + ", parentName=" + parentName + ", resKey=" + resKey + ", resUrl="
				+ resUrl + ", level=" + level + ", type=" + type + ", description=" + description + ", roles=" + roles + ", childs=" + childs + "]";
	}

}