package com.ecej.nove.sms.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSendService {

	@Resource
	private JavaMailSender mailSender;

	@PostConstruct
	public void sendMail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("ecej_dev@163.com");
		message.setTo("598505651@qq.com");
		message.setSubject("主题：简单邮件");
		message.setText("测试邮件内容");
		mailSender.send(message);
	}
}
