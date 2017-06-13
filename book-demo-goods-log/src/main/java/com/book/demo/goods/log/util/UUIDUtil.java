package com.book.demo.goods.log.util;

import java.util.UUID;

/**
 * @描述 : uuid工具类
 * @创建者：liushengsong
 * @创建时间： 2014-6-6上午8:45:16
 * 
 */
public class UUIDUtil {

	/**
	 * @描述 : 获取一个UUID标识(小写)
	 * @创建者：liushengsong
	 * @创建时间： 2014-5-5下午5:31:53
	 * 
	 * @return
	 */
	public static String uuid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

	/**
	 * @描述 : 获取一个UUID标识(大写)
	 * @创建者：liushengsong
	 * @创建时间： 2014-6-6上午8:50:02
	 * 
	 * @return
	 */
	public static String upperUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "").toUpperCase();
	}

}
