package com.book.demo.user.model;

/**
 * @描述 : 用户信息
 * @创建者：liushengsong
 * @创建时间： 2017年2月20日下午2:56:54
 *
 */
public class UserInfo {

	/** 用户名 **/
	private String userName;
	/** 昵称 **/
	private String nickName;
	/** 姓名 **/
	private String name;
	/** 年龄 **/
	private int age;
	/** 性别 **/
	private String sex;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
