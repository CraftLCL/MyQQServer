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
		// ����һ�����û��ʺ�Ϊ�ļ�����properties�ļ�����ź����ʺ�������û�����
		// ���û��ʺ�Ϊ�ļ�����properties�ļ���ʽΪ �����ʺ�=�����û���
	}

	public static boolean findUser(int id) {
		// ��ѯ�Ƿ�����Բ���IDΪ���ֵ���Դ�ļ���
		return false;
	}

	public static List<User> getFriendList(int id) {
		// �����Բ���IDΪ���ֵ���Դ�ļ��к����б�
		return null;
	}

	public static boolean isFriend(int userid, int targetUserid){
		// �ж�targetUserid�Ƿ��Ѿ���userid�����ѣ�����true���Ѿ������ѣ�����false������������
		return false;
	}
	public static void addFriend(int userid, int targetUserid) {
		// ��Ӻ��ѵ���Դ�ļ�
	}

	public static User loadUser(int id) {
		// ����Properties����

		// ��λ�ļ�·��

		// ����Properties��������ļ��е���Ϣ

		// ��ȡ��Ϣ

		// ���ȡ��ֵ������if���飬���ûȡ��ֵ����ֱ�ӷ���һ��nullֵ

		return null;
	}
}
