package com.nbw.docToDB.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.nbw.common.util.PageBean;
import com.nbw.docToDB.dao.GeneDAO;
import com.nbw.docToDB.dao.ProteinDAO;
import com.nbw.docToDB.dao.RnaDAO;
import com.nbw.docToDB.domain.GPR;
import com.nbw.docToDB.domain.Gene;
import com.nbw.docToDB.domain.Protein;
import com.nbw.docToDB.domain.Rna;
import com.nbw.docToDB.util.DBUtil;

/**
 * 
 * GeneManager
 * 
 * @author wangshaobin
 * 
 */
public class GeneManager {

	private static final Logger logger = Logger.getLogger(GeneManager.class);

	/**
	 * dao注入
	 */
	private GeneDAO genedao;
	private ProteinDAO proteindao;
	private RnaDAO rnadao;
	
	
	
	
	public int findBySpeciesName(Integer sid){	
		return this.genedao.findBySpeciesName(sid);
	}
	
	
	
	
	
	public Gene findByGeneId(String id){
		
		return this.genedao.findByGeneId(id);
	}
	
	
	public String findByGeneIds(String id){
		return this.genedao.findByGeneIds(id);
	}
	
	
	public PageBean findDocByGeneId(String id,int page,int pageSize){
		return this.genedao.findDocByGeneId(id,page,pageSize);
	}
	
	
	/**
	 * 
	 * @param geneInfo
	 * @param page
	 * @param pageSize
	 * @return  标准检索gene      返回PageBean
	 */
	public PageBean searchGene(String geneInfo,int page, int pageSize){
		
		
		 PageBean pageBean  = this.genedao.findByInfo(geneInfo,page,pageSize);
		
		
		return pageBean;
	}
	
	public PageBean searchGeneById(String geneInfo,int page, int pageSize){
		
		
		 PageBean pageBean  = this.genedao.findByGeneId(geneInfo,page,pageSize);
		
		
		return pageBean;
	}
	
	
	/**
	 * 
	 * @param speName
	 * @param geneId
	 * @param geneName
	 * @param chref
	 * @param page    当前页数
	 * @param pageSize 每页的条数
	 * @param key    sql语句的and or
	 * @return 高级检索gene
	 */
	public PageBean adSearchGene(String speName,String geneId,String geneName,String chref,int page,int pageSize,String[] key){
			
			
			PageBean pageBean = this.genedao.adFindByInfo(speName,geneId,geneName,chref,page,pageSize,key);
		
		
		
		return pageBean;
	}
	
	

