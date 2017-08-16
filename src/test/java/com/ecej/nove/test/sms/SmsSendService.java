package com.ecej.nove.test.sms;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ecej.nove.base.sms.SMSMessage;
import com.ecej.nove.base.sms.SmsSignEnum;
import com.ecej.nove.base.sms.SmsTypeEnum;
import com.ecej.nove.sms.run.Startup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Startup.class)
public class SmsSendService {

	@Resource
	private RabbitTemplate rabbitTemplate;

	@Test
	public void send() {
		for (int i = 0; i < 2; i++) {

			SMSMessage message = new SMSMessage();
			message.setContent("亲爱的员工，您的员工端已经创建成功，账号：18033638827，密码：941982，请尽快登录系统修改您的初始密码。");
			message.setMobilephoneNO("15801325400");
			message.setSmsSource(0);
			message.setSmsSignEnum(SmsSignEnum.ECEJ);
			message.setSmsTypeEnum(SmsTypeEnum.BUSINESS);

			this.rabbitTemplate.convertAndSend("ecejsms1", message);
			System.out.print("发送成功"+i);

		}
	}

}
