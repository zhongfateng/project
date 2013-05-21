package  com.nbw.jz.service;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.nbw.common.Constants;
import com.nbw.jz.dao.FmptStrainDAO;
import com.nbw.jz.domain.FmptStrain;
/**
 * 
 * FmptBioclassManager
 * 
 * @author 
 * 
 */
public class FmptStrainManager{

	private FmptStrainDAO fmptstraindao;


	/**
	 * 通过id得到对象
	 *
	 * @param  id
	 * @return pObject
	 */
	public FmptStrain findById(String id){
		if(id==null||"".equals(id)){
		   return null;
		}else{
			FmptStrain pObject = fmptstraindao.findByID(id);
		   return pObject;
		}		
	}
	
	/**
	 * 获得该对象所有数据的列表
	 * 
	 * @return list
	 */
	public List getAll(){
		return fmptstraindao.findAll();
	}
	
    /**
	 * 保存对象
	 *
	 * @param  fmptbioclass
	 */
	public void saveFmptStrain(FmptStrain fmptstrain) {
		fmptstraindao.save(fmptstrain);
	}


    /**
	 * 编辑对象
	 *
	 * @param  fmptbioclass
	 */
	public void editFmptStrain(FmptStrain fmptstrain) {
		fmptstraindao.update(fmptstrain);
	}
	
	/**
	 * 删除对象
	 *
	 * @param  id
	 */
	public void deleteFmptStrain(String id) {
		fmptstraindao.delete(id);
	}
	
	public FmptStrain findByNid(Integer id) {
		return fmptstraindao.findByNid(id);
	}
	public List<FmptStrain> findByTree(Integer lft,Integer rgh){
		return fmptstraindao.findByTree(lft, rgh);
	}

	/** ******************************获取模块树的xml开始******************************* */
	/**
	 * 获取模块树的xml
	 * 
	 * @param id
	 * @return 模块树的 xml
	 */
	public String getTreeXml(String id) {
		/** 建立document对象 */
		Document document = DocumentHelper.createDocument();
		/** 建立XML文档的根tree */
		Element treeElement = document.addElement("tree");

		/** 如果id是空则说明是首次加载 把ID置成ROOT */
		if (null == id || "".equals(id)) {
			id = Constants.DEFAULT_ROOTWZ;
		}
		List<FmptStrain> list = fmptstraindao.findChildByID(Integer.valueOf(id));
		if (Constants.DEFAULT_ROOTWZ.equals(id)) {
			treeElement.addAttribute("id", "0");
			/** 建立XML文档的根节点item */
			Element rootElement = treeElement.addElement("item");
			rootElement.addAttribute("id", id);
			rootElement.addAttribute("text", fmptstraindao.findNameByID(Integer.valueOf(id)));
			rootElement.addAttribute("open", "1");
			rootElement.addAttribute("im0", "folderClosed.gif");
			rootElement.addAttribute("im1", "folderOpen.gif");
			rootElement.addAttribute("im2", "folderClosed.gif");
			rootElement.addAttribute("select", "yes");

			addModuleNodes(list, rootElement);
		} else {
			treeElement.addAttribute("id", id);
			addModuleNodes(list, treeElement);
		}
		return document.asXML();
	}

	/**
	 * 动态加载时增加下级节点（只有一级节点）
	 * 
	 * @param
	 * @return Element
	 */
	private Element addModuleNodes(List<FmptStrain> list, Element rElement) {
		for (FmptStrain obj : list) {
			Element itemElement = rElement.addElement("item");
			itemElement.addAttribute("id", obj.getNodeId().toString());
			if(obj.getCname()!=null&&!"".equals(obj.getCname())){
				itemElement.addAttribute("text", obj.getEname()+"("+obj.getCname()+")");	
			}else{
				itemElement.addAttribute("text", obj.getEname());
			}
			
			if (moduleIsLeaf(obj.getNodeId())) {
				itemElement.addAttribute("im0", "iconText.gif");
				itemElement.addAttribute("im1", "iconText.gif");
				itemElement.addAttribute("im2", "iconText.gif");
			} else {
				itemElement.addAttribute("im0", "folderClosed.gif");
				itemElement.addAttribute("im1", "folderOpen.gif");
				itemElement.addAttribute("im2", "folderClosed.gif");
				itemElement.addAttribute("child", obj.getNodeId().toString());
			}
		}
		return rElement;
	}
	/**
	 * 判断模块是否是叶子节点
	 * 
	 * @param 
	 *            id
	 * @return
	 */
	private boolean moduleIsLeaf(Integer id) {
		List list = fmptstraindao.findChildByID(id);
		return list.size() <= 0;
	}
	
	public String findDateByID(Integer id) {
		return fmptstraindao.findDateByID(id);
	}
	
	public boolean  updateDataByID(int id,int jy,int dbz,int rna,int wx,int tp,String updatedate){
		return fmptstraindao.updateDataByID(id, jy, dbz, rna, wx, tp,updatedate);
	}
	public boolean  updateDateByID(int id,String date){
		return fmptstraindao.updateDateByID(id, date);
	}
	/**
	 * 显示饼状图
	 * @param id
	 * @return
	 */
	public String loadChartsXml(Integer id) {
		FmptStrain fmptStrain = fmptstraindao.findByNid(id);
		StringBuffer xml = new StringBuffer();
		xml.append("<chart caption='"+fmptStrain.getEname()+"相关数据' xAxisName='' yAxisName='个' outCnvBaseFontSize ='24' showValues='1' decimals='0' formatNumberScale='0'  bgColor='#F5F8FF'>");
		String s = "<set label='蛋白质' value='"+fmptStrain.getDbz()+"' />";
		s+= "<set label='RNA' value='"+fmptStrain.getRna()+"' />";
		s+= "<set label='基因' value='"+fmptStrain.getJy()+"' />";
		s+= "<set label='文献' value='"+fmptStrain.getWx()+"' />";
		s+= "<set label='指纹图谱' value='"+fmptStrain.getTp()+"' />";
		xml.append(s);
		xml.append("</chart>");
		System.out.println(xml.toString());
		return xml.toString();
	}
	
	//*******************************************************************************set and get
	
	public FmptStrainDAO getFmptstraindao() {
		return fmptstraindao;
	}

	public void setFmptstraindao(FmptStrainDAO fmptstraindao) {
		this.fmptstraindao = fmptstraindao;
	}
}

