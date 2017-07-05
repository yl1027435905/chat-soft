package chat_deal;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import net.sf.json.JSONObject;



public class sendUdp implements Runnable 
{

	public void run() 
	{
		// TODO 自动生成的方法存根
		try{
			//DatagramSocket socket=new DatagramSocket();
			byte[] outbuff=new byte[1024];
			
			while(true)
			{
			    try {
			    	
			    	 outbuff=JSONObject.fromObject(Config.my_json_data).getString("uid").getBytes();
			    	
			    	DatagramPacket inpacket=new DatagramPacket(outbuff,outbuff.length,InetAddress.getByName(Config.IP),30001);
					Config.socket.send(inpacket);
					
					
					Thread.sleep(4999);
					
		        }catch(Exception e)
			   {
				e.printStackTrace();
			   }
		}
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}


	}

}
