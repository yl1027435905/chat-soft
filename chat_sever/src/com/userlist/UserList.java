package com.userlist;

import java.io.IOException;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**���ڱ��������û�
 * @author yl
 *
 */
public class UserList 
{
	private static UserList u=new UserList();
	
	private static Map<String,Userinfo> h=Collections.synchronizedMap(new HashMap<String,Userinfo>());
	
	private UserList(){}
	
	public static  UserList  getUserList()
	{
		return u;
	}
	
	
	//�ͻ��˽��е�¼�����ͻ��˵ĵ�¼��Ϣ���������͸�����������������¼���ߵ��û���Ϣ,����json�������������е����ݣ��ٴ����÷���
	public void registerUser(String uid,String email,String phonenum,Socket socket)
	{
		if (h.containsKey(uid))
		{
			Userinfo userinfo=h.get(uid);
			try {
				userinfo.getSocket().getOutputStream().write(4);
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			//�ر�ԭ����socket;
			try {
				userinfo.getSocket().close();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			
		}else//��¼�û��Լ�����Ϣ
		{
			Userinfo userinfo=new Userinfo(uid,email,phonenum,socket);			
			h.put(uid, userinfo);
		}
	}
	
	
	/**
	 * ���¿ͻ��˵�UDP��Ϣ
	 * 
	 * @param uid
	 *            �û����
	 * @param ip
	 *            udp IP��ַ
	 * @param port
	 *            udp�˿�
	 * @throws NullPointerException
	 *             ��ָ���쳣
	 */
	public void updateOnlineUDP(String uid, String ip, int port) throws NullPointerException 
	{
		//System.out.println("��������UID�ǣ�"+uid);
		Userinfo userinfo =getinfo(uid);
		//System.out.println(userinfo);
		userinfo.setUdpip(ip);
		userinfo.setUdpport(port);
	}
	
	
	
	//�ж�ĳ���û��Ƿ�����
	public boolean isOnline(String uid)
	{
		if(h.containsKey(uid)) return true;
		else return false;
		
	}
	
	
	//��������û�����Ϣ
	public Userinfo getinfo(String uid)
	{
		if(h.containsKey(uid)) return h.get(uid);
		else return null;
	}
	
	//ɾ�������û�����Ϣ
	public void closeUse(String uid)
	{
		h.remove(uid);
	}
	
	//������������û���ID
	public Set<String> getAlluid()
	{
		return h.keySet();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
