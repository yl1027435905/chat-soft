package com.chat_sever;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;



public class UPDServer implements Runnable {

	public void run() 
	{
		// TODO �Զ����ɵķ������
		try {
			DatagramSocket socket=new DatagramSocket(30002);////����������Ϣ�˿�
		    
		    byte[] inbuff=new byte[1024*32];
		    DatagramPacket inpacket=new DatagramPacket(inbuff,inbuff.length);
		    while(true)
		    {
		    	try {
					socket.receive(inpacket);
					System.out.println("���1");
					new Thread(new DealEmg(inpacket)).start();
					
					
					
					
				} catch (IOException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
		    	
		    			    	
		    }
		    
		    			    
		    
		    }catch (SocketException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		    }
		
		
		
		
		
		
		
		
		
		
		
	
    }
}
