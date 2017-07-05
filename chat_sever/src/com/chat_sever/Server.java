package com.chat_sever;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.HashSet;

import com.DB.ManagerDB;
import com.DB.NotFoundExcepction;
import com.DB.Userinfo2;
import com.DB.Userinfo3;
import com.DB.passwordException;
import com.DB.stateExcepction;
import com.userlist.UserList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author yl
 *
 */
public class Server implements Runnable
{
	Socket socket;
	
	
	public Server(Socket socket)
	{
		this.socket=socket;
		
	}
	

	@SuppressWarnings("null")
	public void run() 
	{
		// TODO 自动生成的方法存根
		
		InputStream in=null;
		OutputStream out=null;
		boolean flat = false;
		
		String ss=null;
		
		
		
		byte[] bt=new byte[1024];
		try {
			
			in = socket.getInputStream();
			out = socket.getOutputStream();
			
			
			
			
			int len =in.read(bt);
			String s=new String(bt,0,len);
			JSONObject json = JSONObject.fromObject(s);// 解析
			String username = json.getString("username");
			String password = json.getString("password");
			System.out.println(username);
			if(username.trim().length()==11)
			{						
		     try{			
				Long.parseLong(username);//runtime 异常
				//没有异常，判断密码是否正确
				flat=true;
		     }catch(NumberFormatException e)
				{
				    System.out.println("非手机验证方法！");
				    flat=false;
				}
			}
			
	
			if(flat)//手机形式
			{
				try{
				
				 ss=new ManagerDB().phonenumberFormat(username,password);
				
				//登记到在线用户列表里
				
				 UserList.getUserList().registerUser(ss, null, username, socket);
				  out.write("{\"state\":0,\"msg\":\"登录成功!\"}".getBytes());
				  out.flush();
								
				
				}catch (NotFoundExcepction e) {
					out.write("{\"state\":2,\"msg\":\"账户名未注册!\"}".getBytes());
					out.flush();
					return;
				} catch (passwordException e) {
					out.write("{\"state\":1,\"msg\":\"用户密码错误!\"}".getBytes());
					out.flush();
					return;
				} catch (stateExcepction e) {
					out.write("{\"state\":3,\"msg\":\"账户锁定!\"}".getBytes());
					out.flush();
					return;
				} catch (SQLException e) {
					out.write("{\"state\":4,\"msg\":\"未知错误!\"}".getBytes());
					out.flush();
					return;
				}
			}
								
			else   //email形式
			{
				try{
					System.out.println("1");
					 ss=new ManagerDB().emailFormat(username,password);
					
					 System.out.println("2");
					//登记到在线用户列表里
					
					  UserList.getUserList().registerUser(ss, username, null, socket);
					  out.write("{\"state\":0,\"msg\":\"登录成功!\"}".getBytes());
					  out.flush();
										
					}catch (NotFoundExcepction e) {
						out.write("{\"state\":2,\"msg\":\"账户名未注册!\"}".getBytes());
						out.flush();
						return;
					} catch (passwordException e) {
						out.write("{\"state\":1,\"msg\":\"用户密码错误!\"}".getBytes());
						out.flush();
						return;
					} catch (stateExcepction e) {
						out.write("{\"state\":3,\"msg\":\"账户锁定!\"}".getBytes());
						out.flush();
						return;
					} catch (SQLException e) {
						out.write("{\"state\":4,\"msg\":\"未知错误!\"}".getBytes());
						out.flush();
						return;
					}
			}
			
			//处理登录成功后的后续指令
			//byte		
			while(true)
			{
				System.out.println("服务器还在运行");
				bt = new byte[2048];
				len = in.read(bt);
				String requst=new String(bt,0,len);
				
				
				
				System.out.println("在线用户的UID显示："+UserList.getUserList().getAlluid());
				//System.out.println("在线用户的信息显示："+UserList.getUserList().getinfo(ss));
				if(requst.equals("U0001"))
				{
					System.out.println("U0001指令");
					HashSet<Userinfo2> h;
					try {
						
                         ///////////将得到的信息先放到集合里，再传给json对象，json对象自动将集合里的
						///[{"back":"","dd":0,"email":"","mm":0,"name":"女","phonenumber":"","sex":"","yy":0},
                        ////{"back":"","dd":0,"email":"","mm":0,"name":"男","phonenumber":"","sex":"","yy":0}]
						///变成符合json解析形式的字符串形式

						h = new ManagerDB().getFriendList(ss);
						
						JSONArray jsonArray = JSONArray.fromObject(h);
						//System.out.println(jsonArray.toString());
						out.write(jsonArray.toString().getBytes());
						out.flush();
						
						
					} catch (SQLException e) 
					{
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					
					
				}else if(requst.equals("U0003"))   ///////////////接收获个人资料的请求命令
				{
					System.out.println("U0003指令");
					try {
						Userinfo3 u3  =new ManagerDB().getMyinfo(ss);
						json = JSONObject.fromObject(u3);
						out.write(json.toString().getBytes());
						out.flush();
					} catch (SQLException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}else if(requst.equals("U0002"))
				{
					System.out.println("U0002指令");
					//InputStream	input = socket.getInputStream();
					//OutputStream output= socket.getOutputStream();
					out.write(1);
					out.flush();
					
					//System.out.println("开始接收好友UID");
					bt=new byte[1024*200];
					len=in.read(bt);////////////////////////////////////////////////////要是对方没有好友，会处于一种等待状态
					//System.out.println("接收到了UID");
					String s3=new String(bt,0,len);
					StringBuffer bs3=new StringBuffer();
					String[] st=s3.split(",");
					//System.out.println("开始寻找在线好友");
					for(int i=0;i<st.length;i++)
					{
					if(UserList.getUserList().isOnline(st[i]))
						{
						bs3.append(st[i]);
						bs3.append(",");
						}					
					}
					
					if (bs3.length()==0)
					{out.write("没有好友在线".getBytes());
					out.flush();
					//System.out.println("没有好友在线发送过去了");
					}else{
					out.write(bs3.toString().getBytes());
					out.flush();
					}
					
				}
				
			}
			
			
			
			
			
			
			
		}
			catch( IOException e)
			{
				e.printStackTrace();
			}finally //用户退出了，关闭各种资源,用户端程序运行结束了，线程结束了，会进来这里//socket关闭后，会进来这里
			{///在一个线程中，只有线程运行结束和socket主动关闭，才会结束通信，否则一直服务器保持通信，等待处理输入输出流
				try {
					UserList.getUserList().closeUse(ss);
					System.out.println("资源已经关闭成功");
					in.close();
					out.close();
					socket.close();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				
				
			}
	}
}
			
				
			
			
									
			
			
			
			
			
			
			
			
			
		
		
		
		
			
		    
		
		
		
		
		
		
		
	


