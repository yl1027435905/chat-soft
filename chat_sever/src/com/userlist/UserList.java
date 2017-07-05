package com.userlist;

import java.io.IOException;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**用于保存在线用户
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
	
	
	//客户端进行登录，将客户端的登录信息数据流发送给服务器，服务器记录在线的用户信息,可用json解析出输入流中的数据，再传给该方法
	public void registerUser(String uid,String email,String phonenum,Socket socket)
	{
		if (h.containsKey(uid))
		{
			Userinfo userinfo=h.get(uid);
			try {
				userinfo.getSocket().getOutputStream().write(4);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			//关闭原本的socket;
			try {
				userinfo.getSocket().close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}else//记录用户以及其信息
		{
			Userinfo userinfo=new Userinfo(uid,email,phonenum,socket);			
			h.put(uid, userinfo);
		}
	}
	
	
	/**
	 * 更新客户端的UDP信息
	 * 
	 * @param uid
	 *            用户编号
	 * @param ip
	 *            udp IP地址
	 * @param port
	 *            udp端口
	 * @throws NullPointerException
	 *             空指针异常
	 */
	public void updateOnlineUDP(String uid, String ip, int port) throws NullPointerException 
	{
		//System.out.println("传进来的UID是："+uid);
		Userinfo userinfo =getinfo(uid);
		//System.out.println(userinfo);
		userinfo.setUdpip(ip);
		userinfo.setUdpport(port);
	}
	
	
	
	//判断某个用户是否在线
	public boolean isOnline(String uid)
	{
		if(h.containsKey(uid)) return true;
		else return false;
		
	}
	
	
	//获得在线用户的信息
	public Userinfo getinfo(String uid)
	{
		if(h.containsKey(uid)) return h.get(uid);
		else return null;
	}
	
	//删除下线用户的信息
	public void closeUse(String uid)
	{
		h.remove(uid);
	}
	
	//获得所以在线用户的ID
	public Set<String> getAlluid()
	{
		return h.keySet();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
