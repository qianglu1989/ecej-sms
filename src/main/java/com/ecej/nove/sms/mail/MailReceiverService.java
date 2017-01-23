package com.ecej.nove.sms.mail;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ecej.nove.base.mail.BaseMail;
import com.ecej.nove.sms.service.api.MailSendService;

/**
 * 接收短信入库
 * 
 * @author QIANG
 *
 */
@Component
@RabbitListener(queues = "ecejmail")
public class MailReceiverService {

	private Logger LOG = LoggerFactory.getLogger(MailReceiverService.class);

	@Resource
	private MailSendService mailSendService;

	@Value("${mail.close.flag}")
	private boolean flag;

	@RabbitHandler
	public void ReceiverMessage(BaseMail mail) {

		if (flag)
			return;

		if (MapUtils.isEmpty(mail.getPaths())) {
			mailSendService.sendMail(mail);
		} else {
			mailSendService.sendMailAndFile(mail);
		}
		LOG.info("Receiver Mail Obj !");
	}

	@RabbitHandler
	public void ReceiverMessage(String mail) {
		System.out.println(mail);
	}

}
