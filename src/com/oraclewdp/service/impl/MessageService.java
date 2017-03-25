package com.oraclewdp.service.impl;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JTextArea;

import com.oraclewdp.bean.Request;
import com.oraclewdp.bean.Response;
import com.oraclewdp.bean.User;
import com.oraclewdp.server.config.ServerRunStatus;
import com.oraclewdp.server.thread.ServerThread;
import com.oraclewdp.service.ServerService;

public class MessageService implements ServerService {
	@Override
	public void service(Request request, Socket socket, JTextArea infoText,
			ServerThread serverThread) {
		// 从请求获取好友对象
		//通过服务器运行状态类（ServerRunStatus.java）获得好友对象的Scoket对象
		//将好友聊天信息通过好友对象的Scoket对象发送给好友的客户端
	}
}
