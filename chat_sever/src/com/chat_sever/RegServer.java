package com.chat_sever;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;





/**
 * 
 * 用于注册的服务器线程
 * @author yl
 *
 */
public class RegServer implements Runnable {

	public void run() {
		// TODO 自动生成的方法存根
		ServerSocket rs;
		try {
			rs = new ServerSocket(4001);
			while(true)
			{
				//ServerSocket ss;
				Socket s= rs.accept();
				//s.setSoTimeout(10000);
				System.out.println("注册服务器通信成功！");
				//启动线程方法为客户端服务
				new Thread(new Server2(s)).start();			
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
		new Thread(new RegServer()).start();
		
		
	}

}
