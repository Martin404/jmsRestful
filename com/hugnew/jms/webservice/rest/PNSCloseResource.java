/**  
 * @Title: NotSerialize.java 
 * @Package com.hugnew.jms.webservice.annotation 
 * @author Martin
 * @version V1.0
 * @Copyright: Copyright (c) SUPUY Co.Ltd. 2015
 */
package com.hugnew.jms.webservice.rest;


import java.util.List;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.springframework.beans.factory.annotation.Autowired;

import com.hugnew.jms.webservice.dao.CommonDao;
import com.hugnew.jms.webservice.entity.AppleTokenEntity;
import com.hugnew.jms.webservice.util.JsonUtils;
import com.hugnew.jms.webservice.util.StringUtil;

/**
 * @ClassName: ActiveMQProducerServiceImpl
 * @Description: TODO
 * @author Martin
 * @date 2015年3月26日 上午10:40:00
 * @version V1.0
 */
public class PNSCloseResource extends ServerResource {
	@Autowired
	private CommonDao commonDao;

	/** 
	 * @Title: open4Apple 
	 * @Description:打开苹果客户端推送
	 * @param @return
	 * @return String
	 * @throws 
	 * @author Martin
	 * @date 2015年4月8日 下午3:53:41
	 * @version V1.0
	*/ 
	@Get
	public String close4Apple() {
		try {
			//获取查询参数
			Form form = getRequest().getResourceRef().getQueryAsForm() ;
			String token= form.getFirstValue("token");
			if(StringUtil.isEmpty(token)){
				return JsonUtils.getError("token参数值为null", false);
			}
			List<AppleTokenEntity> appleTokenEntity = commonDao.findByProperty(AppleTokenEntity.class, "token", token);
			if(appleTokenEntity==null||appleTokenEntity.size()==0){
				return JsonUtils.getError("数据库不存在该token记录", false);
			}else if(appleTokenEntity.size()==1){
				commonDao.delete(appleTokenEntity.get(0));
			}else{
				return JsonUtils.getError("数据库该记录不唯一，数据库数据异常", false);
			}
			return JsonUtils.getOK("", true);
		} catch (Exception e) {
			return JsonUtils.getError(e.getMessage(), false);
		}
	}


	@Post
	public String close4Apple2(Representation entity) {
		return close4Apple();
	}
}
