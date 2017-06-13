package com.book.demo.session.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.book.demo.session.util.UUIDUtil;

/**
 * @描述 : Session
 * @创建者：liushengsong
 * @创建时间： 2017年2月27日上午9:44:38
 *
 */
public class RedisSession implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 回话ID **/
	private String sessionId = UUIDUtil.uuid();
	/** 存储对象的map **/
	private Map<String, Object> map = new HashMap<String, Object>();

	public String getSessionId() {
		return sessionId;
	}

	public Object getAttribute(String name) {
		return map.get(name);
	}

	public void setAttribute(String name, Object value) {
		map.put(name, value);
	}

	public Map<String, Object> getMap() {
		return map;
	}
	
	
}
