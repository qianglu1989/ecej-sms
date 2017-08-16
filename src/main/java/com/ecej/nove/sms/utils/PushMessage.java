package com.ecej.nove.sms.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ecej.nove.base.po.MessageBody;

/**
 * 
 * <p>
 * 类描述：。
 * </p>
 * 
 * @author 王峰[wangfeng] 。
 * @version: v1.0.0.1。
 * @since JDK1.8。
 *        <p>
 *        创建日期：2016年4月26日 下午5:02:38。
 *        </p>
 *        Copyright 【新智泛能网络科技有限公司】 2016
 */
public class PushMessage {
	private static Logger logger = LoggerFactory.getLogger(PushMessage.class);

	/**
	 * <p>
	 * 功能描述：向手机推送消息。
	 * </p>
	 * 
	 * @param destination
	 *            安卓用户MQ：推送的主题 ;IOS用户：客户端标识
	 * @param jsonContent
	 *            推送内容
	 * @param type
	 *            手机类型
	 * @since JDK1.8。
	 *        <p>
	 *        创建日期:2016年4月26日 下午4:57:06。
	 *        </p>
	 *        <p>
	 *        更新日期:[日期YYYY-MM-DD][王峰][变更描述]。
	 *        </p>
	 */
	public static ResultMessage<Void> pushEmp(MessageBody body) {
		logger.info("消息推送pushEmp:" + body.getId() + body.toString());
		Map<String, String> map = new HashMap<>();
		map.put("data", body.getJsonObject());
		PushRequest.pushToEmp("EMP" + body.getId(), body.getTitle(), body.getMessage(), map);
		ResultMessage<Void> resultMessage = new ResultMessage<>();
		return resultMessage;
	}

	public static ResultMessage<Void> pushUser(MessageBody body) {
		logger.info("消息推送pushUser:" + body.getId() + body.toString());
		ResultMessage<Void> resultMessage = new ResultMessage<>();

		Map<String, String> map = new HashMap<>();
		map.put("data", body.getJsonObject());
		PushRequest.pushToUser("USER" + body.getId(), body.getTitle(), body.getMessage(), map);
		return resultMessage;
	}

}
