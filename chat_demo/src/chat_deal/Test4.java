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
		// TODO �Զ����ɵķ������
		Socket socket=new Socket(Config.IP,Config.LOGIN_PORT);
		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();
		
		
		String json_out="{\"username\":\"" + "15062201995" + "\",\"password\":\"" + "123" + "\"}";
		out.write(json_out.getBytes());
		out.flush();
		
		
		
		byte[] bt=new byte[1024];
		
		
		int len =in.read(bt);
		String s=new String(bt,0,len);
		JSONObject json = JSONObject.fromObject(s);// ����
		
		if(json.getInt("state")==0)
		{
			System.out.println("��¼�ɹ�");
		}else
		{
			System.out.println("��¼ʧ��");
			
		}
		
		while(true)
		{
		bt = new byte[1024];
		
		len = in.read(bt);
		}

	}

}
