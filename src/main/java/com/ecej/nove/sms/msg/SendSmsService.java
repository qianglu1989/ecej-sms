package com.ecej.nove.sms.msg;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ecej.nove.base.sms.SMSMessage;
import com.ecej.nove.sms.dao.SmsBaseDao;
import com.ecej.nove.sms.po.SysSmsRecentRecordPo;
import com.ecej.nove.sms.utils.PushSM;

/**
 * 异步发送信息
 * 
 * @author QIANG
 *
 */
@Service("sendSmsService")
public class SendSmsService {

	private static Logger LOG = LoggerFactory.getLogger(SendSmsService.class);

	private static final String ECEJ = "0";
	private static final String XINAO_ECEJ = "1";

	@Resource
	private SmsBaseDao smsBaseDao;

	@Resource
	private RabbitTemplate rabbitTemplate;

	@Async("smsConsumer")
	public void sendSms(SMSMessage smsMessage) {

		int smsType = smsMessage.getSmsTypeEnum().getValue();
		String smsSign = smsMessage.getSmsSignEnum().getValue().toString();

		if (smsType == 0 && smsSign.equals(ECEJ)) {
			// 验证码e城e家
			LOG.info("e城e家验证码准备发送...");
			boolean status = PushSM.sendSMS7(smsMessage);
			if (status) {
				followUp(status, smsMessage);
			} else {
				LOG.warn("e城e家验证码主通道发送失败使用备用通道发送！");
				followUp(PushSM.sendSMS6(smsMessage), smsMessage);
			}
		} else if (smsType == 1 && smsSign.equals(ECEJ)) {
			// 业务e城e家
			LOG.info("e城e家业务准备主通道发送");

			followUp(PushSM.sendSMS(smsMessage), smsMessage);

		} else if (smsType == 1 && smsSign.equals(XINAO_ECEJ)) {
			// 业务新奥-e城e家
			LOG.info("新奥-e城e家 业务 主通道发送");
			boolean flag = smsMessage.getSmsSource() == 0 ? PushSM.sendSMS4(smsMessage) : PushSM.sendSMS2(smsMessage);

			followUp(flag, smsMessage);

		} else if (smsType == 2 && smsSign.equals(ECEJ)) {

			// 营销e城e家
			LOG.info("e城e家营销准备主通道发送");

			followUp(PushSM.sendSMS3(smsMessage), smsMessage);

		} else if (smsType == 2 && smsSign.equals(XINAO_ECEJ)) {
			// 营销新奥-e城e家
			LOG.info("新奥-e城e家营销准备主通道发送");

			followUp(PushSM.sendSMS4(smsMessage), smsMessage);

		} else if (smsType == 3 && smsSign.equals(XINAO_ECEJ)) {
			// 短信评价新奥-e城e家
			LOG.info("新奥-e城e家 短信评价 准备主通道发送");

			followUp(PushSM.sendSMS5(smsMessage), smsMessage);
		}

	}

	/**
	 * 跟进步骤，判断是否需要重发
	 * 
	 * @param type
	 *            发送状态
	 * @param smsMessage
	 *            发送对象
	 */
	private void followUp(boolean type, SMSMessage smsMessage) {

		if (type) {
			LOG.info("主通道发送成功,MSG:[{}]", smsMessage.toString());
			Date date = new Date();
			SysSmsRecentRecordPo po = new SysSmsRecentRecordPo();
			po.setWorkOrderNo(smsMessage.getWorkOrderNo());
			po.setContent(smsMessage.getContent());
			po.setReceiveMobile(smsMessage.getMobilephoneNO());
			po.setSendTime(date);
			po.setSendFailCount(smsMessage.getFailCount());
			po.setSendStatus(2);
			po.setCreateTime(date);
			po.setUpdateTime(date);
			po.setSmsSource(smsMessage.getSmsSource());
			po.setSmsType(smsMessage.getSmsTypeEnum().getValue());
			po.setSmsSign(smsMessage.getSmsSignEnum().getValue().toString());
			smsBaseDao.insert(po);
			LOG.info("插入发送历史");
		} else {
			int count = smsMessage.getFailCount();
			if (count > 3) {
				LOG.error("短信发送多次失败,记录日志停止发送。MSG:[{}]", smsMessage.toString());
				return;
			}
			LOG.warn("主通道发送失败,准备进入ecejsmsfail队列进行重试,MSG:[{}]", smsMessage.toString());
			smsMessage.setFailCount(count + 1);
			rabbitTemplate.convertAndSend("ecejsmsfail", smsMessage);
		}
	}

}
