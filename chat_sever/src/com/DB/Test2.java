package com.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test2 {

	public static void main(String[] args) throws SQLException, passwordException, stateExcepction, NotFoundExcepction 
	{
		// TODO 自动生成的方法存根
		Connection c=DBSet.getConnection1();
		Statement s=c.createStatement();
		String sql="select * from user where phonenumber=15062201995";
		ResultSet rs=s.executeQuery(sql);
		if(rs.next())
		{
			if(rs.getInt("state")==0)
			{
			if(rs.getString("password").equals("123"))
			{
				System.out.println("验证成功");
			}else
			{   System.out.println("未验证成功");
				throw new passwordException();
			}
			}
			else{throw new stateExcepction();}
		}else
		{
			throw new NotFoundExcepction();
			
		}
		//System.out.println("成功");

	}

}
