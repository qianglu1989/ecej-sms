package com.ecej.nove.test.sms;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ecej.nove.base.mail.BaseMail;
import com.ecej.nove.base.sms.SMSMessage;
import com.ecej.nove.base.sms.SmsSignEnum;
import com.ecej.nove.base.sms.SmsTypeEnum;
import com.ecej.nove.sms.run.Startup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Startup.class)
public class MailTest {

	@Resource
	private RabbitTemplate rabbitTemplate;

	@Test
	public void sendMQ() {

		for (int i = 0; i < 10; i++) {
			BaseMail baseMail = new BaseMail();
			baseMail.setTo(new String[] { "598505651@qq.com" });
			baseMail.setSubject("这是一个神奇的网站");
			baseMail.setText("这是一个一格" + i);
			baseMail.setCc(new String[] { "35329425@qq.com" });
			rabbitTemplate.convertAndSend("ecejmail", baseMail);
			System.out.println("Sender : " + baseMail.getText());

			String context = "baseMail.getText() " + new Date();
			this.rabbitTemplate.convertAndSend("ecejmail", context + "  " + i);

			SMSMessage message = new SMSMessage();
			message.setContent("this is test");
			message.setMobilephoneNO("15801325400");
			message.setSmsSource(0);
			message.setSmsSignEnum(SmsSignEnum.ECEJ);
			message.setSmsTypeEnum(SmsTypeEnum.BUSINESS);
			this.rabbitTemplate.convertAndSend("ecejsms", message);
			this.rabbitTemplate.convertAndSend("ecejsms", message.toString());
		}

	}
}
