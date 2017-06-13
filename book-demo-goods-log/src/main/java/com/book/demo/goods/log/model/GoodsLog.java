package com.book.demo.goods.log.model;

import java.util.Date;

/**
 * @描述 : 商品记录信息
 * @创建者：liushengsong
 * @创建时间： 2017年2月27日上午9:44:38
 *
 */
public class GoodsLog {

	/** 点击UUID **/
	private String uuid;
	/** 点击时间 **/
	private Date clickDate;
	/** 点击IP **/
	private String ip;
	/** 商品ID **/
	private int id;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getClickDate() {
		return clickDate;
	}

	public void setClickDate(Date clickDate) {
		this.clickDate = clickDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
