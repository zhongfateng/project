package com.nbw.docToDB.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.nbw.docToDB.util.DBUtil;

public class Test {

	
	 
	 public static void sql(){
		 
		 Connection conn = null;
		 PreparedStatement pre = null;
		 ResultSet rs = null;
		 String n = "";
		 int m = 0;
		 int p = 0;
		 int q = 0;
		 try {
				conn = DBUtil.getConnection();
				pre = conn.prepareStatement("SELECT speid FROM species WHERE ename = ?");
				pre.setString(1, "Bacillus cereus");
				rs = pre.executeQuery();
				while(rs.next()){
					n = rs.getString(1);
					System.out.println("N的打小："+n);
				}
				pre = conn.prepareStatement("UPDATE jiyin SET species_id = ? WHERE species_id  = ?");
				pre.setString(1, n);
				pre.setString(2, "");
				m = pre.executeUpdate();
				System.out.println("M的打小："+m);
				
				pre = conn.prepareStatement("UPDATE cds SET species_id = ? WHERE species_id  = ?");
				pre.setString(1, n);
				pre.setString(2, "");
				p = pre.executeUpdate();
				System.out.println("P的打小："+p);
				
				
				pre = conn.prepareStatement("UPDATE jiyin ,cds SET jiyin.protein_id = cds.ncbi_protein_ref WHERE jiyin.protein_id = ? AND jiyin.ncbi_gene_ref = cds.gene_id");
				pre.setString(1, "");
				q = pre.executeUpdate();
				System.out.println("q的打小："+q);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
				try {
					rs.close();
					pre.close();
					conn.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	 }
	 

		public static void doFFNSql() throws SQLException{
			
			Connection conn = DBUtil.getConnection();
			PreparedStatement pre = null;
			ResultSet rs = null;
			pre = conn.prepareStatement("CREATE TABLE ffnTemp AS SELECT * FROM ques WHERE cid IN (SELECT MAX(cid) FROM ques GROUP BY id)");
			int i = pre.executeUpdate();
			System.out.println("创建临时表个数0个："+i);
			
			pre = conn.prepareStatement("UPDATE gene , ffnTemp SET gene.ques = ffnTemp.ques WHERE gene.id = ffnTemp.id");
			int n  = pre.executeUpdate();
			System.out.println("表gene的条数："+n);
			
			pre = conn.prepareStatement("UPDATE jiyin ,gene SET jiyin.sequence = gene.ques WHERE jiyin.chromosome_start = gene.start AND jiyin.chromosome_end = gene.end AND jiyin.chromosome_ref = gene.nc");
			int m = pre.executeUpdate();
			System.out.println("表jiyin更新条数："+m);
			
			pre = conn.prepareStatement("delete from gene");
			int p = pre.executeUpdate();
			System.out.println("gene表删除行数："+p);
			
			pre = conn.prepareStatement("delete from ques");
			int q = pre.executeUpdate();
			System.out.println("表ques被删除行数："+q);
			
			pre = conn.prepareStatement("drop table ffnTemp");
			int w = pre.executeUpdate();
			System.out.println("删除临时表ffnTemp:"+w);
			
			pre.close();
			DBUtil.close(conn);
		}

	 
		private static Logger logger = Logger.getLogger(Test.class);
		
	public static void main(String[] args) throws SQLException {
		
		System.out.println("logger日志开始：");
		logger.info("this is ceshi");
		System.out.println("logger日志结束.");
		//sql();
		//doFFNSql();

	}

}
