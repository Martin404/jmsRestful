/**  
* @Title: RandomUtil.java 
* @Package com.webservice.util 
* @Description: TODO
* @author Martin
* @date 2013-11-6 下午1:18:28 
* @version V1.0
*/ 
package com.hugnew.jms.webservice.util;

/** 
 * @ClassName: RandomUtil 
 * @Description: TODO
 * @author Martin
 * @date 2013-11-6 下午1:18:28
 * @version V1.0 
 */
public class RandomUtil {

	public static final int MAX = Integer.MAX_VALUE;
	public static final int MIN = MAX/2;
	
	/**
	 * 保持 sendNo 的唯一性是有必要的
	 * It is very important to keep sendNo unique.
	 * @return sendNo
	 */
	public static int getRandomSendNo() {
	    return (int) (MIN + Math.random() * (MAX - MIN));
	}
}
