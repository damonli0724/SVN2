package com.saltedfish.dto.security;
/**
*类说明:资源修改页面返回的DTO
*@author LKD
*/
public class ResourceUpdateDTO {
	private Integer resId;
	private String resName;
	private String resKey;
	private Integer resType;
	private String resUrl;
	private Integer resLevel;
	private String resDes;
	private Integer resParentId; //上一级parentId
	private Integer resParentParentId;//上上级parentId
	
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
	public String getResKey() {
		return resKey;
	}
	public void setResKey(String resKey) {
		this.resKey = resKey;
	}
	public Integer getResType() {
		return resType;
	}
	public void setResType(Integer resType) {
		this.resType = resType;
	}
	public String getResUrl() {
		return resUrl;
	}
	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}
	public Integer getResLevel() {
		return resLevel;
	}
	public void setResLevel(Integer resLevel) {
		this.resLevel = resLevel;
	}
	public String getResDes() {
		return resDes;
	}
	public void setResDes(String resDes) {
		this.resDes = resDes;
	}
	public Integer getResParentId() {
		return resParentId;
	}
	public void setResParentId(Integer resParentId) {
		this.resParentId = resParentId;
	}
	public Integer getResParentParentId() {
		return resParentParentId;
	}
	public void setResParentParentId(Integer resParentParentId) {
		this.resParentParentId = resParentParentId;
	}
	@Override
	public String toString() {
		return "ResourceUpdateDTO [resName=" + resName + ", resKey=" + resKey
				+ ", resType=" + resType + ", resUrl=" + resUrl + ", resLevel="
				+ resLevel + ", resDes=" + resDes + ", resParentId="
				+ resParentId + ", resParentParentId=" + resParentParentId
				+ "]";
	}
}
