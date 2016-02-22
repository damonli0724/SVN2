package com.saltedfish.cmd.base;


/**
 * 
 * 	mysql 分页工具
 * @author lkd
 *
 */
public class BaseCmd {
	
	private Integer  startPage;
	private Integer  pageSize;
	
	public Integer getStartPage() {
		return startPage;
	}
	public void setStartPage(Integer startPage) {
		if (startPage ==null) {
			this.startPage = 0;
		}else{
			this.startPage = (startPage - 1)*this.getPageSize();
		}
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize =pageSize ==null?pageSize =10:pageSize;
	}
}
