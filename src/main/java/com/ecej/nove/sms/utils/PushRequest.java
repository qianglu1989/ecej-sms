package com.ecej.nove.sms.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ecej.nove.utils.common.PropertyConfigUtils;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.PushPayload.Builder;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 推送请求处理类
 * 
 * @author 耿建委
 */
public class PushRequest {

	/** app类型：用户app */
	public static final int APP_USER = 1;
	/** app类型：员工app */
	public static final int APP_EMP = 2;

	/** 受众类型：别名 */
	public static final int AUDI_ALIAS = 1;
	/** 受众类型：标签 */
	public static final int AUDI_TAG = 2;

	/** 极光推送帐号等：用户APP */
	private static final String USER_APP_KEY;
	private static final String USER_SECRET_KEY;
	/** 极光推送帐号等：员工APP */
	private static final String EMP_APP_KEY;
	private static final String EMP_SECRET_KEY;

	/** 日志 */
	private static final Logger log = LoggerFactory.getLogger(PushRequest.class);

	/** 线程池 */
	private static ThreadPoolExecutor executor;
	/** 配置 **/
	private static final int corePoolSize;// 核心线程数量
	private static final int maximumPoolSize;// 最大线程数量
	private static final int keepAliveTime;// 空闲时间
	private static final int workQueue;// 最大等待线程数量
	private static final boolean apnsProduction;// IOS开发还是测试

	static {
		try {
			USER_APP_KEY = PropertyConfigUtils.getProperty("ecej.push.userAppKey");
			USER_SECRET_KEY = PropertyConfigUtils.getProperty("ecej.push.userSecretKey");
			EMP_APP_KEY = PropertyConfigUtils.getProperty("ecej.push.empAppKey");
			EMP_SECRET_KEY = PropertyConfigUtils.getProperty("ecej.push.empSecretKey");
			corePoolSize = Integer.parseInt(PropertyConfigUtils.getProperty("ecej.push.thread.corePoolSize", "5"));
			maximumPoolSize = Integer
					.parseInt(PropertyConfigUtils.getProperty("ecej.push.thread.maximumPoolSize", "10"));
			keepAliveTime = Integer.parseInt(PropertyConfigUtils.getProperty("ecej.push.thread.keepAliveTime", "3"));
			workQueue = Integer.parseInt(PropertyConfigUtils.getProperty("ecej.push.thread.workQueue", "200"));
			apnsProduction = Boolean.valueOf(PropertyConfigUtils.getProperty("ecej.push.apnsProduction", "true"));
			executor = initExecutor();
		} catch (Exception e) {
			log.error("读取配置文件错误");
			throw new RuntimeException("读取配置文件错误");
		}
	}

	public static void waiteForClose() {
		executor.shutdown();
		try {
			executor.awaitTermination(5, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			log.warn("等待关闭时发生错误。", e);
		}
	}

	/**
	 * 初始化线程池
	 * 
	 * @return
	 */
	private static ThreadPoolExecutor initExecutor() {
		// 最大等待线程200个
		BlockingQueue<Runnable> e = new ArrayBlockingQueue<>(workQueue);
		// 核心数：5 最大：10 空闲时间3分钟
		return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MINUTES, e);
	}

	/**
	 * 使用别名向用户推送
	 * 
	 * @param alias
	 *            别名
	 * @param title
	 *            标题
	 * @param msg
	 *            内容
	 * @param extraData
	 *            附加数据
	 */
	public static void pushToUser(String alias, String title, String msg, Map<String, String> extraData) {
		List<String> list = new ArrayList<>(1);
		list.add(alias);
		// 启动推送线程
		startProcess(new PushParam(APP_USER, AUDI_ALIAS, alias, title, msg, extraData));
	}

