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
	 * �����������������б����ʾ���������ߺͲ����ߵĺ���
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Create the panel
	 */
	
////////////������Է�ɾ���󣬶Է�����һ�θ��µ����ϻ��ڣ�������һ�������ͻ��˵�ʱ�����Ե����ݶ�û���ˣ�
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
		//System.out.println("�ܷ������");
		String friend_data=Config.friend_json_data;
		
		JSONArray json = JSONArray.fromObject(friend_data);
		if(h.size()==0)
		{System.out.println("���ѵĸ����ǣ� "+json.size());
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
		//System.out.println("��ʼ������������");
		setOnline();
		//System.out.println("��ʼ��������չʾ����");
		rankDisply();
		//System.out.println("�������");
	}
	
	
	public void setOnline()
	{
		if(!Config.my_feiend_online.equals("û�к�������"))
		{
		String online=Config.my_feiend_online;
		String[] online1=online.split(",");
		for(String s:online1)
		{
			OneJpanel value=h.get(s);
			value.setOnline(true);////////////////Ϊ����ȷ����������׼����
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













	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



