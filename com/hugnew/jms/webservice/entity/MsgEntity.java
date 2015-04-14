/**  
 * @Title: MsgEntity.java 
 * @Package com.hugnew.jms.webservice.entity 
 * @Description: TODO
 * @author Martin
 * @date 2015年3月27日 上午8:58:24 
 * @version V1.0
 * @Copyright: Copyright (c) SUPUY Co.Ltd. 2015
 * ★★★★★★★★版权所有※拷贝必究 ★★★★★★★★
*/ 
package com.hugnew.jms.webservice.entity;

/** 
 * @ClassName: MsgEntity 
 * @Description: TODO
 * @author Martin
 * @date 2015年3月27日 上午8:58:24
 * @version V1.0 
 */
public class MsgEntity {
	private String goodsId;//活动id
	private String flashId;//特卖id
	private String categoryId;//分类id
	private String brandId;//品牌id
	private String searchText;//搜索词10字之内
	private String content;//通知显示内容15字之内
	private String title;//通知显示标题10字之内
	private int type;//类型0默认1搜索2品牌3分类4特卖5活动
	
	
	/**
	 * @return the goodsId
	 */
	public String getGoodsId() {
		return goodsId;
	}
	/**
	 * @param goodsId the goodsId to set
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * @return the flashId
	 */
	public String getFlashId() {
		return flashId;
	}
	/**
	 * @param flashId the flashId to set
	 */
	public void setFlashId(String flashId) {
		this.flashId = flashId;
	}
	/**
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return the brandId
	 */
	public String getBrandId() {
		return brandId;
	}
	/**
	 * @param brandId the brandId to set
	 */
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	/**
	 * @return the searchText
	 */
	public String getSearchText() {
		return searchText;
	}
	/**
	 * @param searchText the searchText to set
	 */
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	/* (非 Javadoc) 
	 * <p>Title: toString</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see java.lang.Object#toString()
	 * @author Martin
	 * @date 2015年3月31日 下午4:55:27
	 * @version V1.0
	*/
	@Override
	public String toString() {
		return "MsgEntity [goodsId=" + goodsId + ", flashId=" + flashId
				+ ", categoryId=" + categoryId + ", brandId=" + brandId
				+ ", searchText=" + searchText + ", content=" + content
				+ ", title=" + title + ", type=" + type + "]";
	}

	
}
