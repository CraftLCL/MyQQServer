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
		//��Ӵ��룬������Դ�ļ������ࣨPropertiesOperator.java����getFriendList()������ú����б�
		//��Ӵ��룬�������б���Ӧ�ؿͻ��ˡ�
	}
	public void service(User user,Socket socket){
		//��Ӵ��룬������Դ�ļ������ࣨPropertiesOperator.java����getFriendList()������ú����б�
		//��Ӵ��룬�������б���Ӧ�ؿͻ��ˡ�
	}

}
