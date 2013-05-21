package  com.nbw.lucene.service;

import java.util.List;

import com.nbw.common.util.PageBean;
import com.nbw.lucene.dao.DoctxtDAO;
import com.nbw.lucene.domain.Doctxt;
import com.nbw.lucene.util.SearchPDF;

/**
 * 
 * DoctxtManager
 * 
 * @author 
 * 
 */
public class DoctxtManager{

	private DoctxtDAO doctxtdao;
	/**
	 * ???id??????
	 *
	 * @param  id
	 * @return pObject
	 */
	public int getTotalNumBySpecies(String speciesName)
	{
		List<String> list=null;
		try {
			list = SearchPDF.search(speciesName,true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list.size();
		
		
	}
	
	
	
	
//根据物种名获得相关文献的条数
	public int getCountBySpeciesId(Integer sid)
	{
		

		return this.doctxtdao.getCountBySpeciesId(sid);
	}
	
	//根据物种id获得相关文献列表
	public List<Doctxt> getListDoctxtByGid(Integer gid)
	{
		
		return doctxtdao.findAllDocByGid(gid);
		
	}
	
	
	
	public Doctxt findById(String id){
		if(id==null||"".equals(id)){
		   return null;
		}else{
		   Doctxt pObject = doctxtdao.findByID(id);
		   return pObject;
		}		
	}
	public List<Doctxt>  getDocTxtByList(List<String>  list)
	{
	
		return doctxtdao.getListbyPmidList(list);
	}
	
	public Doctxt getDoctxtByPmid(String pmid)
	{
		return doctxtdao.getDoctxtByPmid(pmid);
	}
	
	/**
	 * ??????????????????
	 * 
	 * @return list
	 */
	public List getAll(){
		return doctxtdao.findAll();
	}
	
//	public List<Doctxt> searchByProperty(String[] propertyName,String[] value,boolean flag)
//	{
//		
//		
//		
//		return doctxtdao.searchByPropertys(propertyName,value,false);
//		
//		
//	}
//	
	public PageBean advanceSearchList(String Title,String Author,String Abstract,String Journal,int page,int pageSize,String StartDate,String EndDate)
	{
	return 	doctxtdao.advanceSearchListDoctxt(Title,Author,Abstract,Journal,page,pageSize,StartDate,EndDate);
		
	}
    /**
	 * ??????
	 *
	 * @param  doctxt
	 */
	public void saveDoctxt(Doctxt doctxt) {
		doctxtdao.save(doctxt);
	}


    /**
	 * ??????
	 *
	 * @param  doctxt
	 */
	public void editDoctxt(Doctxt doctxt) {
		doctxtdao.update(doctxt);
	}
	
	/**
	 * ??????
	 *
	 * @param  id
	 */
	public void deleteDoctxt(String id) {
		doctxtdao.delete(id);
	}
	//*******************************************************************************set and get
	public DoctxtDAO getDoctxtdao() {
		return doctxtdao;
	}

	public void setDoctxtdao(DoctxtDAO doctxtdao) {
		this.doctxtdao = doctxtdao;
	}

}

