package com.chat_sever;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Random;





import com.DB.ManagerDB;

import net.sf.json.JSONObject;

public class Server2 implements Runnable////////////////只要主函数没结束，在程序中调用的类或者方法，其中被改变的静态变量值就被改变了，直到主函数结束下次才回重新
{
	
    Socket socket;
	
	
	public Server2(Socket socket)
	{
		this.socket=socket;
		
	}

	public void run() 
	{
		// TODO 自动生成的方法存根
		InputStream in=null;
	    OutputStream out=null;
	    
	    
	     try {
			in=socket.getInputStream();
			out=socket.getOutputStream();
			
		 //////////////////接收客户端的注册或者发送验证码的请求
			byte[] bt=new byte[1024];
			int len=in.read(bt);
			String s=new String(bt,0,len);
			
			JSONObject json = JSONObject.fromObject(s);// 解析
			String format=json.getString("type");
			if(format.equals("code"))  /////////是发送验证码的请求
			{
				String format2=json.getString("username");
				if(format2.indexOf('@')>-1)//////////////可能是email的形式，可能会是个无效的，接收不到是用户自己的问题
				{
					StringBuffer bs=new StringBuffer();
					Random random = new Random();
					for(int i=0;i<4;i++)
					{
						bs.append(random.nextInt(10));
						
					}
					CodeTest.getHashMap().put(format2, bs.toString());///////////等会注册的时候需要验证该验证码
					////////////通过163服务器发送个验证码给用户
					if(Sendcode.sendEmail(format2,bs.toString()))
					{						
						out.write("验证码发送成功，请查看！".getBytes());
						out.flush();
						CodeTest.getHashMap().put(format2, bs.toString());///////////等会注册的时候需要验证该验证码
						System.out.println("验证码是："+CodeTest.getHashMap().get(format2));
					}else
					{
						out.write("用户名格式不正确或网络连接不可用，请重新输入！".getBytes());
						out.flush();
					}										
				}else             //// 可能是手机的形式，可能会是个无效的，接收不到是用户自己的问题
				{					
					out.write("手机验证暂时停用，谢谢您的使用！".getBytes());
					out.flush();
				}				
			}else if (format.equals("reg"))                    //////////是注册的请求
			{
				String username=json.getString("username");
				String password=json.getString("password");
				String code=json.getString("code");
				System.out.println("到达步1");
			try{
				boolean flat=new ManagerDB().containsUser(username);
				System.out.println(flat);
				if(flat)
				{
					
					//System.out.println("验证验证码是："+CodeTest.getHashMap().get(username));
					if(code.equals(CodeTest.getHashMap().get(username)))
					{
						System.out.println("到达步3");
						new ManagerDB().userRegister(username, password);
						out.write("恭喜您注册成功！".getBytes());
						out.flush();
						CodeTest.getHashMap().remove(username);																		
					}else 
					{
						out.write("验证码错误，请重新获取！".getBytes());
						out.flush();
					}				
					
				}else
				{
					out.write("账户已存在，请直接登录！".getBytes());
					out.flush();
				}
				}catch(SQLException e)
				{
					System.out.println("到达步4");
					out.write("未知错误，请耐心等待修复".getBytes());
					out.flush();
				}			
			}		
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally
		{
			try {
				socket.close();
				in.close();
				out.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			System.out.println("注册服务器资源关闭成功");
			
		}
	    
		
		
		
		
		
		
		
		
		
		
	}

}
