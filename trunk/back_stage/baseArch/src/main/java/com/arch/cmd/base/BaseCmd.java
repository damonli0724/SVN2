package com.arch.cmd.base;

import org.apache.log4j.Logger;

/**
 * 
 * 	mysql 分页工具
 * @author lkd
 *
 */
public class BaseCmd {
	protected final Logger logger = Logger.getLogger(this.getClass());
	
	private Integer  startPage;
	private Integer  pageSize;
	
	public Integer getStartPage() {
		return startPage;
	}
	public void setStartPage(Integer startPage) {
		if (startPage ==null) {
			logger.error("开始行不能为空!");
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
