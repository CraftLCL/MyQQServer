package com.oraclewdp.server.config;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.oraclewdp.bean.User;

public class ServerRunStatus {
	//�ͻ����б�
	public static final Map<String, Socket> CLIENTS = new HashMap<String, Socket>();
	//�û��б�
	public static final List<User> USERS = new ArrayList<User>();
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
