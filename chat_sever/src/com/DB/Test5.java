package com.DB;

import java.sql.SQLException;
import java.util.HashSet;

import net.sf.json.JSONArray;

public class Test5 {

	public static void main(String[] args) throws SQLException 
	{
		// TODO �Զ����ɵķ������
		HashSet<Userinfo2> h=new ManagerDB().getFriendList("12");
		JSONArray jsonArray = JSONArray.fromObject(h);
		System.out.println(jsonArray.toString());
		
		

	}

}
