package com.book.demo.order.model;

/**
 * @描述 : 商品信息
 * @创建者：liushengsong
 * @创建时间： 2017年2月27日上午9:44:38
 *
 */
public class Goods {

	/** 商品ID **/
	private int id;
	/** 商品基本信息 **/
	private String goodsInfo;
	/** 规格信息 **/
	private String specificationsInfo;
	/** 广告信息 **/
	private String adInfo;
	/** 数量 **/
	private int num;
	/** 价格 **/
	private int price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(String goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public String getSpecificationsInfo() {
		return specificationsInfo;
	}

	public void setSpecificationsInfo(String specificationsInfo) {
		this.specificationsInfo = specificationsInfo;
	}

	public String getAdInfo() {
		return adInfo;
	}

	public void setAdInfo(String adInfo) {
		this.adInfo = adInfo;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
