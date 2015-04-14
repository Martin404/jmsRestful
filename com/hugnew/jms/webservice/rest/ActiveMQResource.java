/**  
 * @Title: NotSerialize.java 
 * @Package com.hugnew.jms.webservice.annotation 
 * @author Martin
 * @version V1.0
 * @Copyright: Copyright (c) SUPUY Co.Ltd. 2015
 */
package com.hugnew.jms.webservice.rest;


import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQTopic;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.springframework.beans.factory.annotation.Autowired;

import com.hugnew.jms.webservice.entity.MsgEntity;
import com.hugnew.jms.webservice.service.ActiveMQProducerService;
import com.hugnew.jms.webservice.util.JsonUtils;
import com.hugnew.jms.webservice.util.StringUtil;

/**
 * @ClassName: ActiveMQProducerServiceImpl
 * @Description: TODO
 * @author Martin
 * @date 2015年3月26日 上午10:40:00
 * @version V1.0
 */
public class ActiveMQResource extends ServerResource {
	@Autowired
	private ActiveMQProducerService activeMQProducerService;

	/*@Autowired
	@Qualifier("topicDestination")*/
	/** 
	 * @Title: sendMsg 
	 * @Description: 推送数据restful接口
	 * @param @return
	 * @return String
	 * @throws 
	 * @author Martin
	 * @date 2015年3月27日 上午9:43:25
	 * @version V1.0
	*/ 
	@Get
	public String sendMsg() {
		try {
			MsgEntity msgEntity=new MsgEntity();
			//获取查询参数
			Form form = getRequest().getResourceRef().getQueryAsForm() ;
			String destinationName= form.getFirstValue("destinationName");
			String goodsId= form.getFirstValue("goodsId");
			String flashId= form.getFirstValue("flashId");
			String categoryId= form.getFirstValue("categoryId");
			String brandId= form.getFirstValue("brandId");
			String searchText= form.getFirstValue("searchText");
			String content= form.getFirstValue("content");
			String title= form.getFirstValue("title");
			String type= form.getFirstValue("type");
			String scope= form.getFirstValue("scope");
			//请求参数校验
			if(StringUtil.isEmpty(destinationName)||StringUtil.isEmpty(content)||StringUtil.isEmpty(title)||StringUtil.isEmpty(type)){
				return JsonUtils.getError("请求必填参数值含有空值", null);
			}
			msgEntity.setGoodsId(goodsId);
			msgEntity.setBrandId(brandId);
			msgEntity.setCategoryId(categoryId);
			msgEntity.setContent(content);
			msgEntity.setFlashId(flashId);
			msgEntity.setSearchText(searchText);
			msgEntity.setTitle(title);
		    msgEntity.setType(Integer.parseInt(type));
			//根据设备标识判断推送范围，0或空为全部推送，1为安卓推送，2为苹果推送
			if(StringUtil.isEmpty(scope)||scope.equals("0")){
				//安卓推送
				Destination destination=new ActiveMQTopic(destinationName);
				activeMQProducerService.sendMsg(destination, msgEntity);
				//苹果推送
				activeMQProducerService.pushAll4Apple(msgEntity);
			}else if(scope.equals("1")){
				//安卓推送
				Destination destination=new ActiveMQTopic(destinationName);
				activeMQProducerService.sendMsg(destination, msgEntity);
			}else{
				//苹果推送
				activeMQProducerService.pushAll4Apple(msgEntity);
			}
			
			return JsonUtils.getOK("", true);
		} catch (Exception e) {
			return JsonUtils.getError(e.getMessage(), null);
		}
	}


	@Post
	public String sendMsg2(Representation entity) {
		return sendMsg();
	}
}
