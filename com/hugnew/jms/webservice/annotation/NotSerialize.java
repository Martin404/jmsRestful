/**  
 * @Title: NotSerialize.java 
 * @Package com.hugnew.jms.webservice.annotation 
 * @author Martin
 * @version V1.0
 * @Copyright: Copyright (c) SUPUY Co.Ltd. 2015
*/ 
package com.hugnew.jms.webservice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @ClassName: NotSerialize 
 * @Description: 针对gson将对象转成json时，如果属性变量加有此注解，那么该变量不会被序列化
 * @author Howard.Sun
 * @date 2015-1-20 下午8:02:49
 * @version V1.0 
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.FIELD}) 
public @interface NotSerialize {

}
