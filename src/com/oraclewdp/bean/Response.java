package com.oraclewdp.bean;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.List;

public class Response implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final int LOGIN_SUCCESS = 100;
	public static final int LOGIN_USERNAME_INVALID = 101;
	public static final int LOGIN_PASSWORD_ERROR = 102;
	
	public static final int REGISTER_SUCCESS = 200;
	public static final int REGISTER_FAIL = 201;
	
	public static final int ADDFRIEND_SUCCESS = 300;
	public static final int ADDFRIEND_FAIL = 301;
	public static final int ADDFRIEND_FAIL_ISFRIEND = 302;
	
	private String responseServiceName;
	private int responseCode;
	private User fromUser;
	private User toUser;
	private List<User> friendList;
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getResponseServiceName() {
		return responseServiceName;
	}
	public void setResponseServiceName(String responseServiceName) {
		this.responseServiceName = responseServiceName;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public User getFromUser() {
		return fromUser;
	}
	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}
	public User getToUser() {
		return toUser;
	}
	public void setToUser(User toUser) {
		this.toUser = toUser;
	}
	public List<User> getFriendList() {
		return friendList;
	}
	public void setFriendList(List<User> friendList) {
		this.friendList = friendList;
	}
	/**
	 * 向客户端发送响应
	 * @param socket
	 */
	public void send(Socket socket) {
		try {
			OutputStream in = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(in);
			oos.writeObject(this);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void send(ObjectOutputStream oos){
		try {
			oos.writeObject(this);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
