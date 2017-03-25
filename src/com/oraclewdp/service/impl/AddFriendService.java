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
			boolean isExists=PropertiesOperator.findUser(request.getToUser().getId());

		// ��Ӵ��룬������Ӧ����
		Response response=new Response();
		response.setResponseServiceName(request.getRequestServiceName());

		// ��Ӵ��룬������ڵ���PropertiesOperator�е�isFriend()����Ƿ��Ѿ��Ǻ��ѣ�addFriend()��Ӻ���
		if (isExists){
			if (PropertiesOperator.isFriend(request.getUser().getId(),request.getToUser().getId())){
				response.setResponseCode(Response.ADDFRIEND_FAIL_ISFRIEND);
			}
			else {
				PropertiesOperator.addFriend(request.getUser().getId(),request.getToUser().getId());
				response.setResponseCode(Response.ADDFRIEND_SUCCESS);
			}
		}else {
			response.setResponseCode(Response.ADDFRIEND_FAIL);
		}
		// ��Ӵ��룬������Ӧ״̬����
		response.send(socket);
		// ��Ӵ��룬����Ӧ������Ӧ�ؿͻ���

	}

}
