package chat_deal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import net.sf.json.JSONObject;



public class ClientDeal ////////////////单例类，每次使用的对象都是一个初始的对象，不会被之前使用的结果所影响，但是在一个main函数里会被改变
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
			
			//开启新的线程去处理
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