	/**
	 * 
	 * @param filepath
	 * @return 处理GFF格式文件，即将文件数据转化成数据库数据，分别存入到jiyin、cds、rna三个表中。
	 */
	public GPR paseGFF(String filepath,String speName) {
		GPR gpr = new GPR();
		int g = 0;
		int p = 0;
		int r = 0;
		String n = "";
		Connection conn = DBUtil.getConnection();
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			pre = conn.prepareStatement("SELECT node_id FROM fmpt_strain WHERE ename = ?");
			pre.setString(1, speName);
			rs = pre.executeQuery();
			
			while (rs.next()) {
				n = rs.getString(1);
				//logger.info("种的编号：" + n);
			};
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			try {
				if(rs!=null){
					rs.close();
				}
				if(pre!=null){
					pre.close();
				}
				if(conn!=null){
					conn.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		try {
			
			
			BufferedReader in = new BufferedReader(new InputStreamReader(
					new BufferedInputStream(new FileInputStream(filepath)),
					"gbk"));
			String str;
			String id = null;
			while ((str = in.readLine()) != null) {
				str = str.trim();
				String ss = "";
		        if(str.equals("")){
					continue;
				}else if(str.contains("##species")){
					String[] data = str.trim().split("=");
					id = data[1];
				}else if(str.contains("gbkey=Gene")){
					Gene gene = new Gene();
				   String[] data = str.split(";");//description字段
				   String chref = "";
				   String start = "";
				   String end = "";
				   String gid = "";
				   String name = "";
				   String dbx = "";
				   String gbk = "";
				   String locus = "";
				   
				   for(String st:data){
					   if(st.contains("ID=gene")){
						   String[] ary = st.trim().split("	");
						    chref = ary[0];
						    start = ary[3];
						    end = ary[4];
						    gid = ary[8];
					   }else if(st.contains("Name=")){
						   //System.out.println(parseTmGBK(st.trim()));
						   name = parseTmGBK(st.trim());
					   }else if(st.contains("Dbxref=GeneID")){
						   //System.out.println(parseTmDBX(st.trim()));
						   dbx = parseTmDBX(st.trim());
					   }else if(st.contains("gbkey=Gene")){
						   //System.out.print(parseTmGBK(st.trim()));
						   gbk = parseTmGBK(st.trim());
					   }else if(st.contains("locus_tag=")){
						   //System.out.println(parseTmRid(st.trim()));
						   locus = parseTmRid(st.trim());
					   }else{
						   String aa = ";";
						   ss = ss+st.trim()+aa;
					   }
				   }
					gene.setGid(parseTmGBK(gid));
					gene.setGtype(gbk);
					gene.setGname(name);
					gene.setChromosomeRef(chref);
					gene.setChromosomeStart(start);
					gene.setChromosomeEnd(end);
					gene.setNcbiGeneRef(dbx);
					gene.setLocusTag(locus);
					gene.setProteinId("");
					gene.setSpeciesId(n);
					gene.setNcbiSpeciesId(id);
					gene.setSequence("");
					boolean flag = genedao.save(gene);
					if(flag){
						g = g + 1;
					}
					
				} else if (str.contains("gbkey=CDS")) {
					Protein protein = new Protein();
					String chref = "";
					   String start = "";
					   String end = "";
					   String gid = "";
					   String name = "";
					   String dbx = "";
					   String gbk = "";
					   String note = "";
					String[] data = str.split(";");
					for(String st:data){
						   if(st.contains("ID=cds")){
							   String[] ary = st.trim().split("	");
							    chref = ary[0];
							    start = ary[3];
							    end = ary[4];
							    gid = ary[8];
						   }else if(st.contains("Name=")){
							   //System.out.println(parseTmGBK(st.trim()));
							   name = parseTmGBK(st.trim());
						   }else if(st.contains("Note=")){
							   //System.out.println(parseTmGBK(st.trim()));
							   note = parseTmGBK(st.trim());
						   }else if(st.contains("Dbxref=Genbank")){
							   //System.out.println(parseTmGid(st.trim()));
							   dbx = parseTmGid(st.trim());
						   }else if(st.contains("gbkey=CDS")){
							   //System.out.print(parseTmGBK(st.trim()));
							   gbk = parseTmGBK(st.trim());
						   }else {
							   String aa = ";";
							   ss = ss+st.trim()+aa;
						   }
					   }
					note = note + ss;
					protein.setPid(parseTmGBK(gid));
					protein.setPtype(gbk);
					protein.setChromosomeRef(chref);
					protein.setChromosomeStart(start);
					protein.setChromosomeEnd(end);
					protein.setNcbiProteinRef(name);
					protein.setNote(note);
					protein.setGeneId(dbx);
					protein.setSpeciesId(n);
					protein.setNcbiSpeciesId(id);
					protein.setSequence("");
					boolean flag = proteindao.save(protein);
					if(flag){
						p = p + 1;
					}
					
				} else if (str.contains("gbkey=rRNA") && !str.contains("exon")) {
					Rna rna = new Rna();
					String[] data = str.split(";");
					String chref = "";
					   String start = "";
					   String end = "";
					   String gid = "";
					   String dbx = "";
					   String gbk = "";
					   String note = "";
					for(String st:data){
						   if(st.contains("ID=rna")){
							   String[] ary = st.trim().split("	");
							    chref = ary[0];
							    start = ary[3];
							    end = ary[4];
							    gid = ary[8];
						   }else if(st.contains("Dbxref=GeneID")){
							   //System.out.println(parseTmDBX(st.trim()));
							   dbx = parseTmDBX(st.trim());
						   }else if(st.contains("gbkey=rRNA")){
							   //System.out.print(parseTmGBK(st.trim()));
							   gbk = parseTmGBK(st.trim());
						   }else{
							   String aa = ";";
							   ss = ss+st.trim()+aa;
						   }
					   }
					note = ss;
					rna.setGid(parseTmRid(gid));
					rna.setChromosomeRef(chref);
					rna.setChromosomeStart(start);
					rna.setChromosomeEnd(end);
					rna.setGbkey(gbk);
					rna.setGeneId(dbx);
					rna.setSpeciesId(n);
					rna.setNcbiSpeciesId(id);
					rna.setNote(note);
					rna.setSequence("");
					boolean flag = rnadao.save(rna);
					if(flag){
						r = r + 1;
					}
					
				} else if (str.contains("gbkey=tRNA") && !str.contains("exon")) {
					Rna rna = new Rna();
					String[] data = str.split(";");
					String chref = "";
					   String start = "";
					   String end = "";
					   String gid = "";
					   String dbx = "";
					   String gbk = "";
					   String note = "";
					for(String st:data){
						   if(st.contains("ID=rna")){
							   String[] ary = st.trim().split("	");
							   chref = ary[0];
							    start = ary[3];
							    end = ary[4];
							    gid = ary[8];
						   }else if(st.contains("Dbxref=GeneID")){
							   //System.out.println(parseTmDBX(st.trim()));
							   dbx = parseTmDBX(st.trim());
						   }else if(st.contains("gbkey=tRNA")){
							   //System.out.print(parseTmGBK(st).trim());
							   gbk = parseTmGBK(st).trim();
						   }else{
							   String aa =";";
							   ss = ss+st.trim()+aa;
						   }
					   }
					note = ss;
					rna.setGid(parseTmGBK(gid));
					rna.setChromosomeRef(chref);
					rna.setChromosomeStart(start);
					rna.setChromosomeEnd(end);
					rna.setGbkey(gbk);
					rna.setGeneId(dbx);
					rna.setSpeciesId(n);
					rna.setNcbiSpeciesId(id);
					rna.setNote(note);
					rna.setSequence("");
					boolean flag = rnadao.save(rna);
					if(flag){
						r = r + 1;
					}
				} else if (str.contains("gbkey=ncRNA") && !str.contains("exon")) {
					Rna rna = new Rna();
					String[] data = str.split(";");
					String chref = "";
					   String start = "";
					   String end = "";
					   String gid = "";
					   String dbx = "";
					   String gbk = "";
					   String note = "";
					for(String st:data){
						   if(st.contains("ID=rna")){
							   String[] ary = st.trim().split("	");
							   chref = ary[0];
							    start = ary[3];
							    end = ary[4];
							    gid = ary[8];
						   }else if(st.contains("Dbxref=GeneID")){
							   //System.out.println(parseTmDBX(st.trim()));
							   dbx = parseTmDBX(st.trim());
						   }else if(st.contains("gbkey=ncRNA")){
							   //System.out.print(parseTmGBK(st.trim()));
							   gbk = parseTmGBK(st.trim());
						   }else{
							   String aa = ";";
							   ss = ss+st.trim()+aa;
						   }
					   }
					note = ss;
					rna.setGid(parseTmGBK(gid));
					rna.setChromosomeRef(chref);
					rna.setChromosomeStart(start);
					rna.setChromosomeEnd(end);
					rna.setGbkey(gbk);
					rna.setGeneId(dbx);
					rna.setSpeciesId(n);
					rna.setNcbiSpeciesId(id);
					rna.setNote(note);
					rna.setSequence("");
					boolean flag = rnadao.save(rna);
					if(flag){
						r = r + 1;
					}
				} else if (str.contains("gbkey=tmRNA") && !str.contains("exon")) {
					Rna rna = new Rna();
					String[] data = str.split(";");
					String chref = "";
					   String start = "";
					   String end = "";
					   String gid = "";
					   String dbx = "";
					   String gbk = "";
					   String note = "";
					for(String st:data){
						   if(st.contains("ID=rna")){
							   String[] ary = st.trim().split("	");
							   chref = ary[0];
							    start = ary[3];
							    end = ary[4];
							    gid = ary[8];
						   }else if(st.contains("Dbxref=GeneID")){
							   //System.out.println(parseTmDBX(st.trim()));
							   dbx = parseTmDBX(st.trim());
						   }else if(st.contains("gbkey=tmRNA")){
							   //System.out.print(parseTmGBK(st.trim()));
							   gbk = parseTmGBK(st.trim());
						   }else{
							   String aa = ";";
							   ss = ss+st.trim()+aa;
						   }
					   }
					note = ss;
					rna.setGid(parseTmRid(gid));
					rna.setChromosomeRef(chref);
					rna.setChromosomeStart(start);
					rna.setChromosomeEnd(end);
					rna.setGbkey(gbk);
					rna.setGeneId(dbx);
					rna.setSpeciesId(n);
					rna.setNcbiSpeciesId(id);
					rna.setNote(note);
					rna.setSequence("");
					boolean flag = rnadao.save(rna);
					if(flag){
						r = r + 1;
					}
				}
			}
			gpr.setG(g + "");
			gpr.setP(p + "");
			gpr.setR(r + "");
			in.close();
		}catch(FileNotFoundException e){
			System.out.println("文件不存在");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//doSql();
		return gpr;
	}

	/**
	 * 
	 * @param str
	 * @return 以冒号分割，成为数组。
	 */
	public static String parseTmDBX(String str) {

		String[] data = str.split(":");
		return data[1];
	}

	/**
	 * 
	 * @param str
	 * @return 以等号分割，成为数组。
	 */
	public static String parseTmRid(String str) {
		String[] data = str.split("=");
		return data[1];
	}

	/**
	 * 
	 * @param str
	 * @return 以等号分割，成为数组。
	 */
	public static String parseTmGBK(String str) {
		String[] data = str.split("=");
		return data[1];
	}

	/**
	 * 
	 * @param str
	 * @return 以逗号分割，成为数组。
	 */
	public static String parseTmGid(String str) {
		String[] data = str.split(",");
		return parseTmDBX(data[1]);
	}
	
	/**
	 * 
	 * 将FFN格式文件转化成数据库数据。对应gene和ques表。表示基因的数据项。
	 * 
	 * @param filepath
	 */
	public int paseFFN(String filepath){

		Connection conn = null;
		PreparedStatement pre = null;
		StringBuffer sb = null;
		PreparedStatement pre1 = null;
		
		int ffn = 0;
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					new BufferedInputStream(new FileInputStream(filepath)),
					"gbk"));
			
			
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
						String[] data = str.split("[>|:,]+");
							Class.forName("com.mysql.jdbc.Driver");
							conn = DriverManager.getConnection("jdbc:mysql://192.168.100.212:3306/organism","root","root");
							pre = conn.prepareStatement("insert into gene values(?,?,?,?,?)");
							
							if(str.equals(">")){
								System.out.println("end:"+aa);
								
								pre1 = conn.prepareStatement("update gene set ques = ? where ques = ?");
								pre1.setString(1, aa);
								pre1.setString(2, "1");
								pre1.executeUpdate();
								continue;
							}
							System.out.println(aa);
							
							if(!aa.isEmpty()){
								m = m+1;
								//pre1 = conn.prepareStatement("insert into ffn values(?,?)");
								pre1 = conn.prepareStatement("update gene set ques = ? where ques = ?");
								pre1.setString(1, aa);
								pre1.setString(2, "1");
								pre1.executeUpdate();
							}
							
							pre.setInt(1, n);
							System.out.print(n+" ;");
							pre.setString(2, data[4].trim());
							System.out.print(data[4].trim()+"  ;");
							pre.setNString(3, parseString(data[5])[0].trim());
							System.out.print(parseString(data[5])[0].trim()+"  ;");
							pre.setString(4, parseString(data[5])[1].trim());
							System.out.print(parseString(data[5])[1].trim()+"  ;");
							pre.setString(5, "1");
							int flag = pre.executeUpdate();
							if(flag>0){
								ffn = ffn+1;
							}
					}else{
						st = st+str.trim();
					}
					
				}
				
