package com.chat_sever;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.userlist.UserList;




/**
 * 用于更新服务器端用户的uip信息
 * @author yl
 *
 */
public class updateServer implements Runnable 
{

	public void run() 
	{
		// TODO 自动生成的方法存根
		
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
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					String ss=inpacket.getAddress().getHostAddress();
					
					UserList.getUserList().updateOnlineUDP((new String(inpacket.getData()).trim()),ss,inpacket.getPort());
					
					System.out.println("收到UDP更新信息！");
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}				
			}			
		} catch (SocketException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
