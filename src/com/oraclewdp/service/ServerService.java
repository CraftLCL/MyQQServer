package com.oraclewdp.service;

import java.net.Socket;

import javax.swing.JTextArea;

import com.oraclewdp.bean.Request;
import com.oraclewdp.server.thread.ServerThread;

public interface ServerService {
	public void service(Request request,Socket socket,JTextArea infoText,ServerThread serverThread);
}
