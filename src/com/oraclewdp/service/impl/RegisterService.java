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
		infoText.append("��Ϊ��" + user.getName() + "�����û�����ע��\n");
		infoText.append("��������һ���û�ID\n");

		OutputStream out = null;
		try {
			// ��λusers.properties�ļ�

			// ����properties�����ȡ�ļ��е�ֵ�����Լ�ֵ�Եķ�ʽ���������properties������

			// ����builderID��������һ�����ظ���ID�ţ������User�����е�id���Ե�ֵ

			infoText.append("������һ��ID��Ϊ��" + user.getId() + "�����û�\n");
			// Ϊproperties���󴴽��µ�����

			// ��properties����д�뵽�ļ�

			// ����Response����

			// ����Response�����responseCode���Ե�ֵ��ͨ���������븳ֵ

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
	 * �����û�ID�����������һ���ظ�ID����������
	 * 
	 * @param user
	 * @param pro
	 */
	private void builderID(User user, Properties pro) {
		// ����һ����λ��������

	}

}
