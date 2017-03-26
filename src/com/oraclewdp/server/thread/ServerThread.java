package com.oraclewdp.server.thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Properties;

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
	 * 控制连接的线程类构造
	 * 
	 * @param socket
	 *            来自于客户端的连接
	 * @param infoText
	 *            ServerWindow中定义的文本域，用于存放服务器端的一些日志信息
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


            while (this.runnable){
				// 从客户端的连接中获取输入流
				in= socket.getInputStream();


				// 创建对象流
				ObjectInputStream objectInputStream=new ObjectInputStream(in);
                // 在这里会存在一个IO阻塞

                // 从Request对象中获取客户端所请求的服务名字
                request = (Request)objectInputStream.readObject();
                String serviceName=request.getRequestServiceName();


                // 从服务器运行状态类中获取Properties对象，并取出对应的键的相应的值
                String className= ServerRunStatus.SERVICEPROPERTIES.getProperty(serviceName);

                // 如果此时的serviceName的值是Register，那么将取出com..service.impl.RegisterService
                // 并通过Class.forName方法获取到对应的一个类
                Class myclass=Class.forName(className);

                // 通过serviceClass的newInstance方法，调用这个类的无参的构造方法，并得到一个实例
                ServerService serverService =(ServerService) myclass.newInstance();
                // 将这个实例转型为ServerService类型
                // 由此可以得出com..service.impl.RegisterServicen必然是实现了ServerService接口的一个类，所以才可以强转

                // 调用实例的service方法
                serverService.service(request,socket,infoText,this);


            }



		} catch (Exception e) {
			exceptionMessage = e.getMessage() + "\n";
			this.exitType = false;
			e.printStackTrace();
		} finally {
			this.runnable = false;
			this.infoText.append(exceptionMessage);
			if (exitType) {
				this.infoText.append("IP地址为【"
						+ this.socket.getInetAddress().getHostAddress()
						+ "】的用户（请求）退出\n");
				ServerRunStatus.USERS.remove(request.getUser());
				new LoginService().sendOnlineMessage(request.getUser());
			} else {
				this.infoText.append("IP地址为【"
						+ this.socket.getInetAddress().getHostAddress()
						+ "】的用户（非正常）退出\n");
				ServerRunStatus.USERS.remove(request.getUser());
				new LoginService().sendOnlineMessage(request.getUser());
			}
			String remoteHost = this.socket.getInetAddress().getHostAddress()
					+ ":" + this.socket.getPort();
			this.infoText.append("移除保存在服务器中的连接与用户信息\n");
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
		this.infoText.append("正在停止【" + Thread.currentThread().getName()
				+ "】的线程\n");
		this.runnable = false;
	}
}
