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
		// �������ȡ���Ѷ���
		//ͨ������������״̬�ࣨServerRunStatus.java����ú��Ѷ����Scoket����
		//������������Ϣͨ�����Ѷ����Scoket�����͸����ѵĿͻ���
	}
}
