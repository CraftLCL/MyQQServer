package com.oraclewdp.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import com.oraclewdp.bean.User;

public class PropertiesOperator {
	public static void newUser(String id) {
		// 创建一个以用户帐号为文件名的properties文件，存放好友帐号与好友用户名。
		Properties properties =new Properties();
		String path=System.getProperty("user.dir")+"/src/com/oraclewdp/user/"+id+".properties";
		try {
			OutputStream outputStream=new FileOutputStream(path);
			properties.store(outputStream,null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 以用户帐号为文件名的properties文件格式为 好友帐号=好友用户名
	}

	public static boolean findUser(int id) {
		// 查询是否存在以参数ID为名字的资源文件。
		File file;
		boolean flag;
		URL url=PropertiesOperator.class.getResource(id+".properties");

		try {
			file=new File(url.toURI());
			flag=file.exists();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			flag=false;
		}catch (NullPointerException e){
			flag=false;
		}

		return flag;
	}

	public static List<User> getFriendList(int id) {
		// 返回以参数ID为名字的资源文件中好友列表
		String  userPath=System.getProperty("user.dir")+"/src/com/oraclewdp/user/"+id+".properties";
		Properties properties=new Properties();
		ArrayList<User> userArrayList = new ArrayList<>();
		try {
			properties.load(new FileInputStream(userPath));
			Set<Map.Entry<Object, Object>> entries = properties.entrySet();
			for (Map.Entry<Object,Object> entry:entries
				 ) {
				User user = new User();
				user.setId(Integer.parseInt(entry.getKey().toString()));
				user.setName(entry.getValue().toString());
				userArrayList.add(user);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}


		return userArrayList;
	}

	public static boolean isFriend(int userId, int targetUserid){
		// 判断targetUserid是否已经是userid的朋友，返回true：已经是朋友；返回false：还不是朋友
		boolean isFriend=false;
		Properties properties=new Properties();
		try {
			properties.load(PropertiesOperator.class.getResourceAsStream(userId+".properties"));

			if(properties.getProperty(targetUserid+"")!=null){
				isFriend=true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isFriend;
	}
	public static void addFriend(int userid, int targetUserid) {
		// 添加好友到资源文件
		User user=loadUser(userid);
		User targetUser=loadUser(targetUserid);

		Properties userProperties=new Properties();
		Properties targetProperties=new Properties();
		String  userPath=System.getProperty("user.dir")+"/src/com/oraclewdp/user/"+userid+".properties";
		String  targetUserPath=System.getProperty("user.dir")+"/src/com/oraclewdp/user/"+targetUserid+".properties";

		try {

			userProperties.load(new FileInputStream(userPath));
			targetProperties.load(new FileInputStream(targetUserPath));
			userProperties.setProperty(targetUser.getId()+"",targetUser.getName());
			userProperties.store(new FileOutputStream(userPath),null);
			targetProperties.setProperty(user.getId()+"",user.getName());
			targetProperties.store(new FileOutputStream(targetUserPath),null);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static User loadUser(int id) {
		// 创建Properties对象
		Properties properties=new Properties();
		User user=null;



		// 定位文件路径
		String path=System.getProperty("user.dir")+"/src/com/oraclewdp/user/users.properties";
		InputStream inputStream=null;
		// 利用Properties对象加载文件中的信息
		try {
			inputStream=new FileInputStream(path);
			properties.load(inputStream);
			String idStr=properties.getProperty(id+".id");
			if (idStr!=null&&!"".equals(idStr)){
				user=new User();
				user.setId(id);
				user.setName(properties.getProperty(id+".name"));
				user.setPwd(properties.getProperty(id+".pwd"));
				user.setAge(Integer.parseInt(properties.getProperty(id+".age")));
				user.setPhone(properties.getProperty(id+".phone"));
				user.setAddress(properties.getProperty(id+".address"));

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// 获取信息

		// 如果取到值，进入if语句块，如果没取到值，将直接返回一个null值

		return user;
	}
}
