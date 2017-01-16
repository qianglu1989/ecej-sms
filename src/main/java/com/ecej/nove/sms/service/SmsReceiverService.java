package com.ecej.nove.sms.service;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ecej.nove.sms.dao.SmsBaseDao;
import com.ecej.nove.sms.po.SMSMessage;
import com.ecej.nove.sms.po.SysSmsToSendPo;

/**
 * 接收短信入库
 * 
 * @author QIANG
 *
 */
@Service
@RabbitListener(queues = "hello")
public class SmsReceiverService {

	private Logger LOG = LoggerFactory.getLogger(SmsReceiverService.class);

	@Value("${sms.close.flag}")
	private boolean flag;

	@Resource
	private SmsBaseDao smsBaseDao;

	@RabbitHandler
	@Transactional(propagation = Propagation.REQUIRED)
	public void ReceiverMessage(SMSMessage smsMessage) {

		if (flag)
			return;
		Date date = new Date();
		SysSmsToSendPo smsToSendPo = new SysSmsToSendPo();
		smsToSendPo.setContent(smsMessage.getContent());
		smsToSendPo.setReceiveMobile(smsMessage.getMobilephoneNO());
		smsToSendPo.setWorkOrderNo(smsMessage.getWorkOrderNo());
		smsToSendPo.setSendTime(date);
		smsToSendPo.setSendFailCount(0);
		smsToSendPo.setSendStatus(0);
		smsToSendPo.setCreateTime(date);
		smsToSendPo.setUpdateTime(date);
		smsToSendPo.setSmsSource(smsMessage.getSmsSource());
		smsToSendPo.setSmsType(smsMessage.getSmsTypeEnum().getValue());
		smsToSendPo.setSmsSign(smsMessage.getSmsSignEnum().getValue().toString());
		smsBaseDao.insert(smsToSendPo);
		LOG.info("待发短信入库,待发ID为:{}", smsToSendPo.getSendId());

	}

}
