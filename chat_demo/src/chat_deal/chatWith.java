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
		// TODO �Զ����ɵķ������
		try{
		//DatagramSocket socket=new DatagramSocket();
		byte[] inbuff=new byte[1024*32];
		
		while(true)
		{
		    try {		    		    	 
		    	DatagramPacket inpacket=new DatagramPacket(inbuff,0,inbuff.length);
				Config.socket.receive(inpacket);/////////////��ʱ�Ȳ�����								
				//Thread.sleep(4999);
				System.out.println("��֤��������Ϣ��"+new String(inpacket.getData(),0,inpacket.getData().length));
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
