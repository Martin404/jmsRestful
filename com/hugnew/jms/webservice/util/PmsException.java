package com.hugnew.jms.webservice.util;

public class PmsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		private Long  errorCode;//错误码。
		

		
		
	
	
		public  PmsException() {  
			super();  
		}  
		public PmsException(String msg) {  
		super(msg);  
		}  
		public PmsException(String msg, Throwable cause) {  
		super(msg, cause);  
		}  
		public PmsException(Throwable cause) {  
			super(cause);  
		}
		public void setErrorCode(Long errorCode) {
			this.errorCode = errorCode;
		}
		public Long getErrorCode() {
			return errorCode;
		}  
		
}
