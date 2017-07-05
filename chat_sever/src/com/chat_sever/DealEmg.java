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
		System.out.println("���2");
	}
	
	public void run() 
	{
		// TODO �Զ����ɵķ������
		System.out.println("���2.5");
		
		byte[] bt=inpacket.getData();
		System.out.println("���2.6");
		
		String ss=new String(bt,0,bt.length);
		System.out.println("���2.7");
		JSONObject json = JSONObject.fromObject(ss);
		System.out.println("���2.8");
		String touid=json.getString("toUID");
		System.out.println("���2.9");
		Userinfo infoto=UserList.getUserList().getinfo(touid);
		System.out.println("���3");
		try {
			DatagramSocket socket=new DatagramSocket();
			byte[] b=inpacket.getData();
			byte[] b2=inpacket.getData();
			
			DatagramPacket outpacket=new DatagramPacket(b,b.length,inpacket.getAddress(),inpacket.getPort());
			DatagramPacket outpacket2=null;
			try {
				int bg=b2.length;
				//System.out.println("���ݴ�С��"+bg);
				System.out.println("���4");
				outpacket2=new DatagramPacket(b2,bg,InetAddress.getByName(infoto.getUdpip()),infoto.getUdpport());/////////����Է������߻���ֿ�ָ��
			} catch (UnknownHostException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			try {
				//socket.send(outpacket);////////////////��֤��
				System.out.println("���5");
				socket.send(outpacket2);///////////////ת���������û���							
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
												
		} catch (SocketException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
}
