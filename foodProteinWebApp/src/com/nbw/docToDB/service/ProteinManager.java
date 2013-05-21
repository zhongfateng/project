package  com.nbw.docToDB.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.nbw.common.util.PageBean;
import com.nbw.docToDB.dao.ProteinDAO;
import com.nbw.docToDB.domain.Protein;
import com.nbw.docToDB.util.DBUtil;
import com.nbw.lucene.domain.Doctxt;

/**
 * 
 * ProteinManager
 * 
 * @author wangshaobin
 * 
 */
public class ProteinManager{

	private static final Logger logger = Logger.getLogger(ProteinManager.class);
	
	
	private ProteinDAO proteindao;
	
	
	
	public int findBySpeciesName(Integer sid){
		
		return this.proteindao.findBySpeciesName(sid);
	}
	
	
	
	/**
	 * 
	 * @param id
	 * @return 查找protein
	 */
	public Protein findByProteinId(String id){
		return this.proteindao.findByProteinId(id);
	}
	
	/**
	 * 
	 * @param id
	 * @param page
	 * @param pageSize
	 * @return 查找文献
	 */
	public PageBean findDocByProteinId(String id,int page,int pageSize){
		return this.proteindao.findDocByProteinId(id,page,pageSize);
	}
	
	
	/**
	 * 
	 * @param proteinInfo 种名
	 * @param page 当前页
	 * @param pageSize 每页的大小
	 * @return 标准检索protein
	 */
	public PageBean searchProtein(String proteinInfo,int page, int pageSize){
		
		
		 PageBean pageBean  = this.proteindao.findByInfo(proteinInfo,page,pageSize);
		
		
		return pageBean;
	}
	
	
	
	public PageBean searchProteinById(String proteinInfo,int page, int pageSize){
		
		
		 PageBean pageBean  = this.proteindao.findByProteinId(proteinInfo,page,pageSize);
		
		
		return pageBean;
	}
	
	public String findByProteinIds(String id){
		
		return this.proteindao.findByProteinIds(id);
	}
	
	
	/**
	 * 
	 * @param ncbiSpeciesId
	 * @param proteinId
	 * @param ncbiProteinRef
	 * @param chref
	 * @param page
	 * @param pageSize
	 * @param key  sql语句的and  or
	 * @return 高级检索protein
	 */
	public PageBean adSearchProtein(String ncbiSpeciesId,String proteinId,String ncbiProteinRef,String chref,int page,int pageSize,String[] key){
		
		PageBean pageBean = this.proteindao.adFindByInfo(ncbiSpeciesId,proteinId,ncbiProteinRef,chref,page,pageSize,key);
	
	return pageBean;
}
	
	
	
	
	/**
	 * 将FAA格式的文件转换成蛋白质cds表的数据项；
	 * @param filepath
	 */
	public int parseFAA(String filepath){
		
		Connection conn = null;
		PreparedStatement pre = null;
		PreparedStatement pre1 = null;
		StringBuffer sb = null;
		
		int faa = 0;
		
		try {
			BufferedReader in = 
				new BufferedReader(
						new InputStreamReader(
								new BufferedInputStream(
										new FileInputStream(filepath)),"gbk"));
			
			String str;
			int n = 0;
			int m = 0;
			String st = "";
				while((str = in.readLine())!=null){
					str = str.trim();
					if(str.equals("") || str.startsWith("#")){
						continue;
					}else if(str.startsWith(">")){
						
						String aa = st;
						 st = "";
						 n = n+1;
						String[] data = str.split("[>|]+");
							Class.forName("com.mysql.jdbc.Driver");
							conn = DriverManager.getConnection("jdbc:mysql://192.168.100.212:3306/organism","root","root");
							pre = conn.prepareStatement("insert into faa1 values(?,?,?)");
							
							if(str.equals(">")){
								System.out.println("end:"+aa);
								//Class.forName("com.mysql.jdbc.Driver");
								//conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/organism","root","root");
								pre1 = conn.prepareStatement("update faa1 set sequence = ? where sequence = ?");
								pre1.setString(1, aa);
								pre1.setString(2, "1");
								pre1.executeUpdate();
								continue;
							}
							System.out.println(aa);
							if(!aa.isEmpty()){
								m = m+1;
								//Class.forName("com.mysql.jdbc.Driver");
								//conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/organism","root","root");
								//pre1 = conn.prepareStatement("insert into faa values(?,?)");
								pre1 = conn.prepareStatement("update faa1 set sequence = ? where sequence = ?");
								pre1.setString(1, aa);
								pre1.setString(2, "1");
//								pre1.setInt(1, m);
//								pre1.setString(2, aa);
								pre1.executeUpdate();
							}
							
							pre.setInt(1, n);
							System.out.print(n+":  ");
							pre.setString(2, data[4].trim());
							System.out.print(data[4].trim()+"  ;");
							pre.setString(3, "1");
							int flag = pre.executeUpdate();
							if(flag>0){
								faa = faa+1;
							}
					}else{
						st = st+str;
					}
					
				}
				
				in.close();

				/*String str;
			int n = 0;
			int m = 0;
				while((str = in.readLine())!=null){
					str = str.trim();
					if(str.equals("") || str.startsWith("#")){
						continue;
					}else if(str.startsWith(">")){
						 sb = new StringBuffer();
						 n = n+1;
						String[] data = str.split("[>|]+");
						    conn = DBUtil.getConnection();
							pre = conn.prepareStatement("insert into faa1 values(?,?,?)");
							pre.setInt(1, n);
							//logger.info("向faa1表中插入的条数："+n);
							pre.setString(2, data[4].trim());
							logger.info(data[4].trim());
							pre.setString(3, "");
							int flag = pre.executeUpdate();
							if(flag>0){
								faa = faa+1;
							}
							
					}else{
						sb.append(str);
					}
					
						pre = conn.prepareStatement("insert into faa2 values(?,?,?)");
						m = m+1;
						pre.setInt(1, n);
						pre.setInt(2, m);
						pre.setString(3, sb.toString());
						pre.executeUpdate();
					
				}
				in.close();*/
		}catch(FileNotFoundException e){
			System.out.println("文件不存在");
		}   catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(pre1!=null){
					pre1.close();
				}
				if(pre!=null){
					pre.close();
				}
				DBUtil.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return faa;
		
	}
	
