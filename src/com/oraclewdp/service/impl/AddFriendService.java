package com.oraclewdp.service.impl;

import java.net.Socket;

import javax.swing.JTextArea;

import com.oraclewdp.bean.Request;
import com.oraclewdp.bean.Response;
import com.oraclewdp.server.thread.ServerThread;
import com.oraclewdp.service.ServerService;
import com.oraclewdp.user.PropertiesOperator;

public class AddFriendService implements ServerService {

	@Override
	public void service(Request request, Socket socket, JTextArea infoText,
			ServerThread serverThread) {
		// ��Ӵ��룬��ѯ��Ӻ����Ƿ����

		// ��Ӵ��룬������Ӧ����

		// ��Ӵ��룬������ڵ���PropertiesOperator�е�isFriend()����Ƿ��Ѿ��Ǻ��ѣ�addFriend()��Ӻ���
		
		// ��Ӵ��룬������Ӧ״̬����
		
		// ��Ӵ��룬����Ӧ������Ӧ�ؿͻ���

	}

}
