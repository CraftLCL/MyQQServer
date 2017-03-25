package com.oraclewdp.server.window;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;

import com.oraclewdp.server.config.ServerRunStatus;
import com.oraclewdp.server.thread.ServerThread;

public class Server implements Runnable {
	private JTextArea infoText;
	private boolean runnable = true;

	/**
	 * Server����
	 * 
	 * @param infoText
	 *            ServerWindow�ж�����ļ������ڴ�ŷ������˵�һЩ��־��Ϣ
	 */
	public Server(JTextArea infoText) {
		this.infoText = infoText;
	}

	@Override
	public void run() {
		ServerSocket server = null;
		try {
			// �����������˵�ServerSocket���󣬲�ָ���˿ں�
			server=new ServerSocket(8989);

			this.infoText.append("���ڵȴ������ڿͻ��˵�����\n");
			while (runnable) {
				// ���������ڿͻ��˵�����
				Socket socket= server.accept();
				// �������л�ȡ�ͻ��˵�IP��ַ+�˿ںţ����һ������ֵ
				String remoteHost = socket.getInetAddress().getHostAddress()+":"+socket.getPort();


				this.infoText.append("��ȡ�����ԡ�" + remoteHost + "��������\n");
				// �������������̶߳���
                // �����̶߳���
                Thread thread=new Thread(new ServerThread(socket,infoText));
                thread.start();


				this.infoText.append("������" + remoteHost + "�����߳�\n");
				// �����߳�

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void shutDown() {
		this.runnable = false;
		ServerRunStatus.CLIENTS.clear();
		ServerRunStatus.USERS.clear();
		System.exit(0);
	}
}
