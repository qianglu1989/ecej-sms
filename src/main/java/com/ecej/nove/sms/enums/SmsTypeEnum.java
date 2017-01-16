package com.ecej.nove.sms.enums;

public enum SmsTypeEnum {
	/**
	 * 验证码
	 */
	VERIFICATION_CODE(0),

	/**
	 * 业务
	 */
	BUSINESS(1),

	/**
	 * 营销
	 */
	MARKETING(2),

	/**
	 * 短信评价
	 */
	EVALUATION(3);

	public Integer getValue() {
		return value;
	}

	private final Integer value;

	SmsTypeEnum(Integer value) {
		this.value = value;
	}
}
