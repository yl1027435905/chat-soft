package com.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Test1 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		// TODO �Զ����ɵķ������
		Connection conn = null;  
        String sql;  
        String url = "jdbc:mysql://localhost:3306/serverad";  
        String user = "root";  
        String pwd = "yl2451169";  
  
        Class.forName("com.mysql.jdbc.Driver");  
        System.out.println("���ݿ��������سɹ�");  
        conn = DriverManager.getConnection(url, user, pwd);  
        System.out.println("��1��"); 
        java.sql.Statement statement = null;  
        statement = conn.createStatement();  
        sql = "select * from user where uid=1235";  
        ResultSet rs = null;  
        rs = statement.executeQuery(sql);
        
        System.out.println("��2��");
        ResultSetMetaData rm=rs.getMetaData();
        System.out.println(rm.getColumnCount()); 
        while(rs.next()){;
        	System.out.println("��3��"); 
            System.out.println(rs.getString("phonenumber")); 
        }
        rs.close();  
        conn.close();  
      

	}

}
