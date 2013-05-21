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
import com.nbw.docToDB.dao.RnaDAO;
import com.nbw.docToDB.domain.Rna;
import com.nbw.docToDB.util.DBUtil;

/**
 * 
 * RnaManager
 * 
 * @author wangshaobin
 * 
 */
public class RnaManager{

	private static final Logger logger = Logger.getLogger(RnaManager.class);
	
	private RnaDAO rnadao;
	
	
	
	
	public int findBySpeciesName(Integer sid){
		return this.rnadao.findBySpeciesName(sid);
	}
	
	/**
	 * 
	 * @param id
	 * @return 查找rna
	 */
	public Rna findByRnaId(String id){
		return this.rnadao.findByRnaId(id);
	}
	
	
	public String findByRnaIds(String id){
		return this.rnadao.findByRnaIds(id);
	}
	
	
	public PageBean findDocByRnaId(String id,int page,int pageSize){
		return this.rnadao.findDocByRnaId(id,page,pageSize);
	}
	
	
	/**
	 * 
	 * @param rnaInfo
	 * @param page
	 * @param pageSize
	 * @return 标准检索
	 */
	public PageBean searchRna(String rnaInfo,int page,int pageSize){
		
		PageBean pageBean = this.rnadao.findByInfo(rnaInfo,page,pageSize);
		return pageBean;
	}
	
	
	public PageBean searchRnaById(String rnaInfo,int page,int pageSize){
		
		PageBean pageBean = this.rnadao.findByRnaId(rnaInfo,page,pageSize);
		return pageBean;
	}
	
	
	/**
	 * 
	 * @param ncbiSpeciesId
	 * @param rnaId
	 * @param chref
	 * @param page
	 * @param pageSize
	 * @param key
	 * @return 高级检索
	 */
public PageBean adSearchRna(String ncbiSpeciesId,String rnaId,String chref,int page,int pageSize,String[] key){
		
		PageBean pageBean = this.rnadao.adFindByInfo(ncbiSpeciesId,rnaId,chref,page,pageSize,key);
		return pageBean;
	}
	
	
	/**
	 * 将frn格式文件转换成数据库数据
	 * @param filepath
	 */
	public int parseRNA(String filepath){
		
		Connection conn = null;
		PreparedStatement pre = null;
		StringBuffer sb = null;
		PreparedStatement pre1 = null;
		
		int rna = 0;
		
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
						String[] data = str.split("[>|:]+");
						
							Class.forName("com.mysql.jdbc.Driver");
							conn = DriverManager.getConnection("jdbc:mysql://192.168.100.212:3306/organism","root","root");
							
							if(str.equals(">")){
								System.out.println("end:"+aa);
								pre1 = conn.prepareStatement("update frn1 set sequence = ? where sequence = ?");
								pre1.setString(1, aa);
								pre1.setString(2, "1");
								pre1.executeUpdate();
								continue;
							}
							System.out.println(aa);
							if(!aa.isEmpty()){
								m=m+1;
								//pre1 = conn.prepareStatement("insert into qwe values(?,?)");
								pre1 = conn.prepareStatement("update frn1 set sequence = ? where sequence = ?");
								pre1.setString(1, aa);
								pre1.setString(2, "1");
								pre1.executeUpdate();
							}
							
							pre = conn.prepareStatement("insert into frn1 values(?,?,?,?,?)");
							pre.setInt(1, n);
							System.out.print(n+" ;");
							pre.setString(2, data[2].trim());
							System.out.print(data[2].trim()+" ;");
							pre.setString(3, data[3].split("-")[0].trim());
							System.out.print(data[3].split("-")[0].trim()+" ;");
							pre.setString(4, data[3].split("-")[1].trim());
							System.out.print(data[3].split("-")[1].trim()+" ;");
							pre.setString(5, "1");
							int flag = pre.executeUpdate();
							if(flag>0){
								rna = rna+1;
							}
					}else{
						st = st+str.trim();
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
						String[] data = str.split("[>|:]+");
						
						    conn = DBUtil.getConnection();
							pre = conn.prepareStatement("insert into frn1 values(?,?,?,?,?)");
							pre.setInt(1, n);
							//logger.info("向frn1表插入的条数："+n);
							pre.setString(2, data[2].trim());
							logger.info(data[2].trim());
							pre.setString(3, data[3].split("-")[0].trim());
							logger.info(data[3].split("-")[0].trim());
							pre.setString(4, data[3].split("-")[1].trim());
							//logger.info(data[3].split("-")[1].trim());
							pre.setString(5, "");
							int flag = pre.executeUpdate();
							if(flag>0){
								rna = rna+1;
							}
							
					}else{
						sb.append(str);
					}
					
