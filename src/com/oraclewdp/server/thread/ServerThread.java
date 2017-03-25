package com.oraclewdp.server.thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.JTextArea;

import com.oraclewdp.bean.Request;
import com.oraclewdp.server.config.ServerRunStatus;
import com.oraclewdp.service.ServerService;
import com.oraclewdp.service.impl.LoginService;

public class ServerThread implements Runnable {
	private JTextArea infoText;
	private Socket socket;
	private boolean runnable = true;
	private boolean exitType = true;

	/**
	 * �������ӵ��߳��๹��
	 * 
	 * @param socket
	 *            �����ڿͻ��˵�����
	 * @param infoText
	 *            ServerWindow�ж�����ı������ڴ�ŷ������˵�һЩ��־��Ϣ
	 */
	public ServerThread(Socket socket, JTextArea infoText) {
		this.infoText = infoText;
		this.socket = socket;
	}

	@Override
	public void run() {
		InputStream in = null;
		String exceptionMessage = "";
		Request request = null;

		try {
			// �ӿͻ��˵������л�ȡ������
			in= socket.getInputStream();


			// ����������
			ObjectInputStream objectInputStream=new ObjectInputStream(in);
			request = (Request)objectInputStream.readObject();


			// ����������һ��IO����

			// ��Request�����л�ȡ�ͻ���������ķ�������


			// �ӷ���������״̬���л�ȡProperties���󣬲�ȡ����Ӧ�ļ�����Ӧ��ֵ
			// �����ʱ��serviceName��ֵ��Register����ô��ȡ��com..service.impl.RegisterService
			// ��ͨ��Class.forName������ȡ����Ӧ��һ����

			// ͨ��serviceClass��newInstance�����������������޲εĹ��췽�������õ�һ��ʵ��
			// �����ʵ��ת��ΪServerService����
			// �ɴ˿��Եó�com..service.impl.RegisterServicen��Ȼ��ʵ����ServerService�ӿڵ�һ���࣬���Բſ���ǿת

			// ����ʵ����service����

		} catch (Exception e) {
			exceptionMessage = e.getMessage() + "\n";
			this.exitType = false;
			e.printStackTrace();
		} finally {
			this.runnable = false;
			this.infoText.append(exceptionMessage);
			if (exitType) {
				this.infoText.append("IP��ַΪ��"
						+ this.socket.getInetAddress().getHostAddress()
						+ "�����û��������˳�\n");
				ServerRunStatus.USERS.remove(request.getUser());
				new LoginService().sendOnlineMessage(request.getUser());
			} else {
				this.infoText.append("IP��ַΪ��"
						+ this.socket.getInetAddress().getHostAddress()
						+ "�����û������������˳�\n");
				ServerRunStatus.USERS.remove(request.getUser());
				new LoginService().sendOnlineMessage(request.getUser());
			}
			String remoteHost = this.socket.getInetAddress().getHostAddress()
					+ ":" + this.socket.getPort();
			this.infoText.append("�Ƴ������ڷ������е��������û���Ϣ\n");
			ServerRunStatus.SERVICEPROPERTIES.remove(remoteHost);

			try {
				this.socket.close();
			} catch (IOException e) {
				exceptionMessage = e.getMessage() + "\n";
				e.printStackTrace();
			}
		}
	}

	public void shutDown() {
		this.infoText.append("����ֹͣ��" + Thread.currentThread().getName()
				+ "�����߳�\n");
		this.runnable = false;
	}
}