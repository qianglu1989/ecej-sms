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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ecej.nove.base.mail.BaseMail;

@Service("mailSendService")
public class MailSendService {

	private Logger LOG = LoggerFactory.getLogger(MailSendService.class);

	@Value("${spring.mail.username}")
	private String from;

	@Resource
	private JavaMailSender mailSender;

	/**
	 * 发送普通邮件
	 * 
	 * @param mail
	 */
	@Async("mailAsync")
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

	/**
	 * 发送带附件的邮件
	 * 
	 * @param mail
	 */
	@Async("mailAsync")
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
					LOG.error("获取文件附件失败,附件名称:{},附件地址:{},异常信息:{}", set.getKey(), set.getValue(), e);
				}

			});
			mailSender.send(mimeMessage);
			LOG.info("发送邮件成功,邮件详情:{}", mail.toString());
		} catch (Exception e) {
			LOG.error("邮件发送失败 !", e);
		}
	}
}
