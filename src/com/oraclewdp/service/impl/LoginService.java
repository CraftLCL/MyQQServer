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
		//������Ӧ�ķ�����
		//���user����Ϊnull����ô����û���ҵ�һ�����Ӧ��ID�ŵ��û�
		if(user!=null){
			//ƥ���û��������������ļ��е������Ƿ�һ��
			if(request.getUser().getPwd().equals(user.getPwd())){
				
				infoText.append("��ǰ�������û�һ���У�"+ServerRunStatus.USERS.size()+"���û�\n");
				
			}else{
				infoText.append("id��Ϊ��"+request.getUser().getId()+"�����û�����������󣬵�¼ʧ��\n");
			}
		}else{
			infoText.append("id��Ϊ��"+request.getUser().getId()+"�����û������ڣ���¼ʧ��\n");
		}
		//ͨ��response�����send��������response�����͸��ͻ���
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

