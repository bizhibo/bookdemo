package com.book.demo.ad.model;

import java.io.Serializable;
import java.util.List;

/**
 * @描述 : 广告位
 * @创建者：liushengsong
 * @创建时间： 2017年2月18日下午3:06:24
 *
 */
public class Advertisement implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 广告位ID **/
	private int id;
	/** 广告位代码 **/
	private String positionCode;
	/** 广告内容集合 **/
	private int tid;
	/** 广告模版ID **/
	private List<AdContent> adContents;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public List<AdContent> getAdContents() {
		return adContents;
	}

	public void setAdContents(List<AdContent> adContents) {
		this.adContents = adContents;
	}

}
