package com.ecej.nove.test.sms;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.ecej.nove.sms.po.TestPo;

public class SmsSendService {

	@Resource
	private RabbitTemplate rabbitTemplate;

	public void send() {
		for (int i = 0; i < 100; i++) {
			String context = "hello " + new Date();
			System.out.println("Sender : " + context);
			this.rabbitTemplate.convertAndSend("hello", context + "  " + i);

			TestPo po = new TestPo();
			po.setAge(i);
			po.setName("qiang" + i);

			this.rabbitTemplate.convertAndSend("hello", po);

		}
	}

}
