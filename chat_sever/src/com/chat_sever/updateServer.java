package com.chat_sever;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.userlist.UserList;




/**
 * ���ڸ��·��������û���uip��Ϣ
 * @author yl
 *
 */
public class updateServer implements Runnable 
{

	public void run() 
	{
		// TODO �Զ����ɵķ������
		
		try {
			DatagramSocket socket=new DatagramSocket(30001);
			byte[] inbuff=new byte[1024];
			DatagramPacket inpacket=new DatagramPacket(inbuff,1024);
			while(true)
			{
			try {
					socket.receive(inpacket);
/*					String jsonStr = new String(inpacket.getData(), 0, inpacket.getLength());
					JSONObject json = JSONObject.fromObject(jsonStr);
					
					String s=json.getString("myuid");*/
					try {
						Thread.sleep(9999);
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
					String ss=inpacket.getAddress().getHostAddress();
					
					UserList.getUserList().updateOnlineUDP((new String(inpacket.getData()).trim()),ss,inpacket.getPort());
					
					System.out.println("�յ�UDP������Ϣ��");
				} catch (IOException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}				
			}			
		} catch (SocketException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}

}
