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
		// 添加代码，查询添加好友是否存在
			boolean isExists=PropertiesOperator.findUser(request.getToUser().getId());

		// 添加代码，创建响应对象
		Response response=new Response();
		response.setResponseServiceName(request.getRequestServiceName());

		// 添加代码，如果存在调用PropertiesOperator中的isFriend()检查是否已经是好友，addFriend()添加好友
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
		// 添加代码，设置响应状态代码
		response.send(socket);
		// 添加代码，将响应对象响应回客户端

	}

}
