package com.saltedfish.cmd.admin;

import java.util.Date;

import com.saltedfish.cmd.base.BaseCmd;


/**
 * 管理员列表 查询条件
 * @author lkd
 *
 */
public class AdminListQueryCmd extends BaseCmd {
	private String loginName;
	private Date beginDate;
	private Date endDate;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "AdminListQueryCmd [loginName=" + loginName + ", beginDate=" + beginDate + ", endDate=" + endDate + "]";
	}

}
