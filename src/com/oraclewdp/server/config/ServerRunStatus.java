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
	//客户端列表
	public static final Map<String, Socket> CLIENTS = new HashMap<String, Socket>();
	//用户列表
	public static final List<User> USERS = new ArrayList<User>();
	public static final Properties SERVICEPROPERTIES = new Properties();
	static {
		// 添加代码，加载资源文件service.properties内容到SERVICEPROPERTIES中。
		try {
			SERVICEPROPERTIES.load(SystemConfig.class.getResourceAsStream("service.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
