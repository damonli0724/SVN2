package com.saltedfish.cmd.admin;

/**
 * 资源添加 CMD
 * 
 * @author lkd
 *
 */
public class ResAddCmd {
	private Integer resId;
	private String resName;
	private String resKey;
	private Integer resType;
	private String resUrl;
	private Integer resLevel;
	private String resDes;
	private Integer resParentId;

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

	@Override
	public String toString() {
		return "ResAddCmd [resId=" + resId + ", resName=" + resName
				+ ", resKey=" + resKey + ", resType=" + resType + ", resUrl="
				+ resUrl + ", resLevel=" + resLevel + ", resDes=" + resDes
				+ ", resParentId=" + resParentId + "]";
	}

}
