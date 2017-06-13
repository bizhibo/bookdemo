package com.book.demo.goods.log.util;

import java.io.Closeable;

/**
 * @描述 : io关闭的工具类
 * @创建者：liushengsong
 * @创建时间： 2014-6-16上午11:56:25
 * 
 */
public class CloseUtil {

	public static void close(Closeable closeable) {
		if (closeable != null)
			try {
				closeable.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