				in.close();
			
			/*String str;
			int n = 0;
			int m = 0;
			while ((str = in.readLine()) != null) {
				str = str.trim();
				if (str.equals("") || str.startsWith("#")) {
					continue;
				} else if (str.startsWith(">")) {
					sb = new StringBuffer();
					n = n + 1;
					String[] data = str.split("[>|:,]+");

					conn = DBUtil.getConnection();
					pre = conn
							.prepareStatement("insert into gene values(?,?,?,?,?)");
					pre.setInt(1, n);
					//logger.info("插入gene表的条数：" + n);
					pre.setString(2, data[4].trim());
					logger.info(data[4].trim());
					pre.setString(3, parseString(data[5])[0].trim());
					logger.info(parseString(data[5])[0].trim());
					pre.setString(4, parseString(data[5])[1].trim());
					//logger.info(parseString(data[5])[1].trim());
					pre.setString(5, "");
					int flag = pre.executeUpdate();
					if(flag>0){
						ffn = ffn+1;
					}
					
				} else {
					sb.append(str);
				}
				pre = conn.prepareStatement("insert into ques values(?,?,?)");
				m = m + 1;
				pre.setInt(1, n);
				pre.setInt(2, m);
				pre.setString(3, sb.toString());
				pre.executeUpdate();

			}
			in.close();*/
		}catch(FileNotFoundException e){
			System.out.println("文件不存在");
		}  catch(Exception e){
			e.printStackTrace();
		} finally {
			try {
				if(pre!=null){
					pre.close();
				}
				if(pre1!=null){
					pre1.close();
				}
				if(conn!=null){
					conn.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ffn;

	}

	/**
	 * 
	 * @param str
	 * @return 先是以空格分组，然后以'-'分组。c大头的基因序列字符串起始位置和结束位置颠倒，需要重新分配。
	 */
	public String[] parseString(String str) {
		String[] c = new String[2];
		String[] data = str.split(" ");
		String[] data1 = data[0].split("-");
		if (data1[0].startsWith("c")) {
			c[1] = data1[0].split("c")[1];
			c[0] = data1[1];
		} else {
			c[0] = data1[0];
			c[1] = data1[1];
		}
		return c;
	}

	/**
	 * 处理完GFF格式文件后紧接着要做的事。即进一步完善jiyin、cds、rna三张表的数据项。
	 */
	public int[] doSql() {

		Connection conn1 = null;
		PreparedStatement pre1 = null;
		String n = "";
		int m = 0;
		int p = 0;
		int q = 0;
		int[] ary = new int[3];
		try {
			conn1 = DBUtil.getConnection();

	/*		// 查找种id
			pre = conn
					.prepareStatement("SELECT speid FROM fmpt_species WHERE ename = ?");
			pre.setString(1, speName);
			rs = pre.executeQuery();
			while (rs.next()) {
				n = rs.getString(1);
				//logger.info("种的编号：" + n);
			}*/

	/*		// 更新jiyin表中species_id数据项
			pre = conn
					.prepareStatement("UPDATE jiyin SET species_id = ? WHERE species_id  = ?");
			pre.setString(1, n);
			pre.setString(2, "");
			m = pre.executeUpdate();
			ary[0] = m;
			//logger.info("jiyin更新条数：" + m);
*/
			// 更新cds表中的species_id数据项。
	/*		pre = conn
					.prepareStatement("UPDATE cds SET species_id = ? WHERE species_id  = ?");
			pre.setString(1, n);
			pre.setString(2, "");
			p = pre.executeUpdate();
			ary[1] = p;
			//logger.info("cds更新条数：" + p);
*/
			// 更新jiyin表中的protein_id数据项。
			pre1 = conn1
					.prepareStatement("UPDATE jiyin ,cds SET jiyin.product_id = cds.ncbi_protein_ref WHERE jiyin.product_id = ? AND jiyin.ncbi_gene_ref = cds.gene_id");
			pre1.setString(1, "");
			p = pre1.executeUpdate();
			ary[0] = p;
			System.out.println("P--Gene-CDS:"+p);
			//logger.info("更新jiyin的protein_id条数：" + q);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pre1!=null){
					pre1.close();
				}
				if(conn1!=null){
					conn1.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ary;

	}

	/**
	 * 
	 * 创建辅助表，用来更新jiyin表的数据项sequence基因序列。
	 * 
	 * @throws SQLException
	 */
	public int doFFNSql() throws SQLException {

		Connection conn = DBUtil.getConnection();
		PreparedStatement pre = null;

		// 创建ffnTemp临时表。
/*		pre = conn
				.prepareStatement("CREATE TABLE ffnTemp AS SELECT * FROM ques WHERE cid IN (SELECT MAX(cid) FROM ques GROUP BY id)");
		int i = pre.executeUpdate();*/
		//logger.info("创建临时表的个数0:" + i);

		// 用ffnTemp临时表更新gene（辅助基因表）表数据。
		/*pre = conn
				.prepareStatement("UPDATE gene , ffn SET gene.ques = ffn.ques WHERE gene.id = ffn.id");
		int n = pre.executeUpdate();
		System.out.println("N--Gene:"+n);*/
		//logger.info("表gene的条数：" + n);

		// 用gene(辅助基因表)表更新jiyin表的sequence数据项。
		pre = conn
				.prepareStatement("UPDATE jiyin ,gene SET jiyin.sequence = gene.ques WHERE jiyin.chromosome_start = gene.start AND jiyin.chromosome_end = gene.end AND jiyin.chromosome_ref = gene.nc");
		int m = pre.executeUpdate();
		System.out.println("M--Gene:"+m);
		//logger.info("表jiyin更新条数：" + m);

		// 删除gene表中的数据
		pre = conn.prepareStatement("delete from gene");
		int p = pre.executeUpdate();
		//logger.info("gene表删除行数：" + p);

		// 删除ques表中的数据
		/*pre = conn.prepareStatement("delete from ffn");
		int q = pre.executeUpdate();*/
		//logger.info("表ques被删除行数：" + q);

		// 删除临时表ffnTemp
		/*pre = conn.prepareStatement("drop table ffnTemp");
		int w = pre.executeUpdate();*/
		//logger.info("删除临时表ffnTemp:" + w);

		if(pre!=null){
			pre.close();
		}
		
		DBUtil.close(conn);
		
		
		return m;
	}

	/**
	 * 通过id得到对象
	 * 
	 * @param id
	 * @return pObject
	 */
	public Gene findById(String id) {
		if (id == null || "".equals(id)) {
			return null;
		} else {
			Gene pObject = genedao.findByID(id);
			return pObject;
		}
	}

	/**
	 * 获得该对象所有数据的列表
	 * 
	 * @return list
	 */
	public List getAll() {
		return genedao.findAll();
	}

	/**
	 * 保存对象
	 * 
	 * @param gene
	 */
	public void saveGene(Gene gene) {
		genedao.save(gene);
	}

	/**
	 * 编辑对象
	 * 
	 * @param gene
	 */
	public void editGene(Gene gene) {
		genedao.update(gene);
	}

	/**
	 * 删除对象
	 * 
	 * @param id
	 */
	public void deleteGene(String id) {
		genedao.delete(id);
	}

	// *******************************************************************************set
	// and get
	public GeneDAO getGenedao() {
		return genedao;
	}

	public void setGenedao(GeneDAO genedao) {
		this.genedao = genedao;
	}

	public ProteinDAO getProteindao() {
		return proteindao;
	}

	public void setProteindao(ProteinDAO proteindao) {
		this.proteindao = proteindao;
	}

	public RnaDAO getRnadao() {
		return rnadao;
	}

	public void setRnadao(RnaDAO rnadao) {
		this.rnadao = rnadao;
	}

}
