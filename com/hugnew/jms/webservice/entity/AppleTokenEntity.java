/**  
 * @Title: AppleTokenEntity.java 
 * @Package com.hugnew.jms.webservice.entity 
 * @Description: TODO
 * @author Martin
 * @date 2015年4月8日 下午2:38:20 
 * @version V1.0
 * @Copyright: Copyright (c) SUPUY Co.Ltd. 2015
*/ 
package com.hugnew.jms.webservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/** 
 * @ClassName: AppleTokenEntity 
 * @Description: TODO
 * @author Martin
 * @date 2015年4月8日 下午2:38:20
 * @version V1.0 
 */
@Entity
@Table(name = "t_apple_token", schema = "")
@SuppressWarnings("serial")
public class AppleTokenEntity {
	private String id;//主键编号
	private String token;//设备唯一标识
	
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=32)
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the token
	 */
	@Column(name ="TOKEN",nullable=true,length=200)
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	
}