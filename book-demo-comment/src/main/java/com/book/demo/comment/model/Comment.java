package com.book.demo.comment.model;

import java.util.Date;

/**
 * @描述 : 评价
 * @创建者：liushengsong
 * @创建时间： 2017年2月20日上午11:53:10
 *
 */
public class Comment {
	/** 用户名 **/
	private String userName;
	/** 评价内容 **/
	private String content;
	/** 商品ID **/
	private int pid;
	/** 评价时间 **/
	private Date createdTime;
	/** 评价星级 **/
	private int star;
	/** 标签集合json **/
	private String commentLabels;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getCommentLabels() {
		return commentLabels;
	}

	public void setCommentLabels(String commentLabels) {
		this.commentLabels = commentLabels;
	}

}
