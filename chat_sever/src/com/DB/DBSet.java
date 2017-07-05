package com.DB;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class DBSet 
{
	private static final String Drivercalss="com.mysql.jdbc.Driver"; 
	private static final String url="jdbc:mysql://localhost:3306/serverad";
	//private static final String url="jdbc:mysql://localhost:3306/serverad?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=UTC";
	private static final String user="root";
	private static final String password="yl2451169";
	private static final int  maxpoolsize=40;
	private static final int  minpoolsize=2;
	//private static ComboPooledDataSource pool=null;
	private static ComboPooledDataSource ds=new ComboPooledDataSource();;
	//public static DataSource pool = null;
	static
	{
		//ComboPooledDataSource ds=new ComboPooledDataSource();
		ds.setJdbcUrl(url);
		try {
			ds.setDriverClass(Drivercalss);
		} catch (PropertyVetoException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		ds.setUser(user);
		ds.setPassword(password);
		ds.setMaxPoolSize(maxpoolsize);
		ds.setMinPoolSize(minpoolsize);
		//pool=ds;
	}
	
	public static Connection getConnection1() throws SQLException
	{
		return ds.getConnection();
	}
	
	
	
	
	
	

}
