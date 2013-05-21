package com.nbw.lucene.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class UpdateUtil {

	
	public static void updateNum(String pmid)
	{
		
		try {
			Connection conn=DBUtil.getConnection();
			conn.prepareStatement("update doctxt set featureWord = featureWord+1 where pmid ="+pmid).execute();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void updateRecord(String userName)
	{
		
		try {
			Connection conn=DBUtil.getConnection();
			conn.prepareStatement("update userScore set jf=jf-2" +" where userName= '"+userName+"'").execute();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
	
	public static int getScoreByUserName(String userName)
	{
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		int m=0;
		try {
			conn=DBUtil.getConnection();
			st=(Statement) conn.createStatement();
			rs=	st.executeQuery("select jf from userScore where userName= '"+userName+"'");
			m= rs.getInt(1);
			conn.close();
			st.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return m;
		
		
		
	}
	
	public static void initRecord(String userName)
	{
		
		try {
			Connection conn=DBUtil.getConnection();
			conn.prepareStatement("insert into userScore(userName,jf) values('"+userName+"',"+100+")").execute();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void initJF(String id,String userName,String trname,String userID)
	{

		try {
			Connection conn=DBUtil.getConnection();
			conn.prepareStatement("insert into organism.fmpt_jf_sum(id,username,userid,trname,jfsum) values('"+id+"',"+"'"+userName+"',"+"'"+userID+"','"+trname+"',"+100+")").execute();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void updateJF(String userID,Integer sum)
	{
		
		try {
			Connection conn=DBUtil.getConnection();
			conn.prepareStatement("update fmpt_jf_sum  set jfsum = "+sum+"  where userid= '"+userID+"'").execute();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}
	
	
	
	
}