						pre = conn.prepareStatement("insert into frn2 values(?,?,?)");
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
				if(pre!=null){
					pre.close();
				}
				if(pre1!=null){
					pre1.close();
				}
				DBUtil.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rna;
	}
	
	
	/**
	 * 
	 * @param str
	 * @return 以'-'分割成数组
	 */
	public static String[] parseString(String str){
		
		String[] data1 = str.split("-");
		//System.out.println(data1[0]+"  "+data1[1]);
		
		return data1;
	}

	
	/**
	 * 
	 * 执行sql语句更新rna表中的数据
	 * @param ncname
	 */
	public int doSql(String ncname){
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement pre = null;
		int sql = 0;
		try {
			
		/*	//创建临时表
			pre =  conn.prepareStatement("CREATE TABLE frnTemp AS SELECT * FROM frn2 WHERE cid IN (SELECT MAX(cid) FROM frn2 GROUP BY id)");
			int i = pre.executeUpdate();
			//logger.info("创建临时表的个数0："+i);
			
			//更新frn1中的数据
			pre = conn.prepareStatement("UPDATE frn1 , frnTemp SET frn1.sequence = frnTemp.sequence WHERE frn1.id = frnTemp.id");
			int n = pre.executeUpdate();
			//logger.info("更新frn1表的条数："+n);
		*/
			//更新frn1的nc数据项
			pre = conn.prepareStatement("UPDATE frn1 SET frn1.nc = ?");
			pre.setString(1, ncname);
			int r = pre.executeUpdate();
			//logger.info("更新frn1的nc数据项："+r);
			
			//更新rna中的数据
			
			/*pre = conn.prepareStatement("UPDATE frn1 , qwe SET frn1.sequence = qwe.sequence WHERE frn1.id = qwe.id");
			int n = pre.executeUpdate();
			System.out.println("N--RNA"+n);*/
			
			
			pre = conn.prepareStatement("UPDATE rna , frn1 SET rna.sequence = frn1.sequence WHERE rna.chromosome_ref = frn1.nc AND rna.chromosome_start = frn1.fstart AND rna.chromosome_end = frn1.fend");
			int m = pre.executeUpdate();
			sql = m;
			System.out.println("M--RNA"+m);
			//logger.info("更新rna表的条数："+m);
			
			//删除frn1表中的数据
			pre = conn.prepareStatement("delete from frn1");
			int p = pre.executeUpdate();
			//logger.info("frn1表删除行数："+p);
			
			//删除frn2表中的数据
		/*	pre = conn.prepareStatement("delete from qwe");
			int q = pre.executeUpdate();*/
			//logger.info("表frn2被删除行数："+q);
			
			//删除临时表frnTemp
		/*	pre = conn.prepareStatement("drop table frnTemp");
			int w = pre.executeUpdate();*/
			//logger.info("删除临时表frnTemp:"+w);
			
			
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
	public Rna findById(String id){
		if(id==null||"".equals(id)){
		   return null;
		}else{
		   Rna pObject = rnadao.findByID(id);
		   return pObject;
		}		
	}
	
	/**
	 * 获得该对象所有数据的列表
	 * 
	 * @return list
	 */
	public List getAll(){
		return rnadao.findAll();
	}
	
    /**
	 * 保存对象
	 *
	 * @param  rna
	 */
	public void saveRna(Rna rna) {
		rnadao.save(rna);
	}


    /**
	 * 编辑对象
	 *
	 * @param  rna
	 */
	public void editRna(Rna rna) {
		rnadao.update(rna);
	}
	
	/**
	 * 删除对象
	 *
	 * @param  id
	 */
	public void deleteRna(String id) {
		rnadao.delete(id);
	}


	
	//*******************************************************************************set and get
	public RnaDAO getRnadao() {
		return rnadao;
	}

	public void setRnadao(RnaDAO rnadao) {
		this.rnadao = rnadao;
	}

}

