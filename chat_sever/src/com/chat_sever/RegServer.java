package com.chat_sever;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;





/**
 * 
 * ����ע��ķ������߳�
 * @author yl
 *
 */
public class RegServer implements Runnable {

	public void run() {
		// TODO �Զ����ɵķ������
		ServerSocket rs;
		try {
			rs = new ServerSocket(4001);
			while(true)
			{
				//ServerSocket ss;
				Socket s= rs.accept();
				//s.setSoTimeout(10000);
				System.out.println("ע�������ͨ�ųɹ���");
				//�����̷߳���Ϊ�ͻ��˷���
				new Thread(new Server2(s)).start();			
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
		new Thread(new RegServer()).start();
		
		
	}

}
