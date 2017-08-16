package com.ecej.nove.sms.jpush;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.ecej.nove.base.po.MessageBody;
import com.ecej.nove.sms.utils.PushMessage;

/**
 * 接收推送
 * 
 * @author QIANG
 *
 */
@Component
@RabbitListener(queues = "ecejjpush")
public class JPushReceiverService {

	private Logger LOG = LoggerFactory.getLogger(JPushReceiverService.class);

	@RabbitHandler
	public void ReceiverMessage(MessageBody body) {

		switch (body.getSendType()) {

		case EMP:
			PushMessage.pushEmp(body);
			break;

		case USER:
			PushMessage.pushUser(body);
			break;

		default:
			break;
		}
	}

	@RabbitHandler
	public void ReceiverMessage(String body) {
		System.out.println(body);
	}

}
