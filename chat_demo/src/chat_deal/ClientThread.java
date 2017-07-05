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
 * 用于与服务器进行持续的通信
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
		// TODO 自动生成的方法存根
		
		
		
		
		try {
			in=socket.getInputStream();
			out=socket.getOutputStream();
			
			
		///////////////客户端请求得到自己的好友列表以及好友的相关资料	
			byte[] bt=new byte[2048];
			bt="U0001".getBytes();
			out.write(bt);
			out.flush();
			//接收来自服务器的好友列表数据
			bt=new byte[4096*10];
			int len=in.read(bt);
			String fl=new String(bt,0,len);
			
			Config.friend_json_data=fl;
			
			System.out.println("全部好友的资料： "+Config.friend_json_data);
			
			
	////////////////////////个人资料的请求
			bt=new byte[2048];
			bt="U0003".getBytes();
			out.write(bt);
			out.flush();
			//////////////个人资料的获得
			bt=new byte[4096];
			 len=in.read(bt);
			String f2=new String(bt,0,len);
			Config.my_json_data=f2;
			System.out.println("个人资料： "+Config.my_json_data);			
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		////////////////////////////////////解析出好友资料的UID
		String out_uid=Config.myFriendUid();
		int j=1;
		
		
		
		
		try {
			Random random = new Random();
			int t=random.nextInt(60000)%(60000-30005+1)+30005;
			DatagramSocket socket1=new DatagramSocket(t);
			Config.socket=socket1;
		} catch (SocketException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		
        /////////////////////////////////////开启持续更新该用户服务器端UDP的客户端线程
        new Thread(new sendUdp()).start();
				
        ////////////////////////////////////开启聊天服务
		//new Thread(new chatWith()).start();
		///////////////////////////////////开启接收其他客户端的转发信息
		new Thread(new talk()).start();
		
		
		
		
		
		try {
			byte[] by=new byte[1024*10];
			while (true) 
			{
				//System.out.println("客户端子线程进行中");
				//////////////////更新自己好友在线
				out.write("U0002".getBytes());
				out.flush();
				//System.out.println("发送指令成功");
				in.read();
				//System.out.println("得到回复了");
				/////////////传给服务器自己好友的UID数据
			if(!out_uid.equals(""))
				{
				out.write(out_uid.getBytes());////////////////////////可能传空字节流，服务器会被等待中》》》》
				out.flush();
				//System.out.println("发送好友UID成功");
				/////////////////////等待接收在线的好友UID
				by=new byte[1024*200];
				int len1=in.read(by);
				//System.out.println("接收到了在线的好友");
				String bbs=new String(by,0,len1);
				
				//System.out.println(bbs);
				Config.my_feiend_online=bbs;
				System.out.println("在线好友： "+Config.my_feiend_online);
			}else
			{
			  Config.my_feiend_online="没有好友在线";
			  System.out.println("您还没有好友！");
			}
				
				
//				if(j==1)
//				{
//					new FriendList2().setVisible(true);
//					j=j+1;
//				}
				
			    
				try {
					Thread.sleep(2000);
					//System.out.println("处理前");
					Config.fs.upDateList();
					//System.out.println("处理后！");
				} catch (InterruptedException e) {
				}
			
				
			}
			
		} catch (Exception e) {
			run = false;
		}
		
	}

}
