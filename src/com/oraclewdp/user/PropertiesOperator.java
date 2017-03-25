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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.oraclewdp.bean.User;

public class PropertiesOperator {
	public static void newUser(String id) {
		// 创建一个以用户帐号为文件名的properties文件，存放好友帐号与好友用户名。
		// 以用户帐号为文件名的properties文件格式为 好友帐号=好友用户名
	}

	public static boolean findUser(int id) {
		// 查询是否存在以参数ID为名字的资源文件。
		return false;
	}

	public static List<User> getFriendList(int id) {
		// 返回以参数ID为名字的资源文件中好友列表
		return null;
	}

	public static boolean isFriend(int userid, int targetUserid){
		// 判断targetUserid是否已经是userid的朋友，返回true：已经是朋友；返回false：还不是朋友
		return false;
	}
	public static void addFriend(int userid, int targetUserid) {
		// 添加好友到资源文件
	}

	public static User loadUser(int id) {
		// 创建Properties对象

		// 定位文件路径

		// 利用Properties对象加载文件中的信息

		// 获取信息

		// 如果取到值，进入if语句块，如果没取到值，将直接返回一个null值

		return null;
	}
}
