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
		List<User> friendList = PropertiesOperator.getFriendList(request.getUser().getId());




		//����㷨�Ƿ���ȷ������������
		for (int i = 0; i <friendList.size() ; i++) {
			for (int j = 0; j <ServerRunStatus.USERS.size() ; j++) {
				if (friendList.get(i).equals(ServerRunStatus.USERS.get(j)))
				{
					friendList.set(i,ServerRunStatus.USERS.get(j));
				}
			}

		}


		Response response=new Response();
		response.setFriendList(friendList);
		response.setResponseServiceName(request.getRequestServiceName());
		response.send(socket);



		//��Ӵ��룬�������б���Ӧ�ؿͻ��ˡ�
	}
	public void service(User user,Socket socket){
		//��Ӵ��룬������Դ�ļ������ࣨPropertiesOperator.java����getFriendList()������ú����б�
		List<User> friendList = PropertiesOperator.getFriendList(user.getId());
		//��Ӵ��룬�������б���Ӧ�ؿͻ��ˡ�
		for (int i = 0; i <friendList.size() ; i++) {
			for (int j = 0; j < ServerRunStatus.USERS.size(); j++) {
				if (friendList.get(i).equals(ServerRunStatus.USERS.get(j))){
					friendList.set(i,ServerRunStatus.USERS.get(j));
				}
			}

		}
		Response response=new Response();
		response.setFriendList(friendList);
		response.setResponseServiceName("FriendList");
		response.send(socket);
	}

}
