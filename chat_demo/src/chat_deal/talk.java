package chat_deal;

import java.net.DatagramPacket;

import net.sf.json.JSONObject;

public class talk implements Runnable {

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
					String msgq=JSONObject.fromObject(new String(inpacket.getData(),0,inpacket.getData().length)).getString("msg");
					Config.oneframe.addMessage(msgq);														
					System.out.println("�����ͻ��˷��͹��������ݣ�"+new String(inpacket.getData(),0,inpacket.getData().length));
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


