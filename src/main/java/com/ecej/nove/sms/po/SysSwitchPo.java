
package com.ecej.nove.sms.po;

import com.ecej.nove.base.po.BasePo;

public class SysSwitchPo extends BasePo {
	private static final long serialVersionUID = 1L;
	// alias
	public static final String TABLE_ALIAS = "SysSwitch";

	// columns START
	/**
	 * 开关id db_column: switch_id
	 */
	private java.lang.Integer switchId;
	/**
	 * 开关识别key db_column: switch_ident_key
	 */
	private java.lang.String switchIdentKey;
	/**
	 * 开关中文名 db_column: switch_chinese_name
	 */
	private java.lang.String switchChineseName;
	/**
	 * 关闭flag（0 开通 1关闭） db_column: close_flag
	 */
	private Integer closeFlag;
	/**
	 * 更新者 db_column: update_user
	 */
	private java.lang.Integer updateUser;
	/**
	 * 更新时间 db_column: update_time
	 */
	private java.util.Date updateTime;
	// columns END

	private String updateUserName;

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public java.lang.Integer getSwitchId() {
		return this.switchId;
	}

	public void setSwitchId(java.lang.Integer value) {
		this.switchId = value;
	}

	public java.lang.String getSwitchIdentKey() {
		return this.switchIdentKey;
	}

	public void setSwitchIdentKey(java.lang.String value) {
		this.switchIdentKey = value;
	}

	public java.lang.String getSwitchChineseName() {
		return this.switchChineseName;
	}

	public void setSwitchChineseName(java.lang.String value) {
		this.switchChineseName = value;
	}

	public Integer getCloseFlag() {
		return this.closeFlag;
	}

	public void setCloseFlag(Integer value) {
		this.closeFlag = value;
	}

	public java.lang.Integer getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(java.lang.Integer value) {
		this.updateUser = value;
	}

	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}

}