	/**
	 * 使用别名向用户推送
	 * 
	 * @param alias
	 *            别名
	 * @param title
	 *            标题
	 * @param msg
	 *            内容
	 * @param extraData
	 *            附加数据
	 */
	public static void pushToUser(List<String> alias, String title, String msg, Map<String, String> extraData) {
		// 启动推送线程
		startProcess(new PushParam(APP_USER, AUDI_ALIAS, alias, title, msg, extraData));
	}

	/**
	 * 使用标签向用户推送
	 * 
	 * @param tags
	 *            标签
	 * @param title
	 *            标题
	 * @param msg
	 *            内容
	 * @param extraData
	 *            附加数据
	 */
	public static void pushToUserTags(List<String> tags, String title, String msg, Map<String, String> extraData) {
		// 启动推送线程
		startProcess(new PushParam(APP_USER, AUDI_TAG, tags, title, msg, extraData));
	}

	/**
	 * 使用别名向员工推送
	 * 
	 * @param alias
	 *            别名
	 * @param title
	 *            标题
	 * @param msg
	 *            内容
	 * @param extraData
	 *            附加数据
	 */
	public static void pushToEmp(String alias, String title, String msg, Map<String, String> extraData) {
		List<String> list = new ArrayList<>(1);
		list.add(alias);
		// 启动推送线程
		startProcess(new PushParam(APP_EMP, AUDI_ALIAS, alias, title, msg, extraData));
	}

	/**
	 * 启动推送线程
	 */
	private static void startProcess(final PushParam param) {
		executor.execute(new Runnable() {
			@Override
			public void run() {
				pushMsg(param);
			}
		});
	}

	/**
	 * 推送消息
	 * 
	 * @param pushParam
	 */
	protected static void pushMsg(PushParam param) {
		if (log.isInfoEnabled()) {
			log.info("正在推送数据：" + param.toString());
		}
		// 组织消息数据
		Builder msgBuilder = PushPayload.newBuilder();
		msgBuilder.setPlatform(Platform.all());
		// 受众是别名时，添加别名受众
		if (param.getAudiType() == AUDI_ALIAS) {
			msgBuilder.setAudience(Audience.alias(param.getAudiences()));
		} else {
			msgBuilder.setAudience(Audience.tag(param.getAudiences()));
		}
		// 没有附加数据时
		if (param.getExtraData() == null) {
			// 仅仅给android平台追加消息标题
			msgBuilder.setNotification(Notification.newBuilder().setAlert(param.getMsg())
					.addPlatformNotification(AndroidNotification.newBuilder().setTitle(param.getTitle()).build())
					.build());
		} else {
			// 追加附加数据，安卓的标题；ios的消息条数等
			msgBuilder.setNotification(Notification.newBuilder().setAlert(param.getMsg())
					.addPlatformNotification(AndroidNotification.newBuilder().setTitle(param.getTitle())
							.addExtras(param.getExtraData()).build())
					.addPlatformNotification(IosNotification.newBuilder().addExtras(param.getExtraData()).build())
					.build());
		}
		// 设置推送证书选项
		msgBuilder.setOptions(Options.newBuilder().setApnsProduction(apnsProduction).build());

		// 发送消息
		JPushClient jpushClient;
		if (APP_USER == param.getAppType()) {
			jpushClient = new JPushClient(USER_SECRET_KEY, USER_APP_KEY);
		} else {
			jpushClient = new JPushClient(EMP_SECRET_KEY, EMP_APP_KEY);
		}

		PushResult result = null;
		try {
			result = jpushClient.sendPush(msgBuilder.build());
			if (log.isInfoEnabled()) {
				log.info("推送结果：" + result.toString());
			}
		} catch (APIConnectionException e) {
			log.error("连接错误，稍后重试", e);
		} catch (APIRequestException e) {
			log.error("请求参数错误", e);
			if (log.isInfoEnabled()) {
				log.info(String.format("HTTP状态码： %d 错误代码：%s 错误信息：%s", e.getStatus(), e.getErrorCode(),
						e.getErrorMessage()));
			}
		}
	}
}
