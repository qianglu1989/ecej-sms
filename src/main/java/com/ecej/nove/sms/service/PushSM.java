package com.ecej.nove.sms.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.RandomStringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ecej.nove.base.sms.SMSMessage;
import com.ecej.nove.utils.common.MD5Util;
import com.ecej.nove.utils.common.PropertyBootConfig;
import com.ecej.nove.utils.http.HttpClientUtils;

public class PushSM {
	private static Logger logger = LoggerFactory.getLogger(PushSM.class);

	private static String SMS_BUSINESS_SERVICE_A;
	private static String SMS_BUSINESS_USERNAME_A;
	private static String SMS_BUSINESS_PASSWORD_A;

	private static String SMS_BUSINESS_SERVICE_C;
	private static String SMS_BUSINESS_USERNAME_C;
	private static String SMS_BUSINESS_PASSWORD_C;

	private static String SMS_MARKETING_SERVICE_A;
	private static String SMS_MARKETING_USERNAME_A;
	private static String SMS_MARKETING_PASSWORD_A;

	private static String SMS_MARKETING_SERVICE_C;
	private static String SMS_MARKETING_USERNAME_C;
	private static String SMS_MARKETING_PASSWORD_C;

	private static String SMS_EVALUATE_SERVICE_C;
	private static String SMS_EVALUATE_USERNAME_C;
	private static String SMS_EVALUATE_PASSWORD_C;

	private static String SMS_VERIFICATION_SERVICE_A;
	private static String SMS_VERIFICATION_USERNAME_A;
	private static String SMS_VERIFICATION_PASSWORD_A;

	private static String SMS_VERIFICATION_SERVICE_B;
	private static String SMS_VERIFICATION_USERNAME_B;
	private static String SMS_VERIFICATION_PASSWORD_B;

	static {
		SMS_BUSINESS_SERVICE_A = PropertyBootConfig.getContextProperty("SMS_BUSINESS_SERVICE_A");
		SMS_BUSINESS_USERNAME_A = PropertyBootConfig.getContextProperty("SMS_BUSINESS_USERNAME_A");
		SMS_BUSINESS_PASSWORD_A = PropertyBootConfig.getContextProperty("SMS_BUSINESS_PASSWORD_A");

		SMS_BUSINESS_SERVICE_C = PropertyBootConfig.getContextProperty("SMS_BUSINESS_SERVICE_C");
		SMS_BUSINESS_USERNAME_C = PropertyBootConfig.getContextProperty("SMS_BUSINESS_USERNAME_C");
		SMS_BUSINESS_PASSWORD_C = PropertyBootConfig.getContextProperty("SMS_BUSINESS_PASSWORD_C");

		SMS_MARKETING_SERVICE_A = PropertyBootConfig.getContextProperty("SMS_MARKETING_SERVICE_A");
		SMS_MARKETING_USERNAME_A = PropertyBootConfig.getContextProperty("SMS_MARKETING_USERNAME_A");
		SMS_MARKETING_PASSWORD_A = PropertyBootConfig.getContextProperty("SMS_MARKETING_PASSWORD_A");

		SMS_MARKETING_SERVICE_C = PropertyBootConfig.getContextProperty("SMS_MARKETING_SERVICE_C");
		SMS_MARKETING_USERNAME_C = PropertyBootConfig.getContextProperty("SMS_MARKETING_USERNAME_C");
		SMS_MARKETING_PASSWORD_C = PropertyBootConfig.getContextProperty("SMS_MARKETING_PASSWORD_C");

		SMS_EVALUATE_SERVICE_C = PropertyBootConfig.getContextProperty("SMS_EVALUATE_SERVICE_C");
		SMS_EVALUATE_USERNAME_C = PropertyBootConfig.getContextProperty("SMS_EVALUATE_USERNAME_C");
		SMS_EVALUATE_PASSWORD_C = PropertyBootConfig.getContextProperty("SMS_EVALUATE_PASSWORD_C");

		SMS_VERIFICATION_SERVICE_A = PropertyBootConfig.getContextProperty("SMS_VERIFICATION_SERVICE_A");
		SMS_VERIFICATION_USERNAME_A = PropertyBootConfig.getContextProperty("SMS_VERIFICATION_USERNAME_A");
		SMS_VERIFICATION_PASSWORD_A = PropertyBootConfig.getContextProperty("SMS_VERIFICATION_PASSWORD_A");

		SMS_VERIFICATION_SERVICE_B = PropertyBootConfig.getContextProperty("SMS_VERIFICATION_SERVICE_B");
		SMS_VERIFICATION_USERNAME_B = PropertyBootConfig.getContextProperty("SMS_VERIFICATION_USERNAME_B");
		SMS_VERIFICATION_PASSWORD_B = PropertyBootConfig.getContextProperty("SMS_VERIFICATION_PASSWORD_B");
	}

