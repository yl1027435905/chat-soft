package com.chat_sever;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer implements Runnable {

	public void run() {
		// TODO �Զ����ɵķ������
		//ServerSocket s;	
				ServerSocket ss;
				try {
					ss = new ServerSocket(30000);
					while(true)
					{
						//ServerSocket ss;
						Socket s= ss.accept();
						//s.setSoTimeout(10000);
						System.out.println("��¼������ͨ�ųɹ���");
						//�����̷߳���Ϊ�ͻ��˷���
						new Thread(new Server(s)).start();			
					}
				} catch (IOException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}	
				
		
	}
	
	
	public static void main(String[] args) throws IOException  
	{
		// TODO �Զ����ɵķ������
		
		//ServerSocket s;
		new Thread(new ChatServer()).start();
		new Thread(new UPDServer()).start();
		new Thread(new updateServer()).start();
		
	}
	
	
	
	
	
	
	
	

}
