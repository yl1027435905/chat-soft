package com.chat_sever;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.userlist.UserList;
import com.userlist.Userinfo;

import net.sf.json.JSONObject;

public class DealEmg implements Runnable {
	
	private DatagramPacket inpacket;
		
	public DealEmg(DatagramPacket inpacket)
	{		
		this.inpacket=inpacket;
		System.out.println("打点2");
	}
	
	public void run() 
	{
		// TODO 自动生成的方法存根
		System.out.println("打点2.5");
		
		byte[] bt=inpacket.getData();
		System.out.println("打点2.6");
		
		String ss=new String(bt,0,bt.length);
		System.out.println("打点2.7");
		JSONObject json = JSONObject.fromObject(ss);
		System.out.println("打点2.8");
		String touid=json.getString("toUID");
		System.out.println("打点2.9");
		Userinfo infoto=UserList.getUserList().getinfo(touid);
		System.out.println("打点3");
		try {
			DatagramSocket socket=new DatagramSocket();
			byte[] b=inpacket.getData();
			byte[] b2=inpacket.getData();
			
			DatagramPacket outpacket=new DatagramPacket(b,b.length,inpacket.getAddress(),inpacket.getPort());
			DatagramPacket outpacket2=null;
			try {
				int bg=b2.length;
				//System.out.println("数据大小："+bg);
				System.out.println("打点4");
				outpacket2=new DatagramPacket(b2,bg,InetAddress.getByName(infoto.getUdpip()),infoto.getUdpport());/////////如果对方不在线会出现空指针
			} catch (UnknownHostException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			try {
				//socket.send(outpacket);////////////////验证的
				System.out.println("打点5");
				socket.send(outpacket2);///////////////转发给其他用户的							
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
												
		} catch (SocketException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
