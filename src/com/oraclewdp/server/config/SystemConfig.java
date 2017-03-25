package com.oraclewdp.server.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.oraclewdp.bean.User;
import com.oraclewdp.server.thread.ServerThread;

public class SystemConfig {
	//�ͻ����б�
	public static final Map<String, Socket> CLIENTS = new HashMap<String, Socket>();
	//�û��б�
	public static final List<User> USERS = new ArrayList<User>();
	//�����߳��б�
	public static final List<ServerThread> THREADS = new ArrayList<ServerThread>();
	public static final Properties SERVICEPROPERTIES = new Properties();
	static {
		// ��Ӵ��룬������Դ�ļ�service.properties���ݵ�SERVICEPROPERTIES�С�
		try {
			SERVICEPROPERTIES.load(SystemConfig.class.getResourceAsStream("service.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
