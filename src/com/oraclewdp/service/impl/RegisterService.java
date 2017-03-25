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
import org.junit.Test;

public class RegisterService implements ServerService {

	public void service(Request request, Socket socket, JTextArea infoText,
			ServerThread serverThread) {
		User user = request.getUser();
		infoText.append("名为【" + user.getName() + "】的用户尝试注册\n");
		infoText.append("尝试生成一个用户ID\n");

		OutputStream out = null;
		try {
			// 定位users.properties文件
			Properties properties=new Properties();
			// RegisterService.class.getResource("../../user/users.properties").toString();
			String path=System.getProperty("user.dir")+"/src/com/oraclewdp/user/users.properties";
			properties.load(new FileInputStream(path));
			//properties.load(RegisterService.class.getResourceAsStream("../../user/user.properties"));

			// 利用properties对象读取文件中的值，并以键值对的方式存在在这个properties对象中

			// 调用builderID方法生成一个不重复的ID号，并填充User对象中的id属性的值
			builderID(user,properties);

			infoText.append("创建了一个ID号为【" + user.getId() + "】的用户\n");
			// 为properties对象创建新的属性
			properties.setProperty(user.getId()+".id",String.valueOf(user.getId()));
			properties.setProperty(user.getId()+".pwd",user.getPwd());
			properties.setProperty(user.getId()+".name",user.getName());
			properties.setProperty(user.getId()+".age", String.valueOf(user.getAge()));
			properties.setProperty(user.getId()+".phone",user.getPhone());
			properties.setProperty(user.getId()+".address",user.getAddress());
			out =new FileOutputStream(path);

			// 将properties对象写入到文件
			properties.store(out,null);





			// 创建Response对象
			Response response=new Response();


			// 设置Response对象的responseCode属性的值，通过常量进入赋值
			response.setResponseCode(Response.REGISTER_SUCCESS);
			//
			response.setResponseServiceName(request.getRequestServiceName());
			response.setFromUser(user);
			response.send(socket);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//socket.close();
                serverThread.shutDown();
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
		int id=(int)(Math.random()*(100000-10000)+10000);
		String key=id+".id";
		String uid=pro.getProperty(key);
		if(uid==null||"".equals(key)){
			user.setId(id);
			PropertiesOperator.newUser(String.valueOf(user.getId()));
		}
		else {
			builderID(user,pro);
		}



	}
	/*@Test
	public void pathTest(){
		System.out.println(RegisterService.class.getResource("../../user/users.properties").toString());
	}*/

}
