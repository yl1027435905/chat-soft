package com.chat_sever;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Random;





import com.DB.ManagerDB;

import net.sf.json.JSONObject;

public class Server2 implements Runnable////////////////ֻҪ������û�������ڳ����е��õ�����߷��������б��ı�ľ�̬����ֵ�ͱ��ı��ˣ�ֱ�������������´βŻ�����
{
	
    Socket socket;
	
	
	public Server2(Socket socket)
	{
		this.socket=socket;
		
	}

	public void run() 
	{
		// TODO �Զ����ɵķ������
		InputStream in=null;
	    OutputStream out=null;
	    
	    
	     try {
			in=socket.getInputStream();
			out=socket.getOutputStream();
			
		 //////////////////���տͻ��˵�ע����߷�����֤�������
			byte[] bt=new byte[1024];
			int len=in.read(bt);
			String s=new String(bt,0,len);
			
			JSONObject json = JSONObject.fromObject(s);// ����
			String format=json.getString("type");
			if(format.equals("code"))  /////////�Ƿ�����֤�������
			{
				String format2=json.getString("username");
				if(format2.indexOf('@')>-1)//////////////������email����ʽ�����ܻ��Ǹ���Ч�ģ����ղ������û��Լ�������
				{
					StringBuffer bs=new StringBuffer();
					Random random = new Random();
					for(int i=0;i<4;i++)
					{
						bs.append(random.nextInt(10));
						
					}
					CodeTest.getHashMap().put(format2, bs.toString());///////////�Ȼ�ע���ʱ����Ҫ��֤����֤��
					////////////ͨ��163���������͸���֤����û�
					if(Sendcode.sendEmail(format2,bs.toString()))
					{						
						out.write("��֤�뷢�ͳɹ�����鿴��".getBytes());
						out.flush();
						CodeTest.getHashMap().put(format2, bs.toString());///////////�Ȼ�ע���ʱ����Ҫ��֤����֤��
						System.out.println("��֤���ǣ�"+CodeTest.getHashMap().get(format2));
					}else
					{
						out.write("�û�����ʽ����ȷ���������Ӳ����ã����������룡".getBytes());
						out.flush();
					}										
				}else             //// �������ֻ�����ʽ�����ܻ��Ǹ���Ч�ģ����ղ������û��Լ�������
				{					
					out.write("�ֻ���֤��ʱͣ�ã�лл����ʹ�ã�".getBytes());
					out.flush();
				}				
			}else if (format.equals("reg"))                    //////////��ע�������
			{
				String username=json.getString("username");
				String password=json.getString("password");
				String code=json.getString("code");
				System.out.println("���ﲽ1");
			try{
				boolean flat=new ManagerDB().containsUser(username);
				System.out.println(flat);
				if(flat)
				{
					
					//System.out.println("��֤��֤���ǣ�"+CodeTest.getHashMap().get(username));
					if(code.equals(CodeTest.getHashMap().get(username)))
					{
						System.out.println("���ﲽ3");
						new ManagerDB().userRegister(username, password);
						out.write("��ϲ��ע��ɹ���".getBytes());
						out.flush();
						CodeTest.getHashMap().remove(username);																		
					}else 
					{
						out.write("��֤����������»�ȡ��".getBytes());
						out.flush();
					}				
					
				}else
				{
					out.write("�˻��Ѵ��ڣ���ֱ�ӵ�¼��".getBytes());
					out.flush();
				}
				}catch(SQLException e)
				{
					System.out.println("���ﲽ4");
					out.write("δ֪���������ĵȴ��޸�".getBytes());
					out.flush();
				}			
			}		
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally
		{
			try {
				socket.close();
				in.close();
				out.close();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			System.out.println("ע���������Դ�رճɹ�");
			
		}
	    
		
		
		
		
		
		
		
		
		
		
	}

}
