package chat_deal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;

import chat_demo.FriendList2;
import chat_demo.FriendShow;

/**
 * 
 * 
 * ��������������г�����ͨ��
 * @author yl
 *
 */
public class ClientThread implements Runnable {
	
	private Socket socket;
	boolean run=true;
	private InputStream in;
	private OutputStream out;
	
	 public ClientThread(Socket socket)
	{
		this.socket=socket;
	}
	
	

	public void run() {
		// TODO �Զ����ɵķ������
		
		
		
		
		try {
			in=socket.getInputStream();
			out=socket.getOutputStream();
			
			
		///////////////�ͻ�������õ��Լ��ĺ����б��Լ����ѵ��������	
			byte[] bt=new byte[2048];
			bt="U0001".getBytes();
			out.write(bt);
			out.flush();
			//�������Է������ĺ����б�����
			bt=new byte[4096*10];
			int len=in.read(bt);
			String fl=new String(bt,0,len);
			
			Config.friend_json_data=fl;
			
			System.out.println("ȫ�����ѵ����ϣ� "+Config.friend_json_data);
			
			
	////////////////////////�������ϵ�����
			bt=new byte[2048];
			bt="U0003".getBytes();
			out.write(bt);
			out.flush();
			//////////////�������ϵĻ��
			bt=new byte[4096];
			 len=in.read(bt);
			String f2=new String(bt,0,len);
			Config.my_json_data=f2;
			System.out.println("�������ϣ� "+Config.my_json_data);			
		} catch (IOException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		////////////////////////////////////�������������ϵ�UID
		String out_uid=Config.myFriendUid();
		int j=1;
		
		
		
		
		try {
			Random random = new Random();
			int t=random.nextInt(60000)%(60000-30005+1)+30005;
			DatagramSocket socket1=new DatagramSocket(t);
			Config.socket=socket1;
		} catch (SocketException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		
		
        /////////////////////////////////////�����������¸��û���������UDP�Ŀͻ����߳�
        new Thread(new sendUdp()).start();
				
        ////////////////////////////////////�����������
		//new Thread(new chatWith()).start();
		///////////////////////////////////�������������ͻ��˵�ת����Ϣ
		new Thread(new talk()).start();
		
		
		
		
		
		try {
			byte[] by=new byte[1024*10];
			while (true) 
			{
				//System.out.println("�ͻ������߳̽�����");
				//////////////////�����Լ���������
				out.write("U0002".getBytes());
				out.flush();
				//System.out.println("����ָ��ɹ�");
				in.read();
				//System.out.println("�õ��ظ���");
				/////////////�����������Լ����ѵ�UID����
			if(!out_uid.equals(""))
				{
				out.write(out_uid.getBytes());////////////////////////���ܴ����ֽ������������ᱻ�ȴ��С�������
				out.flush();
				//System.out.println("���ͺ���UID�ɹ�");
				/////////////////////�ȴ��������ߵĺ���UID
				by=new byte[1024*200];
				int len1=in.read(by);
				//System.out.println("���յ������ߵĺ���");
				String bbs=new String(by,0,len1);
				
				//System.out.println(bbs);
				Config.my_feiend_online=bbs;
				System.out.println("���ߺ��ѣ� "+Config.my_feiend_online);
			}else
			{
			  Config.my_feiend_online="û�к�������";
			  System.out.println("����û�к��ѣ�");
			}
				
				
//				if(j==1)
//				{
//					new FriendList2().setVisible(true);
//					j=j+1;
//				}
				
			    
				try {
					Thread.sleep(2000);
					//System.out.println("����ǰ");
					Config.fs.upDateList();
					//System.out.println("�����");
				} catch (InterruptedException e) {
				}
			
				
			}
			
		} catch (Exception e) {
			run = false;
		}
		
	}

}
