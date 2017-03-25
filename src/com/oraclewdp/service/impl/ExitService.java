package com.oraclewdp.service.impl;

import java.net.Socket;

import javax.swing.JTextArea;

import com.oraclewdp.bean.Request;
import com.oraclewdp.server.thread.ServerThread;
import com.oraclewdp.service.ServerService;

public class ExitService implements ServerService {

	@Override
	public void service(Request request, Socket socket, JTextArea infoText,	ServerThread serverThread) {
		//向客户端发出关闭socket的通知
		serverThread.shutDown();
		
	}

}
