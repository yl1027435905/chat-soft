package com.userlist;

import java.net.Socket;

public class Userinfo 
{
	private String uid;
	private String phone;
	private String email;
	private Socket socket;
	private String udpip;
	private int udpport;

	
	public Userinfo(String uid,String email,String phone,Socket socket)
	{
		this.uid=uid;
		this.phone=phone;
		this.email=email;
		this.socket=socket;
	}
			
	
	
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getUdpip() {
		return udpip;
	}

	public void setUdpip(String udpip) {
		this.udpip = udpip;
	}

	public int getUdpport() {
		return udpport;
	}

	public void setUdpport(int udpport) {
		this.udpport = udpport;
	}

}
