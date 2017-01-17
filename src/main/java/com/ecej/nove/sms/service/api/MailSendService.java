package com.ecej.nove.sms.service.api;

import com.ecej.nove.base.mail.BaseMail;

/**
 * 接收短信入库
 * 
 * @author QIANG
 *
 */
public interface MailSendService {

	/**
	 * 不带附件的邮件
	 * 
	 * @param mail
	 */
	public void sendMail(BaseMail mail);

	/**
	 * 带附件的邮件
	 * 
	 * @param mail
	 */
	public void sendMailAndFile(BaseMail mail);

}