	/**
	 * 
	 * @param str
	 * @return 分别以空格和‘-’组分隔符。
	 */
	public static String[] parseString(String str){
		
		String[] data = str.split(" ");
		String[] data1 = data[0].split("-");
		//System.out.println(data1[0]+"  "+data1[1]);
		
		return data1;
	}
	
	
	/**
	 * 更新cds表中的数据。
	 */
	public int doSql(){
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement pre = null;
		int sql = 0;
		try {
			
			//创建临时表
			/*pre =  conn.prepareStatement("CREATE TABLE faaTemp AS SELECT * FROM faa2 WHERE cid IN (SELECT MAX(cid) FROM faa2 GROUP BY id)");
			int i = pre.executeUpdate();*/
			//logger.info("创建临时表的个数0："+i);
			
			//更新faa1中的数据
//			pre = conn.prepareStatement("UPDATE faa1 , faa SET faa1.sequence = faa.sequence WHERE faa1.id = faa.id");
//			int n = pre.executeUpdate();
//			System.out.println("N:"+n);
			//logger.info("更新faa1表的条数："+n);
			
			//更新cds中的数据
			pre = conn.prepareStatement("UPDATE cds , faa1 SET cds.sequence = faa1.sequence WHERE cds.ncbi_protein_ref = faa1.ty");
			int m = pre.executeUpdate();
			sql = m;
			System.out.println("M:"+m);
			//logger.info("更新cds表的条数："+m);
			
			//删除faa1表中的数据
			pre = conn.prepareStatement("delete from faa1");
			int p = pre.executeUpdate();
			//logger.info("faa1表删除行数："+p);
			
			//删除faa2表中的数据
		/*	pre = conn.prepareStatement("delete from faa");
			int q = pre.executeUpdate();*/
			//logger.info("表faa2被删除行数："+q);
			
			//删除临时表faaTemp
		/*	pre = conn.prepareStatement("drop table faaTemp");
			int w = pre.executeUpdate();*/
			//logger.info("删除临时表faaTemp:"+w);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			
			try {
				if(pre!=null){
					pre.close();
				}
				
				DBUtil.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return sql;
	}


	/**
	 * 通过id得到对象
	 *
	 * @param  id
	 * @return pObject
	 */
	public Protein findById(String id){
		if(id==null||"".equals(id)){
		   return null;
		}else{
		   Protein pObject = proteindao.findByID(id);
		   return pObject;
		}		
	}
	
	/**
	 * 获得该对象所有数据的列表
	 * 
	 * @return list
	 */
	public List getAll(){
		return proteindao.findAll();
	}
	
    /**
	 * 保存对象
	 *
	 * @param  protein
	 */
	public void saveProtein(Protein protein) {
		proteindao.save(protein);
	}


    /**
	 * 编辑对象
	 *
	 * @param  protein
	 */
	public void editProtein(Protein protein) {
		proteindao.update(protein);
	}
	
	/**
	 * 删除对象
	 *
	 * @param  id
	 */
	public void deleteProtein(String id) {
		proteindao.delete(id);
	}


	
	//*******************************************************************************set and get
	public ProteinDAO getProteindao() {
		return proteindao;
	}

	public void setProteindao(ProteinDAO proteindao) {
		this.proteindao = proteindao;
	}

}

