package chat_deal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import net.sf.json.JSONObject;



public class ClientDeal ////////////////�����࣬ÿ��ʹ�õĶ�����һ����ʼ�Ķ��󣬲��ᱻ֮ǰʹ�õĽ����Ӱ�죬������һ��main������ᱻ�ı�
{
	private static ClientDeal client=new ClientDeal();
	
	
	private ClientDeal(){}
	
	
	
	public static ClientDeal getClientDeal()
	{
		return client;
	}
	
	private  Socket socket;
	private InputStream in;
	private OutputStream out;
	public JSONObject clientLogin() throws UnknownHostException, IOException
	{
		    
		    socket=new Socket(Config.IP,Config.LOGIN_PORT);
			in=socket.getInputStream();
			out=socket.getOutputStream();
			
			String json_out="{\"username\":\"" + Config.username + "\",\"password\":\"" + Config.password + "\"}";
			out.write(json_out.getBytes());
			out.flush();
			
			byte[] b=new byte[1024];
			int len=in.read(b);
			String js=new String(b,0,len);
			JSONObject json_in = JSONObject.fromObject(js);
			
			//�����µ��߳�ȥ����
			//socket.close();
			//new Thread(new ClientThread(socket)).start();
			//socket.close();
		
			return json_in;
				
	}
	
	
	public void startNewThread()
	{
		new Thread(new ClientThread(socket)).start();
	}
	
	
	
	
	
	
	

}
