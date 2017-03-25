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

public class LoginService implements ServerService {
	
	@Override
	public void service(Request request,Socket socket,JTextArea infoText,ServerThread serverThread) {
		infoText.append("id��Ϊ��"+request.getUser().getId()+"�����û����ڳ��Ե�¼\n");
		User user = PropertiesOperator.loadUser(request.getUser().getId());
		//����Response����׼���ط����ͻ���
		Response response=new Response();
		//������Ӧ�ķ�����
		response.setResponseServiceName(request.getRequestServiceName());
		//���user����Ϊnull����ô����û���ҵ�һ�����Ӧ��ID�ŵ��û�
		if(user!=null){
			//ƥ���û��������������ļ��е������Ƿ�һ��
			if(request.getUser().getPwd().equals(user.getPwd())){
				String remoteHost=socket.getInetAddress().getHostAddress()+":"+socket.getPort();
				//
				ServerRunStatus.CLIENTS.put(remoteHost,socket);
				infoText.append("id��Ϊ��"+request.getUser().getId()+"�����û���¼�ɹ�\n");
				user.setIp(socket.getInetAddress().getHostAddress());
				user.setPort(socket.getPort());
				response.setFromUser(user);
				response.setResponseCode(Response.LOGIN_SUCCESS);
				user.setOnline(true);
				ServerRunStatus.USERS.add(user);
				//




				//sendOnlineMessage(user);







				//
				infoText.append("��ǰ�������û�һ���У�"+ServerRunStatus.USERS.size()+"���û�\n");

			}else{
				infoText.append("id��Ϊ��"+request.getUser().getId()+"�����û�����������󣬵�¼ʧ��\n");
				response.setResponseCode(Response.LOGIN_PASSWORD_ERROR);
			}
		}else{
			infoText.append("id��Ϊ��"+request.getUser().getId()+"�����û������ڣ���¼ʧ��\n");
			response.setResponseCode(Response.LOGIN_USERNAME_INVALID);
		}
		//ͨ��response�����send��������response�����͸��ͻ���
		response.send(socket);
	}
	public void sendOnlineMessage(User user){
		List<User> userList = PropertiesOperator.getFriendList(user.getId());
		for(int i=0;i<userList.size();i++){
			for(int j=0;j<ServerRunStatus.USERS.size();j++){
				if(userList.get(i).equals(ServerRunStatus.USERS.get(j))){
					User sendUser = ServerRunStatus.USERS.get(j);
					String remoteHost = sendUser.getIp()+":"+sendUser.getPort();
					Socket socket = ServerRunStatus.CLIENTS.get(remoteHost);
					new FriendListService().service(ServerRunStatus.USERS.get(j),socket);
				}
			}
		}

	}
}

