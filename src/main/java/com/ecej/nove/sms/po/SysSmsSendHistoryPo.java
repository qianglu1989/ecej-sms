
package com.ecej.nove.sms.po;

import com.ecej.nove.base.po.BasePo;

public class SysSmsSendHistoryPo extends BasePo {
	private static final long serialVersionUID = 1L;
	// alias
	public static final String TABLE_ALIAS = "SysSmsSendHistory";

	// columns START
	/**
	 * sendHistoryId db_column: send_history_id
	 */
	private Integer sendHistoryId;
	/**
	 * 订单编号 db_column: work_order_no
	 */
	private String workOrderNo;
	/**
	 * 接收者手机号 db_column: receive_mobile
	 */
	private String receiveMobile;
	/**
	 * 短信内容 db_column: content
	 */
	private String content;
	/**
	 * 发送时间 db_column: send_time
	 */
	private java.util.Date sendTime;
	/**
	 * 发送状态 db_column: send_status
	 */
	private Integer sendStatus;
	/**
	 * 短信类型 db_column: sms_type
	 */
	private Integer smsType;
	/**
	 * 短信来源 db_column: sms_source
	 */
	private Integer smsSource;
	/**
	 * 发送优先级 db_column: send_priority
	 */
	private Integer sendPriority;
	/**
	 * 操作员ID(发送者) db_column: admin_id
	 */
	private Integer adminId;
	/**
	 * 短信服务返回信息 db_column: service_back_info
	 */
	private String serviceBackInfo;
	/**
	 * 发送失败次数 db_column: send_fail_count
	 */
	private Integer sendFailCount;
	/**
	 * 消息群发记录ID db_column: group_send_id
	 */
	private Integer groupSendId;
	/**
	 * 短信签名 db_column: sms_sign
	 */
	private String smsSign;
	/**
	 * 更新时间 db_column: update_time
	 */
	private java.util.Date updateTime;
	/**
	 * 创建时间 db_column: create_time
	 */
	private java.util.Date createTime;
	// columns END

	public Integer getSendHistoryId() {
		return this.sendHistoryId;
	}

	public void setSendHistoryId(Integer value) {
		this.sendHistoryId = value;
	}

	public String getWorkOrderNo() {
		return this.workOrderNo;
	}

	public void setWorkOrderNo(String value) {
		this.workOrderNo = value;
	}

	public String getReceiveMobile() {
		return this.receiveMobile;
	}

	public void setReceiveMobile(String value) {
		this.receiveMobile = value;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String value) {
		this.content = value;
	}

	public java.util.Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(java.util.Date value) {
		this.sendTime = value;
	}

	public Integer getSendStatus() {
		return this.sendStatus;
	}

	public void setSendStatus(Integer value) {
		this.sendStatus = value;
	}

	public Integer getSmsType() {
		return this.smsType;
	}

	public void setSmsType(Integer value) {
		this.smsType = value;
	}

	public Integer getSmsSource() {
		return this.smsSource;
	}

	public void setSmsSource(Integer value) {
		this.smsSource = value;
	}

	public Integer getSendPriority() {
		return this.sendPriority;
	}

	public void setSendPriority(Integer value) {
		this.sendPriority = value;
	}

	public Integer getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Integer value) {
		this.adminId = value;
	}

	public String getServiceBackInfo() {
		return this.serviceBackInfo;
	}

	public void setServiceBackInfo(String value) {
		this.serviceBackInfo = value;
	}

	public Integer getSendFailCount() {
		return this.sendFailCount;
	}

	public void setSendFailCount(Integer value) {
		this.sendFailCount = value;
	}

	public Integer getGroupSendId() {
		return this.groupSendId;
	}

	public void setGroupSendId(Integer value) {
		this.groupSendId = value;
	}

	public String getSmsSign() {
		return this.smsSign;
	}

	public void setSmsSign(String value) {
		this.smsSign = value;
	}

	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}

}
