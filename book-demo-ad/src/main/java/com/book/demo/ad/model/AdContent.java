package com.book.demo.ad.model;

import java.io.Serializable;

/**
 * @描述 : 广告内容
 * @创建者：liushengsong
 * @创建时间： 2017年2月18日下午3:06:40
 *
 */
public class AdContent implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/** 广告内容ID **/
	private int id;
	/** 广告内容名称 **/
	private String name;
	/** 广告链接URL **/
	private String url;
	/** 广告图片URL **/
	private String imageUrl;
	/** 广告序号 **/
	private int sequence;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

}
