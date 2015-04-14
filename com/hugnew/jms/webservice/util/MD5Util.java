package com.hugnew.jms.webservice.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Description:密码加密工具类.</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company:Centling</p>
 * @author Martin
 * @version 1.0
 */
public  class MD5Util {
	
	private MD5Util() {
		
	}
	/**
	 * 
	* @Title: encodeStr 
	* @Description: TODO
	* @param @param str
	* @param @return
	* @return String
	* @throws 
	* @author Martin
	* @date 2013-12-19 上午11:25:42
	* @version V1.0
	 */
	public static String encodeStr(String str) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new  RuntimeException();
		}
		//使用指定的字节更新摘要
		Charset charSet = Charset.forName("UTF-8"); //指定字符集
		md5.update(str.getBytes(charSet));
		//通过执行诸如填充之类的最终操作完成哈希计算。
		final byte[] hash = md5.digest();
		//转换为16进制 
		final StringBuilder sb = new StringBuilder();
		final int andNum = 0xff;
		final int moveLeft = 0x10;
		final int length = 16;
		for (int i = 0; i < hash.length; i++) { 
		if ((hash[i] & andNum) < moveLeft) { 
		   sb.append("0"); 
		} 
		sb.append(Long.toString(hash[i] & andNum, length)); 
		} 
		return sb.toString(); 
	}
	
	
	
}