	/**
	 * 业务 e城e家 主通道
	 */
	public static boolean sendSMS(SMSMessage message) {
		boolean flag = false;
		Map<String, Object> map = new HashMap<>();
		try {
			map.put("username", URLEncoder.encode(SMS_BUSINESS_USERNAME_A, "utf-8"));
			map.put("password", URLEncoder.encode(MD5Util.MD5(SMS_BUSINESS_PASSWORD_A), "utf-8"));
			map.put("mobile", URLEncoder.encode(message.getMobilephoneNO(), "utf-8"));
			map.put("content", message.getContent());
			map.put("ext", URLEncoder.encode("", "utf-8"));
			map.put("schtime", URLEncoder.encode("", "utf-8"));
			map.put("rrid", URLEncoder.encode("", "utf-8"));
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
		try {
			String response = HttpClientUtils.doPost(SMS_BUSINESS_SERVICE_A, map);
			logger.debug("业务 e城e家 主通道 ---> 短信平台响应结果:{}", response);
			String responseCode = DocumentHelper.parseText(response).getStringValue();
			flag = !responseCode.startsWith("-"); // 负数状态为失败,详见《主短信平台(内部连接)》文档
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return flag;
	}

	/**
	 * 业务 新奥-e城e家 主通道(员工)
	 *
	 * @param message
	 * @return
	 */
	public static boolean sendSMS2(SMSMessage message) {
		boolean flag = false;
		Map<String, Object> map = new HashMap<>();
		try {
			map.put("username", URLEncoder.encode(SMS_BUSINESS_USERNAME_C, "utf-8"));
			map.put("password", URLEncoder.encode(MD5Util.MD5(SMS_BUSINESS_PASSWORD_C), "utf-8"));
			map.put("mobile", URLEncoder.encode(message.getMobilephoneNO(), "utf-8"));
			map.put("content", message.getContent());
			map.put("ext", URLEncoder.encode("", "utf-8"));
			map.put("schtime", URLEncoder.encode("", "utf-8"));
			map.put("rrid", URLEncoder.encode("", "utf-8"));
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
		try {
			String response = HttpClientUtils.doPost(SMS_BUSINESS_SERVICE_C, map);
			// logger.debug("业务 新奥-e城e家 主通道 ---> 短信平台响应结果:{}", response);
			String responseCode = DocumentHelper.parseText(response).getStringValue();
			flag = !responseCode.startsWith("-"); // 负数状态为失败,详见《主短信平台(内部连接)》文档
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return flag;
	}

	/**
	 * 营销 e城e家 主通道
	 *
	 * @param message
	 * @return
	 */
	public static boolean sendSMS3(SMSMessage message) {
		boolean flag = false;
		Map<String, Object> map = new HashMap<>();
		try {
			map.put("username", URLEncoder.encode(SMS_MARKETING_USERNAME_A, "utf-8"));
			map.put("password", URLEncoder.encode(MD5Util.MD5(SMS_MARKETING_PASSWORD_A), "utf-8"));
			map.put("mobile", URLEncoder.encode(message.getMobilephoneNO(), "utf-8"));
			map.put("content", message.getContent());
			map.put("ext", URLEncoder.encode("", "utf-8"));
			map.put("schtime", URLEncoder.encode("", "utf-8"));
			map.put("rrid", URLEncoder.encode("", "utf-8"));
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
		try {
			String response = HttpClientUtils.doPost(SMS_MARKETING_SERVICE_A, map);
			logger.debug("营销 e城e家 主通道 ---> 短信平台响应结果:{}", response);
			String responseCode = DocumentHelper.parseText(response).getStringValue();
			flag = !responseCode.startsWith("-"); // 负数状态为失败,详见《主短信平台(内部连接)》文档
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return flag;
	}

	/**
	 * 营销 新奥-e城e家 主通道 && 业务 新奥-e城e家 主通道(用户)
	 *
	 * @param message
	 * @return
	 */
	public static boolean sendSMS4(SMSMessage message) {
		boolean flag = false;
		Map<String, Object> map = new HashMap<>();
		try {
			map.put("username", URLEncoder.encode(SMS_MARKETING_USERNAME_C, "utf-8"));
			map.put("password", URLEncoder.encode(MD5Util.MD5(SMS_MARKETING_PASSWORD_C), "utf-8"));
			map.put("mobile", URLEncoder.encode(message.getMobilephoneNO(), "utf-8"));
			map.put("content", message.getContent());
			map.put("ext", URLEncoder.encode("", "utf-8"));
			map.put("schtime", URLEncoder.encode("", "utf-8"));
			map.put("rrid", URLEncoder.encode("", "utf-8"));
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
		try {
			String response = HttpClientUtils.doPost(SMS_MARKETING_SERVICE_C, map);
			logger.info("营销 新奥-e城e家 主通道 ---> 短信平台响应结果:{}", response);
			String responseCode = DocumentHelper.parseText(response).getStringValue();
			flag = !responseCode.startsWith("-"); // 负数状态为失败,详见《主短信平台(内部连接)》文档
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return flag;
	}

	/**
	 * <p>
	 * 功能描述：评价 新奥-e城e家 主通道
	 * </p>
	 *
	 * @author 张军保
	 * @since JDK1.8
	 *        <p>
	 *        创建日期: 2016/7/23
	 *        </p>
	 */
	public static boolean sendSMS5(SMSMessage message) {
		boolean flag = false;
		try {
			Element root = DocumentHelper.createElement("Group");
			Document document = DocumentHelper.createDocument(root);
			root.addAttribute("Login_Name", SMS_EVALUATE_USERNAME_C)
					.addAttribute("Login_Pwd", MD5Util.MD5(SMS_EVALUATE_PASSWORD_C)).addAttribute("OpKind", "0")
					.addAttribute("InterFaceID", "").addAttribute("SerType", "评价");
			root.addElement("E_Time");
			Element item = root.addElement("Item");
			Element task = item.addElement("Task");

			task.addElement("Recive_Phone_Number").setText(message.getMobilephoneNO());
			task.addElement("Content").setText(message.getContent());
			task.addElement("Search_ID").setText(message.getId() + "_" + message.getWorkOrderNo());
			task.addElement("extNo").setText(RandomStringUtils.randomNumeric(5));
			logger.debug("请求 XML 信息：{}", document.asXML());
			String response = HttpClientUtils.doPost(SMS_EVALUATE_SERVICE_C, document.asXML());
			logger.info("评价 新奥-e城e家 主通道 ---> 短信平台响应结果:{}", response);
			if (response.equals("00")) {
				flag = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return flag;
	}

	/**
	 * <p>
	 * 功能描述：验证码 e城e家 备通道
	 * </p>
	 *
	 * @author 张军保
	 * @since JDK1.8
	 *        <p>
	 *        创建日期: 2016/7/23
	 *        </p>
	 */
	public static boolean sendSMS6(SMSMessage message) {
		boolean flag = false;
		try {
			Element root = DocumentHelper.createElement("Group");
			Document document = DocumentHelper.createDocument(root);
			root.addAttribute("Login_Name", SMS_VERIFICATION_USERNAME_B)
					.addAttribute("Login_Pwd", MD5Util.MD5(SMS_VERIFICATION_PASSWORD_B)).addAttribute("OpKind", "0")
					.addAttribute("InterFaceID", "").addAttribute("SerType", "'Smit");
			root.addElement("E_Time");
			Element item = root.addElement("Item");
			Element task = item.addElement("Task");

			task.addElement("Recive_Phone_Number").setText(message.getMobilephoneNO());
			task.addElement("Content").setText(message.getContent());
			task.addElement("Search_ID").setText(message.getId() + "_" + message.getWorkOrderNo());
			logger.debug("请求 XML 信息：{}", document.asXML());
			String response = HttpClientUtils.doPost(SMS_VERIFICATION_SERVICE_B, document.asXML());
			logger.info("验证码 e城e家 备通道 ---> 短信平台响应结果:{}", response);
			if (response.equals("00")) {
				flag = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return flag;
	}

	/**
	 * <p>
	 * 功能描述：验证码 e城e家 主通道
	 * </p>
	 *
	 * @author 张军保
	 * @since JDK1.8
	 *        <p>
	 *        创建日期: 2016/7/23
	 *        </p>
	 */
	public static boolean sendSMS7(SMSMessage message) {
		try {
			Map<String, Object> map = new HashedMap();
			map.put("Sn", SMS_VERIFICATION_USERNAME_A);
			map.put("pwd", MD5Util.MD5(SMS_VERIFICATION_USERNAME_A + SMS_VERIFICATION_PASSWORD_A));
			map.put("mobile", message.getMobilephoneNO());
			map.put("Content", message.getContent() + " 【e城e家】");
			map.put("Ext", "3");
			map.put("Stime", "");
			map.put("Rrid", "");
			map.put("msgfmt", "");
			String response = HttpClientUtils.doPost(SMS_VERIFICATION_SERVICE_A, map);
			logger.info("验证码 e城e家 主通道 ---> 短信平台响应结果:{}", response);
			return !response.startsWith("-");// 负数状态为失败,详见《主短信平台（直连）http》
		} catch (Exception e) {
			logger.error("ecej主通道验证码发送失败", e);
		}
		return false;
	}

}
