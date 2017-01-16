package com.ecej.nove.sms.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ecej.nove.sms.dao.SmsBaseDao;
import com.ecej.nove.sms.po.SMSMessage;
import com.ecej.nove.sms.po.SysSmsRecentRecordPo;
import com.ecej.nove.sms.po.SysSmsSendHistoryPo;
import com.ecej.nove.sms.po.SysSmsToSendPo;
import com.ecej.nove.sms.service.api.AsyncSendSmsService;

@Component
@Transactional(propagation = Propagation.REQUIRED)
public class AsyncSendSmsServiceImpl implements AsyncSendSmsService {

	private static Logger LOG = LoggerFactory.getLogger(AsyncSendSmsServiceImpl.class);

	@Resource
	private SmsBaseDao smsBaseDao;

	/**
	 * 发送 验证码e城e家的短信
	 * 
	 * @param sms
	 *            数据库待发信息数据
	 * @param smsMessage
	 *            实际发送数据模型
	 */
	@Override
	@Async("simpleAsync")
	public void sendSmsEcej0(SysSmsToSendPo sms, SMSMessage smsMessage) {

		LOG.info("验证码 e城e家 ...");
		boolean status = PushSM.sendSMS7(smsMessage);
		if (status) {
			sms.setSendStatus(2);// 发送成功
			smsBaseDao.insert(sms.convertToSysSmsRecentRecordPo());
			SysSmsToSendPo po = new SysSmsToSendPo();
			po.setSendId(sms.getSendId());
			smsBaseDao.delete(po);
		} else { // 有主备
			smsMessage.setId(sms.getSendId());
			smsMessage.setWorkOrderNo(sms.getWorkOrderNo());
			status = PushSM.sendSMS6(smsMessage);// 需要回调状态
			if (status) {
				sms.setSendStatus(1);// 已请求
			} else {
				sms.setSendFailCount(sms.getSendFailCount() + 1);
			}
			smsBaseDao.update(sms);
		}
	}

	/**
	 * 发送业务ecej的短信
	 * 
	 * @param sms
	 *            数据库待发信息数据
	 * @param smsMessage
	 *            实际发送数据模型
	 */
	@Override
	@Async("simpleAsync")
	public void sendSmsEcej1(SysSmsToSendPo sms, SMSMessage smsMessage) {

		LOG.info("业务 e城e家 ...");
		if (PushSM.sendSMS(smsMessage)) {
			sms.setSendStatus(2);// 发送成功
			smsBaseDao.insert(sms.convertToSysSmsRecentRecordPo());
			SysSmsToSendPo po = new SysSmsToSendPo();
			po.setSendId(sms.getSendId());
			smsBaseDao.delete(po);
		} else {
			sms.setSendFailCount(sms.getSendFailCount() + 1);
			smsBaseDao.update(sms);
		}
	}

	/**
	 * 发送业务 新奥-e城e家 的短信
	 * 
	 * @param sms
	 *            数据库待发信息数据
	 * @param smsMessage
	 *            实际发送数据模型
	 */
	@Override
	@Async("simpleAsync")
	public void sendSmsXinao1(SysSmsToSendPo sms, SMSMessage smsMessage) {

		LOG.info("业务 新奥-e城e家 ...");
		boolean flag = sms.getSmsSource() == 0 ? PushSM.sendSMS4(smsMessage) : PushSM.sendSMS2(smsMessage);
		if (flag) {
			sms.setSendStatus(2);// 发送成功
			smsBaseDao.insert(sms.convertToSysSmsRecentRecordPo());
			SysSmsToSendPo po = new SysSmsToSendPo();
			po.setSendId(sms.getSendId());
			smsBaseDao.delete(po);
		} else {
			sms.setSendFailCount(sms.getSendFailCount() + 1);
			smsBaseDao.update(sms);
		}
	}

	/**
	 * 发送 营销 e城e家的短信
	 * 
	 * @param sms
	 *            数据库待发信息数据
	 * @param smsMessage
	 *            实际发送数据模型
	 */
	@Override
	@Async("simpleAsync")
	public void sendSmsEcej2(SysSmsToSendPo sms, SMSMessage smsMessage) {

		LOG.info("营销 e城e家 ...");
		if (PushSM.sendSMS3(smsMessage)) {
			sms.setSendStatus(2);// 发送成功
			smsBaseDao.insert(sms.convertToSysSmsRecentRecordPo());
			SysSmsToSendPo po = new SysSmsToSendPo();
			po.setSendId(sms.getSendId());
			smsBaseDao.delete(po);
		} else {
			sms.setSendFailCount(sms.getSendFailCount() + 1);
			smsBaseDao.update(sms);
		}
	}

	/**
	 * 发送 营销 新奥-e城e家的短信
	 * 
	 * @param sms
	 *            数据库待发信息数据
	 * @param smsMessage
	 *            实际发送数据模型
	 */
	@Override
	@Async("simpleAsync")
	public void sendSmsXinao2(SysSmsToSendPo sms, SMSMessage smsMessage) {

		LOG.info("营销 新奥-e城e家 ...");
		if (PushSM.sendSMS4(smsMessage)) {
			sms.setSendStatus(2);// 发送成功
			smsBaseDao.insert(sms.convertToSysSmsRecentRecordPo());
			SysSmsToSendPo po = new SysSmsToSendPo();
			po.setSendId(sms.getSendId());
			smsBaseDao.delete(po);
		} else {
			sms.setSendFailCount(sms.getSendFailCount() + 1);
			smsBaseDao.update(sms);
		}
	}

	/**
	 * 发送 短信评价新奥-e城e家的短信
	 * 
	 * @param sms
	 *            数据库待发信息数据
	 * @param smsMessage
	 *            实际发送数据模型
	 */
	@Override
	@Async("simpleAsync")
	public void sendSmsXinao3(SysSmsToSendPo sms, SMSMessage smsMessage) {
		LOG.info("短信评价 新奥-e城e家 ...");
		smsMessage.setId(sms.getSendId());
		smsMessage.setWorkOrderNo(sms.getWorkOrderNo());
		if (PushSM.sendSMS5(smsMessage)) {
			sms.setSendStatus(1);// 已请求
			smsBaseDao.update(sms);
		} else {
			sms.setSendFailCount(sms.getSendFailCount() + 1);
			smsBaseDao.update(sms);
		}
	}

	/**
	 * 转移最近短信到历史记录表
	 */
	@Override
	@Async("simpleAsync")
	public void backupHis() {

		List<SysSmsRecentRecordPo> weekBefore = smsBaseDao.selectList("findWeekBefore"); // 一周前的记录
		if (CollectionUtils.isNotEmpty(weekBefore)) {
			List<SysSmsSendHistoryPo> list = new ArrayList<>();
			for (SysSmsRecentRecordPo recordPo : weekBefore) {
				list.add(recordPo.convertToSysSmsSendHistoryPo());
				SysSmsRecentRecordPo del = new SysSmsRecentRecordPo();
				del.setRecentRecordId(recordPo.getRecentRecordId());
				smsBaseDao.delete(del);
			}
			smsBaseDao.insert(list);
			LOG.info("转移最近短信到历史记录表 {} 条", list.size());
		}

	}
}
