/**  
 * @Title: ActiveMQProducerService.java 
 * @Package com.hugnew.jms.webservice.service 
 * @Description: TODO
 * @author Martin
 * @date 2015年3月26日 上午10:45:26 
 * @version V1.0
 * @Copyright: Copyright (c) SUPUY Co.Ltd. 2015
*/ 
package com.hugnew.jms.webservice.service;

import javax.jms.Destination;

import com.hugnew.jms.webservice.entity.MsgEntity;

/** 
 * @ClassName: ActiveMQProducerService 
 * @Description: TODO
 * @author Martin
 * @date 2015年3月26日 上午10:45:26
 * @version V1.0 
 */
public interface ActiveMQProducerService {
	 public void sendMsg(Destination destination,final MsgEntity msgEntity);
	 public void pushAll4Apple(MsgEntity msgEntity);
}
