package com.chat_sever;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;



public class UPDServer implements Runnable {

	public void run() 
	{
		// TODO 自动生成的方法存根
		try {
			DatagramSocket socket=new DatagramSocket(30002);////接收聊天信息端口
		    
		    byte[] inbuff=new byte[1024*32];
		    DatagramPacket inpacket=new DatagramPacket(inbuff,inbuff.length);
		    while(true)
		    {
		    	try {
					socket.receive(inpacket);
					System.out.println("打点1");
					new Thread(new DealEmg(inpacket)).start();
					
					
					
					
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
		    	
		    			    	
		    }
		    
		    			    
		    
		    }catch (SocketException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		    }
		
		
		
		
		
		
		
		
		
		
		
	
    }
}
