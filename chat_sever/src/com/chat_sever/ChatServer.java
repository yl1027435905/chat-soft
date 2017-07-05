package com.chat_sever;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer implements Runnable {

	public void run() {
		// TODO 自动生成的方法存根
		//ServerSocket s;	
				ServerSocket ss;
				try {
					ss = new ServerSocket(30000);
					while(true)
					{
						//ServerSocket ss;
						Socket s= ss.accept();
						//s.setSoTimeout(10000);
						System.out.println("登录服务器通信成功！");
						//启动线程方法为客户端服务
						new Thread(new Server(s)).start();			
					}
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}	
				
		
	}
	
	
	public static void main(String[] args) throws IOException  
	{
		// TODO 自动生成的方法存根
		
		//ServerSocket s;
		new Thread(new ChatServer()).start();
		new Thread(new UPDServer()).start();
		new Thread(new updateServer()).start();
		
	}
	
	
	
	
	
	
	
	

}
