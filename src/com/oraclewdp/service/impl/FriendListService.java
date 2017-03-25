package com.oraclewdp.service.impl;

import java.net.Socket;
import java.util.List;

import javax.swing.JTextArea;

import com.oraclewdp.bean.Request;
import com.oraclewdp.bean.Response;
import com.oraclewdp.bean.User;
import com.oraclewdp.server.config.ServerRunStatus;
import com.oraclewdp.server.thread.ServerThread;
import com.oraclewdp.service.ServerService;
import com.oraclewdp.user.PropertiesOperator;

public class FriendListService implements ServerService {

	@Override
	public void service(Request request, Socket socket, JTextArea infoText,ServerThread serverThread) {
		//添加代码，调用资源文件操作类（PropertiesOperator.java）的getFriendList()方法获得好友列表
		//添加代码，将好友列表响应回客户端。
	}
	public void service(User user,Socket socket){
		//添加代码，调用资源文件操作类（PropertiesOperator.java）的getFriendList()方法获得好友列表
		//添加代码，将好友列表响应回客户端。
	}

}
