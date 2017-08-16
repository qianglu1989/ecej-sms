package com.ecej.nove.sms.msg;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.backoff.ThreadWaitSleeper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ecej.nove.base.sms.SMSMessage;
import com.ecej.nove.sms.dao.SmsBaseDao;
import com.ecej.nove.sms.po.SysSmsToSendPo;

/**
 * 接收短信入库
 * 
 * @author QIANG
 *
 */
@Service
@RabbitListener(queues = "ecejsms")
public class SmsReceiverService {

	private Logger LOG = LoggerFactory.getLogger(SmsReceiverService.class);

	@Value("${sms.close.flag}")
	private boolean flag;

	@Resource
	private SendSmsService sendSmsService;

	@RabbitHandler
	public void ReceiverMessage(SMSMessage smsMessage) {

		if (flag)
			return;

		sendSmsService.sendSms(smsMessage);

	}

	@RabbitHandler
	public void ReceiverMessage(String sms) {
		System.out.println(sms);
	}
}
