package chat_deal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import net.sf.json.JSONObject;

public class Test4 {

	public static void main(String[] args) throws UnknownHostException, IOException 
	{
		// TODO 自动生成的方法存根
		Socket socket=new Socket(Config.IP,Config.LOGIN_PORT);
		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();
		
		
		String json_out="{\"username\":\"" + "15062201995" + "\",\"password\":\"" + "123" + "\"}";
		out.write(json_out.getBytes());
		out.flush();
		
		
		
		byte[] bt=new byte[1024];
		
		
		int len =in.read(bt);
		String s=new String(bt,0,len);
		JSONObject json = JSONObject.fromObject(s);// 解析
		
		if(json.getInt("state")==0)
		{
			System.out.println("登录成功");
		}else
		{
			System.out.println("登录失败");
			
		}
		
		while(true)
		{
		bt = new byte[1024];
		
		len = in.read(bt);
		}

	}

}
