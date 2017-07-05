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
		// TODO �Զ����ɵķ������
		
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
			JSONObject json = JSONObject.fromObject(s);// ����
			String username = json.getString("username");
			String password = json.getString("password");
			System.out.println(username);
			if(username.trim().length()==11)
			{						
		     try{			
				Long.parseLong(username);//runtime �쳣
				//û���쳣���ж������Ƿ���ȷ
				flat=true;
		     }catch(NumberFormatException e)
				{
				    System.out.println("���ֻ���֤������");
				    flat=false;
				}
			}
			
	
			if(flat)//�ֻ���ʽ
			{
				try{
				
				 ss=new ManagerDB().phonenumberFormat(username,password);
				
				//�Ǽǵ������û��б���
				
				 UserList.getUserList().registerUser(ss, null, username, socket);
				  out.write("{\"state\":0,\"msg\":\"��¼�ɹ�!\"}".getBytes());
				  out.flush();
								
				
				}catch (NotFoundExcepction e) {
					out.write("{\"state\":2,\"msg\":\"�˻���δע��!\"}".getBytes());
					out.flush();
					return;
				} catch (passwordException e) {
					out.write("{\"state\":1,\"msg\":\"�û��������!\"}".getBytes());
					out.flush();
					return;
				} catch (stateExcepction e) {
					out.write("{\"state\":3,\"msg\":\"�˻�����!\"}".getBytes());
					out.flush();
					return;
				} catch (SQLException e) {
					out.write("{\"state\":4,\"msg\":\"δ֪����!\"}".getBytes());
					out.flush();
					return;
				}
			}
								
			else   //email��ʽ
			{
				try{
					System.out.println("1");
					 ss=new ManagerDB().emailFormat(username,password);
					
					 System.out.println("2");
					//�Ǽǵ������û��б���
					
					  UserList.getUserList().registerUser(ss, username, null, socket);
					  out.write("{\"state\":0,\"msg\":\"��¼�ɹ�!\"}".getBytes());
					  out.flush();
										
					}catch (NotFoundExcepction e) {
						out.write("{\"state\":2,\"msg\":\"�˻���δע��!\"}".getBytes());
						out.flush();
						return;
					} catch (passwordException e) {
						out.write("{\"state\":1,\"msg\":\"�û��������!\"}".getBytes());
						out.flush();
						return;
					} catch (stateExcepction e) {
						out.write("{\"state\":3,\"msg\":\"�˻�����!\"}".getBytes());
						out.flush();
						return;
					} catch (SQLException e) {
						out.write("{\"state\":4,\"msg\":\"δ֪����!\"}".getBytes());
						out.flush();
						return;
					}
			}
			
			//�����¼�ɹ���ĺ���ָ��
			//byte		
			while(true)
			{
				System.out.println("��������������");
				bt = new byte[2048];
				len = in.read(bt);
				String requst=new String(bt,0,len);
				
				
				
				System.out.println("�����û���UID��ʾ��"+UserList.getUserList().getAlluid());
				//System.out.println("�����û�����Ϣ��ʾ��"+UserList.getUserList().getinfo(ss));
				if(requst.equals("U0001"))
				{
					System.out.println("U0001ָ��");
					HashSet<Userinfo2> h;
					try {
						
                         ///////////���õ�����Ϣ�ȷŵ�������ٴ���json����json�����Զ����������
						///[{"back":"","dd":0,"email":"","mm":0,"name":"Ů","phonenumber":"","sex":"","yy":0},
                        ////{"back":"","dd":0,"email":"","mm":0,"name":"��","phonenumber":"","sex":"","yy":0}]
						///��ɷ���json������ʽ���ַ�����ʽ

						h = new ManagerDB().getFriendList(ss);
						
						JSONArray jsonArray = JSONArray.fromObject(h);
						//System.out.println(jsonArray.toString());
						out.write(jsonArray.toString().getBytes());
						out.flush();
						
						
					} catch (SQLException e) 
					{
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
					
					
				}else if(requst.equals("U0003"))   ///////////////���ջ�������ϵ���������
				{
					System.out.println("U0003ָ��");
					try {
						Userinfo3 u3  =new ManagerDB().getMyinfo(ss);
						json = JSONObject.fromObject(u3);
						out.write(json.toString().getBytes());
						out.flush();
					} catch (SQLException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}else if(requst.equals("U0002"))
				{
					System.out.println("U0002ָ��");
					//InputStream	input = socket.getInputStream();
					//OutputStream output= socket.getOutputStream();
					out.write(1);
					out.flush();
					
					//System.out.println("��ʼ���պ���UID");
					bt=new byte[1024*200];
					len=in.read(bt);////////////////////////////////////////////////////Ҫ�ǶԷ�û�к��ѣ��ᴦ��һ�ֵȴ�״̬
					//System.out.println("���յ���UID");
					String s3=new String(bt,0,len);
					StringBuffer bs3=new StringBuffer();
					String[] st=s3.split(",");
					//System.out.println("��ʼѰ�����ߺ���");
					for(int i=0;i<st.length;i++)
					{
					if(UserList.getUserList().isOnline(st[i]))
						{
						bs3.append(st[i]);
						bs3.append(",");
						}					
					}
					
					if (bs3.length()==0)
					{out.write("û�к�������".getBytes());
					out.flush();
					//System.out.println("û�к������߷��͹�ȥ��");
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
			}finally //�û��˳��ˣ��رո�����Դ,�û��˳������н����ˣ��߳̽����ˣ����������//socket�رպ󣬻��������
			{///��һ���߳��У�ֻ���߳����н�����socket�����رգ��Ż����ͨ�ţ�����һֱ����������ͨ�ţ��ȴ��������������
				try {
					UserList.getUserList().closeUse(ss);
					System.out.println("��Դ�Ѿ��رճɹ�");
					in.close();
					out.close();
					socket.close();
				} catch (IOException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				
				
			}
	}
}
			
				
			
			
									
			
			
			
			
			
			
			
			
			
		
		
		
		
			
		    
		
		
		
		
		
		
		
	


