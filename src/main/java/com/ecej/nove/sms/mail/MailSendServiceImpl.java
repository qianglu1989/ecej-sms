package com.ecej.nove.sms.mail;

import java.io.File;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ecej.nove.base.mail.BaseMail;
import com.ecej.nove.sms.service.api.MailSendService;

@Service("mailSendService")
public class MailSendServiceImpl implements MailSendService {

	private Logger LOG = LoggerFactory.getLogger(MailSendServiceImpl.class);

	@Value("${spring.mail.username}")
	private String from;

	@Resource
	private JavaMailSender mailSender;

	@Override
	public void sendMail(BaseMail mail) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(mail.getTo());
		message.setSubject(mail.getSubject());
		message.setText(mail.getText());
		message.setBcc(mail.getBcc());
		message.setCc(mail.getCc());
		mailSender.send(message);
		LOG.info("发送邮件成功,邮件详情:{}", message.toString());
	}

	@Override
	public void sendMailAndFile(BaseMail mail) {

		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(from);
			helper.setTo(mail.getTo());
			helper.setSubject(mail.getSubject());
			helper.setText(mail.getText());
			helper.setBcc(mail.getBcc());
			helper.setCc(mail.getCc());
			mail.getPaths().entrySet().stream().forEach(set -> {
				FileSystemResource file = new FileSystemResource(new File(set.getValue()));
				try {
					helper.addAttachment(set.getKey(), file);

				} catch (MessagingException e) {
					LOG.error("SAVE MAIL FILE ERROR!", e);
				}

			});
			mailSender.send(mimeMessage);
			LOG.info("发送邮件成功,邮件详情:{}", mail.toString());
		} catch (Exception e) {
			LOG.error("SEND MAIL ERROR !", e);
		}
	}
}
