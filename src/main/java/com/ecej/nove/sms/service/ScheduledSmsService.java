package com.ecej.nove.sms.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ecej.nove.sms.dao.SmsBaseDao;
import com.ecej.nove.sms.po.SMSMessage;
import com.ecej.nove.sms.po.SysSmsToSendPo;
import com.ecej.nove.sms.service.api.AsyncSendSmsService;

/**
 * 定时发送信息
 * 
 * @author QIANG
 *
 */
@Component
public class ScheduledSmsService {

	@Resource
	private AsyncSendSmsService asyncSendSmsService;
	private static Logger LOG = LoggerFactory.getLogger(ScheduledSmsService.class);

	private static final String ECEJ = "0";
	private static final String XINAO_ECEJ = "1";

	@Resource
	private SmsBaseDao smsBaseDao;

	// TODO 待修改时间
	@Scheduled(fixedRate = 50000)
	public void sendSms() {

		LOG.info("发送短信任务 start...");
		long startTime = System.currentTimeMillis();
		List<SysSmsToSendPo> noSend = smsBaseDao.selectList("findNotSendSMS");

		LOG.info("本次任务共需发送 {} 条短信", noSend.size());

		if (CollectionUtils.isEmpty(noSend))
			return;

		for (SysSmsToSendPo sms : noSend) {

			LOG.info("即将发送短信 ----> {}", noSend.size());

			SMSMessage smsMessage = new SMSMessage();
			smsMessage.setMobilephoneNO(sms.getReceiveMobile());
			smsMessage.setContent(sms.getContent());
			if (sms.getSmsSource() == null)
				sms.setSmsSource(0);

			if (sms.getSmsType() == 0 && sms.getSmsSign().equals(ECEJ)) {
				// 验证码e城e家
				asyncSendSmsService.sendSmsEcej0(sms, smsMessage);
			} else if (sms.getSmsType() == 1 && sms.getSmsSign().equals(ECEJ)) {
				// 业务e城e家
				//
				asyncSendSmsService.sendSmsEcej1(sms, smsMessage);

			} else if (sms.getSmsType() == 1 && sms.getSmsSign().equals(XINAO_ECEJ)) {
				// 业务新奥-e城e家
				asyncSendSmsService.sendSmsXinao1(sms, smsMessage);

			} else if (sms.getSmsType() == 2 && sms.getSmsSign().equals(ECEJ)) {

				// 营销e城e家
				asyncSendSmsService.sendSmsEcej2(sms, smsMessage);

			} else if (sms.getSmsType() == 2 && sms.getSmsSign().equals(XINAO_ECEJ)) {
				// 营销新奥-e城e家
				asyncSendSmsService.sendSmsXinao2(sms, smsMessage);

			} else if (sms.getSmsType() == 3 && sms.getSmsSign().equals(XINAO_ECEJ)) {
				// 短信评价新奥-e城e家
				asyncSendSmsService.sendSmsXinao3(sms, smsMessage);
			}
		}

		asyncSendSmsService.backupHis();

		LOG.info("发送短信任务 end... 耗时 [{} 毫秒]", System.currentTimeMillis() - startTime);
	}

}
