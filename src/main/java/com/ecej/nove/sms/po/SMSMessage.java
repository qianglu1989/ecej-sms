package com.ecej.nove.sms.po;

import java.io.Serializable;

import com.ecej.nove.sms.enums.SmsSignEnum;
import com.ecej.nove.sms.enums.SmsTypeEnum;

/**
 * 封装短信对象
 * <p>
 * 类描述：。
 * </p>
 *
 * @author 王峰[wangfeng] 。
 * @version: v1.0.0.1。
 * @since JDK1.8。
 *        <p>
 *        创建日期：2016年6月24日 上午10:19:38。
 *        </p>
 *        Copyright 【新智泛能网络科技有限公司】 2016
 */
public class SMSMessage implements Serializable {

	private static final long serialVersionUID = -1276820105753124258L;
	// 手机号码
	private String mobilephoneNO;
	// 短信内容
	private String content;
	// 短信签名
	private SmsSignEnum smsSignEnum;
	// 短信类型
	private SmsTypeEnum smsTypeEnum;
	// 订单编号(短信评价必填字段)
	private String workOrderNo;
	// 接受端 0 用户 1 员工
	private int smsSource;

	/* 发送短信不需要关心此字段 */
	private Integer id;

	public String getMobilephoneNO() {
		return mobilephoneNO;
	}

	public void setMobilephoneNO(String mobilephoneNO) {
		this.mobilephoneNO = mobilephoneNO;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public SmsSignEnum getSmsSignEnum() {
		return smsSignEnum;
	}

	public void setSmsSignEnum(SmsSignEnum smsSignEnum) {
		this.smsSignEnum = smsSignEnum;
	}

	public SmsTypeEnum getSmsTypeEnum() {
		return smsTypeEnum;
	}

	public void setSmsTypeEnum(SmsTypeEnum smsTypeEnum) {
		this.smsTypeEnum = smsTypeEnum;
	}

	public String getWorkOrderNo() {
		return workOrderNo;
	}

	public void setWorkOrderNo(String workOrderNo) {
		this.workOrderNo = workOrderNo;
	}

	public int getSmsSource() {
		return smsSource;
	}

	public void setSmsSource(int smsSource) {
		this.smsSource = smsSource;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
