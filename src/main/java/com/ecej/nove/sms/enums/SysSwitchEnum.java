package com.ecej.nove.sms.enums;

/**
 * @Description 系统开关枚举
 * @author liuqing
 *
 */
public enum SysSwitchEnum {
	/**
	 * 开关名称-短信发送
	 */
	SMS_SEND("SMS_SEND", "短信发送");

	private String val;// 值
	private String desc;

	private SysSwitchEnum(String val, String desc) {
		this.val = val;
		this.desc = desc;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
