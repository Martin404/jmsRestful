package com.hugnew.jms.webservice.util;

import org.apache.log4j.Logger;

import com.hugnew.jms.webservice.annotation.NotSerialize;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/** 
 * @ClassName: JsonUtils 
 * @author Martin
 * @date 2014年8月18日 下午4:38:13
 * @version V1.0 
 */
public class JsonUtils {
	
	private static Logger logger= Logger.getLogger(JsonUtils.class);
	
	public static String getOK(String message, Object result){
		String jsonStr=getGson().toJson(result);
		if(message==null || "".equals(message)){
			message = "执行成功";
		}
		String res= "{" +
				"\"success\"" + ":" + true + "," + 
				"\"message\"" + ":" + "\"" + message + "\"" + "," + 
				"\"result\"" + ":" + jsonStr + 
				"}";
		logger.info(res);
		return res;
	}
	
	public static String getError(String message, Object result){
		String jsonStr=getGson().toJson(result);
		if(message==null || "".equals(message)){
			message = "执行失败";
		}
		String res= "{" +
				"\"success\"" + ":" + false + "," + 
				"\"message\"" + ":" + "\"" + message + "\"" + "," + 
				"\"result\"" + ":" + jsonStr + 
				"}";
		logger.info(res);
		return res;
	}
	
	
	private static Gson gson; //=new Gson();

	public static Gson getGson() {
		if (gson==null) {
			gson = new GsonBuilder().setExclusionStrategies(new myExclusionStrategy())
			   .setDateFormat("yyyy-MM-dd").create();
		}
		return gson;
	}
	
	private static class myExclusionStrategy implements ExclusionStrategy{
		private final Class<?> typeToSkip;
		
		public myExclusionStrategy(){  
	        this.typeToSkip=null;  
	    }
		
		@SuppressWarnings("unused")
		public myExclusionStrategy(Class<?> typeToSkip) {  
	        this.typeToSkip = typeToSkip;  
	    }
		
		@Override
		public boolean shouldSkipClass(Class<?> clazz) {
			return this.typeToSkip == clazz;
		}
		
		@Override
		public boolean shouldSkipField(FieldAttributes f) {
			return f.getAnnotation(NotSerialize.class) != null;
		}
		
	}

}
