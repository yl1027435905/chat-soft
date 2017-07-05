package chat_demo;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import chat_deal.Config;

public class FriendShow extends JPanel 
{

	/**
	 * 用于设置整个好友列表的显示，包括在线和不在线的好友
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Create the panel
	 */
	
////////////如果被对方删除后，对方的上一次更新的资料还在，但是下一次重启客户端的时候，所以的数据都没有了；
	 public static HashMap<String,OneJpanel> h=new HashMap<String,OneJpanel>();
	 
	
	
	
	public FriendShow() 
	{
		super();
		setLayout(null);
		
		upDateList();
		//
		Config.fs=this;
		
	}
		
		
	
		
		//this.setPreferredSize(new Dimension(0,40*50));
	
	public void upDateList()
	{
		//System.out.println("能否进来？");
		String friend_data=Config.friend_json_data;
		
		JSONArray json = JSONArray.fromObject(friend_data);
		if(h.size()==0)
		{System.out.println("好友的个数是： "+json.size());
		for (int i = 0; i < json.size(); i++) {
			JSONObject jsonobj = (JSONObject) json.get(i);
			h.put(jsonobj.getString("uid"), new OneJpanel(jsonobj.getString("uid"),jsonobj.getString("image"),jsonobj.getString("netname"),
					jsonobj.getString("info")));
		}
		}else
		{
			for (int i = 0; i < json.size(); i++) 
			{
				JSONObject jsonobj = (JSONObject) json.get(i);
				String uid2=jsonobj.getString("uid");
				if(h.containsKey(uid2))
				{
					OneJpanel one=h.get(uid2);
					one.setImage(jsonobj.getString("image"));
					one.setInfo(jsonobj.getString("info"));
					one.setNetname(jsonobj.getString("netname"));																		
				}else
				{				
				    h.put(uid2, new OneJpanel(jsonobj.getString("uid"),jsonobj.getString("image"),jsonobj.getString("netname"),
						jsonobj.getString("info")));
				}
			
		    }
		
	   }
		//System.out.println("开始处理在线设置");
		setOnline();
		//System.out.println("开始处理排序展示设置");
		rankDisply();
		//System.out.println("处理好了");
	}
	
	
	public void setOnline()
	{
		if(!Config.my_feiend_online.equals("没有好友在线"))
		{
		String online=Config.my_feiend_online;
		String[] online1=online.split(",");
		for(String s:online1)
		{
			OneJpanel value=h.get(s);
			value.setOnline(true);////////////////为了正确的排序面板而准备的
			value.setLableImage(true);
			
		}
		}else
		{
			Collection<OneJpanel> value_set=h.values();
			List<OneJpanel> l1=new ArrayList<OneJpanel>(value_set);
			for(OneJpanel one2:l1)
			{
				one2.setOnline(false);
				one2.setLableImage(false);
			}
		}
	}
	
	public void rankDisply()
	{
		 Collection<OneJpanel> value_set=h.values();
		 List<OneJpanel> l1=new ArrayList<OneJpanel>(value_set);
		 Collections.sort(l1);
		 
		 
		 this.removeAll();
			int i = 0;
			for (OneJpanel faceJPanel : l1) {
				faceJPanel.setBounds(0, i++ * 55, 546, 59);
				this.add(faceJPanel);
			}
			this.setPreferredSize(new Dimension(0, 40 * l1.size()));
			this.updateUI();		 
	}
	
		
}













	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



