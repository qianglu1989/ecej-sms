package com.ecej.nove.test.sms;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSON;
import com.ecej.nove.base.junit.BaseTest;
import com.ecej.nove.sms.dao.SmsBaseDao;
import com.ecej.nove.sms.enums.SysSwitchEnum;
import com.ecej.nove.sms.po.SMSMessage;
import com.ecej.nove.sms.po.SysSmsToSendPo;
import com.ecej.nove.sms.po.SysSwitchPo;
import com.ecej.nove.sms.run.Startup;

@RunWith(SpringRunner.class)
@SpringApplicationConfiguration(classes = Startup.class)
@WebAppConfiguration
public class SendSMServiceImplXinAo extends BaseTest {

	@Resource
	private SmsBaseDao smsBaseDao;// EcejService数据源

	@Test
	public void sendMsg() {
		SMSMessage smsMessage = new SMSMessage();
		sendSMS(smsMessage);
	}

	private Logger logger = LoggerFactory.getLogger(SendSMServiceImplXinAo.class);

	public void sendSMS(SMSMessage smsMessage) {

		SysSmsToSendPo smsToSendPo = new SysSmsToSendPo();
		/***************** 短信开关 **********************/
		// 根据短信发送开关key查找系统开关的保存详细信息
		SysSwitchPo sysSwitchPo = new SysSwitchPo();
		sysSwitchPo.setSwitchIdentKey(SysSwitchEnum.SMS_SEND.getVal());
		sysSwitchPo = smsBaseDao.selectOne(sysSwitchPo);
		// 查询无结果(未设置)或者关闭状态 关闭flag（0 开通 1关闭）
		if (sysSwitchPo == null || sysSwitchPo.getCloseFlag() == 1) {
			return;
		}

		smsToSendPo.setContent(smsMessage.getContent());
		smsToSendPo.setReceiveMobile(smsMessage.getMobilephoneNO());
		smsToSendPo.setWorkOrderNo(smsMessage.getWorkOrderNo());
		smsToSendPo.setSendTime(new Date());
		smsToSendPo.setSendFailCount(0);
		smsToSendPo.setSendStatus(0);// 未发送
		smsToSendPo.setCreateTime(new Date());
		smsToSendPo.setUpdateTime(new Date());
		smsToSendPo.setSmsSource(smsMessage.getSmsSource());
		try {
			smsToSendPo.setSmsType(smsMessage.getSmsTypeEnum().getValue());
			smsToSendPo.setSmsSign(smsMessage.getSmsSignEnum().getValue().toString());
		} catch (NullPointerException e) {
			throw new RuntimeException("未设置短信类型或短信签名");
		}
		logger.info("待发短信入库：{}", JSON.toJSONString(smsToSendPo));
		smsBaseDao.insert(smsToSendPo);
	}

	/**
	 * 即使发送短信接口
	 * <p>
	 * 功能描述：。
	 * </p>
	 *
	 * @param list
	 * @since JDK1.8。
	 *        <p>
	 *        创建日期:2016年6月24日 下午1:51:34。
	 *        </p>
	 *        <p>
	 *        更新日期:[日期YYYY-MM-DD][王峰][变更描述]。
	 *        </p>
	 *        <p>
	 *        更新日期:[日期2016-10-08][翟爱雪][系统设置短信发送开关]。
	 *        </p>
	 */
	public void sendSMS(List<SMSMessage> list) {

		for (SMSMessage smsMessage : list) {
			sendSMS(smsMessage);
		}
	}

}
