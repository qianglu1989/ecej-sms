package com.ecej.nove.sms.msg;

import com.ecej.nove.base.sms.SMSMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by QIANG on 2017/5/9.
 */
@Service
@RabbitListener(queues = "ecejsmsfail")
public class SmsFailConsumer {


    @Value("${sms.close.flag}")
    private boolean flag;

    @Resource
    private SendSmsService sendSmsService;

    @RabbitHandler
    public void ReceiverMessage(SMSMessage smsMessage) {

        if (flag)
            return;

        sendSmsService.sendSms(smsMessage);

    }

}
