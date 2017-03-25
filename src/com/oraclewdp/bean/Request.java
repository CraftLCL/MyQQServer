package com.oraclewdp.bean;

import java.io.Serializable;

public class Request implements Serializable {
	private static final long serialVersionUID = 1L;
	//请求服务名属性
	private String requestServiceName;
	//用户属性
	private User user;
	private User toUser;
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRequestServiceName() {
		return requestServiceName;
	}
	public void setRequestServiceName(String requestServiceName) {
		this.requestServiceName = requestServiceName;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getToUser() {
		return toUser;
	}
	public void setToUser(User toUser) {
		this.toUser = toUser;
	}
	
	

}
