package chat_deal;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class chatWith implements Runnable 
{

	public void run() 
	{
		// TODO 自动生成的方法存根
		try{
		//DatagramSocket socket=new DatagramSocket();
		byte[] inbuff=new byte[1024*32];
		
		while(true)
		{
		    try {		    		    	 
		    	DatagramPacket inpacket=new DatagramPacket(inbuff,0,inbuff.length);
				Config.socket.receive(inpacket);/////////////暂时先不发送								
				//Thread.sleep(4999);
				System.out.println("验证回来的信息："+new String(inpacket.getData(),0,inpacket.getData().length));
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
