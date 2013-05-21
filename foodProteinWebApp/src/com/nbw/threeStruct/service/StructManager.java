package com.nbw.threeStruct.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.nbw.common.util.PageBean;
import com.nbw.docToDB.domain.Rna;
import com.nbw.docToDB.util.DBUtil;
import com.nbw.threeStruct.dao.StructDAO;
import com.nbw.threeStruct.domain.ThreeStructure;

public class StructManager {
	
	private StructDAO structdao;
	
	
	/**
	 * 
	 * @param speciesName
	 * @param page
	 * @param pageSize
	 * @return 列出菌株详细信息
	 */
	public PageBean findByName(String speciesName,int page , int pageSize){
		
		PageBean pageBean = this.structdao.findByName(speciesName,page,pageSize);
		
		return pageBean;
	}
	
	
	/**
	 * 
	 * @param speciesName
	 * @param page
	 * @param pageSize
	 * @return 列出菌株和菌种
	 */
public PageBean findByNameSum(String speciesName,int page , int pageSize){
		
		PageBean pageBean = this.structdao.findByNameSum(speciesName,page,pageSize);
		
		return pageBean;
	}
	
	
	/**
	 * 
	 * @param speciesName
	 * @return 通过种名like模糊查找菌株的相关信息列表不含种名的。
	 */
	public List<String[]> findBySpeciesName(String speciesName){
		return this.structdao.findBySpeciesName(speciesName);
	}
	
	
	/**
	 * 
	 * @param speciesName
	 * @return 通过种名查找所有的（菌种和菌株）个数。
	 */
	public int findBySpeciesNameSum(String speciesName){
		return this.structdao.findBySpeciesNameSum(speciesName);
	}
	
	
	/**
	 * 
	 * @param filePath
	 * @param speciesName
	 * @return 处理三维数据文件
	 */
	public int paseThreeStruct(String filePath,String speciesName){
		
		Connection conn  = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		int pid = 0;
		int n = 0;
		try {
			BufferedReader in = 
				new BufferedReader(
						new InputStreamReader(
								new BufferedInputStream(
										new FileInputStream(filePath)),"gbk"));
			String str;
			String name = "";
			String tax = "";
				String protein = "";
				String chemical = "";
				String mmdb = "";
				String pdb = "";
				String selfid = "";
				 conn = DBUtil.getConnection();
				 pre = conn.prepareStatement("select count(*) from threestructure"); 
				 rs = pre.executeQuery();
				while(rs.next()){
					pid = rs.getInt(1);
					
				}
				while((str = in.readLine())!=null){
					        str = str.trim();
					        if(str.equals("") || str.startsWith("#")){
								continue;
							}
					        
					        if(str.startsWith("1")||str.startsWith("2")||str.startsWith("3")||str.startsWith("4")||str.startsWith("5")||str.startsWith("6")||str.startsWith("7")||str.startsWith("8")||str.startsWith("9")){
							   String[] data = str.split(" ");//description字段
							   String index = data[0];
							   String [] data1 = str.split(index+" ");
							    name = data1[1];
							    pid = pid +1;
								   }
					        tax = in.readLine();
					        tax = tax.split("Taxonomy: ")[1];
					        if(tax.equals(speciesName)){
					        	selfid = "1";
					        	//System.out.print("Sid :"+selfid);
					        }else{
					        	selfid = "2";
					        	//System.out.print("Sid :"+selfid);
					        }
					        
					        String proteins = in.readLine();
					        String pc = proteins.split("modified: ")[0];
					        if(pc.contains("Chemicals:")){
					        	String[] ary = pc.split(" ");
					        	 protein = ary[1];
					        	 chemical = ary[3];
					        }else{
					        	String[] ary = pc.split(" ");
					        	 protein = ary[1];
					        	 chemical = "";
					        }
					        
					        String id = in.readLine();
					        String[] array = id.split(" ");
					         mmdb = array[2];
					         pdb = array[5];
								conn = DBUtil.getConnection();
								pre = conn.prepareStatement("insert into threestructure values(?,?,?,?,?,?,?,?)");
								pre.setInt(1, pid);
								pre.setString(2, name);
								pre.setString(3, tax);
								pre.setString(4, protein);
								pre.setString(5, chemical);
								pre.setString(6, mmdb);
								pre.setString(7, pdb);
								pre.setString(8, selfid);
								int nn =pre.executeUpdate();
								if(nn>0){
									n  = n+1;
								}
				}
							
				in.close();
		}catch (FileNotFoundException e){
			System.out.println("文件不存在");
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				pre.close();
				DBUtil.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return n;
	}

	
	
	/**
	 * 
	 * @param filePath
	 * @param speciesName
	 * @return 处理三维数据文件
	 */
public int paseThree(String filePath,String speciesName){
		
		int pid = 0;
		int n = 0;
		try {
			BufferedReader in = 
				new BufferedReader(
						new InputStreamReader(
								new BufferedInputStream(
										new FileInputStream(filePath)),"gbk"));
			String str;
			String name = "";
			String tax = "";
				String protein = "";
				String chemical = "";
				String mmdb = "";
				String pdb = "";
				String selfid = "";

				while((str = in.readLine())!=null){
					        str = str.trim();
					        if(str.equals("") || str.startsWith("#")){
								continue;
							}
					        
					        if(str.startsWith("1")||str.startsWith("2")||str.startsWith("3")||str.startsWith("4")||str.startsWith("5")||str.startsWith("6")||str.startsWith("7")||str.startsWith("8")||str.startsWith("9")){
							   String[] data = str.split(" ");//description字段
							   String index = data[0];
							   String [] data1 = str.split(index+" ");
							    name = data1[1];
							    pid = pid +1;
								   }
					        tax = in.readLine();
					        tax = tax.split("Taxonomy: ")[1];
					        if(tax.equals(speciesName)){
					        	selfid = "1";
					        	//System.out.print("Sid :"+selfid);
					        }else{
					        	selfid = "2";
					        	//System.out.print("Sid :"+selfid);
					        }
					        
					        String proteins = in.readLine();
					        String pc = proteins.split("modified: ")[0];
					        if(pc.contains("Chemicals:")){
					        	String[] ary = pc.split(" ");
					        	 protein = ary[1];
					        	 chemical = ary[3];
					        }else{
					        	String[] ary = pc.split(" ");
					        	 protein = ary[1];
					        	 chemical = "";
					        }
					        
					        String id = in.readLine();
					        String[] array = id.split(" ");
					         mmdb = array[2];
					         pdb = array[5];
					         ThreeStructure t = new ThreeStructure();
					         t.setSname(name);
					         t.setChemicals(chemical);
					         t.setTaxonomy(tax);
					         t.setProteins(protein);
					         t.setMmdbId(mmdb);
					         t.setPdbId(pdb);
					         t.setSelfId(selfid);
							boolean flag = this.structdao.save(t);	
							if(flag){
								n= n+1;
							}
				}
							
				in.close();
		}catch (FileNotFoundException e){
			System.out.println("文件不存在");
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return n;
	}

/**
 * 通过id得到对象
 *
 * @param  id
 * @return pObject
 */
public ThreeStructure findById(String id){
	if(id==null||"".equals(id)){
	   return null;
	}else{
		ThreeStructure pObject = structdao.findByID(id);
	   return pObject;
	}		
}

/**
 * 获得该对象所有数据的列表
 * 
 * @return list
 */
public List getAll(){
	return structdao.findAll();
}

/**
 * 保存对象
 *
 * @param  rna
 */
public void saveThreeStructure(ThreeStructure rna) {
	structdao.save(rna);
}


/**
 * 编辑对象
 *
 * @param  rna
 */
public void editThreeStructure(ThreeStructure rna) {
	structdao.update(rna);
}

/**
 * 删除对象
 *
 * @param  id
 */
public void deleteThreeStructure(String id) {
	structdao.delete(id);
}

public StructDAO getStructdao() {
	return structdao;
}

public void setStructdao(StructDAO structdao) {
	this.structdao = structdao;
}
	
}
