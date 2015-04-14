/**  
 * @Title: ActiveMQProducerServiceImpl.java 
 * @Package com.hugnew.jms.webservice.service.impl 
 * @Description: TODO
 * @author Martin
 * @date 2015年3月26日 上午10:40:00 
 * @version V1.0
 * @Copyright: Copyright (c) SUPUY Co.Ltd. 2015
 */
package com.hugnew.jms.webservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import javapns.notification.AppleNotificationServer;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PayloadPerDevice;
import javapns.notification.PushNotificationPayload;
import javapns.notification.transmission.NotificationProgressListener;
import javapns.notification.transmission.NotificationThread;
import javapns.notification.transmission.NotificationThreads;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.hugnew.jms.webservice.entity.AppleTokenEntity;
import com.hugnew.jms.webservice.entity.MsgEntity;
import com.hugnew.jms.webservice.service.ActiveMQProducerService;
import com.hugnew.jms.webservice.util.ResourceUtil;
import com.hugnew.jms.webservice.dao.CommonDao;

/**
 * @ClassName: ActiveMQProducerServiceImpl
 * @Description: TODO
 * @author Martin
 * @date 2015年3月26日 上午10:40:00
 * @version V1.0
 */
@Component
public class ActiveMQProducerServiceImpl implements ActiveMQProducerService {
	private static final Logger logger = Logger
			.getLogger(ActiveMQProducerServiceImpl.class);
	@Autowired
	private CommonDao commonDao;

	/* (非 Javadoc) 
	 * <p>Title: sendMsg</p> 
	 * <p>android端群推 </p> 
	 * @param destination
	 * @param msgEntity 
	 * @see com.hugnew.jms.webservice.service.ActiveMQProducerService#sendMsg(javax.jms.Destination, com.hugnew.jms.webservice.entity.MsgEntity)
	 * @author Martin
	 * @date 2015年4月9日 上午11:19:37
	 * @version V1.0
	*/
	public void sendMsg(Destination destination, final MsgEntity msgEntity) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		CachingConnectionFactory connectionFactory = (CachingConnectionFactory) context
				.getBean("cachingConnectionFactory");
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setDefaultDestination(destination);
		jmsTemplate.setConnectionFactory(connectionFactory);
		jmsTemplate.setReceiveTimeout(100000);
		jmsTemplate.setPubSubDomain(true);
		jmsTemplate.send(destination, new MessageCreator() {

			public Message createMessage(Session session) throws JMSException {
				Gson gson = new Gson();
				String msg = gson.toJson(msgEntity);
				return session.createTextMessage(msg);
			}
		});
		logger.info("push String:" + new Gson().toJson(msgEntity));
		try {
			logger.info("destination:" + ((Topic) destination).getTopicName());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (非 Javadoc) <p>Title: pushAll4Apple</p> <p>apple客户端群推/p>
	 * @param msgEntity
	 * @see
	 * com.hugnew.jms.webservice.service.ActiveMQProducerService#pushAll4Apple
	 * (com.hugnew.jms.webservice.entity.MsgEntity)
	 * @author Martin
	 * @date 2015年4月9日 上午10:59:45
	 * @version V1.0
	 */
	@Override
	public void pushAll4Apple(MsgEntity msgEntity) {
		//群推线程数
		String	pushThreads = ResourceUtil.getConfigByName("applePushAllThread");
		//p12文件地址
		String	appleP12Path = ResourceUtil.getConfigByName("appleP12Path");
		//p12文件密码
		String	appleP12Pwd = ResourceUtil.getConfigByName("appleP12Pwd");
		//苹果推送服务器选择，true为正式服务器，false为开发者服务器
		String	applePushServer = ResourceUtil.getConfigByName("applePushServer");
		try {
			// 建立与Apple服务器连接
			AppleNotificationServer server = new AppleNotificationServerBasicImpl(
					appleP12Path, appleP12Pwd, Boolean.parseBoolean(applePushServer));
			List<PayloadPerDevice> list = new ArrayList<PayloadPerDevice>();
			// 获取要推送的tokenlist
			List<AppleTokenEntity> tokenList = commonDao.loadAll(AppleTokenEntity.class);
			for (AppleTokenEntity tokenEntity : tokenList) {
				Gson gson = new Gson();
				String msg = gson.toJson(msgEntity);
				StringBuilder sb=new StringBuilder();
				PushNotificationPayload payload = new PushNotificationPayload();
				sb.append("{'title':").append(msgEntity.getTitle()).append(",'body':").append(msgEntity.getContent()).append("}");
				payload.addBadge(0);// 图标小红圈的数值
				payload.addCustomAlertBody(msgEntity.getContent());
				payload.addCustomDictionary("data", msg);
//				payload.addCustomDictionary("content-available", 1);
				PayloadPerDevice pay = new PayloadPerDevice(payload,
						tokenEntity.getToken());// 将要推送的消息和手机唯一标识绑定
				list.add(pay);
			}
			NotificationThreads work = new NotificationThreads(server, list,
					Integer.parseInt(pushThreads));//
			work.setListener(DEBUGGING_PROGRESS_LISTENER);// 对线程的监听，一定要加上这个监听
			work.start(); // 启动线程
			work.waitForAllThreads();// 等待所有线程启动完成

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 线程监听
	public static final NotificationProgressListener DEBUGGING_PROGRESS_LISTENER = new NotificationProgressListener() {
		public void eventThreadStarted(NotificationThread notificationThread) {
			logger.info("   [EVENT]: thread #"
					+ notificationThread.getThreadNumber() + " started with "
					+ " devices beginning at message id #"
					+ notificationThread.getFirstMessageIdentifier());
		}

		public void eventThreadFinished(NotificationThread thread) {
			logger.info("   [EVENT]: thread #" + thread.getThreadNumber()
					+ " finished: pushed messages #"
					+ thread.getFirstMessageIdentifier() + " to "
					+ thread.getLastMessageIdentifier() + " toward "
					+ " devices");
		}

		public void eventConnectionRestarted(NotificationThread thread) {
			logger.info("   [EVENT]: connection restarted in thread #"
					+ thread.getThreadNumber() + " because it reached "
					+ thread.getMaxNotificationsPerConnection()
					+ " notifications per connection");
		}

		public void eventAllThreadsStarted(
				NotificationThreads notificationThreads) {
			logger.info("   [EVENT]: all threads started: "
					+ notificationThreads.getThreads().size());
		}

		public void eventAllThreadsFinished(
				NotificationThreads notificationThreads) {
			logger.info("   [EVENT]: all threads finished: "
					+ notificationThreads.getThreads().size());
		}

		public void eventCriticalException(
				NotificationThread notificationThread, Exception exception) {
			logger.info("   [EVENT]: critical exception occurred: " + exception);
		}
	};
}
