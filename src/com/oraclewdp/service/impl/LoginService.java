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
		infoText.append("id号为【"+request.getUser().getId()+"】的用户正在尝试登录\n");
		User user = PropertiesOperator.loadUser(request.getUser().getId());
		//创建Response对象，准备回发给客户端
		//设置响应的服务名
		//如果user对象为null，那么就是没有找到一个相对应的ID号的用户
		if(user!=null){
			//匹配用户的密码与配置文件中的密码是否一致
			if(request.getUser().getPwd().equals(user.getPwd())){
				
				infoText.append("当前的在线用户一共有："+ServerRunStatus.USERS.size()+"个用户\n");
				
			}else{
				infoText.append("id号为【"+request.getUser().getId()+"】的用户由于密码错误，登录失败\n");
			}
		}else{
			infoText.append("id号为【"+request.getUser().getId()+"】的用户不存在，登录失败\n");
		}
		//通过response对象的send方法，将response对象发送给客户端
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

