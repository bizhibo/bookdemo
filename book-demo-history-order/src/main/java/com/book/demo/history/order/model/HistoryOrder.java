package com.book.demo.history.order.model;

import java.util.Date;

/**
 * @描述 : 历史订单
 * @创建者：liushengsong
 * @创建时间： 2017年2月27日上午9:58:03
 *
 */
public class HistoryOrder {

	/** 订单ID **/
	private long orderId;
	/** 下单时间 **/
	private Date createdDate;
	/** 下单用户 **/
	private String userName;
	/** 购买商品json **/
	private String goodsList;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(String goodsList) {
		this.goodsList = goodsList;
	}

}
