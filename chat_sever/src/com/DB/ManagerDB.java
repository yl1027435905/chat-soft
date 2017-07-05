package com.DB;





import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;





/**
 * 
 * ���ڸ�����������ģ�������֤��Ϣ���������û����ݵ�
 * @author yl
 *
 */
public class ManagerDB 
{
	
	//ѡ��email��֤
	public String emailFormat(String key, String password)
			throws SQLException, stateExcepction, passwordException, NotFoundExcepction
	{
		Connection c=null;
		
		try{
		   c=DBSet.getConnection1();
		//PreparedStatement s = c.prepareStatement(sql);
		//s.setString(1, key);
		//Statement s=c.createStatement();
		String sql="select * from user where email=?";
		PreparedStatement s = c.prepareStatement(sql);
		s.setString(1, key);
		System.out.println("��ʼ��֤��");
		ResultSet rs=s.executeQuery();
		//System.out.println("�Ƿ��м�¼��"+rs.next());
		  if(rs.next())
		  {
			if(rs.getInt("state")==0)
			{
			if(rs.getString("password").equals(password))
			{
				 return rs.getString("uid");
			}else
			{
				throw new passwordException();
			}
			}
			else{throw new stateExcepction();}
		  }else
		   {
			throw new NotFoundExcepction();
			
		   }
		}catch(SQLException e)
		{
			e.printStackTrace();
			throw e;
		}finally
		{
			c.close();
		}
		
	}
	
	//ѡ���ֻ���֤
	public String phonenumberFormat(String key, String password) 
			throws SQLException, stateExcepction, passwordException, NotFoundExcepction
	{
		Connection c=null;
		try{
			c=DBSet.getConnection1();
			//PreparedStatement s = c.prepareStatement(sql);
			//s.setString(1, key);
			//Statement s=c.createStatement();
			String sql="select * from user where phonenumber=?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, key);
			ResultSet rs=s.executeQuery();
	
	if(rs.next())
	{
		if(rs.getInt("state")==0)
		{
		if(rs.getString("password").equals(password))
		{
			return rs.getString("uid");
		}else
		{
			throw new passwordException();
		}
		}
		else{throw new stateExcepction();}
	}else
	{
		throw new NotFoundExcepction();
		
	}
		}catch(SQLException e)
		{
			e.printStackTrace();
			throw e;
			
		}finally{
			c.close();
		}
		 
	}
	
	
	
