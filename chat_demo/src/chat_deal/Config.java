package chat_deal;



import java.net.DatagramSocket;

import javax.swing.JScrollPane;

import chat_demo.ChatWindows;
import chat_demo.FriendList2;
import chat_demo.FriendShow;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;




/**
 * @author yl
 *
 */
public class Config 
{
	
	public static final String IP = "127.0.0.1";
	// 登录端口
	public static final int LOGIN_PORT = 30000;
	public static final int EEG_PORT = 4001;
	public static final int udpsever = 30001;
	// 用户名和密码寄存
	public static String username;///////////////////////////static 类变量每次在一个进程main里可以被改变。一旦进程结束，下次就会被还原为初始化的值
	public static String password;
	
	
	private String netname;
	private String email;
	private String phonenumber;
	private String info;
	private String sex;
	
	public static String friend_json_data ;
	public static String my_json_data;
	
	public static String my_feiend_data;
	public static String my_feiend_online;
	
	public static FriendList2 fl;
	
	public static FriendShow fs;
	public static JScrollPane sp;
	
	public static DatagramSocket socket;
	public static ChatWindows oneframe;
	
	public static String myFriendUid()
	{
		JSONArray json = JSONArray.fromObject(friend_json_data);
		StringBuffer bs=new StringBuffer();
		for (int i = 0; i < json.size(); i++) {
			JSONObject jsonobj = (JSONObject) json.get(i);
			bs.append(jsonobj.getString("uid"));
			bs.append(",");
		}
		my_feiend_data=bs.toString();
		return my_feiend_data;
	}
	
	
	
	public String getNetname() {
		return netname;
	}
	public void setNetname(String netname) {
		this.netname = netname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
	
	
	
	
	
	
	
	
	

}
