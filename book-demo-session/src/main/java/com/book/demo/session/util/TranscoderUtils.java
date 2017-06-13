package com.book.demo.session.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @描述 : 转码器工具类
 * @创建者：liushengsong
 * @创建时间： 2014-6-13下午3:38:32
 * 
 */
public class TranscoderUtils {

	/**
	 * @描述 : 将对象转码为byte
	 * @创建者：liushengsong
	 * @创建时间： 2014-6-16上午8:53:21
	 * 
	 * @param o
	 * @return
	 */
	public static byte[] encodeObject(Object o) {
		return compress(serialize(o));
	}

	/**
	 * @描述 : 将byte解码转换为对象
	 * @创建者：liushengsong
	 * @创建时间： 2014-6-16上午9:03:18
	 * 
	 * @param b
	 * @return
	 */
	public static Object decodeObject(byte[] b) {
		return deserialize(decompress(b));
	}

	/**
	 * @描述 : 转码
	 * @创建者：liushengsong
	 * @创建时间： 2014-6-16上午9:03:41
	 * 
	 * @param in
	 * @return
	 * @throws RuntimeException
	 */
	private static byte[] compress(byte[] in) throws RuntimeException {
		if (in == null) {
			throw new NullPointerException("Can't compress null");
		}
		ByteArrayOutputStream bos = null;
		GZIPOutputStream gz = null;
		try {
			bos = new ByteArrayOutputStream();
			gz = new GZIPOutputStream(bos);
			gz.write(in);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("IO exception compressing data", e);
		} finally {
			CloseUtil.close(gz);
			CloseUtil.close(bos);
		}
		return bos.toByteArray();
	}

	/**
	 * @描述 : 解码
	 * @创建者：liushengsong
	 * @创建时间： 2014-6-16上午9:03:51
	 * 
	 * @param in
	 * @return
	 * @throws RuntimeException
	 */
	private static byte[] decompress(byte[] in) throws RuntimeException {
		if (in != null) {
			ByteArrayOutputStream byteArrayOutputStream = null;
			ByteArrayInputStream byteArrayInputStream = null;
			GZIPInputStream gzipInputStream = null;
			try {
				byteArrayInputStream = new ByteArrayInputStream(in);
				byteArrayOutputStream = new ByteArrayOutputStream();
				gzipInputStream = new GZIPInputStream(byteArrayInputStream);
				byte[] buf = new byte[8192];
				int r = -1;
				while ((r = gzipInputStream.read(buf)) > 0) {
					byteArrayOutputStream.write(buf, 0, r);
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("IO exception decompress data", e);
			} finally {
				CloseUtil.close(gzipInputStream);
				CloseUtil.close(byteArrayInputStream);
				CloseUtil.close(byteArrayOutputStream);
			}
			return byteArrayOutputStream.toByteArray();
		} else {
			return null;
		}
	}

	/**
	 * @描述 : 序列化
	 * @创建者：liushengsong
	 * @创建时间： 2014-6-16上午9:04:01
	 * 
	 * @param o
	 * @return
	 */
	private static byte[] serialize(Object o) {
		if (o == null) {
			throw new NullPointerException("Can't serialize null");
		}
		ByteArrayOutputStream byteArrayOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(o);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Non-serializable object", e);
		} finally {
			CloseUtil.close(objectOutputStream);
			CloseUtil.close(byteArrayOutputStream);
		}
		return byteArrayOutputStream.toByteArray();
	}

	/**
	 * @描述 : 反序列化
	 * @创建者：liushengsong
	 * @创建时间： 2014-6-16上午9:04:25
	 * 
	 * @param in
	 * @return
	 */
	private static Object deserialize(byte[] in) {
		if (in != null) {
			ByteArrayInputStream byteArrayInputStream = null;
			ObjectInputStream objectInputStream = null;
			Object obj = null;
			try {
				if (in != null) {
					byteArrayInputStream = new ByteArrayInputStream(in);
					objectInputStream = new ObjectInputStream(
							byteArrayInputStream);
					obj = objectInputStream.readObject();
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				CloseUtil.close(objectInputStream);
				CloseUtil.close(byteArrayInputStream);
			}
			return obj;
		} else {
			return null;
		}
	}
}
