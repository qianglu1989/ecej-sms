package com.ecej.nove.test.sms;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ecej.nove.sms.po.TestPo;
import com.ecej.nove.sms.run.Startup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Startup.class)
public class SmsSendService {

	@Resource
	private RabbitTemplate rabbitTemplate;

	@Test
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
