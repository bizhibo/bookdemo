package com.book.demo.user.util;

import java.lang.reflect.Field;

import org.bson.Document;

/**
 * @描述 : 转换工具类
 * @创建者：liushengsong
 * @创建时间： 2017年2月20日下午3:00:17
 *
 */
public class ConvertUtil {

	/**
	 * @描述 : 对象转换为Document
	 * @创建者：liushengsong
	 * @创建时间： 2017年2月20日下午3:02:40
	 *
	 * @param o
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Document convertDoc(Object o)
			throws IllegalArgumentException, IllegalAccessException {
		Document document = new Document();
		Field[] fields = o.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			document.append(field.getName(), field.get(o));
		}
		return document;
	}

	/**
	 * @描述 : 对象转换为Document
	 * @创建者：liushengsong
	 * @创建时间： 2017年2月20日下午3:04:40
	 *
	 * @param o
	 *            追加对象
	 * @param doc
	 *            已有的document
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Document convertDoc(Object o, Document doc)
			throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = o.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			doc.append(field.getName(), field.get(o));
		}
		return doc;
	}

}
