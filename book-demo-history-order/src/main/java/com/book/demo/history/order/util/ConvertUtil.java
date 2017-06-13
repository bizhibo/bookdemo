package com.book.demo.history.order.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
	 * @创建时间： 2017年2月27日下午1:22:33
	 *
	 * @param list
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static List<Document> convertDoc(List<?> list)
			throws IllegalArgumentException, IllegalAccessException {
		List<Document> documents = new ArrayList<Document>();
		for (Object o : list) {
			Document document = new Document();
			Field[] fields = o.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				document.append(field.getName(), field.get(o));
			}
			documents.add(document);
		}

		return documents;
	}

}
