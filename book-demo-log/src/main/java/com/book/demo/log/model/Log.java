package com.book.demo.log.model;

import java.util.Date;

/**
 * @描述 : 日志
 * @创建者：liushengsong
 * @创建时间： 2017年2月18日下午8:44:59
 *
 */
public class Log {

	/** 日志级别 **/
	private String level;
	/** 堆栈信息 **/
	private String stackTrace;
	/** 日志消息 **/
	private String message;
	/** 创建时间 **/
	private Date createdTime;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}
