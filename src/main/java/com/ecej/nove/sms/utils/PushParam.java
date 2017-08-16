package com.ecej.nove.sms.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 用户保存推送时使用的参数
 * 
 * @author 耿建委
 *
 */
public class PushParam {
	private int appType;
	private int audiType;
	private List<String> audiences;
	private String title;
	private String msg;
	private Map<String, String> extraData;

	/**
	 * 构造方法
	 * 
	 * @param appType
	 *            应用类型
	 * @param audiType
	 *            受众类型
	 * @param audience
	 *            别名或tag
	 * @param title
	 *            标题
	 * @param msg
	 *            消息内容
	 * @param extraData
	 *            附加数据
	 */
	public PushParam(int appType, int audiType, String audience, String title, String msg,
			Map<String, String> extraData) {
		super();
		this.appType = appType;
		this.audiType = audiType;
		this.title = title;
		this.msg = msg;
		this.extraData = extraData;
		this.audiences = new ArrayList<>();
		this.audiences.add(audience);
	}

	/**
	 * 构造方法
	 * 
	 * @param appType
	 *            应用类型
	 * @param audiType
	 *            受众类型
	 * @param audience
	 *            别名或tag
	 * @param title
	 *            标题
	 * @param msg
	 *            消息内容
	 * @param extraData
	 *            附加数据
	 */
	public PushParam(int appType, int audiType, List<String> audience, String title, String msg,
			Map<String, String> extraData) {
		super();
		this.appType = appType;
		this.audiType = audiType;
		this.audiences = audience;
		this.title = title;
		this.msg = msg;
		this.extraData = extraData;
	}

	public int getAppType() {
		return appType;
	}

	public void setAppType(int appType) {
		this.appType = appType;
	}

	public int getAudiType() {
		return audiType;
	}

	public void setAudiType(int type) {
		this.audiType = type;
	}

	public List<String> getAudiences() {
		return audiences;
	}

	public void setAudiences(List<String> audiences) {
		this.audiences = audiences;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, String> getExtraData() {
		return extraData;
	}

	public void setExtraData(Map<String, String> extraData) {
		this.extraData = extraData;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
