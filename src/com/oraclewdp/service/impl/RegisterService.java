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
		infoText.append("��Ϊ��" + user.getName() + "�����û�����ע��\n");
		infoText.append("��������һ���û�ID\n");

		OutputStream out = null;
		try {
			// ��λusers.properties�ļ�
			Properties properties=new Properties();
			// RegisterService.class.getResource("../../user/users.properties").toString();
			String path=System.getProperty("user.dir")+"/src/com/oraclewdp/user/users.properties";
			properties.load(new FileInputStream(path));
			//properties.load(RegisterService.class.getResourceAsStream("../../user/user.properties"));

			// ����properties�����ȡ�ļ��е�ֵ�����Լ�ֵ�Եķ�ʽ���������properties������

			// ����builderID��������һ�����ظ���ID�ţ������User�����е�id���Ե�ֵ
			builderID(user,properties);

			infoText.append("������һ��ID��Ϊ��" + user.getId() + "�����û�\n");
			// Ϊproperties���󴴽��µ�����
			properties.setProperty(user.getId()+".id",String.valueOf(user.getId()));
			properties.setProperty(user.getId()+".pwd",user.getPwd());
			properties.setProperty(user.getId()+".name",user.getName());
			properties.setProperty(user.getId()+".age", String.valueOf(user.getAge()));
			properties.setProperty(user.getId()+".phone",user.getPhone());
			properties.setProperty(user.getId()+".address",user.getAddress());
			out =new FileOutputStream(path);

			// ��properties����д�뵽�ļ�
			properties.store(out,null);





			// ����Response����
			Response response=new Response();


			// ����Response�����responseCode���Ե�ֵ��ͨ���������븳ֵ
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
	 * �����û�ID�����������һ���ظ�ID����������
	 * 
	 * @param user
	 * @param pro
	 */
	private void builderID(User user, Properties pro) {
		// ����һ����λ��������
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
