package com.oraclewdp.service.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Properties;

import javax.swing.JTextArea;

import com.oraclewdp.bean.Request;
import com.oraclewdp.bean.Response;
import com.oraclewdp.bean.User;
import com.oraclewdp.server.thread.ServerThread;
import com.oraclewdp.service.ServerService;
import com.oraclewdp.user.PropertiesOperator;

public class RegisterService implements ServerService {

	public void service(Request request, Socket socket, JTextArea infoText,
			ServerThread serverThread) {
		User user = request.getUser();
		infoText.append("名为【" + user.getName() + "】的用户尝试注册\n");
		infoText.append("尝试生成一个用户ID\n");

		OutputStream out = null;
		try {
			// 定位users.properties文件

			// 利用properties对象读取文件中的值，并以键值对的方式存在在这个properties对象中

			// 调用builderID方法生成一个不重复的ID号，并填充User对象中的id属性的值

			infoText.append("创建了一个ID号为【" + user.getId() + "】的用户\n");
			// 为properties对象创建新的属性

			// 将properties对象写入到文件

			// 创建Response对象

			// 设置Response对象的responseCode属性的值，通过常量进入赋值

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 生成用户ID，如果生成了一个重复ID将重新生成
	 * 
	 * @param user
	 * @param pro
	 */
	private void builderID(User user, Properties pro) {
		// 生成一个五位数的整数

	}

}
