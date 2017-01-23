package com.ecej.nove.sms.service.api;

import com.ecej.nove.base.sms.SMSMessage;
import com.ecej.nove.sms.po.SysSmsToSendPo;

public interface AsyncSendSmsService {

	/**
	 * 发送 验证码e城e家的短信
	 * 
	 * @param sms
	 *            数据库待发信息数据
	 * @param smsMessage
	 *            实际发送数据模型
	 */
	public void sendSmsEcej0(SysSmsToSendPo sms, SMSMessage smsMessage);

	/**
	 * 发送业务ecej的短信
	 * 
	 * @param sms
	 *            数据库待发信息数据
	 * @param smsMessage
	 *            实际发送数据模型
	 */
	public void sendSmsEcej1(SysSmsToSendPo sms, SMSMessage smsMessage);

	/**
	 * 发送业务 新奥-e城e家 的短信
	 * 
	 * @param sms
	 *            数据库待发信息数据
	 * @param smsMessage
	 *            实际发送数据模型
	 */
	public void sendSmsXinao1(SysSmsToSendPo sms, SMSMessage smsMessage);

	/**
	 * 发送 营销 e城e家的短信
	 * 
	 * @param sms
	 *            数据库待发信息数据
	 * @param smsMessage
	 *            实际发送数据模型
	 */
	public void sendSmsEcej2(SysSmsToSendPo sms, SMSMessage smsMessage);

	/**
	 * 发送 营销 新奥-e城e家的短信
	 * 
	 * @param sms
	 *            数据库待发信息数据
	 * @param smsMessage
	 *            实际发送数据模型
	 */
	public void sendSmsXinao2(SysSmsToSendPo sms, SMSMessage smsMessage);

	/**
	 * 发送 短信评价新奥-e城e家的短信
	 * 
	 * @param sms
	 *            数据库待发信息数据
	 * @param smsMessage
	 *            实际发送数据模型
	 */
	public void sendSmsXinao3(SysSmsToSendPo sms, SMSMessage smsMessage);

	/**
	 * 转移最近短信到历史记录表
	 */
	public void backupHis();
}
