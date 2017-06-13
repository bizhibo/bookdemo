package com.book.demo.ad.model;

import java.io.Serializable;

/**
 * @描述 : 广告模版 
 * @创建者：liushengsong
 * @创建时间： 2017年2月18日下午3:06:08
 *
 */
public class Template implements Serializable{

	private static final long serialVersionUID = 1L;
	/** 广告模版ID **/
	private int id;
	/** 广告模版名称 **/
	private String name;
	/** 广告模版脚本 **/
	private String script;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

}