	public HashSet<Userinfo2> getFriendList(String uid) throws SQLException
	{
		Connection c=null;
		HashSet<Userinfo2> hs=new HashSet<Userinfo2>();
		try{
		 c=DBSet.getConnection1();
	     //PreparedStatement s = c.prepareStatement(sql);
	     //s.setString(1, key);
	        //Statement s=c.createStatement();
	        String sql="SELECT u.`uid`,u.`netname`,u.`email`,u.`phonenumber`,u.`info`,u.`sex`,u.`image`"
	        +"FROM USER u JOIN friendlist f ON u.`uid`=f.`friendid` AND f.uid=?";////////////////������������ݷ�ʽ
	        PreparedStatement s = c.prepareStatement(sql);
	        s.setString(1, uid);
	        ResultSet rs=s.executeQuery();
	        while(rs.next())
	        {
	        	Userinfo2 ui=new Userinfo2();
	        	ui.setNetname(rs.getString("netname"));
	        	ui.setEmail(rs.getString("email"));
	        	ui.setPhonenumber(rs.getString("phonenumber"));
	        	ui.setInfo(rs.getString("info"));
	        	ui.setSex(rs.getString("sex"));	 
	        	ui.setUid(rs.getString("uid"));
	        	ui.setImage(rs.getString("image"));
	        	hs.add(ui);	        	
	        }
	        return hs;	
	       }catch(SQLException e)
	       {
		        throw e;
	       }finally
	       {
		           c.close();
	       }
	}
	
	
	
	
	public Userinfo3 getMyinfo (String uid) throws SQLException
	{
		Connection c=null;
		Userinfo3 hs=new Userinfo3();
		try{
		 c=DBSet.getConnection1();
	     //PreparedStatement s = c.prepareStatement(sql);
	     //s.setString(1, key);
	        Statement s=c.createStatement();
	        String sql="SELECT * from user where uid="+uid;////////////////������������ݷ�ʽ	        
	        ResultSet rs=s.executeQuery(sql);
	        while(rs.next())
	        {
	        	
	        	hs.setUid(rs.getString("uid"));
				hs.setPhonenumber(rs.getString("phonenumber"));
				hs.setEmail(rs.getString("email"));
				hs.setNetname(rs.getString("netname"));
				hs.setInfo(rs.getString("info"));
				hs.setName(rs.getString("name"));
				hs.setImage(rs.getString("image"));
				hs.setBack(rs.getString("back"));
				hs.setSex(rs.getString("sex"));
				hs.setYy(rs.getInt("yy"));
				hs.setMm(rs.getInt("mm"));
				hs.setDd(rs.getInt("dd"));	        	
	        		        	
	        }
	        return hs;	
	     }catch(SQLException e)
	     {
		      throw e;
	      }finally
	     {
		       c.close();
	     }
	}
	
	
	 public  boolean containsUser(String username) throws SQLException 
	 {
		    Connection c=null;			
			try{
			 c=DBSet.getConnection1();
		     //PreparedStatement s = c.prepareStatement(sql);
		     //s.setString(1, key);
		        //Statement s=c.createStatement();
			 if(username.indexOf('@')>-1)
			 {
		        String sql1="select * from user where email=?";////////////////������������ݷ�ʽ
		       
		        
		        PreparedStatement s=c.prepareStatement(sql1);
		        
		        
		     
		     
		       
				s.setString(1, username);				
				ResultSet rs1=s.executeQuery();
				//System.out.println("�Ƿ��м�¼����"+rs1.next());
				if(rs1.next())
				{
					return false;
				}else
					{System.out.println("Ϊʲô������������"+rs1.next());
					return true;}
				
				
				
				
				
		     }else
		        {
		    	 String sql1="select * from user where phonenumber=?";////////////////������������ݷ�ʽ
				
		    	 PreparedStatement s=c.prepareStatement(sql1);
			        			       
					s.setString(1, username);				
					ResultSet rs1=s.executeQuery();
					System.out.println("�Ƿ��м�¼��"+rs1.next());
				
				
				
				if(rs1.next()) 
				        {    
				        	
				        	System.out.println("�������˻�");
				        	return false;
				        }
				        else {return true;}	
		        }
		        //String sql2="SELECT * FROM user where phonenumber="+username;////////////////������������ݷ�ʽ
		        
		        //ResultSet rs1=s.executeQuery(sql1);
		       
		        //ResultSet rs2=s.executeQuery(sql2);
		        
		                
		       }catch(SQLException e)
		       {
		    	   e.printStackTrace();
		    	   throw e;
			       
		       }finally
		       {
		    	   c.close();
		       }
		 
	 }
	 
	 
	 
	 public void userRegister(String username,String password)
	 {
		 Connection c=null;
		 try{
		 c=DBSet.getConnection1();
		 String sql="insert into user (uid,email,phonenumber,password,creattime) value (?,?,?,?,SYSDATE())";
		 PreparedStatement s=c.prepareStatement(sql);
		 if(username.indexOf('@')>-1)
		 {
			 s.setString(1, "System.currentTimeMillis()  + (int) (Math.random() * 10000)");
			 s.setString(2, username);
			 s.setString(3, null);
			 s.setString(4, password);
			 s.executeUpdate();			 
		 }else
		 {
			 s.setString(1, "System.currentTimeMillis() + (int) (Math.random() * 10000)");
			 s.setString(2, null);
			 s.setString(3, username);
			 s.setString(4, password);
			 s.executeUpdate();			 		 
		 }
		 }catch(SQLException e)
		 {
			 e.printStackTrace();
		 }finally
		 {
			 try {
				c.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		 }
	 }
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	
//	private String login(String key, String password, String sql)
//			throws SQLException, stateExcepction, passwordException, NotFoundExcepction
//	{
//		Connection c=DBSet.getConnection1();
//		//PreparedStatement s = c.prepareStatement(sql);
//		//s.setString(1, key);
//		Statement s=c.createStatement();
//		ResultSet rs=s.executeQuery(sql);
//		
//		if(rs.next())
//		{
//			if(rs.getInt("state")==0)
//			{
//			if(rs.getString("password").equals(password))
//			{
//				return "��֤�ɹ�";
//			}else
//			{
//				throw new passwordException();
//			}
//			}
//			else{throw new stateExcepction();}
//		}else
//		{
//			throw new NotFoundExcepction();
//			
//		}		
//	}
	
	
	
	
	
	public static void main(String[] args) 
			throws SQLException, stateExcepction, passwordException, NotFoundExcepction 
	{
		System.out.println(new ManagerDB().phonenumberFormat("15062201995", "123")) ;
	}
	
	
	

}
