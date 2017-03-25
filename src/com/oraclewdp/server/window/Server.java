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
	 * Server构造
	 * 
	 * @param infoText
	 *            ServerWindow中定义的文件域，用于存放服务器端的一些日志信息
	 */
	public Server(JTextArea infoText) {
		this.infoText = infoText;
	}

	@Override
	public void run() {
		ServerSocket server = null;
		try {
			// 创建服务器端的ServerSocket对象，并指定端口号
			server=new ServerSocket(8989);

			this.infoText.append("正在等待来自于客户端的连接\n");
			while (runnable) {
				// 监听来自于客户端的连接
				Socket socket= server.accept();
				// 从连接中获取客户端的IP地址+端口号，组成一个键的值
				String remoteHost = socket.getInetAddress().getHostAddress()+":"+socket.getPort();


				this.infoText.append("获取到来自【" + remoteHost + "】的连接\n");
				// 创建服务器端线程对象
                // 创建线程对象
                Thread thread=new Thread(new ServerThread(socket,infoText));
                thread.start();


				this.infoText.append("启动【" + remoteHost + "】的线程\n");
				// 启动线程

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
