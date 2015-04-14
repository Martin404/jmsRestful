/**  
 * @Title: NotSerialize.java 
 * @Package com.supuy.activemq.webservice.annotation 
 * @author Martin
 * @version V1.0
 * @Copyright: Copyright (c) SUPUY Co.Ltd. 2015
 * ★★★★★★★★版权所有※拷贝必究 ★★★★★★★★
*/ 
package com.hugnew.jms.webservice.util;

import java.util.List;

/** 
 * @ClassName: Pagenition 
 * @author Martin
 * @date 2015-1-20 下午4:32:51
 * @version V1.0 
 */
public class Pagenition {
	private int currentPage;
	private int pageRow;
	private int totalRow;
	private int totalPage;
	private List<Object>rows;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageRow() {
		return pageRow;
	}
	public void setPageRow(int pageRow) {
		this.pageRow = pageRow;
	}
	public int getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<Object> getRows() {
		return rows;
	}
	public void setRows(List<Object> rows) {
		this.rows = rows;
	}
	
}
