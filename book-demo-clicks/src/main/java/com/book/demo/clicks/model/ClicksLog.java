package com.book.demo.clicks.model;

/**
 * @描述 : 点击日志
 * @创建者：liushengsong
 * @创建时间： 2017年2月27日上午9:26:25
 *
 */
public class ClicksLog {

	/** 页面编号 **/
	private String pageCode;
	/** 页面地址 **/
	private String url;
	/** 点击位置 **/
	private String clickPosition;
	/** 页面内容 **/
	private String pageContent;

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getClickPosition() {
		return clickPosition;
	}

	public void setClickPosition(String clickPosition) {
		this.clickPosition = clickPosition;
	}

	public String getPageContent() {
		return pageContent;
	}

	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}

}
