package com.ecej.nove.sms.enums;

public enum SmsSignEnum {

	/**
	 * e城e家
	 */
	ECEJ(0),

	/**
	 * 新奥-e城e家
	 */
	XINAO_ECEJ(1);

	public Integer getValue() {
		return value;
	}

	private final Integer value;

	SmsSignEnum(Integer value) {
		this.value = value;
	}
}
